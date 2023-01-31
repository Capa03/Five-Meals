package com.example.fivemealsmobileproject.ui.main;

public class ProgressHelper {
    private ProgressHelper(){}

    public static String getTimeToString(int minTime, int maxTime){
        return maxTime == 0 ? String.valueOf(minTime) : (minTime + " - " + maxTime);
    }

    public static int getProgressInPercentage(int stepsMade, int maxSteps){
        return (stepsMade*100/maxSteps);
    }
    
}
