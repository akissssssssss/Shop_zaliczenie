package com.example.shop.Data;

import static android.provider.Settings.System.getString;

import com.example.shop.MainActivity;
import com.example.shop.R;

import java.util.Date;

public class Order {
    private int id;
    private int account_id;
    private String items;
    private float price;
    private String data;

    public Order() {
    }

    public Order(int id, int account_id, String items, float price, String data) {
        this.id = id;
        this.account_id = account_id;
        this.items = items;
        this.price = price;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int[][] readItems(){
        String[] items_list = items.split("&");
        int[][] items_read = new int[items_list.length][];
        for (int i = 0; i < items_list.length; i++) {
            String[] split = items_list[i].split(";");
            items_read[i] = new int[]{Integer.parseInt(split[0]), Integer.parseInt(split[1])};
        }
        return items_read;
    }


}
