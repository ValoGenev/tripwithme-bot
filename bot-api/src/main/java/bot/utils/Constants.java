package bot.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Constants {

    public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");


    public static final String URL_EXTENSION_FOR_CHRONOLOGICAL="?sorting_setting=CHRONOLOGICAL";

    public static final String CHROME_DRIVER = "webdriver.chrome.driver";
    public static final String PATH_TO_CHROME_DRIVER= System.getProperty("user.dir")+"\\chromedriver.exe";
    public static final String FACEBOOK_URL="http://facebook.com";
    public static final String EMAIL = "valo_genev@abv.bg";
    public static final String PASS = "";
    public static final String XPATH_TO_LOGIN_ELEMENT="//button[@name=\"login\"]";
    public static final String XPATH_TO_EMAIL_ELEMENT="email";
    public static final String XPATH_TO_PASS_ELEMENT ="pass";
    public static final String XPATH_TO_USERNAMES = "//a[@class=\"oajrlxb2 g5ia77u1 qu0x051f esr5mh6w e9989ue4 r7d6kgcz rq0escxv nhd2j8a9 nc684nl6 p7hjln8o kvgmc6g5 cxmmr5t8 oygrvhab hcukyx3x jb3vyjys rz4wbd8a qt6c0cv9 a8nywdso i1ao9s8h esuyzwwr f1sip0of lzcic4wl oo9gr5id gpro0wi8 lrazzd5p\"\n]";
    public static final String XPATH_TO_STATUSES_TEXT="//div[@class=\"kvgmc6g5 cxmmr5t8 oygrvhab hcukyx3x c1et5uql ii04i59q\"]";
    public static final String XPATH_TO_STATUSES_PIC="//div[@class=\"k4urcfbm kr520xx4 j9ispegn pmk7jnqg taijpn5t datstx6m cbu4d94t j83agx80 bp9cbjyn\"]";
    public static final String XPATH_TO_STATUSES_COMMENTS="//div[@class=\"tw6a2znq sj5x9vvc d1544ag0 cxgpxx05\"]";
    public static final String XPATH_TO_STATUSES_TIME_POSTED="//span[@class=\"oi732d6d ik7dh3pa d2edcug0 qv66sw1b c1et5uql a8c37x1j hop8lmos enqfppq2 e9vueds3 j5wam9gi knj5qynh m9osqain hzawbc8m\"]";
    public static final String XPATH_TO_WHOLE_STATUS="//div[@class=\"du4w35lb k4urcfbm l9j0dhe7 sjgh65i0\"]";
    public static final String XPATH_TO_FACEBOOK_GROUP_NAME="//span[@class=\"oi732d6d ik7dh3pa d2edcug0 hpfvmrgz qv66sw1b c1et5uql a8c37x1j irj2b8pg q9se6cdp m6dqt4wy h7mekvxk hnhda86s oo9gr5id hzawbc8m\"]";

    public static final String XPATH_TO_VIEW_PROFILE="//a[@class=\"oajrlxb2 tdjehn4e gcieejh5 bn081pho humdl8nn izx4hr6d rq0escxv nhd2j8a9 j83agx80 p7hjln8o kvgmc6g5 cxmmr5t8 oygrvhab hcukyx3x jb3vyjys hv4rvrfc qt6c0cv9 dati1w0a i1ao9s8h esuyzwwr f1sip0of lzcic4wl l9j0dhe7 abiwlrkh p8dawk7l beltcj47 p86d2i9g aot14ch1 kzx2olss cbu4d94t taijpn5t ni8dbmo4 stjgntxs k4urcfbm tv7at329\"]";

    public static final String XPATH_TEST = "//div[@class=\"oajrlxb2 b3i9ofy5 qu0x051f esr5mh6w e9989ue4 r7d6kgcz rq0escxv nhd2j8a9 j83agx80 p7hjln8o kvgmc6g5 cxmmr5t8 oygrvhab hcukyx3x cxgpxx05 d1544ag0 sj5x9vvc tw6a2znq i1ao9s8h esuyzwwr f1sip0of lzcic4wl l9j0dhe7 abiwlrkh p8dawk7l bp9cbjyn orhb3f3m czkt41v7 fmqxjp7s emzo65vh btwxx1t3 buofh1pr idiwt2bm jifvfom9 ni8dbmo4 stjgntxs kbf60n1y\"]";

    public static final String XPATH_TO_FACEBOOK_ACCEPT_COOKIES="//button[@data-testid=\"cookie-policy-dialog-accept-button\"]";
    public static final String HREF_ATTRIBUTE="href";

    public static final String OPEN_FACEBOOK_MESSAGE="Opening www.facebook.com";
    public static final String OPEN_FACEBOOK_GROUP_MESSAGE="Opening facebook group with name %s";
    public static final String LOGIN_TO_FACEBOOK_MESSAGE="Logging to facebook with email: %s and password: %s";
    public static final String SCANNING_FACEBOOK_GROUP="Scanning facebook group with name %s";





    public static String getCurrentTime(){
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

}
