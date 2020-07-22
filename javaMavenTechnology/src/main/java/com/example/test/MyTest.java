package com.example.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MyTest {
    public static void main(String[] args) {
        long maxStatisticTime = getTimeBeforeAnyDay("0", "yyyy-MM-dd");
        long minStatisticTime = 1579449600000L;
//        int betweenDays= new Long(maxStatisticTime-minStatisticTime).intValue()/(1000*60*60*24);
        long betweenDays= (maxStatisticTime - minStatisticTime) /(1000*60*60*24);
        int bet = Integer.parseInt(String.valueOf(betweenDays));
        System.out.println(betweenDays);
        System.out.println(bet);
    }


    public static long getTimeBeforeAnyDay(String days, String sf){
        int invocatonMainTainDays =Integer.parseInt("-"+days);
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, invocatonMainTainDays);
        long intervalEndTime = cal.getTimeInMillis();
        return intervalEndTime;
    }
}
