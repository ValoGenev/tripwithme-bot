package bot.processor;


import bot.model.ScrappedPostInfo;
import bot.model.ScrappedTestInfo;
import bot.model.WillRideOrDrivePair;
import bot.utils.City;
import bot.utils.InfoExtractor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bot.config.ExecutorConfiguration;
import bot.model.Group;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static bot.utils.InfoExtractor.*;
import static java.lang.String.*;
import static bot.utils.Constants.*;

public class BotExecutorService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final ChromeDriver chromeDriver;

    private final ExecutorConfiguration config;
    private static final Pattern idExtractorPattern = Pattern.compile("user/[0-9]*");
    private static final Pattern dateExtractionPattern = Pattern.compile("\\d+");
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d.M.yyyy");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private static final Pattern userNameExtractorPattern = Pattern.compile("facebook.com/.*");
    private static final Pattern phoneExtractorPattern = Pattern.compile("\\d{10}");
    private static final DateTimeFormatter hoursAndMinutesFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateTimeFormatter dayAndMonthFormatter = DateTimeFormatter.ofPattern("dd.mm");
    LocalTime START_OF_DAY = LocalTime.MIDNIGHT;
    LocalTime END_OF_DAY = LocalTime.MIDNIGHT.plusHours(24);

    List<ScrappedTestInfo> allPosts = new ArrayList<>();


    public BotExecutorService(ChromeDriver chromeDriver, ExecutorConfiguration config) {
        this.chromeDriver = chromeDriver;
        this.config = config;
    }


    public void navigateToGroup(String groupName, String groupUrl) throws InterruptedException {

        LOGGER.info(format(OPEN_FACEBOOK_GROUP_MESSAGE, groupName));

        if (!groupUrl.endsWith(URL_EXTENSION_FOR_CHRONOLOGICAL)) {
            groupUrl += URL_EXTENSION_FOR_CHRONOLOGICAL;
        }

        chromeDriver.navigate().to(groupUrl);

        // This  will scroll down the page by  1000 pixel vertical
        ((JavascriptExecutor) chromeDriver).executeScript("window.scrollBy(0,document.body.scrollHeight)");

        Thread.sleep(5000);

        ((JavascriptExecutor) chromeDriver).executeScript("window.scrollBy(0,document.body.scrollHeight)");

        Thread.sleep(5000);
    }


    public void startScanning() throws InterruptedException {

        List<Group> fbGroups = config.getFbGroups();


        for (Group group : fbGroups) {

            List<ScrappedPostInfo> postFromFBS;

            navigateToGroup(group.getName(), group.getUrl());

            //взима статуси от група (да се добави: да скролва докато не има всички нови статуси)
            postFromFBS = getStatuses();

            //минава по статусите и отваря профила на усер-а(ако усер-а съществува (по снимката) да не го взима)
            //важно: да взима сторита снимки
            //getProfileInfo(postFromFBS);

            extractInformation2(postFromFBS);
            //екстрактва информация от постове и формира нов обект

        }

        ObjectMapper mapper = new ObjectMapper();

        try {

            // Writing to a file
            mapper.writeValue(new File("C:\\Users\\seasi\\Desktop\\coop backup\\bez address i payment\\2\\coop-travel\\bot-api\\src\\main\\java\\bot\\test.json"), allPosts );

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void extractInformation2(List<ScrappedPostInfo> postFromFBS) {
        System.out.println("------------------------------------------------");


        for (ScrappedPostInfo post : postFromFBS) {

            String text = post.getContent().toLowerCase();
            int willDriveCount=0;
            int willRideCount=0;
            boolean willDrive=false;
            boolean willRide=false;
            LocalDate travelDate;
            LocalDate dayOfTheWeek;
            LocalDate date = LocalDate.now();
            LocalTime[] periodOfTime;
            LocalTime[] specificPeriodOfTime;
            LocalTime startPeriodOfTime = LocalTime.now().withSecond(0).withNano(0);
            LocalTime endPeriodOfTime= LocalTime.of(23,59);

            int seats =0;

            for (WillRideOrDrivePair willRideString : WILL_RIDE_PAIRS) {
                if (text.contains(willRideString.getVerb())) {
                    willRideCount += willRideString.getPoints();
                }
            }

            for (WillRideOrDrivePair willDriveString : WILL_DRIVE_PAIRS) {
                if (text.contains(willDriveString.getVerb())) {
                    willDriveCount += willDriveString.getPoints();
                }
            }

            ScrappedTestInfo scrappedTestInfo = new ScrappedTestInfo();
            scrappedTestInfo.setDescription(text);


            if (willDriveCount > willRideCount) {
                System.out.println("ПРЕДЛАГА МЯСТО");
                scrappedTestInfo.setConditional("ПРЕДЛАГА МЯСТО");
                willDrive=true;
                // създаване на пътуване
            } else if (willDriveCount < willRideCount) {
                System.out.println("ТЪРСИ МЯСТО");
                scrappedTestInfo.setConditional("ТЪРСИ МЯСТО");
                willRide=true;
                // създаване на търсене
            } else {
                scrappedTestInfo.setConditional("НЕВАЛИДНО");
                System.out.println("НЕВАЛИДНО");
                System.out.println(text);

                continue;
            }


            seats = extractSeatsFromStatus(text);

            travelDate = extractDateFromStatus(text);
            dayOfTheWeek = extractDayOfTheWeekFromStatus(text);

            if(dayOfTheWeek!=null){
                date=dayOfTheWeek;
            }
            if(travelDate!=null){
                date=travelDate;
            }

            periodOfTime = extractTimeOfTheDay(text);
            specificPeriodOfTime = extractSpecificTimeOfTheDay(text);

            if(periodOfTime!=null){
                startPeriodOfTime = periodOfTime[0];
                endPeriodOfTime = periodOfTime[1];
            }
            if(specificPeriodOfTime!=null) {
                startPeriodOfTime = specificPeriodOfTime[0];
                endPeriodOfTime = specificPeriodOfTime[1];
            }

            LocalDateTime startTime = LocalDateTime.of(date,startPeriodOfTime);
            LocalDateTime endTime = LocalDateTime.of(date,endPeriodOfTime);

            List<City> cities = new ArrayList<>();
            List<City> testCities = new ArrayList<>();


            for (City c : City.values()) {

                for (String cityAlternativeName : c.getNames()) {
                    if (text.contains(cityAlternativeName.toLowerCase())) {
                        cities.add(c);
                        testCities.add(c);
                        text = text.replace(cityAlternativeName.toLowerCase(), c.name());
                        break;
                    }
                }
            }

            for(City c: cities){
                String textWithoutSpaces = text.replaceAll("\\s+","");
                for(String s : DIRECTIONS){
                    if(textWithoutSpaces.contains(DIRECTIONS+c.name())){
                        if(s.equals("от")){
                            testCities.add(0,c);
                        }

                    }
                }


            }


            scrappedTestInfo.setCities(cities);
            allPosts.add(scrappedTestInfo);


            String finalText = text;
            cities.sort(Comparator.comparingInt(city -> finalText.indexOf(city.name())));




            System.out.println("-------------------------------------");
            System.out.println(text);
            System.out.println(startTime);
            System.out.println(endTime);
            System.out.println("-------------------------------------");

        }

    }

    private LocalTime[] extractSpecificTimeOfTheDay(String text){
        for (Map.Entry<String,String[]> entry : InfoExtractor.hoursAndMinutez.entrySet()) {
            if (text.contains(entry.getKey())) {
                return Arrays.stream(entry.getValue()).map(this::formatTime).toArray(LocalTime[]::new);
            }
        }

        return null;
    }

    private LocalTime[] extractTimeOfTheDay(String text){

        for (Map.Entry<String,String[]> entry : timeOfTheDayzz.entrySet()) {
            if (text.contains(entry.getKey())) {
                return Arrays.stream(entry.getValue()).map(this::formatTime).toArray(LocalTime[]::new);
            }
        }
        return null;
    }

    private int extractSeatsFromStatus(String text){
        for (Map.Entry<String, Integer> entry: seatz.entrySet()) {
            if(text.contains(entry.getKey())){
                return entry.getValue();
            }
        }
        return 0;
    }

    private LocalDate extractDayOfTheWeekFromStatus(String text) {
        for (Map.Entry<String, Supplier<LocalDate>> entry: dayzz.entrySet()) {
            if(text.contains(entry.getKey())){
                return entry.getValue().get();
            }
        }
        return null;
    }

    private LocalDate extractDateFromStatus(String text){

        for (String d : InfoExtractor.dates) {
            if (text.contains(d)) {
                return formatDate(d);

            }
        }

        return null;
    }


    private void extractInformation(List<ScrappedPostInfo> postFromFBS) {
        System.out.println("------------------------------------------------");


        for (ScrappedPostInfo post : postFromFBS) {
            String text = post.getContent().toLowerCase();
            String date = "";
            String seat = "";
            String timeOfTheDay = "";
            String dayOfTheWeek = "";
            String phoneNumber = "";
            String departureTime = "";


            int willDriveCount = 0;
            int willRideCount = 0;


            for (WillRideOrDrivePair willRideString : WILL_RIDE_PAIRS) {
                if (text.contains(willRideString.getVerb())) {
                    willRideCount += willRideString.getPoints();
                }
            }

            for (WillRideOrDrivePair willDriveString : WILL_DRIVE_PAIRS) {
                if (text.contains(willDriveString.getVerb())) {
                    willDriveCount += willDriveString.getPoints();
                }
            }



            for (String s : InfoExtractor.seats) {
                if (text.contains(s)) {
                    seat = s;
                    break;
                }
            }

            for (String s : InfoExtractor.days) {
                if (text.contains(s)) {
                    dayOfTheWeek = s;
                    break;
                }
            }

            for (String s : InfoExtractor.timeOfTheDay) {
                if (text.contains(s)) {
                    timeOfTheDay = s;
                    break;
                }
            }

            for (String s : InfoExtractor.hourAndMinutes) {
                if (text.contains(s)) {
                    departureTime = s;
                    break;
                }
            }


            System.out.println(text);

            if (willDriveCount > willRideCount) {
                System.out.println("ПРЕДЛАГА МЯСТО");
            } else if (willDriveCount < willRideCount) {
                System.out.println("ТЪРСИ МЯСТО");
            } else {
                System.out.println("НЕВАЛИДНО");
                continue;
            }

            List<City> cities = new ArrayList<>();


            for (City c : City.values()) {

                for (String cityAlternativeName : c.getNames()) {
                    if (text.contains(cityAlternativeName.toLowerCase())) {
                        cities.add(c);
                        text = text.replace(cityAlternativeName.toLowerCase(), c.name());
                        break;
                    }
                }
            }


            String finalText = text;
            cities.sort(Comparator.comparingInt(city -> finalText.indexOf(city.name())));


            System.out.println("Брой свободни места : " + seat);
            System.out.println("Ден на тръгване : " + date);
            System.out.println("Ден от седмицата : " + dayOfTheWeek);
            System.out.println("Време в деня : " + timeOfTheDay);
            System.out.println("Час на тръване : " + departureTime);
            System.out.println("Телефон: " + extractNumber(text));
            System.out.println("Градове: " + cities.stream().map(Enum::name).collect(Collectors.joining(",")));

            System.out.println("------------------------------------");

        }

    }


    private void getProfileInfo(List<ScrappedPostInfo> postFromFBS) {

        for (ScrappedPostInfo post : postFromFBS) {

            try {


                chromeDriver.navigate().to(post.getProfileUrl());

                WebElement picElem = chromeDriver.findElementByXPath("//*[local-name()='svg' and @role=\"img\"]");

                String profilePic = picElem.findElements(By.tagName("image")).get(0).getAttribute("xlink:href");

                post.setPicUrl(profilePic);
                post.setProfileUrl(chromeDriver.getCurrentUrl());

                if (post.getFacebookId().isBlank()) {
                    post.setFacebookId(extractor(post.getProfileUrl(), userNameExtractorPattern));
                }
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }

    }

    private List<ScrappedPostInfo> getStatuses() {
        String fullName = "";
        String facebookId = "";
        String profileUrl = "";
        String content = "";
        String timePosted = "";

        List<ScrappedPostInfo> postFromFBS = new ArrayList<>();

        try {
            List<WebElement> elements = chromeDriver.findElementsByXPath("//div[@class=\"du4w35lb k4urcfbm l9j0dhe7 sjgh65i0\"]");

            for (WebElement e : elements) {


                try {
                    WebElement userNameElement = e.findElement(By.xpath(".//a[@class=\"oajrlxb2 g5ia77u1 qu0x051f esr5mh6w e9989ue4 r7d6kgcz rq0escxv nhd2j8a9 nc684nl6 p7hjln8o kvgmc6g5 cxmmr5t8 oygrvhab hcukyx3x jb3vyjys rz4wbd8a qt6c0cv9 a8nywdso i1ao9s8h esuyzwwr f1sip0of lzcic4wl oo9gr5id gpro0wi8 lrazzd5p\"\n]"));
                    WebElement contentElement;
                    WebElement userProfileRef = e.findElement(By.xpath(".//a[@class=\"oajrlxb2 g5ia77u1 qu0x051f esr5mh6w e9989ue4 r7d6kgcz rq0escxv nhd2j8a9 nc684nl6 p7hjln8o kvgmc6g5 cxmmr5t8 oygrvhab hcukyx3x jb3vyjys rz4wbd8a qt6c0cv9 a8nywdso i1ao9s8h esuyzwwr f1sip0of lzcic4wl oo9gr5id gpro0wi8 lrazzd5p\"]"));


                    if (e.findElements(By.xpath(".//div[@class=\" linoseic datstx6m\"]")).size() > 0) {
                        contentElement = e.findElement(By.xpath(".//div[@class=\" linoseic datstx6m\"]"));
                    } else if (e.findElements(By.xpath(".//div[@class=\"kvgmc6g5 cxmmr5t8 oygrvhab hcukyx3x c1et5uql ii04i59q\"]")).size() > 0) {
                        contentElement = e.findElement(By.xpath(".//div[@class=\"kvgmc6g5 cxmmr5t8 oygrvhab hcukyx3x c1et5uql ii04i59q\"]"));
                    } else {
                        continue;
                    }

                    fullName = userNameElement.getText();
                    content = contentElement.getText();
                    facebookId = extractor(userProfileRef.getAttribute(HREF_ATTRIBUTE), idExtractorPattern);

                    if (facebookId.isEmpty()) {
                        profileUrl = userProfileRef.getAttribute(HREF_ATTRIBUTE);
                    } else {
                        profileUrl = "https://www.facebook.com/" + facebookId;
                    }

                    timePosted = getCurrentTime();

                    ScrappedPostInfo scrappedPostInfo = new ScrappedPostInfo();

                    scrappedPostInfo.setFullName(fullName);
                    scrappedPostInfo.setProfileUrl(profileUrl);
                    scrappedPostInfo.setTimePosted(timePosted);
                    scrappedPostInfo.setContent(content);
                    scrappedPostInfo.setFacebookId(facebookId);

                    postFromFBS.add(scrappedPostInfo);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return postFromFBS;
    }

    private LocalTime formatTime(String text){
        return LocalTime.parse(text,timeFormatter);
    }

    private LocalDate formatDate(String text){

        return LocalDate.parse(text.replaceFirst("[/.\\-]",".")+"."+LocalDate.now().getYear(), dateFormatter);
    }


    private String extractor(String profileUrl, Pattern pattern) {
        Matcher m = pattern.matcher(profileUrl);

        if (m.find()) {

            return m.group().split("/")[1];
        }

        return "";
    }

    private String extractNumber(String text) {
        Matcher m = phoneExtractorPattern.matcher(text);

        if (m.find()) {
            return m.group();
        }

        return "";
    }


}
