package jodaTime;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by ZSX on 2018/12/13.
 *
 * @author ZSX
 */
public class JodaTimeDemo {


    public static void main(String[] args) {
//        test1();
        test2();
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

    public static void test2() {

        DateTime 一月一号 = new DateTime(2018, 01, 01, 0, 0);
        DateTime 二月一号 = new DateTime(2018, 02, 01, 0, 0);

//        得到两个日期的相差天数
        Period period = new Period(一月一号, 二月一号, PeriodType.days());
        int days = period.getDays();
        System.out.println(days); // 31


//       加 天数
        一月一号 = 一月一号.plusDays(30);
        System.out.println(一月一号); // 2018-01-31T00:00:00.000+08:00

//        结果一样
        System.out.println(一月一号.plusDays(-5)); // 2018-01-26T00:00:00.000+08:00
        System.out.println(一月一号.minusDays(5)); // 2018-01-26T00:00:00.000+08:00
    }
}
