package com.example.fivemealsmobileproject.main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeHelper {

    private static final SimpleDateFormat minutesSeconds = new SimpleDateFormat("mm:ss");

    private TimeHelper(){}

    public static long getAverageTimeInMillis(int minTime, int maxTime){
        return maxTime == 0 ? (long) minTime * 60000 : (long) (((minTime + maxTime) / 2) * 60000L);
    }

    public static String getTimeToString(int minTime, int maxTime){
        return maxTime == 0 ? String.valueOf(minTime) : (minTime + " - " + maxTime);
    }

    public static int getProgressInPercentage(double minTime, double maxTime, long orderTimeInMillis){
        long averageTime = maxTime == 0 ? (long) minTime * 60000 : (long) (((minTime + maxTime) / 2) * 60000);
        if (averageTime == 0) return 100;
        return (int) ((System.currentTimeMillis() - orderTimeInMillis) * 100 / averageTime);
    }


    public static String getProgressInTimeStamp(double minTime, double maxTime, long orderTimeInMillis){
        long averageTime = maxTime == 0 ? (long) minTime * 60000 : (long) (((minTime + maxTime) / 2) * 60000);
        long differenceInMillis = System.currentTimeMillis() - orderTimeInMillis;
        if (differenceInMillis < averageTime){
            return minutesSeconds.format(new Date(averageTime - differenceInMillis));
        }else {
            return "DONE";
        }

    }
}
