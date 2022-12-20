package com.example.shop.Data;

import android.content.Context;


public class User {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    public User(){
    }

    public User(int id,String name, String surname, String email, String phone) {
        this.id=id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getEmail() {
        return email;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPhone() {
        return phone;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public static boolean registerUser(Context context, String name, String surname, String email,String phone, String password){
        return UserDAO.registerUser(context, name, surname, email, phone, password);
    }
}
