package bot;

import bot.config.CustomChromeDriver;
import bot.config.ExecutorConfiguration;
import bot.model.*;
import bot.processor.BotExecutorService;
import bot.processor.BotNavigationService;

import bot.utils.City;
import bot.utils.InfoExtractor;
import com.cloudmersive.client.RephraseApi;
import com.cloudmersive.client.invoker.ApiClient;
import com.cloudmersive.client.invoker.ApiException;
import com.cloudmersive.client.invoker.Configuration;
import com.cloudmersive.client.invoker.auth.ApiKeyAuth;
import com.cloudmersive.client.model.RephraseRequest;
import com.cloudmersive.client.model.RephraseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil;
import org.apache.tomcat.jni.Local;
import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;


import javax.swing.text.DateFormatter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static bot.utils.GroupsNamesAndUrls.*;
import static bot.utils.InfoExtractor.DIRECTIONS;
import static bot.utils.InfoExtractor.seats;


@org.springframework.boot.autoconfigure.SpringBootApplication
public class BotApplication {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(BotApplication.class, args);

//        CustomChromeDriver customChromeDriver = new CustomChromeDriver();
//
//        ChromeDriver chromeDriver = customChromeDriver.getChromeDriver();
//
//        ExecutorConfiguration executorConfiguration = new ExecutorConfiguration(GROUPS);
//
//        BotNavigationService botNavigationService = new BotNavigationService(chromeDriver);
//        botNavigationService.openFaceBook();
//        botNavigationService.loginToFacebook();
//        BotExecutorService botExecutorService = botNavigationService.prepareExecutor(executorConfiguration);
//
//
//        botExecutorService.startScanning();

        String text = "през Пловдив";

        text=  text.toLowerCase();

        String textWithoutAlternativeNames =  replaceAlternativeName(text);

        Map<Integer, City> extractedCities = extractCities(textWithoutAlternativeNames);

        List<City> cities = orderCities(textWithoutAlternativeNames,extractedCities,List.of(City.SOFIA,City.BURGAS));


        System.out.println(cities);



    }


    public static List<City> orderCities(String text, Map<Integer, City> extractedCities, List<City> defaultCities) {
        List<City> citiesInOrder = new ArrayList<>();

        String textWithoutSpaces = text.replaceAll("\\s+", "");

        System.out.println(textWithoutSpaces);

        //TODO default cities should be orderedBy distance between givenCity

        if (extractedCities.values().size() == 1) {

            defaultCities
                    .stream()
                    .filter(d -> !extractedCities.containsValue(d))
                    .findFirst()
                    .ifPresent(citiesInOrder::add);
        }

        boolean isEntryPointGiven = false;
        boolean isEndPointGiven = false;
        boolean isInterMediatePointGiven = false;

        for (City city : extractedCities.values()) {

            citiesInOrder.add(city);


                if (isEndPointGiven && isInterMediatePointGiven) {
                    System.out.println("през");
                    System.out.println("SHRKKK");
                    System.out.println(city.name());
                    citiesInOrder.remove(city);
                    citiesInOrder.add(citiesInOrder.size() == 0 ? 0 : citiesInOrder.size() - 1, city);
                    isInterMediatePointGiven = true;
                    break;
                }



            for (String s : DIRECTIONS) {


                if (textWithoutSpaces.contains(s + city.name())) {


                    if (s.equals("от")) {
                        System.out.println("ot");
                        System.out.println(city.name());
                        citiesInOrder.remove(city);
                        citiesInOrder.add(0, city);
                        isEntryPointGiven = true;
                        break;
                    }

                    if (s.equals("за") || s.equals("към") || s.equals("до") || s.equals("на")) {
                        System.out.println("за към до на");
                        System.out.println(city.name());
                        citiesInOrder.remove(city);
                        citiesInOrder.add(citiesInOrder.size(), city);
                        isEndPointGiven = true;
                        break;
                    }


                    if (s.equals("през")) {
                        System.out.println("през");
                        System.out.println(city.name());

                        citiesInOrder.remove(city);
                        citiesInOrder.add(isEndPointGiven ? citiesInOrder.size() - 1 : citiesInOrder.size(), city);
                        isInterMediatePointGiven = true;
                        break;
                    }

                }


            }
        }

        return citiesInOrder;
    }


    private static String replaceAlternativeName(String text){


        for (City c : City.values()) {

            for (String cityAlternativeName : c.getNames()) {
                if (text.contains(cityAlternativeName.toLowerCase())) {
                    text = text.replace(cityAlternativeName.toLowerCase(), c.name());

                    break;
                }
            }
        }

        return text;
    }

    private static Map<Integer,City> extractCities(String text){
        Map<Integer, City> maps = new TreeMap<>();

        for (City c : City.values()) {

                if (text.contains(c.name())) {

                    maps.put(text.indexOf(c.name()), c);

                }
            }

        return maps;
    }
}
