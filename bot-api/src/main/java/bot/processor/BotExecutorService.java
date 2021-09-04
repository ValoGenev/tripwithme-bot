package bot.processor;


import bot.model.ScrappedPostInfo;
import bot.model.WillRideOrDrivePair;
import bot.utils.City;
import bot.utils.Group;
import bot.utils.InfoExtractor;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import bot.config.ExecutorConfiguration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static bot.utils.InfoExtractor.*;
import static java.lang.String.*;
import static bot.utils.Constants.*;

public class BotExecutorService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final ChromeDriver chromeDriver;

    private final ExecutorConfiguration config;
    private static final Pattern idUserExtractionPattern = Pattern.compile("user/[0-9]*");
    private static final Pattern phpUserExtractionPattern = Pattern.compile("profile.php\\?id=[0-9]*");
    private static final Pattern userNameIdUserExtractionPattern = Pattern.compile("com/.*\\?");
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("d.M.yyyy");
    private static final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    private static final Pattern userNameExtractorPattern = Pattern.compile("facebook.com/.*");
    private static final Pattern phoneExtractorPattern = Pattern.compile("(359|0)\\d{9}");



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

            //getProfileInfo(postFromFBS);

            extractInformation(group.getCities(), postFromFBS);
            //екстрактва информация от постове и формира нов обект

        }


    }

    private void extractInformation(List<City> defaultCities, List<ScrappedPostInfo> postFromFBS) {

        for (ScrappedPostInfo post : postFromFBS) {

            try {

                String text = post.getContent().toLowerCase();
                int seats;
                String number="";
                int willDriveCount;
                int willRideCount;
                LocalDate date;

                willRideCount = calculateWillRidePoints(text);
                willDriveCount = calculateWillDrivePoints(text);

                seats = extractSeatsFromStatus(text);

                number = extractNumber(text);

                date = extractValidOrDefaultDate(text);

                LocalTime[] period = extractValidOrDefaultPeriodOfTime(text);
                LocalDateTime startTime = LocalDateTime.of(date, period[0]);
                LocalDateTime endTime = LocalDateTime.of(date, period[1]);

                Map<Integer, City> extractedCities = extractCities(text);
                List<City> citiesInOrder = orderCities(text,extractedCities,defaultCities);

                if(citiesInOrder.size()<2){
                    System.out.println("INVALID");

                    continue;
                }

                City startPoint = citiesInOrder.get(0);
                City endPoints = citiesInOrder.get(citiesInOrder.size()-1);
                List<City> interMediatePoints = extractInterMediateCities(citiesInOrder);

                if(seats != 0){
                    willDriveCount++;
                }

                System.out.println("USER: " + post.getFullName());
                System.out.println("USER MINI PICTURE: " + post.getSmallPicUrl());
                System.out.println("USER PICTURE : "+ post.getPicUrl());
                System.out.println("PROFILE : " + post.getProfileUrl());
                System.out.println("ID : "+ post.getFacebookId());
                System.out.println("POSTED ON: "+ post.getTimePosted());
                System.out.println("DESCRIPTION: "+ post.getContent());
                System.out.println("PHONE NUMBER: " + number);
                System.out.println("START TIME :" + startTime);
                System.out.println("END TIME : "+ endTime);
                System.out.println("STARTING POINT : "+ startPoint);
                System.out.println("END POINT : "+ endPoints);
                System.out.println("INTERMEDIATE POINTS : " + interMediatePoints);
                System.out.println("FULL ROUTE : " + citiesInOrder);
                System.out.println("SEATS : " + seats);

                if (willDriveCount > willRideCount) {
                    System.out.println("ПРЕДЛАГА МЯСТО");

                    // създаване на пътуване
                } else if (willDriveCount < willRideCount) {
                    System.out.println("ТЪРСИ МЯСТО");

                    // създаване на търсене
                } else {
                    System.out.println("НЕВАЛИДНО");

                    System.out.println("-----------------------------");

                    continue;

                }


            }catch (Exception e){
                e.printStackTrace();
            }

            System.out.println("-------------------------------------");

        }

    }

    private List<City> extractInterMediateCities(List<City> citiesInOrder){

        List<City> interMediatePoints = new ArrayList<>();

        for(int i=0;i<citiesInOrder.size();i++){

            if(i==0 || i==citiesInOrder.size()-1) continue;
            interMediatePoints.add(citiesInOrder.get(i));
        }
        return interMediatePoints;
    }

    private void assertValidRoute(List<City> cities){
        System.out.println();
        if(cities.size() < 2){
            throw new RuntimeException("INVALID ROUTE");
        }
    }

    private Map<Integer,City> extractCities(String text){
        Map<Integer, City> maps = new TreeMap<>();

        for (City c : City.values()) {

            for (String cityAlternativeName : c.getNames()) {
                if (text.contains(cityAlternativeName.toLowerCase())) {
                    text = text.replace(cityAlternativeName.toLowerCase(), c.name());

                    maps.put(text.indexOf(c.name()), c);

                    break;
                }
            }
        }
        return maps;
    }


    private LocalTime[] extractValidOrDefaultPeriodOfTime(String text){
        LocalTime startPeriodOfTime = LocalTime.now().withSecond(0).withNano(0);
        LocalTime endPeriodOfTime = LocalTime.of(23, 59);

        LocalTime[] periodOfTime = extractTimeOfTheDay(text);
        LocalTime[] specificPeriodOfTime = extractSpecificTimeOfTheDay(text);

        if (periodOfTime != null) {
            startPeriodOfTime = periodOfTime[0];
            endPeriodOfTime = periodOfTime[1];
        }
        if (specificPeriodOfTime != null) {
            startPeriodOfTime = specificPeriodOfTime[0];
            endPeriodOfTime = specificPeriodOfTime[1];
        }

        return new LocalTime[]{startPeriodOfTime,endPeriodOfTime};
    }

    private LocalDate extractValidOrDefaultDate(String text){

        LocalDate d1  = extractDateFromStatus(text);
        LocalDate d2 = extractDayOfTheWeekFromStatus(text);

        if (d1 != null) {
            return d1;
        }
        if (d2 != null) {
            return d2;
        }

        return LocalDate.now();


    }

    private int calculateWillDrivePoints(String text){
        int willDriveCount = 0;
        for (WillRideOrDrivePair willDriveString : WILL_DRIVE_PAIRS) {
            if (text.contains(willDriveString.getVerb())) {
                willDriveCount += willDriveString.getPoints();
            }
        }
        return willDriveCount;
    }


    private List<City> orderCities(String text,Map<Integer,City> extractedCities,List<City> defaultCities){

        List<City> citiesInOrder = new ArrayList<>();

        String textWithoutSpaces = text.replaceAll("\\s+", "");

        for (City city : extractedCities.values()) {

            citiesInOrder.add(city);
            if (extractedCities.values().size() == 1) {

                defaultCities
                        .stream()
                        .filter(d -> !d.equals(city))
                        .findFirst()
                        .ifPresent(citiesInOrder::add);
            }


            for (String s : DIRECTIONS) {
                if (textWithoutSpaces.contains(s + city.name())) {

                    if (s.equals("от")) {
                        citiesInOrder.remove(city);
                        citiesInOrder.add(0, city);
                    }

                    if (s.equals("за") || s.equals("към") || s.equals("до") || s.equals("на")) {
                        citiesInOrder.remove(city);
                        citiesInOrder.add(citiesInOrder.size(), city);
                    }

                }
            }
        }

        return citiesInOrder;
    }

    private int calculateWillRidePoints(String text){
        int willRideCount=0;

        for (WillRideOrDrivePair willRideString : WILL_RIDE_PAIRS) {
            if (text.contains(willRideString.getVerb())) {
                willRideCount += willRideString.getPoints();
            }
        }

        return willRideCount;
    }

    private void getProfileInfo(List<ScrappedPostInfo> postFromFBS) {

        for (ScrappedPostInfo post : postFromFBS) {

            //TODO if USER exists using user url or id
            //TODO if yes(get them from database and skip selenium)


            try {

                chromeDriver.navigate().to(post.getProfileUrl());

                WebElement picElem = chromeDriver.findElementByXPath("//*[local-name()='svg' and @role=\"img\"]");

                String profilePic = picElem.findElements(By.tagName("image")).get(0).getAttribute("xlink:href");
                //TODO need to fix xpath when story is available


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

    private List<ScrappedPostInfo> getStatuses()  {
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
                    String smallProfilePic = e.findElement(By.tagName("image")).getAttribute("xlink:href");


                    if (e.findElements(By.xpath(".//div[@class=\" linoseic datstx6m\"]")).size() > 0) {
                        contentElement = e.findElement(By.xpath(".//div[@class=\" linoseic datstx6m\"]"));
                    } else if (e.findElements(By.xpath(".//div[@class=\"kvgmc6g5 cxmmr5t8 oygrvhab hcukyx3x c1et5uql ii04i59q\"]")).size() > 0) {
                        contentElement = e.findElement(By.xpath(".//div[@class=\"kvgmc6g5 cxmmr5t8 oygrvhab hcukyx3x c1et5uql ii04i59q\"]"));
                    } else {
                        continue;
                    }

                    fullName = userNameElement.getText();
                    content = contentElement.getText();
                   // facebookId = extractor(userProfileRef.getAttribute(HREF_ATTRIBUTE), idUserExtractionPattern);

                    System.out.println(userProfileRef.getAttribute(HREF_ATTRIBUTE));

                    facebookId = testExtractor(userProfileRef.getAttribute(HREF_ATTRIBUTE));

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
                    scrappedPostInfo.setSmallPicUrl(smallProfilePic);

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

    private LocalTime formatTime(String text) {
        return LocalTime.parse(text, timeFormatter);
    }

    private LocalDate formatDate(String text) {

        return LocalDate.parse(text.replaceFirst("[/.\\-]", ".") + "." + LocalDate.now().getYear(), dateFormatter);
    }

    private String testExtractor(String profileUrl){

        Matcher m = idUserExtractionPattern.matcher(profileUrl);
        Matcher m2 = phpUserExtractionPattern.matcher(profileUrl);
        Matcher m3 = userNameIdUserExtractionPattern.matcher(profileUrl);

        if(m3.find()){
            return m3.group().substring(0, m3.group().length() - 1).split("/")[1];
        }

        if(m2.find()){
            return m2.group();
        }

        if (m.find()) {
            return m.group().split("/")[1];
        }

        return "";
    }

    private String extractor(String profileUrl, Pattern pattern) {
        Matcher m = pattern.matcher(profileUrl);


        if (m.find()) {

            return m.group().split("/")[1];
        }

        return "";
    }

    private String extractNumber(String text) {
        Matcher m = phoneExtractorPattern.matcher(text.replaceAll("\\s+", ""));

        if (m.find()) {
            return m.group();
        }

        return "";
    }

    private LocalTime[] extractSpecificTimeOfTheDay(String text) {
        for (Map.Entry<String, String[]> entry : InfoExtractor.hoursAndMinutez.entrySet()) {
            if (text.contains(entry.getKey())) {
                return Arrays.stream(entry.getValue()).map(this::formatTime).toArray(LocalTime[]::new);
            }
        }

        return null;
    }

    private LocalTime[] extractTimeOfTheDay(String text) {

        for (Map.Entry<String, String[]> entry : timeOfTheDayzz.entrySet()) {
            if (text.contains(entry.getKey())) {
                return Arrays.stream(entry.getValue()).map(this::formatTime).toArray(LocalTime[]::new);
            }
        }
        return null;
    }

    private int extractSeatsFromStatus(String text) {
        for (Map.Entry<String, Integer> entry : seatz.entrySet()) {
            if (text.contains(entry.getKey())) {
                return entry.getValue();
            }
        }
        return 1;
    }

    private LocalDate extractDayOfTheWeekFromStatus(String text) {
        for (Map.Entry<String, Supplier<LocalDate>> entry : dayzz.entrySet()) {
            if (text.contains(entry.getKey())) {
                return entry.getValue().get();
            }
        }
        return null;
    }

    private LocalDate extractDateFromStatus(String text) {

        for (String d : InfoExtractor.dates) {
            if (text.contains(d)) {
                return formatDate(d);

            }
        }

        return null;
    }


}
