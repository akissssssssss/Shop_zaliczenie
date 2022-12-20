package com.example.shop.Data;

public class Product {
    private int id;
    private String item_name;
    private String description;
    private Float price;
    private String photo;
    private int quantity;

    public Product(int id, String item_name, String description, Float price, String photo) {
        this.id = id;
        this.item_name = item_name;
        this.description = description;
        this.price = price;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public String getItem_name() {
        return item_name;
    }

    public String getDescription() {
        return description;
    }

    public Float getPrice() {
        return price;
    }

    public String getPhoto() {
        return photo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
