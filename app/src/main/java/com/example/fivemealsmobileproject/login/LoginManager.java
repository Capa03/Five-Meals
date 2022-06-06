package com.example.fivemealsmobileproject.login;

import android.content.Context;

import com.example.fivemealsmobileproject.database.AppDataBase;
import com.example.fivemealsmobileproject.database.User;

public class LoginManager {

    public static User validateUser(String username, int passwordHash, Context context) {
        User user = AppDataBase.getInstance(context).getUserDAO().getUserById(username);
        if (user == null) return null;
        return user.getPassword() == passwordHash ? user : null;
    }
}
