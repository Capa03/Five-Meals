package com.example.fivemealsmobileproject.ui.login;

import android.content.Context;

import com.example.fivemealsmobileproject.datasource.room.AppDataBase;
import com.example.fivemealsmobileproject.datasource.room.User;

public class LoginManager {

    public static User validateUser(Context context, String username, int passwordHash) {
        User user = AppDataBase.getInstance(context).getUserDAO().getUserByUsername(username);
        if (user == null) return null;
        return user.getPassword() == passwordHash ? user : null;
    }

    public static boolean userExists(Context context, String username){
        User user = AppDataBase.getInstance(context).getUserDAO().getUserByUsername(username);
        return user != null;
    }

    public static boolean emailExists(Context context, String email){
        User user = AppDataBase.getInstance(context).getUserDAO().getUserByEmail(email);
        return user != null;
    }
}
