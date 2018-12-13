package jodaTime;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by ZSX on 2018/12/13.
 *
 * @author ZSX
 */
public class JodaTimeDemo {


    public static void main(String[] args) {
        test1();
    }


    public static void test1() {
        DateTime dateTime1 = new DateTime(2018, 1, 1, 0, 0);
        DateTime dateTime2 = new DateTime(2018, 1, 2, 0, 0);

        System.out.println(dateTime1.isBefore(dateTime2));
        System.out.println(dateTime1.isEqual(dateTime2));
        System.out.println(dateTime1.isAfter(dateTime2));

        System.out.println(dateTime1.toString("yyyyMM"));
        System.out.println(dateTime2.toString("yyyyMM"));

        System.out.println(dateTime1.toString("yyyyMM").equals(dateTime2.toString("yyyyMM")));

        System.out.println(dateTime2.minusDays(1));

        System.out.println(dateTime2.dayOfMonth().withMinimumValue());

        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime parse = DateTime.parse("2012-12-21", formatter);
        System.out.println(parse);

    }


}
