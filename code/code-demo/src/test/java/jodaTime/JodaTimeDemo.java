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
        DateTime 一月一号 = new DateTime(2018, 1, 1, 0, 0);
        DateTime 一月二号 = new DateTime(2018, 1, 2, 0, 0);

//        比较
        System.out.println(一月一号.isBefore(一月二号)); // true
        System.out.println(一月一号.isEqual(一月二号)); // false
        System.out.println(一月一号.isAfter(一月二号)); // false

//        格式化
        System.out.println(一月一号.toString("yyyyMM"));

        System.out.println(一月一号.toString("yyyyMM").equals(一月二号.toString("yyyyMM")));

//        减一天
        System.out.println(一月二号.minusDays(1)); // 2018-01-01T00:00:00.000+08:00

//        月初
        System.out.println(一月二号.dayOfMonth().withMinimumValue());

//        格式化函数
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime parse = DateTime.parse("2012-12-21", formatter);
        System.out.println(parse);
    }


}
