package bot.utils;


import bot.model.WillRideOrDrivePair;
import org.apache.tomcat.jni.Local;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.function.Supplier;

public class InfoExtractor {


    public static final Map<String, Supplier<LocalDate>> dayzz = new LinkedHashMap<>() {

        {
            put("днеска", LocalDate::now);
            put("днес", LocalDate::now);
            put("утре", () -> LocalDate.now().plusDays(1));
            put("понеделник", () -> LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY)));
            put("вторник", () -> LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY)));
            put("сряда", () -> LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.WEDNESDAY)));
            put("четврътак", () -> LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.THURSDAY)));
            put("петък", () -> LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY)));
            put("събота", () -> LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY)));
            put("неделя", () -> LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)));
        }

    };

    public final static List<String> days = Arrays.asList(

            "днеска",
            "днес",
            "утре",
            "понеделник",
            "вторник",
            "сряда",
            "четвъртак",
            "четвъртък",
            "петък",
            "събота",
            "неделя"
    );


    public final static Map<String, String[]> timeOfTheDayzz = new LinkedHashMap<>() {
        {
            put("рано сутрин", new String[]{"05:00", "10:00"});
            put("рано сутринта", new String[]{"05:00", "10:00"});
            put("сутрин", new String[]{"06:00", "11:00"});
            put("сутринта", new String[]{"06:00", "11:00"});
            put("преди обяд", new String[]{"06:00", "12:00"});
            put("преди обед", new String[]{"06:00", "12:00"});
            put("предиобед", new String[]{"06:00", "12:00"});
            put("следобяд", new String[]{"12:00", "23:59"});
            put("следобед", new String[]{"12:00", "23:59"});
            put("около обяд", new String[]{"09:00", "15:00"});
            put("обяд", new String[]{"10:00", "14:00"});
            put("на обяд", new String[]{"10:00", "14:00"});
            put("привечер", new String[]{"17:00", "22:00"});
            put("вечер", new String[]{"18:00", "23:59"});
            put("вечерта", new String[]{"18:00", "23:59"});
            put("вечерно време", new String[]{"18:00", "23:59"});
            put("полунощ", new String[]{"22:00", "23:59"});

        }
    };

    public final static List<String> timeOfTheDay = Arrays.asList(

            "рано сутрин",
            "рано сутринта",

            "сутрин",
            "сутринта",

            "преди обяд",
            "преди обед",
            "предиобед",

            "следобяд",
            "следобед",

            "около обяд",
            "обяд",
            "на обяд",

            "привечер",

            "вечер",
            "вечерта",
            "вечерно време",
            "полунощ"

    );



    public final static List<WillRideOrDrivePair> WILL_DRIVE_PAIRS = Arrays.asList(
            new WillRideOrDrivePair("пътувам", 10),
            new WillRideOrDrivePair("пътуваме", 10),
            new WillRideOrDrivePair("ще карам", 10),
            new WillRideOrDrivePair("карам", 10),
            new WillRideOrDrivePair("тръгваме", 10),
            new WillRideOrDrivePair("тръгвам", 10),
            new WillRideOrDrivePair("заминавам", 10),
            new WillRideOrDrivePair("потеглям", 10),
            new WillRideOrDrivePair("потегляме", 10),
            new WillRideOrDrivePair("тръгване", 10),
            new WillRideOrDrivePair("да взема", 10),
            new WillRideOrDrivePair("приятелка пътува", 10),
            new WillRideOrDrivePair("приятел пътува", 10),
            new WillRideOrDrivePair("майка ми пътува", 10),
            new WillRideOrDrivePair("мъжът ми пътува", 10),
            new WillRideOrDrivePair("мъжа ми пътува", 10),
            new WillRideOrDrivePair("синът ми пътува", 10),
            new WillRideOrDrivePair("дъщеря ми пътува", 10),
            new WillRideOrDrivePair("приятелка тръгва", 10),
            new WillRideOrDrivePair("приятел тръгва", 10),
            new WillRideOrDrivePair("майка ми тръгва", 10),
            new WillRideOrDrivePair("мъжът ми тръгва", 10),
            new WillRideOrDrivePair("мажа ми тръгва", 10),
            new WillRideOrDrivePair("синът ми тръгва", 10),
            new WillRideOrDrivePair("дъщеря ми тръгва", 10),
            new WillRideOrDrivePair("приятелка ще пътува", 10),
            new WillRideOrDrivePair("приятел ще пътува", 10),
            new WillRideOrDrivePair("майка ми ще пътува", 10),
            new WillRideOrDrivePair("мъжът ми ще пътува", 10),
            new WillRideOrDrivePair("мъжа ми ще пътува", 10),
            new WillRideOrDrivePair("синът ми ще пътува", 10),
            new WillRideOrDrivePair("дъщеря ми ще пътува", 10),
            new WillRideOrDrivePair("приятелка ще тръгва", 10),
            new WillRideOrDrivePair("приятел ще тръгва", 10),
            new WillRideOrDrivePair("майка ми ще тръгва", 10),
            new WillRideOrDrivePair("мъжът ми ще тръгва", 10),
            new WillRideOrDrivePair("мъжа ми ще тръгва", 10),
            new WillRideOrDrivePair("синът ми ще тръгва", 10),
            new WillRideOrDrivePair("дъщеря ми ще тръгва", 10),
            new WillRideOrDrivePair("тръгва",5)

    );

    public final static List<String> DIRECTIONS= Arrays.asList(
            "от",
            "до",
            "към",
            "за",
            "през"
    );

    public final static List<WillRideOrDrivePair> WILL_RIDE_PAIRS = Arrays.asList(
            new WillRideOrDrivePair("търся", 10),
            new WillRideOrDrivePair("търсим си", 10),
            new WillRideOrDrivePair("търсим", 10),
            new WillRideOrDrivePair("има ли", 10),
            new WillRideOrDrivePair("някой има ли", 10),
            new WillRideOrDrivePair("пътува ли", 10),
            new WillRideOrDrivePair("ще ме вземе ли", 10),
            new WillRideOrDrivePair("вземе", 5),
            new WillRideOrDrivePair("предлага ли", 10),
            new WillRideOrDrivePair("заминава ли", 10),
            new WillRideOrDrivePair("има ли свободни", 10),
            new WillRideOrDrivePair("да ме закара", 10),
            new WillRideOrDrivePair("някой да пътува", 10),
            new WillRideOrDrivePair("някой тръгва ли", 10),
            new WillRideOrDrivePair("някой има ли свободн", 10),
            new WillRideOrDrivePair("някой да има свободн", 10),
            new WillRideOrDrivePair("се търси", 5),
            new WillRideOrDrivePair("търси се",5),
            new WillRideOrDrivePair("си търси", 5),
            new WillRideOrDrivePair("някой", 5),
            new WillRideOrDrivePair("?", 5)

    );

    public final static List<String> willRide = Arrays.asList(
            "търся",
            "търсим си",
            "търсим",
            "търси",
            "има ли",
            "някой има ли",
            "пътува ли",
            "ще ме вземе ли",
            "вземе",
            "предлага ли",
            "заминава ли",
            "има ли свободни",
            "да ме закара",
            "ще ме закара ли",
            "някой да пътува",
            "някой тръгва ли",
            "някой има ли свободн",
            "някой да има свободн",
            "?"
    );

    public static Map<String, Integer> seatz = new LinkedHashMap<>() {
        {
            put("1 място", 1);
            put("2 места", 2);
            put("3 места", 3);
            put("4 места", 4);
            put("5 места", 5);
            put("1 свободно място", 1);
            put("2 свободни места", 2);
            put("3 свободни места", 3);
            put("4 свободни места", 4);
            put("5 свободни места", 5);
            put("1място", 1);
            put("2места", 2);
            put("3места", 3);
            put("4места", 4);
            put("5места", 5);
            put("1-място", 1);
            put("2-места", 2);
            put("3-места", 3);
            put("4-места", 4);
            put("5-места", 5);
            put("1свободно място", 1);
            put("2свободни места", 2);
            put("3свободни места", 3);
            put("4свободни места", 4);
            put("5свободни места", 5);
            put("1-свободно място", 1);
            put("2-свободни места", 2);
            put("3-свободни места", 3);
            put("4-свободни места", 4);
            put("5-свободни места", 5);
            put("едно място", 1);
            put("две места", 2);
            put("три места", 3);
            put("четери места", 4);
            put("едно свободно място", 1);
            put("две свободни места", 2);
            put("три свободни места", 3);
            put("четери свободни места", 4);
            put("пет свободни места", 5);
            put("едно местенце",1);
            put("две местенца",1);
            put("три местенца",1);
            put("четери местенца",1);
            put("пет местенца",1);
            put("едно свободно местенце",1);
            put("две свободни местенца",1);
            put("три свободни местенца",1);
            put("четери свободни местенца",1);
            put("пет свободни местенца",1);

            put("имам места",2);
            put("място",1);

        }
    };

    public static List<String> seats = Arrays.asList(
            "1 място",
            "2 места",
            "3 места",
            "4 места",
            "5 места",
            "1 свободно място",
            "2 свободни места",
            "3 свободни места",
            "4 свободни места",
            "5 свободни места",
            "1място",
            "2места",
            "3места",
            "4места",
            "5места",
            "1-място",
            "2-места",
            "3-места",
            "4-места",
            "5-места",
            "1свободно място",
            "2свободни места",
            "3свободни места",
            "4свободни места",
            "5свободни места",
            "1-свободно място",
            "2-свободни места",
            "3-свободни места",
            "4-свободни места",
            "5-свободни места",
            "едно място",
            "две места",
            "три места",
            "четери места",
            "едно свободно място",
            "две свободни места",
            "три свободни места",
            "четери свободни места",
            "пет свободни места"
    );

    public static Map<String, String[]> hoursAndMinutez = new LinkedHashMap<>() {
        {
            put("00:00ч", new String[]{"23:30", "00:30"});
            put("00:15ч", new String[]{"23:45", "00:45"});
            put("00:30ч", new String[]{"00:00", "01:00"});
            put("00:45ч", new String[]{"00:15", "01:15"});
            put("01:00ч", new String[]{"00:30", "01:30"});
            put("01:15ч", new String[]{"00:45", "01:45"});
            put("01:30ч", new String[]{"01:00", "02:00"});
            put("01:45ч", new String[]{"01:15", "02:15"});
            put("02:00ч", new String[]{"01:30", "02:30"});
            put("02:15ч", new String[]{"01:45", "02:45"});
            put("02:30ч", new String[]{"02:00", "03:00"});
            put("02:45ч", new String[]{"02:15", "03:15"});
            put("03:00ч", new String[]{"02:30", "03:30"});
            put("03:15ч", new String[]{"02:45", "03:45"});
            put("03:30ч", new String[]{"03:00", "04:00"});
            put("03:45ч", new String[]{"03:15", "04:15"});
            put("04:00ч", new String[]{"03:30", "04:30"});
            put("04:15ч", new String[]{"03:45", "04:45"});
            put("04:30ч", new String[]{"04:00", "05:00"});
            put("04:45ч", new String[]{"04:15", "05:15"});
            put("05:00ч", new String[]{"04:30", "05:30"});
            put("05:15ч", new String[]{"04:45", "05:45"});
            put("05:30ч", new String[]{"05:00", "06:00"});
            put("05:45ч", new String[]{"05:15", "06:15"});
            put("06:00ч", new String[]{"05:30", "06:30"});
            put("06:15ч", new String[]{"05:45", "06:45"});
            put("06:30ч", new String[]{"06:00", "07:00"});
            put("06:45ч", new String[]{"06:15", "07:15"});
            put("07:00ч", new String[]{"06:30", "07:30"});
            put("07:15ч", new String[]{"06:45", "07:45"});
            put("07:30ч", new String[]{"07:00", "08:00"});
            put("07:45ч", new String[]{"07:15", "08:15"});
            put("08:00ч", new String[]{"07:30", "08:30"});
            put("08:15ч", new String[]{"07:45", "08:45"});
            put("08:30ч", new String[]{"08:00", "09:00"});
            put("08:45ч", new String[]{"08:15", "09:15"});
            put("09:00ч", new String[]{"08:30", "09:30"});
            put("09:15ч", new String[]{"08:45", "09:45"});
            put("09:30ч", new String[]{"09:00", "10:00"});
            put("09:45ч", new String[]{"09:15", "10:15"});
            put("10:00ч", new String[]{"09:30", "10:30"});
            put("10:15ч", new String[]{"09:45", "10:45"});
            put("10:30ч", new String[]{"10:00", "11:00"});
            put("10:45ч", new String[]{"10:15", "11:15"});
            put("11:00ч", new String[]{"10:30", "11:30"});
            put("11:15ч", new String[]{"10:45", "11:45"});
            put("11:30ч", new String[]{"11:00", "12:00"});
            put("11:45ч", new String[]{"11:15", "12:15"});
            put("12:00ч", new String[]{"11:30", "12:30"});
            put("12:15ч", new String[]{"11:45", "12:45"});
            put("12:30ч", new String[]{"12:00", "13:00"});
            put("12:45ч", new String[]{"12:15", "13:15"});
            put("13:00ч", new String[]{"12:30", "13:30"});
            put("13:15ч", new String[]{"12:45", "13:45"});
            put("13:30ч", new String[]{"13:00", "14:00"});
            put("13:45ч", new String[]{"13:15", "14:15"});
            put("14:00ч", new String[]{"13:30", "14:30"});
            put("14:15ч", new String[]{"13:45", "14:45"});
            put("14:30ч", new String[]{"14:00", "15:00"});
            put("14:45ч", new String[]{"14:15", "15:15"});
            put("15:00ч", new String[]{"14:30", "15:30"});
            put("15:15ч", new String[]{"14:45", "15:45"});
            put("15:30ч", new String[]{"15:00", "16:00"});
            put("15:45ч", new String[]{"15:15", "16:15"});
            put("16:00ч", new String[]{"15:30", "16:30"});
            put("16:15ч", new String[]{"15:45", "16:45"});
            put("16:30ч", new String[]{"16:00", "17:00"});
            put("16:45ч", new String[]{"16:15", "17:15"});
            put("17:00ч", new String[]{"16:30", "17:30"});
            put("17:15ч", new String[]{"16:45", "17:45"});
            put("17:30ч", new String[]{"17:00", "18:00"});
            put("17:45ч", new String[]{"17:15", "18:15"});
            put("18:00ч", new String[]{"17:30", "18:30"});
            put("18:15ч", new String[]{"17:45", "18:45"});
            put("18:30ч", new String[]{"18:00", "19:00"});
            put("18:45ч", new String[]{"18:15", "19:15"});
            put("19:00ч", new String[]{"18:30", "19:30"});
            put("19:15ч", new String[]{"18:45", "19:45"});
            put("19:30ч", new String[]{"19:00", "20:00"});
            put("19:45ч", new String[]{"19:15", "20:15"});
            put("20:00ч", new String[]{"19:30", "20:30"});
            put("20:15ч", new String[]{"19:45", "20:45"});
            put("20:30ч", new String[]{"20:00", "21:00"});
            put("20:45ч", new String[]{"20:15", "21:15"});
            put("21:00ч", new String[]{"20:30", "21:30"});
            put("21:15ч", new String[]{"20:45", "21:45"});
            put("21:30ч", new String[]{"21:00", "22:00"});
            put("21:45ч", new String[]{"21:15", "22:15"});
            put("22:00ч", new String[]{"21:30", "22:30"});
            put("22:15ч", new String[]{"21:45", "22:45"});
            put("22:30ч", new String[]{"22:00", "23:00"});
            put("22:45ч", new String[]{"22:15", "23:15"});
            put("23:00ч", new String[]{"22:30", "23:30"});
            put("23:15ч", new String[]{"22:45", "23:45"});
            put("23:30ч", new String[]{"23:00", "00:00"});
            put("23:45ч", new String[]{"23:15", "00:15"});

            put("00:00", new String[]{"23:30", "00:30"});
            put("00:15", new String[]{"23:45", "00:45"});
            put("00:30", new String[]{"00:00", "01:00"});
            put("00:45", new String[]{"00:15", "01:15"});
            put("01:00", new String[]{"00:30", "01:30"});
            put("01:15", new String[]{"00:45", "01:45"});
            put("01:30", new String[]{"01:00", "02:00"});
            put("01:45", new String[]{"01:15", "02:15"});
            put("02:00", new String[]{"01:30", "02:30"});
            put("02:15", new String[]{"01:45", "02:45"});
            put("02:30", new String[]{"02:00", "03:00"});
            put("02:45", new String[]{"02:15", "03:15"});
            put("03:00", new String[]{"02:30", "03:30"});
            put("03:15", new String[]{"02:45", "03:45"});
            put("03:30", new String[]{"03:00", "04:00"});
            put("03:45", new String[]{"03:15", "04:15"});
            put("04:00", new String[]{"03:30", "04:30"});
            put("04:15", new String[]{"03:45", "04:45"});
            put("04:30", new String[]{"04:00", "05:00"});
            put("04:45", new String[]{"04:15", "05:15"});
            put("05:00", new String[]{"04:30", "05:30"});
            put("05:15", new String[]{"04:45", "05:45"});
            put("05:30", new String[]{"05:00", "06:00"});
            put("05:45", new String[]{"05:15", "06:15"});
            put("06:00", new String[]{"05:30", "06:30"});
            put("06:15", new String[]{"05:45", "06:45"});
            put("06:30", new String[]{"06:00", "07:00"});
            put("06:45", new String[]{"06:15", "07:15"});
            put("07:00", new String[]{"06:30", "07:30"});
            put("07:15", new String[]{"06:45", "07:45"});
            put("07:30", new String[]{"07:00", "08:00"});
            put("07:45", new String[]{"07:15", "08:15"});
            put("08:00", new String[]{"07:30", "08:30"});
            put("08:15", new String[]{"07:45", "08:45"});
            put("08:30", new String[]{"08:00", "09:00"});
            put("08:45", new String[]{"08:15", "09:15"});
            put("09:00", new String[]{"08:30", "09:30"});
            put("09:15", new String[]{"08:45", "09:45"});
            put("09:30", new String[]{"09:00", "10:00"});
            put("09:45", new String[]{"09:15", "10:15"});
            put("10:00", new String[]{"09:30", "10:30"});
            put("10:15", new String[]{"09:45", "10:45"});
            put("10:30", new String[]{"10:00", "11:00"});
            put("10:45", new String[]{"10:15", "11:15"});
            put("11:00", new String[]{"10:30", "11:30"});
            put("11:15", new String[]{"10:45", "11:45"});
            put("11:30", new String[]{"11:00", "12:00"});
            put("11:45", new String[]{"11:15", "12:15"});
            put("12:00", new String[]{"11:30", "12:30"});
            put("12:15", new String[]{"11:45", "12:45"});
            put("12:30", new String[]{"12:00", "13:00"});
            put("12:45", new String[]{"12:15", "13:15"});
            put("13:00", new String[]{"12:30", "13:30"});
            put("13:15", new String[]{"12:45", "13:45"});
            put("13:30", new String[]{"13:00", "14:00"});
            put("13:45", new String[]{"13:15", "14:15"});
            put("14:00", new String[]{"13:30", "14:30"});
            put("14:15", new String[]{"13:45", "14:45"});
            put("14:30", new String[]{"14:00", "15:00"});
            put("14:45", new String[]{"14:15", "15:15"});
            put("15:00", new String[]{"14:30", "15:30"});
            put("15:15", new String[]{"14:45", "15:45"});
            put("15:30", new String[]{"15:00", "16:00"});
            put("15:45", new String[]{"15:15", "16:15"});
            put("16:00", new String[]{"15:30", "16:30"});
            put("16:15", new String[]{"15:45", "16:45"});
            put("16:30", new String[]{"16:00", "17:00"});
            put("16:45", new String[]{"16:15", "17:15"});
            put("17:00", new String[]{"16:30", "17:30"});
            put("17:15", new String[]{"16:45", "17:45"});
            put("17:30", new String[]{"17:00", "18:00"});
            put("17:45", new String[]{"17:15", "18:15"});
            put("18:00", new String[]{"17:30", "18:30"});
            put("18:15", new String[]{"17:45", "18:45"});
            put("18:30", new String[]{"18:00", "19:00"});
            put("18:45", new String[]{"18:15", "19:15"});
            put("19:00", new String[]{"18:30", "19:30"});
            put("19:15", new String[]{"18:45", "19:45"});
            put("19:30", new String[]{"19:00", "20:00"});
            put("19:45", new String[]{"19:15", "20:15"});
            put("20:00", new String[]{"19:30", "20:30"});
            put("20:15", new String[]{"19:45", "20:45"});
            put("20:30", new String[]{"20:00", "21:00"});
            put("20:45", new String[]{"20:15", "21:15"});
            put("21:00", new String[]{"20:30", "21:30"});
            put("21:15", new String[]{"20:45", "21:45"});
            put("21:30", new String[]{"21:00", "22:00"});
            put("21:45", new String[]{"21:15", "22:15"});
            put("22:00", new String[]{"21:30", "22:30"});
            put("22:15", new String[]{"21:45", "22:45"});
            put("22:30", new String[]{"22:00", "23:00"});
            put("22:45", new String[]{"22:15", "23:15"});
            put("23:00", new String[]{"22:30", "23:30"});
            put("23:15", new String[]{"22:45", "23:45"});
            put("23:30", new String[]{"23:00", "00:00"});
            put("23:45", new String[]{"23:15", "00:15"});

            put("00.00", new String[]{"23:30", "00:30"});
            put("00.15", new String[]{"23:45", "00:45"});
            put("00.30", new String[]{"00:00", "01:00"});
            put("00.45", new String[]{"00:15", "01:15"});
            put("01.00", new String[]{"00:30", "01:30"});
            put("01.15", new String[]{"00:45", "01:45"});
            put("01.30", new String[]{"01:00", "02:00"});
            put("01.45", new String[]{"01:15", "02:15"});
            put("02.00", new String[]{"01:30", "02:30"});
            put("02.15", new String[]{"01:45", "02:45"});
            put("02.30", new String[]{"02:00", "03:00"});
            put("02.45", new String[]{"02:15", "03:15"});
            put("03.00", new String[]{"02:30", "03:30"});
            put("03.15", new String[]{"02:45", "03:45"});
            put("03.30", new String[]{"03:00", "04:00"});
            put("03.45", new String[]{"03:15", "04:15"});
            put("04.00", new String[]{"03:30", "04:30"});
            put("04.15", new String[]{"03:45", "04:45"});
            put("04.30", new String[]{"04:00", "05:00"});
            put("04.45", new String[]{"04:15", "05:15"});
            put("05.00", new String[]{"04:30", "05:30"});
            put("05.15", new String[]{"04:45", "05:45"});
            put("05.30", new String[]{"05:00", "06:00"});
            put("05.45", new String[]{"05:15", "06:15"});
            put("06.00", new String[]{"05:30", "06:30"});
            put("06.15", new String[]{"05:45", "06:45"});
            put("06.30", new String[]{"06:00", "07:00"});
            put("06.45", new String[]{"06:15", "07:15"});
            put("07.00", new String[]{"06:30", "07:30"});
            put("07.15", new String[]{"06:45", "07:45"});
            put("07.30", new String[]{"07:00", "08:00"});
            put("07.45", new String[]{"07:15", "08:15"});
            put("08.00", new String[]{"07:30", "08:30"});
            put("08.15", new String[]{"07:45", "08:45"});
            put("08.30", new String[]{"08:00", "09:00"});
            put("08.45", new String[]{"08:15", "09:15"});
            put("09.00", new String[]{"08:30", "09:30"});
            put("09.15", new String[]{"08:45", "09:45"});
            put("09.30", new String[]{"09:00", "10:00"});
            put("09.45", new String[]{"09:15", "10:15"});
            put("10.00", new String[]{"09:30", "10:30"});
            put("10.15", new String[]{"09:45", "10:45"});
            put("10.30", new String[]{"10:00", "11:00"});
            put("10.45", new String[]{"10:15", "11:15"});
            put("11.00", new String[]{"10:30", "11:30"});
            put("11.15", new String[]{"10:45", "11:45"});
            put("11.30", new String[]{"11:00", "12:00"});
            put("11.45", new String[]{"11:15", "12:15"});
            put("12.00", new String[]{"11:30", "12:30"});
            put("12.15", new String[]{"11:45", "12:45"});
            put("12.30", new String[]{"12:00", "13:00"});
            put("12.45", new String[]{"12:15", "13:15"});
            put("13.00", new String[]{"12:30", "13:30"});
            put("13.15", new String[]{"12:45", "13:45"});
            put("13.30", new String[]{"13:00", "14:00"});
            put("13.45", new String[]{"13:15", "14:15"});
            put("14.00", new String[]{"13:30", "14:30"});
            put("14.15", new String[]{"13:45", "14:45"});
            put("14.30", new String[]{"14:00", "15:00"});
            put("14.45", new String[]{"14:15", "15:15"});
            put("15.00", new String[]{"14:30", "15:30"});
            put("15.15", new String[]{"14:45", "15:45"});
            put("15.30", new String[]{"15:00", "16:00"});
            put("15.45", new String[]{"15:15", "16:15"});
            put("16.00", new String[]{"15:30", "16:30"});
            put("16.15", new String[]{"15:45", "16:45"});
            put("16.30", new String[]{"16:00", "17:00"});
            put("16.45", new String[]{"16:15", "17:15"});
            put("17.00", new String[]{"16:30", "17:30"});
            put("17.15", new String[]{"16:45", "17:45"});
            put("17.30", new String[]{"17:00", "18:00"});
            put("17.45", new String[]{"17:15", "18:15"});
            put("18.00", new String[]{"17:30", "18:30"});
            put("18.15", new String[]{"17:45", "18:45"});
            put("18.30", new String[]{"18:00", "19:00"});
            put("18.45", new String[]{"18:15", "19:15"});
            put("19.00", new String[]{"18:30", "19:30"});
            put("19.15", new String[]{"18:45", "19:45"});
            put("19.30", new String[]{"19:00", "20:00"});
            put("19.45", new String[]{"19:15", "20:15"});
            put("20.00", new String[]{"19:30", "20:30"});
            put("20.15", new String[]{"19:45", "20:45"});
            put("20.30", new String[]{"20:00", "21:00"});
            put("20.45", new String[]{"20:15", "21:15"});
            put("21.00", new String[]{"20:30", "21:30"});
            put("21.15", new String[]{"20:45", "21:45"});
            put("21.30", new String[]{"21:00", "22:00"});
            put("21.45", new String[]{"21:15", "22:15"});
            put("22.00", new String[]{"21:30", "22:30"});
            put("22.15", new String[]{"21:45", "22:45"});
            put("22.30", new String[]{"22:00", "23:00"});
            put("22.45", new String[]{"22:15", "23:15"});
            put("23.00", new String[]{"22:30", "23:30"});
            put("23.15", new String[]{"22:45", "23:45"});
            put("23.30", new String[]{"23:00", "00:00"});
            put("23.45", new String[]{"23:15", "00:15"});

            put("преди 23", new String[]{"18:00", "23:00"});
            put("преди 22", new String[]{"17:00", "22:00"});
            put("преди 21", new String[]{"16:00", "21:00"});
            put("преди 20", new String[]{"15:00", "20:00"});
            put("преди 19", new String[]{"14:00", "19:00"});
            put("преди 18", new String[]{"13:00", "18:00"});
            put("преди 17", new String[]{"12:00", "17:00"});
            put("преди 16", new String[]{"11:00", "16:00"});
            put("преди 15", new String[]{"10:00", "15:00"});
            put("преди 14", new String[]{"09:00", "14:00"});
            put("преди 13", new String[]{"08:00", "13:00"});
            put("преди 12", new String[]{"07:00", "12:00"});
            put("преди 11", new String[]{"06:00", "11:00"});
            put("преди 10", new String[]{"05:00", "10:00"});
            put("преди 9", new String[]{"04:00", "09:00"});
            put("преди 8", new String[]{"03:00", "08:00"});
            put("преди 7", new String[]{"02:00", "07:00"});
            put("преди 6", new String[]{"01:00", "06:00"});
            put("преди 5", new String[]{"00:00", "05:00"});
            put("преди 4", new String[]{"23:00", "04:00"});
            put("преди 3", new String[]{"22:00", "03:00"});
            put("преди 2", new String[]{"21:00", "02:00"});
            put("преди 1", new String[]{"20:00", "01:00"});

            put("след 23", new String[]{"23:00", "04:00"});
            put("след 22", new String[]{"22:00", "03:00"});
            put("след 21", new String[]{"21:00", "02:00"});
            put("след 20", new String[]{"20:00", "01:00"});
            put("след 19", new String[]{"19:00", "00:00"});
            put("след 18", new String[]{"18:00", "23:00"});
            put("след 17", new String[]{"17:00", "22:00"});
            put("след 16", new String[]{"16:00", "21:00"});
            put("след 15", new String[]{"15:00", "20:00"});
            put("след 14", new String[]{"14:00", "19:00"});
            put("след 13", new String[]{"13:00", "18:00"});
            put("след 12", new String[]{"12:00", "17:00"});
            put("след 11", new String[]{"11:00", "16:00"});
            put("след 10", new String[]{"10:00", "15:00"});
            put("след 9", new String[]{"09:00", "14:00"});
            put("след 8", new String[]{"08:00", "13:00"});
            put("след 7", new String[]{"07:00", "12:00"});
            put("след 6", new String[]{"06:00", "11:00"});
            put("след 5", new String[]{"05:00", "10:00"});
            put("след 4", new String[]{"04:00", "09:00"});
            put("след 3", new String[]{"03:00", "08:00"});
            put("след 2", new String[]{"02:00", "07:00"});
            put("след 1", new String[]{"01:00", "06:00"});

            put("в 23", new String[]{"22:00", "00:00"});
            put("в 22", new String[]{"21:00", "23:00"});
            put("в 21", new String[]{"20:00", "22:00"});
            put("в 20", new String[]{"19:00", "21:00"});
            put("в 19", new String[]{"18:00", "20:00"});
            put("в 18", new String[]{"17:00", "19:00"});
            put("в 17", new String[]{"16:00", "18:00"});
            put("в 16", new String[]{"15:00", "17:00"});
            put("в 15", new String[]{"14:00", "16:00"});
            put("в 14", new String[]{"13:00", "15:00"});
            put("в 13", new String[]{"12:00", "14:00"});
            put("в 12", new String[]{"11:00", "13:00"});
            put("в 11", new String[]{"10:00", "12:00"});
            put("в 10", new String[]{"09:00", "11:00"});
            put("в 9", new String[]{"08:00", "10:00"});
            put("в 8", new String[]{"07:00", "09:00"});
            put("в 7", new String[]{"06:00", "08:00"});
            put("в 6", new String[]{"05:00", "07:00"});
            put("в 5", new String[]{"04:00", "06:00"});
            put("в 4", new String[]{"03:00", "05:00"});
            put("в 3", new String[]{"02:00", "04:00"});
            put("в 2", new String[]{"01:00", "03:00"});
            put("в 1", new String[]{"00:00", "02:00"});

            put("около 23", new String[]{"22:00", "00:00"});
            put("около 22", new String[]{"21:00", "23:00"});
            put("около 21", new String[]{"20:00", "22:00"});
            put("около 20", new String[]{"19:00", "21:00"});
            put("около 19", new String[]{"18:00", "20:00"});
            put("около 18", new String[]{"17:00", "19:00"});
            put("около 17", new String[]{"16:00", "18:00"});
            put("около 16", new String[]{"15:00", "17:00"});
            put("около 15", new String[]{"14:00", "16:00"});
            put("около 14", new String[]{"13:00", "15:00"});
            put("около 13", new String[]{"12:00", "14:00"});
            put("около 12", new String[]{"11:00", "13:00"});
            put("около 11", new String[]{"10:00", "12:00"});
            put("около 10", new String[]{"09:00", "11:00"});
            put("около 9", new String[]{"08:00", "10:00"});
            put("около 8", new String[]{"07:00", "09:00"});
            put("около 7", new String[]{"06:00", "08:00"});
            put("около 6", new String[]{"05:00", "07:00"});
            put("около 5", new String[]{"04:00", "06:00"});
            put("около 4", new String[]{"03:00", "05:00"});
            put("около 3", new String[]{"02:00", "04:00"});
            put("около 2", new String[]{"01:00", "03:00"});
            put("около 1", new String[]{"00:00", "02:00"});

            put("към 23", new String[]{"22:00", "00:00"});
            put("към 22", new String[]{"21:00", "23:00"});
            put("към 21", new String[]{"20:00", "22:00"});
            put("към 20", new String[]{"19:00", "21:00"});
            put("към 19", new String[]{"18:00", "20:00"});
            put("към 18", new String[]{"17:00", "19:00"});
            put("към 17", new String[]{"16:00", "18:00"});
            put("към 16", new String[]{"15:00", "17:00"});
            put("към 15", new String[]{"14:00", "16:00"});
            put("към 14", new String[]{"13:00", "15:00"});
            put("към 13", new String[]{"12:00", "14:00"});
            put("към 12", new String[]{"11:00", "13:00"});
            put("към 11", new String[]{"10:00", "12:00"});
            put("към 10", new String[]{"09:00", "11:00"});
            put("към 9", new String[]{"08:00", "10:00"});
            put("към 8", new String[]{"07:00", "09:00"});
            put("към 7", new String[]{"06:00", "08:00"});
            put("към 6", new String[]{"05:00", "07:00"});
            put("към 5", new String[]{"04:00", "06:00"});
            put("към 4", new String[]{"03:00", "05:00"});
            put("към 3", new String[]{"02:00", "04:00"});
            put("към 2", new String[]{"01:00", "03:00"});
            put("към 1", new String[]{"00:00", "02:00"});

            put("23ч", new String[]{"22:00", "00:00"});
            put("22ч", new String[]{"21:00", "23:00"});
            put("21ч", new String[]{"20:00", "22:00"});
            put("20ч", new String[]{"19:00", "21:00"});
            put("19ч", new String[]{"18:00", "20:00"});
            put("18ч", new String[]{"17:00", "19:00"});
            put("17ч", new String[]{"16:00", "18:00"});
            put("16ч", new String[]{"15:00", "17:00"});
            put("15ч", new String[]{"14:00", "16:00"});
            put("14ч", new String[]{"13:00", "15:00"});
            put("13ч", new String[]{"12:00", "14:00"});
            put("12ч", new String[]{"11:00", "13:00"});
            put("11ч", new String[]{"10:00", "12:00"});
            put("10ч", new String[]{"09:00", "11:00"});
            put("9ч", new String[]{"08:00", "10:00"});
            put("8ч", new String[]{"07:00", "09:00"});
            put("7ч", new String[]{"06:00", "08:00"});
            put("6ч", new String[]{"05:00", "07:00"});
            put("5ч", new String[]{"04:00", "06:00"});
            put("4ч", new String[]{"03:00", "05:00"});
            put("3ч", new String[]{"02:00", "04:00"});
            put("2ч", new String[]{"01:00", "03:00"});
            put("1ч", new String[]{"00:00", "02:00"});

            put("23 ч", new String[]{"22:00", "00:00"});
            put("22 ч", new String[]{"21:00", "23:00"});
            put("21 ч", new String[]{"20:00", "22:00"});
            put("20 ч", new String[]{"19:00", "21:00"});
            put("19 ч", new String[]{"18:00", "20:00"});
            put("18 ч", new String[]{"17:00", "19:00"});
            put("17 ч", new String[]{"16:00", "18:00"});
            put("16 ч", new String[]{"15:00", "17:00"});
            put("15 ч", new String[]{"14:00", "16:00"});
            put("14 ч", new String[]{"13:00", "15:00"});
            put("13 ч", new String[]{"12:00", "14:00"});
            put("12 ч", new String[]{"11:00", "13:00"});
            put("11 ч", new String[]{"10:00", "12:00"});
            put("10 ч", new String[]{"09:00", "11:00"});

            put("9 ч", new String[]{"08:00", "10:00"});
            put("8 ч", new String[]{"07:00", "09:00"});
            put("7 ч", new String[]{"06:00", "08:00"});
            put("6 ч", new String[]{"05:00", "07:00"});
            put("5 ч", new String[]{"04:00", "06:00"});
            put("4 ч", new String[]{"03:00", "05:00"});
            put("3 ч", new String[]{"02:00", "04:00"});
            put("2 ч", new String[]{"01:00", "03:00"});
            put("1 ч", new String[]{"00:00", "02:00"});

        }
    };


    public static List<String> hourAndMinutes = Arrays.asList(
            "00:00ч",
            "00:15ч",
            "00:30ч",
            "00:45ч",

            "01:00ч",
            "01:15ч",
            "01:30ч",
            "01:45ч",

            "02:00ч",
            "02:15ч",
            "02:30ч",
            "02:45ч",

            "03:00ч",
            "03:15ч",
            "03:30ч",
            "03:45ч",

            "04:00ч",
            "04:15ч",
            "04:30ч",
            "04:45ч",

            "05:00ч",
            "05:15ч",
            "05:30ч",
            "05:45ч",

            "06:00ч",
            "06:15ч",
            "06:30ч",
            "06:45ч",

            "07:00ч",
            "07:15ч",
            "07:30ч",
            "07:45ч",

            "08:00ч",
            "08:15ч",
            "08:30ч",
            "08:45ч",

            "09:00ч",
            "09:15ч",
            "09:30ч",
            "09:45ч",

            "10:00ч",
            "10:15ч",
            "10:30ч",
            "10:45ч",


            "11:00ч",
            "11:15ч",
            "11:30ч",
            "11:45ч",

            "12:00ч",
            "12:15ч",
            "12:30ч",
            "12:45ч",


            "13:00ч",
            "13:15ч",
            "13:30ч",
            "13:45ч",


            "14:00ч",
            "14:15ч",
            "14:30ч",
            "14:45ч",


            "15:00ч",
            "15:15ч",
            "15:30ч",
            "15:45ч",


            "16:00ч",
            "16:15ч",
            "16:30ч",
            "16:45ч",

            "17:00ч",
            "17:15ч",
            "17:30ч",
            "17:45ч",

            "18:00ч",
            "18:15ч",
            "18:30ч",
            "18:45ч",

            "19:00ч",
            "19:15ч",
            "19:30ч",
            "19:45ч",

            "20:00ч",
            "20:15ч",
            "20:30ч",
            "20:45ч",

            "21:00ч",
            "21:15ч",
            "21:30ч",
            "21:45ч",

            "22:00ч",
            "22:15ч",
            "22:30ч",
            "22:45ч",

            "23:00ч",
            "23:15ч",
            "23:30ч",
            "23:45ч",

            "00:00",
            "00:15",
            "00:30",
            "00:45",

            "01:00",
            "01:15",
            "01:30",
            "01:45",

            "02:00",
            "02:15",
            "02:30",
            "02:45",

            "03:00",
            "03:15",
            "03:30",
            "03:45",

            "04:00",
            "04:15",
            "04:30",
            "04:45",


            "05:00",
            "05:15",
            "05:30",
            "05:45",


            "06:00",
            "06:15",
            "06:30",
            "06:45",


            "07:00",
            "07:15",
            "07:30",
            "07:45",


            "08:00",
            "08:15",
            "08:30",
            "08:45",


            "09:00",
            "09:15",
            "09:30",
            "09:45",


            "10:00",
            "10:15",
            "10:30",
            "10:45",


            "11:00",
            "11:15",
            "11:30",
            "11:45",


            "12:00",
            "12:15",
            "12:30",
            "12:45",


            "13:00",
            "13:15",
            "13:30",
            "13:45",


            "14:00",
            "14:15",
            "14:30",
            "14:45",


            "15:00",
            "15:15",
            "15:30",
            "15:45",


            "16:00",
            "16:15",
            "16:30",
            "16:45",

            "17:00",
            "17:15",
            "17:30",
            "17:45",

            "18:00",
            "18:15",
            "18:30",
            "18:45",

            "19:00",
            "19:15",
            "19:30",
            "19:45",

            "20:00",
            "20:15",
            "20:30",
            "20:45",

            "21:00",
            "21:15",
            "21:30",
            "21:45",

            "22:00",
            "22:15",
            "22:30",
            "22:45",

            "23:00",
            "23:15",
            "23:30",
            "23:45",


            "00.00ч",
            "00.15ч",
            "00.30ч",
            "00.45ч",

            "01.00ч",
            "01.15ч",
            "01.30ч",
            "01.45ч",

            "02.00ч",
            "02.15ч",
            "02.30ч",
            "02.45ч",

            "03.00ч",
            "03.15ч",
            "03.30ч",
            "03.45ч",

            "04.00ч",
            "04.15ч",
            "04.30ч",
            "04.45ч",


            "05.00ч",
            "05.15ч",
            "05.30ч",
            "05.45ч",


            "06.00ч",
            "06.15ч",
            "06.30ч",
            "06.45ч",


            "07.00ч",
            "07.15ч",
            "07.30ч",
            "07.45ч",


            "08.00ч",
            "08.15ч",
            "08.30ч",
            "08.45ч",


            "09.00ч",
            "09.15ч",
            "09.30ч",
            "09.45ч",


            "10.00ч",
            "10.15ч",
            "10.30ч",
            "10.45ч",


            "11.00ч",
            "11.15ч",
            "11.30ч",
            "11.45ч",


            "12.00ч",
            "12.15ч",
            "12.30ч",
            "12.45ч",


            "13.00ч",
            "13.15ч",
            "13.30ч",
            "13.45ч",


            "14.00ч",
            "14.15ч",
            "14.30ч",
            "14.45ч",


            "15.00ч",
            "15.15ч",
            "15.30ч",
            "15.45ч",


            "16.00ч",
            "16.15ч",
            "16.30ч",
            "16.45ч",

            "17.00ч",
            "17.15ч",
            "17.30ч",
            "17.45ч",

            "18.00ч",
            "18.15ч",
            "18.30ч",
            "18.45ч",

            "19.00ч",
            "19.15ч",
            "19.30ч",
            "19.45ч",

            "20.00ч",
            "20.15ч",
            "20.30ч",
            "20.45ч",

            "21.00ч",
            "21.15ч",
            "21.30ч",
            "21.45ч",

            "22.00ч",
            "22.15ч",
            "22.30ч",
            "22.45ч",

            "23.00ч",
            "23.15ч",
            "23.30ч",
            "23.45ч",

            "00.00",
            "00.15",
            "00.30",
            "00.45",

            "01.00",
            "01.15",
            "01.30",
            "01.45",

            "02.00",
            "02.15",
            "02.30",
            "02.45",

            "03.00",
            "03.15",
            "03.30",
            "03.45",

            "04.00",
            "04.15",
            "04.30",
            "04.45",


            "05.00",
            "05.15",
            "05.30",
            "05.45",


            "06.00",
            "06.15",
            "06.30",
            "06.45",


            "07.00",
            "07.15",
            "07.30",
            "07.45",


            "08.00",
            "08.15",
            "08.30",
            "08.45",


            "09.00",
            "09.15",
            "09.30",
            "09.45",


            "10.00",
            "10.15",
            "10.30",
            "10.45",


            "11.00",
            "11.15",
            "11.30",
            "11.45",


            "12.00",
            "12.15",
            "12.30",
            "12.45",

            "13.00",
            "13.15",
            "13.30",
            "13.45",

            "14.00",
            "14.15",
            "14.30",
            "14.45",


            "15.00",
            "15.15",
            "15.30",
            "15.45",


            "16.00",
            "16.15",
            "16.30",
            "16.45",

            "17.00",
            "17.15",
            "17.30",
            "17.45",

            "18.00",
            "18.15",
            "18.30",
            "18.45",

            "19.00",
            "19.15",
            "19.30",
            "19.45",

            "20.00",
            "20.15",
            "20.30",
            "20.45",

            "21.00",
            "21.15",
            "21.30",
            "21.45",

            "22.00",
            "22.15",
            "22.30",
            "22.45",

            "23.00",
            "23.15",
            "23.30",
            "23.45",

            "преди 23",
            "преди 22",
            "преди 21",
            "преди 20",
            "преди 19",
            "преди 18",
            "преди 17",
            "преди 16",
            "преди 15",
            "преди 14",
            "преди 13",
            "преди 12",
            "преди 11",
            "преди 10",
            "преди 9",
            "преди 8",
            "преди 7",
            "преди 6",
            "преди 5",
            "преди 4",
            "преди 3",
            "преди 2",
            "преди 1",


            "след 23",
            "след 22",
            "след 21",
            "след 20",
            "след 19",
            "след 18",
            "след 17",
            "след 16",
            "след 15",
            "след 14",
            "след 13",
            "след 12",
            "след 11",
            "след 10",
            "след 9",
            "след 8",
            "след 7",
            "след 6",
            "след 5",
            "след 4",
            "след 3",
            "след 2",
            "след 1",


            "в 23",
            "в 22",
            "в 21",
            "в 20",
            "в 19",
            "в 18",
            "в 17",
            "в 16",
            "в 15",
            "в 14",
            "в 13",
            "в 12",
            "в 11",
            "в 10",
            "в 9",
            "в 8",
            "в 7",
            "в 6",
            "в 5",
            "в 4",
            "в 3",
            "в 2",
            "в 1",

            "около 23",
            "около 22",
            "около 21",
            "около 20",
            "около 19",
            "около 18",
            "около 17",
            "около 16",
            "около 15",
            "около 14",
            "около 13",
            "около 12",
            "около 11",
            "около 10",
            "около 9",
            "около 8",
            "около 7",
            "около 6",
            "около 5",
            "около 4",
            "около 3",
            "около 2",
            "около 1",


            "към 23",
            "към 22",
            "към 21",
            "към 20",
            "към 19",
            "към 18",
            "към 17",
            "към 16",
            "към 15",
            "към 14",
            "към 13",
            "към 12",
            "към 11",
            "към 10",
            "към 9",
            "към 8",
            "към 7",
            "към 6",
            "към 5",
            "към 4",
            "към 3",
            "към 2",
            "към 1",


            "1ч",
            "2ч",
            "3ч",
            "4ч",
            "5ч",
            "6ч",
            "7ч",
            "8ч",
            "9ч",
            "10ч",
            "11ч",
            "12ч",
            "13ч",
            "14ч",
            "15ч",
            "16ч",
            "17ч",
            "18ч",
            "19ч",
            "20ч",
            "21ч",
            "22ч",
            "23ч",
            "24ч"
    );

    public static List<String> dates = Arrays.asList(

            "01.01",
            "01/01",
            "02.01",
            "02/01",
            "03.01",
            "03/01",
            "04.01",
            "04/01",
            "05.01",
            "05/01",
            "06.01",
            "06/01",
            "07.01",
            "07/01",
            "08.01",
            "08/01",
            "09.01",
            "09/01",
            "10.01",
            "10/01",
            "11.01",
            "11/01",
            "12.01",
            "12/01",
            "13.01",
            "13/01",
            "14.01",
            "14/01",
            "15.01",
            "15/01",
            "16.01",
            "16/01",
            "17.01",
            "17/01",
            "18.01",
            "18/01",
            "19.01",
            "19/01",
            "20.01",
            "20/01",
            "21.01",
            "21/01",
            "22.01",
            "22/01",
            "23.01",
            "23/01",
            "24.01",
            "24/01",
            "25.01",
            "25/01",
            "26.01",
            "26/01",
            "27.01",
            "27/01",
            "28.01",
            "28/01",
            "29.01",
            "29/01",
            "30.01",
            "30/01",
            "31.01",
            "31/01",
            "01.02",
            "01/02",
            "02.02",
            "02/02",
            "03.02",
            "03/02",
            "04.02",
            "04/02",
            "05.02",
            "05/02",
            "06.02",
            "06/02",
            "07.02",
            "07/02",
            "08.02",
            "08/02",
            "09.02",
            "09/02",
            "10.02",
            "10/02",
            "11.02",
            "11/02",
            "12.02",
            "12/02",
            "13.02",
            "13/02",
            "14.02",
            "14/02",
            "15.02",
            "15/02",
            "16.02",
            "16/02",
            "17.02",
            "17/02",
            "18.02",
            "18/02",
            "19.02",
            "19/02",
            "20.02",
            "20/02",
            "21.02",
            "21/02",
            "22.02",
            "22/02",
            "23.02",
            "23/02",
            "24.02",
            "24/02",
            "25.02",
            "25/02",
            "26.02",
            "26/02",
            "27.02",
            "27/02",
            "28.02",
            "28/02",
            "01.03",
            "01/03",
            "02.03",
            "02/03",
            "03.03",
            "03/03",
            "04.03",
            "04/03",
            "05.03",
            "05/03",
            "06.03",
            "06/03",
            "07.03",
            "07/03",
            "08.03",
            "08/03",
            "09.03",
            "09/03",
            "10.03",
            "10/03",
            "11.03",
            "11/03",
            "12.03",
            "12/03",
            "13.03",
            "13/03",
            "14.03",
            "14/03",
            "15.03",
            "15/03",
            "16.03",
            "16/03",
            "17.03",
            "17/03",
            "18.03",
            "18/03",
            "19.03",
            "19/03",
            "20.03",
            "20/03",
            "21.03",
            "21/03",
            "22.03",
            "22/03",
            "23.03",
            "23/03",
            "24.03",
            "24/03",
            "25.03",
            "25/03",
            "26.03",
            "26/03",
            "27.03",
            "27/03",
            "28.03",
            "28/03",
            "29.03",
            "29/03",
            "30.03",
            "30/03",
            "31.03",
            "31/03",
            "01.04",
            "01/04",
            "02.04",
            "02/04",
            "03.04",
            "03/04",
            "04.04",
            "04/04",
            "05.04",
            "05/04",
            "06.04",
            "06/04",
            "07.04",
            "07/04",
            "08.04",
            "08/04",
            "09.04",
            "09/04",
            "10.04",
            "10/04",
            "11.04",
            "11/04",
            "12.04",
            "12/04",
            "13.04",
            "13/04",
            "14.04",
            "14/04",
            "15.04",
            "15/04",
            "16.04",
            "16/04",
            "17.04",
            "17/04",
            "18.04",
            "18/04",
            "19.04",
            "19/04",
            "20.04",
            "20/04",
            "21.04",
            "21/04",
            "22.04",
            "22/04",
            "23.04",
            "23/04",
            "24.04",
            "24/04",
            "25.04",
            "25/04",
            "26.04",
            "26/04",
            "27.04",
            "27/04",
            "28.04",
            "28/04",
            "29.04",
            "29/04",
            "30.04",
            "30/04",
            "01.05",
            "01/05",
            "02.05",
            "02/05",
            "03.05",
            "03/05",
            "04.05",
            "04/05",
            "05.05",
            "05/05",
            "06.05",
            "06/05",
            "07.05",
            "07/05",
            "08.05",
            "08/05",
            "09.05",
            "09/05",
            "10.05",
            "10/05",
            "11.05",
            "11/05",
            "12.05",
            "12/05",
            "13.05",
            "13/05",
            "14.05",
            "14/05",
            "15.05",
            "15/05",
            "16.05",
            "16/05",
            "17.05",
            "17/05",
            "18.05",
            "18/05",
            "19.05",
            "19/05",
            "20.05",
            "20/05",
            "21.05",
            "21/05",
            "22.05",
            "22/05",
            "23.05",
            "23/05",
            "24.05",
            "24/05",
            "25.05",
            "25/05",
            "26.05",
            "26/05",
            "27.05",
            "27/05",
            "28.05",
            "28/05",
            "29.05",
            "29/05",
            "30.05",
            "30/05",
            "31.05",
            "31/05",
            "01.06",
            "01/06",
            "02.06",
            "02/06",
            "03.06",
            "03/06",
            "04.06",
            "04/06",
            "05.06",
            "05/06",
            "06.06",
            "06/06",
            "07.06",
            "07/06",
            "08.06",
            "08/06",
            "09.06",
            "09/06",
            "10.06",
            "10/06",
            "11.06",
            "11/06",
            "12.06",
            "12/06",
            "13.06",
            "13/06",
            "14.06",
            "14/06",
            "15.06",
            "15/06",
            "16.06",
            "16/06",
            "17.06",
            "17/06",
            "18.06",
            "18/06",
            "19.06",
            "19/06",
            "20.06",
            "20/06",
            "21.06",
            "21/06",
            "22.06",
            "22/06",
            "23.06",
            "23/06",
            "24.06",
            "24/06",
            "25.06",
            "25/06",
            "26.06",
            "26/06",
            "27.06",
            "27/06",
            "28.06",
            "28/06",
            "29.06",
            "29/06",
            "30.06",
            "30/06",
            "01.07",
            "01/07",
            "02.07",
            "02/07",
            "03.07",
            "03/07",
            "04.07",
            "04/07",
            "05.07",
            "05/07",
            "06.07",
            "06/07",
            "07.07",
            "07/07",
            "08.07",
            "08/07",
            "09.07",
            "09/07",
            "10.07",
            "10/07",
            "11.07",
            "11/07",
            "12.07",
            "12/07",
            "13.07",
            "13/07",
            "14.07",
            "14/07",
            "15.07",
            "15/07",
            "16.07",
            "16/07",
            "17.07",
            "17/07",
            "18.07",
            "18/07",
            "19.07",
            "19/07",
            "20.07",
            "20/07",
            "21.07",
            "21/07",
            "22.07",
            "22/07",
            "23.07",
            "23/07",
            "24.07",
            "24/07",
            "25.07",
            "25/07",
            "26.07",
            "26/07",
            "27.07",
            "27/07",
            "28.07",
            "28/07",
            "29.07",
            "29/07",
            "30.07",
            "30/07",
            "31.07",
            "31/07",
            "01.08",
            "01/08",
            "02.08",
            "02/08",
            "03.08",
            "03/08",
            "04.08",
            "04/08",
            "05.08",
            "05/08",
            "06.08",
            "06/08",
            "07.08",
            "07/08",
            "08.08",
            "08/08",
            "09.08",
            "09/08",
            "10.08",
            "10/08",
            "11.08",
            "11/08",
            "12.08",
            "12/08",
            "13.08",
            "13/08",
            "14.08",
            "14/08",
            "15.08",
            "15/08",
            "16.08",
            "16/08",
            "17.08",
            "17/08",
            "18.08",
            "18/08",
            "19.08",
            "19/08",
            "20.08",
            "20/08",
            "21.08",
            "21/08",
            "22.08",
            "22/08",
            "23.08",
            "23/08",
            "24.08",
            "24/08",
            "25.08",
            "25/08",
            "26.08",
            "26/08",
            "27.08",
            "27/08",
            "28.08",
            "28/08",
            "29.08",
            "29/08",
            "30.08",
            "30/08",
            "31.08",
            "31/08",
            "01.09",
            "01/09",
            "02.09",
            "02/09",
            "03.09",
            "03/09",
            "04.09",
            "04/09",
            "05.09",
            "05/09",
            "06.09",
            "06/09",
            "07.09",
            "07/09",
            "08.09",
            "08/09",
            "09.09",
            "09/09",
            "10.09",
            "10/09",
            "11.09",
            "11/09",
            "12.09",
            "12/09",
            "13.09",
            "13/09",
            "14.09",
            "14/09",
            "15.09",
            "15/09",
            "16.09",
            "16/09",
            "17.09",
            "17/09",
            "18.09",
            "18/09",
            "19.09",
            "19/09",
            "20.09",
            "20/09",
            "21.09",
            "21/09",
            "22.09",
            "22/09",
            "23.09",
            "23/09",
            "24.09",
            "24/09",
            "25.09",
            "25/09",
            "26.09",
            "26/09",
            "27.09",
            "27/09",
            "28.09",
            "28/09",
            "29.09",
            "29/09",
            "30.09",
            "30/09",
            "01.10",
            "01/10",
            "02.10",
            "02/10",
            "03.10",
            "03/10",
            "04.10",
            "04/10",
            "05.10",
            "05/10",
            "06.10",
            "06/10",
            "07.10",
            "07/10",
            "08.10",
            "08/10",
            "09.10",
            "09/10",
            "10.10",
            "10/10",
            "11.10",
            "11/10",
            "12.10",
            "12/10",
            "13.10",
            "13/10",
            "14.10",
            "14/10",
            "15.10",
            "15/10",
            "16.10",
            "16/10",
            "17.10",
            "17/10",
            "18.10",
            "18/10",
            "19.10",
            "19/10",
            "20.10",
            "20/10",
            "21.10",
            "21/10",
            "22.10",
            "22/10",
            "23.10",
            "23/10",
            "24.10",
            "24/10",
            "25.10",
            "25/10",
            "26.10",
            "26/10",
            "27.10",
            "27/10",
            "28.10",
            "28/10",
            "29.10",
            "29/10",
            "30.10",
            "30/10",
            "31.10",
            "31/10",
            "01.11",
            "01/11",
            "02.11",
            "02/11",
            "03.11",
            "03/11",
            "04.11",
            "04/11",
            "05.11",
            "05/11",
            "06.11",
            "06/11",
            "07.11",
            "07/11",
            "08.11",
            "08/11",
            "09.11",
            "09/11",
            "10.11",
            "10/11",
            "11.11",
            "11/11",
            "12.11",
            "12/11",
            "13.11",
            "13/11",
            "14.11",
            "14/11",
            "15.11",
            "15/11",
            "16.11",
            "16/11",
            "17.11",
            "17/11",
            "18.11",
            "18/11",
            "19.11",
            "19/11",
            "20.11",
            "20/11",
            "21.11",
            "21/11",
            "22.11",
            "22/11",
            "23.11",
            "23/11",
            "24.11",
            "24/11",
            "25.11",
            "25/11",
            "26.11",
            "26/11",
            "27.11",
            "27/11",
            "28.11",
            "28/11",
            "29.11",
            "29/11",
            "30.11",
            "30/11",
            "01.12",
            "01/12",
            "02.12",
            "02/12",
            "03.12",
            "03/12",
            "04.12",
            "04/12",
            "05.12",
            "05/12",
            "06.12",
            "06/12",
            "07.12",
            "07/12",
            "08.12",
            "08/12",
            "09.12",
            "09/12",
            "10.12",
            "10/12",
            "11.12",
            "11/12",
            "12.12",
            "12/12",
            "13.12",
            "13/12",
            "14.12",
            "14/12",
            "15.12",
            "15/12",
            "16.12",
            "16/12",
            "17.12",
            "17/12",
            "18.12",
            "18/12",
            "19.12",
            "19/12",
            "20.12",
            "20/12",
            "21.12",
            "21/12",
            "22.12",
            "22/12",
            "23.12",
            "23/12",
            "24.12",
            "24/12",
            "25.12",
            "25/12",
            "26.12",
            "26/12",
            "27.12",
            "27/12",
            "28.12",
            "28/12",
            "29.12",
            "29/12",
            "30.12",
            "30/12",


            "1/01",
            "1.01",
            "2/01",
            "2.01",
            "3/01",
            "3.01",
            "4/01",
            "4.01",
            "5/01",
            "5.01",
            "6/01",
            "6.01",
            "7/01",
            "7.01",
            "8/01",
            "8.01",
            "9/01",
            "9.01",
            "10/01",
            "10.01",
            "11/01",
            "11.01",
            "12/01",
            "12.01",
            "13/01",
            "13.01",
            "14/01",
            "14.01",
            "15/01",
            "15.01",
            "16/01",
            "16.01",
            "17/01",
            "17.01",
            "18/01",
            "18.01",
            "19/01",
            "19.01",
            "20/01",
            "20.01",
            "21/01",
            "21.01",
            "22/01",
            "22.01",
            "23/01",
            "23.01",
            "24/01",
            "24.01",
            "25/01",
            "25.01",
            "26/01",
            "26.01",
            "27/01",
            "27.01",
            "28/01",
            "28.01",
            "29/01",
            "29.01",
            "30/01",
            "30.01",
            "31/01",
            "31.01",
            "1/02",
            "1.02",
            "2/02",
            "2.02",
            "3/02",
            "3.02",
            "4/02",
            "4.02",
            "5/02",
            "5.02",
            "6/02",
            "6.02",
            "7/02",
            "7.02",
            "8/02",
            "8.02",
            "9/02",
            "9.02",
            "10/02",
            "10.02",
            "11/02",
            "11.02",
            "12/02",
            "12.02",
            "13/02",
            "13.02",
            "14/02",
            "14.02",
            "15/02",
            "15.02",
            "16/02",
            "16.02",
            "17/02",
            "17.02",
            "18/02",
            "18.02",
            "19/02",
            "19.02",
            "20/02",
            "20.02",
            "21/02",
            "21.02",
            "22/02",
            "22.02",
            "23/02",
            "23.02",
            "24/02",
            "24.02",
            "25/02",
            "25.02",
            "26/02",
            "26.02",
            "27/02",
            "27.02",
            "28/02",
            "28.02",
            "1/03",
            "1.03",
            "2/03",
            "2.03",
            "3/03",
            "3.03",
            "4/03",
            "4.03",
            "5/03",
            "5.03",
            "6/03",
            "6.03",
            "7/03",
            "7.03",
            "8/03",
            "8.03",
            "9/03",
            "9.03",
            "10/03",
            "10.03",
            "11/03",
            "11.03",
            "12/03",
            "12.03",
            "13/03",
            "13.03",
            "14/03",
            "14.03",
            "15/03",
            "15.03",
            "16/03",
            "16.03",
            "17/03",
            "17.03",
            "18/03",
            "18.03",
            "19/03",
            "19.03",
            "20/03",
            "20.03",
            "21/03",
            "21.03",
            "22/03",
            "22.03",
            "23/03",
            "23.03",
            "24/03",
            "24.03",
            "25/03",
            "25.03",
            "26/03",
            "26.03",
            "27/03",
            "27.03",
            "28/03",
            "28.03",
            "29/03",
            "29.03",
            "30/03",
            "30.03",
            "31/03",
            "31.03",
            "1/04",
            "1.04",
            "2/04",
            "2.04",
            "3/04",
            "3.04",
            "4/04",
            "4.04",
            "5/04",
            "5.04",
            "6/04",
            "6.04",
            "7/04",
            "7.04",
            "8/04",
            "8.04",
            "9/04",
            "9.04",
            "10/04",
            "10.04",
            "11/04",
            "11.04",
            "12/04",
            "12.04",
            "13/04",
            "13.04",
            "14/04",
            "14.04",
            "15/04",
            "15.04",
            "16/04",
            "16.04",
            "17/04",
            "17.04",
            "18/04",
            "18.04",
            "19/04",
            "19.04",
            "20/04",
            "20.04",
            "21/04",
            "21.04",
            "22/04",
            "22.04",
            "23/04",
            "23.04",
            "24/04",
            "24.04",
            "25/04",
            "25.04",
            "26/04",
            "26.04",
            "27/04",
            "27.04",
            "28/04",
            "28.04",
            "29/04",
            "29.04",
            "30/04",
            "30.04",
            "1/05",
            "1.05",
            "2/05",
            "2.05",
            "3/05",
            "3.05",
            "4/05",
            "4.05",
            "5/05",
            "5.05",
            "6/05",
            "6.05",
            "7/05",
            "7.05",
            "8/05",
            "8.05",
            "9/05",
            "9.05",
            "10/05",
            "10.05",
            "11/05",
            "11.05",
            "12/05",
            "12.05",
            "13/05",
            "13.05",
            "14/05",
            "14.05",
            "15/05",
            "15.05",
            "16/05",
            "16.05",
            "17/05",
            "17.05",
            "18/05",
            "18.05",
            "19/05",
            "19.05",
            "20/05",
            "20.05",
            "21/05",
            "21.05",
            "22/05",
            "22.05",
            "23/05",
            "23.05",
            "24/05",
            "24.05",
            "25/05",
            "25.05",
            "26/05",
            "26.05",
            "27/05",
            "27.05",
            "28/05",
            "28.05",
            "29/05",
            "29.05",
            "30/05",
            "30.05",
            "31/05",
            "31.05",
            "1/06",
            "1.06",
            "2/06",
            "2.06",
            "3/06",
            "3.06",
            "4/06",
            "4.06",
            "5/06",
            "5.06",
            "6/06",
            "6.06",
            "7/06",
            "7.06",
            "8/06",
            "8.06",
            "9/06",
            "9.06",
            "10/06",
            "10.06",
            "11/06",
            "11.06",
            "12/06",
            "12.06",
            "13/06",
            "13.06",
            "14/06",
            "14.06",
            "15/06",
            "15.06",
            "16/06",
            "16.06",
            "17/06",
            "17.06",
            "18/06",
            "18.06",
            "19/06",
            "19.06",
            "20/06",
            "20.06",
            "21/06",
            "21.06",
            "22/06",
            "22.06",
            "23/06",
            "23.06",
            "24/06",
            "24.06",
            "25/06",
            "25.06",
            "26/06",
            "26.06",
            "27/06",
            "27.06",
            "28/06",
            "28.06",
            "29/06",
            "29.06",
            "30/06",
            "30.06",
            "1/07",
            "1.07",
            "2/07",
            "2.07",
            "3/07",
            "3.07",
            "4/07",
            "4.07",
            "5/07",
            "5.07",
            "6/07",
            "6.07",
            "7/07",
            "7.07",
            "8/07",
            "8.07",
            "9/07",
            "9.07",
            "10/07",
            "10.07",
            "11/07",
            "11.07",
            "12/07",
            "12.07",
            "13/07",
            "13.07",
            "14/07",
            "14.07",
            "15/07",
            "15.07",
            "16/07",
            "16.07",
            "17/07",
            "17.07",
            "18/07",
            "18.07",
            "19/07",
            "19.07",
            "20/07",
            "20.07",
            "21/07",
            "21.07",
            "22/07",
            "22.07",
            "23/07",
            "23.07",
            "24/07",
            "24.07",
            "25/07",
            "25.07",
            "26/07",
            "26.07",
            "27/07",
            "27.07",
            "28/07",
            "28.07",
            "29/07",
            "29.07",
            "30/07",
            "30.07",
            "31/07",
            "31.07",
            "1/08",
            "1.08",
            "2/08",
            "2.08",
            "3/08",
            "3.08",
            "4/08",
            "4.08",
            "5/08",
            "5.08",
            "6/08",
            "6.08",
            "7/08",
            "7.08",
            "8/08",
            "8.08",
            "9/08",
            "9.08",
            "10/08",
            "10.08",
            "11/08",
            "11.08",
            "12/08",
            "12.08",
            "13/08",
            "13.08",
            "14/08",
            "14.08",
            "15/08",
            "15.08",
            "16/08",
            "16.08",
            "17/08",
            "17.08",
            "18/08",
            "18.08",
            "19/08",
            "19.08",
            "20/08",
            "20.08",
            "21/08",
            "21.08",
            "22/08",
            "22.08",
            "23/08",
            "23.08",
            "24/08",
            "24.08",
            "25/08",
            "25.08",
            "26/08",
            "26.08",
            "27/08",
            "27.08",
            "28/08",
            "28.08",
            "29/08",
            "29.08",
            "30/08",
            "30.08",
            "31/08",
            "31.08",
            "1/09",
            "1.09",
            "2/09",
            "2.09",
            "3/09",
            "3.09",
            "4/09",
            "4.09",
            "5/09",
            "5.09",
            "6/09",
            "6.09",
            "7/09",
            "7.09",
            "8/09",
            "8.09",
            "9/09",
            "9.09",
            "10/09",
            "10.09",
            "11/09",
            "11.09",
            "12/09",
            "12.09",
            "13/09",
            "13.09",
            "14/09",
            "14.09",
            "15/09",
            "15.09",
            "16/09",
            "16.09",
            "17/09",
            "17.09",
            "18/09",
            "18.09",
            "19/09",
            "19.09",
            "20/09",
            "20.09",
            "21/09",
            "21.09",
            "22/09",
            "22.09",
            "23/09",
            "23.09",
            "24/09",
            "24.09",
            "25/09",
            "25.09",
            "26/09",
            "26.09",
            "27/09",
            "27.09",
            "28/09",
            "28.09",
            "29/09",
            "29.09",
            "30/09",
            "30.09",
            "1/10",
            "1.10",
            "2/10",
            "2.10",
            "3/10",
            "3.10",
            "4/10",
            "4.10",
            "5/10",
            "5.10",
            "6/10",
            "6.10",
            "7/10",
            "7.10",
            "8/10",
            "8.10",
            "9/10",
            "9.10",
            "10/10",
            "10.10",
            "11/10",
            "11.10",
            "12/10",
            "12.10",
            "13/10",
            "13.10",
            "14/10",
            "14.10",
            "15/10",
            "15.10",
            "16/10",
            "16.10",
            "17/10",
            "17.10",
            "18/10",
            "18.10",
            "19/10",
            "19.10",
            "20/10",
            "20.10",
            "21/10",
            "21.10",
            "22/10",
            "22.10",
            "23/10",
            "23.10",
            "24/10",
            "24.10",
            "25/10",
            "25.10",
            "26/10",
            "26.10",
            "27/10",
            "27.10",
            "28/10",
            "28.10",
            "29/10",
            "29.10",
            "30/10",
            "30.10",
            "31/10",
            "31.10",
            "1/11",
            "1.11",
            "2/11",
            "2.11",
            "3/11",
            "3.11",
            "4/11",
            "4.11",
            "5/11",
            "5.11",
            "6/11",
            "6.11",
            "7/11",
            "7.11",
            "8/11",
            "8.11",
            "9/11",
            "9.11",
            "10/11",
            "10.11",
            "11/11",
            "11.11",
            "12/11",
            "12.11",
            "13/11",
            "13.11",
            "14/11",
            "14.11",
            "15/11",
            "15.11",
            "16/11",
            "16.11",
            "17/11",
            "17.11",
            "18/11",
            "18.11",
            "19/11",
            "19.11",
            "20/11",
            "20.11",
            "21/11",
            "21.11",
            "22/11",
            "22.11",
            "23/11",
            "23.11",
            "24/11",
            "24.11",
            "25/11",
            "25.11",
            "26/11",
            "26.11",
            "27/11",
            "27.11",
            "28/11",
            "28.11",
            "29/11",
            "29.11",
            "30/11",
            "30.11",
            "1/12",
            "1.12",
            "2/12",
            "2.12",
            "3/12",
            "3.12",
            "4/12",
            "4.12",
            "5/12",
            "5.12",
            "6/12",
            "6.12",
            "7/12",
            "7.12",
            "8/12",
            "8.12",
            "9/12",
            "9.12",
            "10/12",
            "10.12",
            "11/12",
            "11.12",
            "12/12",
            "12.12",
            "13/12",
            "13.12",
            "14/12",
            "14.12",
            "15/12",
            "15.12",
            "16/12",
            "16.12",
            "17/12",
            "17.12",
            "18/12",
            "18.12",
            "19/12",
            "19.12",
            "20/12",
            "20.12",
            "21/12",
            "21.12",
            "22/12",
            "22.12",
            "23/12",
            "23.12",
            "24/12",
            "24.12",
            "25/12",
            "25.12",
            "26/12",
            "26.12",
            "27/12",
            "27.12",
            "28/12",
            "28.12",
            "29/12",
            "29.12",
            "30/12",
            "30.12",
            "01-01",
            "1-01",
            "02-01",
            "2-01",
            "03-01",
            "3-01",
            "04-01",
            "4-01",
            "05-01",
            "5-01",
            "06-01",
            "6-01",
            "07-01",
            "7-01",
            "08-01",
            "8-01",
            "09-01",
            "9-01",
            "10-01",
            "10-01",
            "11-01",
            "11-01",
            "12-01",
            "12-01",
            "13-01",
            "13-01",
            "14-01",
            "14-01",
            "15-01",
            "15-01",
            "16-01",
            "16-01",
            "17-01",
            "17-01",
            "18-01",
            "18-01",
            "19-01",
            "19-01",
            "20-01",
            "20-01",
            "21-01",
            "21-01",
            "22-01",
            "22-01",
            "23-01",
            "23-01",
            "24-01",
            "24-01",
            "25-01",
            "25-01",
            "26-01",
            "26-01",
            "27-01",
            "27-01",
            "28-01",
            "28-01",
            "29-01",
            "29-01",
            "30-01",
            "30-01",
            "31-01",
            "31-01",
            "01-02",
            "1-02",
            "02-02",
            "2-02",
            "03-02",
            "3-02",
            "04-02",
            "4-02",
            "05-02",
            "5-02",
            "06-02",
            "6-02",
            "07-02",
            "7-02",
            "08-02",
            "8-02",
            "09-02",
            "9-02",
            "10-02",
            "10-02",
            "11-02",
            "11-02",
            "12-02",
            "12-02",
            "13-02",
            "13-02",
            "14-02",
            "14-02",
            "15-02",
            "15-02",
            "16-02",
            "16-02",
            "17-02",
            "17-02",
            "18-02",
            "18-02",
            "19-02",
            "19-02",
            "20-02",
            "20-02",
            "21-02",
            "21-02",
            "22-02",
            "22-02",
            "23-02",
            "23-02",
            "24-02",
            "24-02",
            "25-02",
            "25-02",
            "26-02",
            "26-02",
            "27-02",
            "27-02",
            "28-02",
            "28-02",
            "01-03",
            "1-03",
            "02-03",
            "2-03",
            "03-03",
            "3-03",
            "04-03",
            "4-03",
            "05-03",
            "5-03",
            "06-03",
            "6-03",
            "07-03",
            "7-03",
            "08-03",
            "8-03",
            "09-03",
            "9-03",
            "10-03",
            "10-03",
            "11-03",
            "11-03",
            "12-03",
            "12-03",
            "13-03",
            "13-03",
            "14-03",
            "14-03",
            "15-03",
            "15-03",
            "16-03",
            "16-03",
            "17-03",
            "17-03",
            "18-03",
            "18-03",
            "19-03",
            "19-03",
            "20-03",
            "20-03",
            "21-03",
            "21-03",
            "22-03",
            "22-03",
            "23-03",
            "23-03",
            "24-03",
            "24-03",
            "25-03",
            "25-03",
            "26-03",
            "26-03",
            "27-03",
            "27-03",
            "28-03",
            "28-03",
            "29-03",
            "29-03",
            "30-03",
            "30-03",
            "31-03",
            "31-03",
            "01-04",
            "1-04",
            "02-04",
            "2-04",
            "03-04",
            "3-04",
            "04-04",
            "4-04",
            "05-04",
            "5-04",
            "06-04",
            "6-04",
            "07-04",
            "7-04",
            "08-04",
            "8-04",
            "09-04",
            "9-04",
            "10-04",
            "10-04",
            "11-04",
            "11-04",
            "12-04",
            "12-04",
            "13-04",
            "13-04",
            "14-04",
            "14-04",
            "15-04",
            "15-04",
            "16-04",
            "16-04",
            "17-04",
            "17-04",
            "18-04",
            "18-04",
            "19-04",
            "19-04",
            "20-04",
            "20-04",
            "21-04",
            "21-04",
            "22-04",
            "22-04",
            "23-04",
            "23-04",
            "24-04",
            "24-04",
            "25-04",
            "25-04",
            "26-04",
            "26-04",
            "27-04",
            "27-04",
            "28-04",
            "28-04",
            "29-04",
            "29-04",
            "30-04",
            "30-04",
            "01-05",
            "1-05",
            "02-05",
            "2-05",
            "03-05",
            "3-05",
            "04-05",
            "4-05",
            "05-05",
            "5-05",
            "06-05",
            "6-05",
            "07-05",
            "7-05",
            "08-05",
            "8-05",
            "09-05",
            "9-05",
            "10-05",
            "10-05",
            "11-05",
            "11-05",
            "12-05",
            "12-05",
            "13-05",
            "13-05",
            "14-05",
            "14-05",
            "15-05",
            "15-05",
            "16-05",
            "16-05",
            "17-05",
            "17-05",
            "18-05",
            "18-05",
            "19-05",
            "19-05",
            "20-05",
            "20-05",
            "21-05",
            "21-05",
            "22-05",
            "22-05",
            "23-05",
            "23-05",
            "24-05",
            "24-05",
            "25-05",
            "25-05",
            "26-05",
            "26-05",
            "27-05",
            "27-05",
            "28-05",
            "28-05",
            "29-05",
            "29-05",
            "30-05",
            "30-05",
            "31-05",
            "31-05",
            "01-06",
            "1-06",
            "02-06",
            "2-06",
            "03-06",
            "3-06",
            "04-06",
            "4-06",
            "05-06",
            "5-06",
            "06-06",
            "6-06",
            "07-06",
            "7-06",
            "08-06",
            "8-06",
            "09-06",
            "9-06",
            "10-06",
            "10-06",
            "11-06",
            "11-06",
            "12-06",
            "12-06",
            "13-06",
            "13-06",
            "14-06",
            "14-06",
            "15-06",
            "15-06",
            "16-06",
            "16-06",
            "17-06",
            "17-06",
            "18-06",
            "18-06",
            "19-06",
            "19-06",
            "20-06",
            "20-06",
            "21-06",
            "21-06",
            "22-06",
            "22-06",
            "23-06",
            "23-06",
            "24-06",
            "24-06",
            "25-06",
            "25-06",
            "26-06",
            "26-06",
            "27-06",
            "27-06",
            "28-06",
            "28-06",
            "29-06",
            "29-06",
            "30-06",
            "30-06",
            "01-07",
            "1-07",
            "02-07",
            "2-07",
            "03-07",
            "3-07",
            "04-07",
            "4-07",
            "05-07",
            "5-07",
            "06-07",
            "6-07",
            "07-07",
            "7-07",
            "08-07",
            "8-07",
            "09-07",
            "9-07",
            "10-07",
            "10-07",
            "11-07",
            "11-07",
            "12-07",
            "12-07",
            "13-07",
            "13-07",
            "14-07",
            "14-07",
            "15-07",
            "15-07",
            "16-07",
            "16-07",
            "17-07",
            "17-07",
            "18-07",
            "18-07",
            "19-07",
            "19-07",
            "20-07",
            "20-07",
            "21-07",
            "21-07",
            "22-07",
            "22-07",
            "23-07",
            "23-07",
            "24-07",
            "24-07",
            "25-07",
            "25-07",
            "26-07",
            "26-07",
            "27-07",
            "27-07",
            "28-07",
            "28-07",
            "29-07",
            "29-07",
            "30-07",
            "30-07",
            "31-07",
            "31-07",
            "01-08",
            "1-08",
            "02-08",
            "2-08",
            "03-08",
            "3-08",
            "04-08",
            "4-08",
            "05-08",
            "5-08",
            "06-08",
            "6-08",
            "07-08",
            "7-08",
            "08-08",
            "8-08",
            "09-08",
            "9-08",
            "10-08",
            "10-08",
            "11-08",
            "11-08",
            "12-08",
            "12-08",
            "13-08",
            "13-08",
            "14-08",
            "14-08",
            "15-08",
            "15-08",
            "16-08",
            "16-08",
            "17-08",
            "17-08",
            "18-08",
            "18-08",
            "19-08",
            "19-08",
            "20-08",
            "20-08",
            "21-08",
            "21-08",
            "22-08",
            "22-08",
            "23-08",
            "23-08",
            "24-08",
            "24-08",
            "25-08",
            "25-08",
            "26-08",
            "26-08",
            "27-08",
            "27-08",
            "28-08",
            "28-08",
            "29-08",
            "29-08",
            "30-08",
            "30-08",
            "31-08",
            "31-08",
            "01-09",
            "1-09",
            "02-09",
            "2-09",
            "03-09",
            "3-09",
            "04-09",
            "4-09",
            "05-09",
            "5-09",
            "06-09",
            "6-09",
            "07-09",
            "7-09",
            "08-09",
            "8-09",
            "09-09",
            "9-09",
            "10-09",
            "10-09",
            "11-09",
            "11-09",
            "12-09",
            "12-09",
            "13-09",
            "13-09",
            "14-09",
            "14-09",
            "15-09",
            "15-09",
            "16-09",
            "16-09",
            "17-09",
            "17-09",
            "18-09",
            "18-09",
            "19-09",
            "19-09",
            "20-09",
            "20-09",
            "21-09",
            "21-09",
            "22-09",
            "22-09",
            "23-09",
            "23-09",
            "24-09",
            "24-09",
            "25-09",
            "25-09",
            "26-09",
            "26-09",
            "27-09",
            "27-09",
            "28-09",
            "28-09",
            "29-09",
            "29-09",
            "30-09",
            "30-09",
            "01-10",
            "1-10",
            "02-10",
            "2-10",
            "03-10",
            "3-10",
            "04-10",
            "4-10",
            "05-10",
            "5-10",
            "06-10",
            "6-10",
            "07-10",
            "7-10",
            "08-10",
            "8-10",
            "09-10",
            "9-10",
            "10-10",
            "10-10",
            "11-10",
            "11-10",
            "12-10",
            "12-10",
            "13-10",
            "13-10",
            "14-10",
            "14-10",
            "15-10",
            "15-10",
            "16-10",
            "16-10",
            "17-10",
            "17-10",
            "18-10",
            "18-10",
            "19-10",
            "19-10",
            "20-10",
            "20-10",
            "21-10",
            "21-10",
            "22-10",
            "22-10",
            "23-10",
            "23-10",
            "24-10",
            "24-10",
            "25-10",
            "25-10",
            "26-10",
            "26-10",
            "27-10",
            "27-10",
            "28-10",
            "28-10",
            "29-10",
            "29-10",
            "30-10",
            "30-10",
            "31-10",
            "31-10",
            "01-11",
            "1-11",
            "02-11",
            "2-11",
            "03-11",
            "3-11",
            "04-11",
            "4-11",
            "05-11",
            "5-11",
            "06-11",
            "6-11",
            "07-11",
            "7-11",
            "08-11",
            "8-11",
            "09-11",
            "9-11",
            "10-11",
            "10-11",
            "11-11",
            "11-11",
            "12-11",
            "12-11",
            "13-11",
            "13-11",
            "14-11",
            "14-11",
            "15-11",
            "15-11",
            "16-11",
            "16-11",
            "17-11",
            "17-11",
            "18-11",
            "18-11",
            "19-11",
            "19-11",
            "20-11",
            "20-11",
            "21-11",
            "21-11",
            "22-11",
            "22-11",
            "23-11",
            "23-11",
            "24-11",
            "24-11",
            "25-11",
            "25-11",
            "26-11",
            "26-11",
            "27-11",
            "27-11",
            "28-11",
            "28-11",
            "29-11",
            "29-11",
            "30-11",
            "30-11",
            "01-12",
            "1-12",
            "02-12",
            "2-12",
            "03-12",
            "3-12",
            "04-12",
            "4-12",
            "05-12",
            "5-12",
            "06-12",
            "6-12",
            "07-12",
            "7-12",
            "08-12",
            "8-12",
            "09-12",
            "9-12",
            "10-12",
            "10-12",
            "11-12",
            "11-12",
            "12-12",
            "12-12",
            "13-12",
            "13-12",
            "14-12",
            "14-12",
            "15-12",
            "15-12",
            "16-12",
            "16-12",
            "17-12",
            "17-12",
            "18-12",
            "18-12",
            "19-12",
            "19-12",
            "20-12",
            "20-12",
            "21-12",
            "21-12",
            "22-12",
            "22-12",
            "23-12",
            "23-12",
            "24-12",
            "24-12",
            "25-12",
            "25-12",
            "26-12",
            "26-12",
            "27-12",
            "27-12",
            "28-12",
            "28-12",
            "29-12",
            "29-12",
            "30-12",
            "30-12"
    );


}
