package util;

import java.time.LocalDate;

/**
 * @author wangfujie
 * @date 2017-12-25 16:55
 * @description DESCRIPTION
 */
public class DateTest {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        //获取本月1号的LocalDate日期
        localDate = localDate.minusDays(localDate.getDayOfMonth() - 1);
        System.out.println("年：" + localDate.getYear());
        System.out.println("月：" + localDate.getMonthValue());
        System.out.println("日：" + localDate.getDayOfMonth());
        System.out.println("星期：" + localDate.getDayOfWeek().getValue());
    }
}
