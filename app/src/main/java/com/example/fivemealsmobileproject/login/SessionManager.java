package com.example.fivemealsmobileproject.login;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private SessionManager(){

    }

    private static SharedPreferences sharedPreferences;

    private  static SharedPreferences getSharedPreferences(Context context){

        if(sharedPreferences == null){
            sharedPreferences = context.getSharedPreferences("sessionPreferences",Context.MODE_PRIVATE);
        }

        return sharedPreferences;
    }

    public static void saveSession(Context context, String username, boolean rememberMe) {
        getSharedPreferences(context).edit().putString("username", username).putBoolean("rememberMe", rememberMe).apply();
    }

    public static String getActiveSession(Context context) {
        return getSharedPreferences(context).getString("username", null);
    }

    public static boolean sessionExists(Context context) {
        String loggedInUser = getSharedPreferences(context).getString("username", null);
        return loggedInUser != null;
    }

    public static boolean persistedSession(Context context) {
        return getSharedPreferences(context).getBoolean("rememberMe", false);
    }

    public static void clearSession(Context context) {
        getSharedPreferences(context).edit().clear().apply();
    }

}
