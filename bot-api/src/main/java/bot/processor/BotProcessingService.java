package bot.processor;

import okhttp3.internal.http2.Header;
import org.h2.util.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Random;

public class BotProcessingService {


    private RestTemplate restTemplate;

    private static int counter=0;


    public BotProcessingService() {
         restTemplate = new RestTemplate();
    }

    public void processFurther(String userName,String linkToProfile,String linkToProfileInGroup,String status,String timePosted){


        System.out.println("Username : " + userName);
        System.out.println("Link to profile : "+ linkToProfile);
        System.out.println("Link to profile in group : "+ linkToProfileInGroup);
        System.out.println("Posted : "+ timePosted);
        System.out.println(status);

//        String names[] = userName.split(" ");
//        String firstName = names[0];
//        String lastName = names[1];
//
//        User user = new User(
//                null,
//                userName,
//                firstName,
//                "",
//                lastName,
//                "DEFAULT_USER_EMAIL"+ counter++,
//                "1234",
//                linkToProfile,
//                "11111",
//                null,
//                Gender.MALE,
//                Role.USER,
//                null,
//                null);
//
//        Trip trip = new Trip(
//                null,
//                null,
//                "bot",
//                "bot",
//                LocalDateTime.now(),
//                null,
//                LocalDateTime.now(),
//                status,null);
//
//
//        String users_url = "http://localhost:8080/config/api/v1/users";
//        String trips_url = "http://localhost:8080/config/api/v1/trips";
//
//
//        ResponseEntity<User> returnedUser = restTemplate.postForEntity(users_url,user,User.class);
//
//        user.setId(returnedUser.getBody().getId());
//
//        trip.setDriver(user);
//
//        restTemplate.postForEntity(trips_url,trip,Trip.class);








//
//        System.out.println("------------------------------------");
//
//
//        restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//
//        String[] firstLastName = userName.split(" ");
//
//
//        HttpEntity<String> entity = new HttpEntity<String>("{\n" +
//                "    \n" +
//                "         \n" +
//                "        \"firstName\": \""+ firstLastName[0]+"\",\n" +
//                "        \"lastName\": \""+ firstLastName[1]+"\",\n" +
//                "        \"role\": \"USER\",\n" +
//                "    }", headers);
//        restTemplate.postForEntity(url, entity,String.class);

//
//
//
//        restTemplate.postForEntity("http://localhost:8080/config/api/v1/users",driver,User.class);
//
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        Trip trip = new Trip(null,null,null,null,null,null,null,status,null);
//
//        restTemplate.postForEntity(url, trip,Trip.class);

    }
}
