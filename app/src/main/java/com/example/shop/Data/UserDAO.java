package com.example.shop.Data;

import android.content.Context;

public class UserDAO {
    public static boolean registerUser(Context context, String name, String surname, String email, String phone, String password) {
        DbController db = new DbController(context);
        return db.registerUser(name, surname, email, phone, password);
    }
}
