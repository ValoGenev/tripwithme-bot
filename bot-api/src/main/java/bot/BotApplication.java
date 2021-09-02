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


@org.springframework.boot.autoconfigure.SpringBootApplication
public class BotApplication {


    public static void main(String[] args) throws Exception {
        SpringApplication.run(BotApplication.class, args);

//        List<ScrappedTestInfo> tests = new ArrayList<>();
//
//        ScrappedTestInfo test = new ScrappedTestInfo();
//        test.setCities(List.of(City.BURGAS,City.SOFIA));
//        test.setConditional("SHTE PUTUVA");
//        test.setDescription("PUTUVAM UTRE V 5.30");
//
//
//        tests.add(test);
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        try {
//
//            // Writing to a file
//            mapper.writeValue(new File("C:\\Users\\seasi\\Desktop\\coop backup\\bez address i payment\\2\\coop-travel\\bot-api\\src\\main\\java\\bot\\test.json"), tests );
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

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
//         botExecutorService.startScanning();



//        String textWithoutSpaces = ("Valo     Genev ot Burgas do Varna" +
//                "dasdasdasdasd " +
//                "dasdasdasda ne moga " +
//                " dbras  das  " +
//                "\n  dasdada dasdsdasda").replaceAll("\\s+","");
//
//        System.out.println(textWithoutSpaces);


        List<String> lists = new ArrayList<>();

        lists.add("valo");
        lists.add("dana");
        lists.add("kati");


        lists.add(0,"petko");

        System.out.println(lists);








    }

}
