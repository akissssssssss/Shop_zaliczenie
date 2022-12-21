package com.example.shop;

import static com.example.shop.Data.Fragments.getFragment;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.shop.Data.Cart;
import com.example.shop.Data.DbController;
import com.example.shop.Data.Fragments;
import com.example.shop.Data.Order;
import com.example.shop.Data.Product;
import com.example.shop.Data.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public final static int MY_PERMISSION_REQUEST_SEND_SMS = 1;
    public BottomNavigationView bottomNavigationView;
    private User user;
    private Product product;
    private List<Product> products;
    private List<Cart> cart;
    private List<Order> orders;
    boolean isLogged=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottomNavigationView = findViewById(R.id.nav_view);
        products=new ArrayList<>();
        cart=new ArrayList<>();
        orders=new ArrayList<>();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,getFragment(0)).commit();
        askForPermission(Manifest.permission.SEND_SMS, MY_PERMISSION_REQUEST_SEND_SMS);
        LoadUser();
        if(user==null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.AlertDialog_message)
                    .setPositiveButton(R.string.loguj, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            showFragment(Fragments.FRAGMENT_LOGIN);
                        }
                    })
                    .setNegativeButton(R.string.register, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            showFragment(Fragments.FRAGMENT_REGISTER);
                        }
                    })
                    .setCancelable(false)
                    .show();
            builder.create();
        }

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,getFragment(0)).commit();
                        return true;
//                    case R.id.navigation_search:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,getFragment(1)).commit();
//                        return true;
                    case R.id.navigation_cart:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,getFragment(2)).commit();
                        return true;
                    case R.id.navigation_orders:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,getFragment(3)).commit();
                        return true;
                    case R.id.navigation_account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,getFragment(4)).commit();
                        return true;
                }
                return false;
            }
        });
        loadData();
    }

    private void loadData(){
        DbController helper=new DbController(getApplicationContext());

        if (helper.getCategories().getCount()==0){
            helper.insert1("Bartosz","Redecki","a","690188005","a");

            helper.insert4("Apple");
            helper.insert4("Samsung");
            helper.insert4("Chargers");
            helper.insert4("Headphones");
            helper.insert4("Portable chargers");

            helper.insert2("APPLE iPhone 14 128GB 5G 6.1","1","To bardzo fajny telefon", (float) 4399.32,ImageToString(BitmapFactory.decodeResource(getResources(),R.drawable.iphone14)));
            helper.insert2("APPLE iPhone 11 64GB 6.1","1","To fajny telefon", (float) 3399.32,ImageToString(BitmapFactory.decodeResource(getResources(),R.drawable.iphone11)));
            helper.insert2("SAMSUNG Galaxy S20 FE 6/128GB 5G 6.5","2","To bardzo fajny telefon", (float) 2399.32,ImageToString(BitmapFactory.decodeResource(getResources(),R.drawable.samsungs20)));
            helper.insert2("SAMSUNG Galaxy M23 4/128GB 5G 6.6","2","To fajny telefon", (float) 1399.32,ImageToString(BitmapFactory.decodeResource(getResources(),R.drawable.samsungm23)));
            helper.insert2("Ładowarka sieciowa APPLE 20W USB-C","3","To bardzo dobra ładowarka", (float) 109.32,ImageToString(BitmapFactory.decodeResource(getResources(),R.drawable.appleusbc)));
            helper.insert2("Ładowarka sieciowa SAMSUNG EP-TA800NBEGEU 25W USB-C","3","To dobra ładowarka", (float) 99.32,ImageToString(BitmapFactory.decodeResource(getResources(),R.drawable.samsungusbc)));
            helper.insert2("Słuchawki bezprzewodowe APPLE AirPods Pro","4","To bardzo dobre słuchawki", (float) 1099.32,ImageToString(BitmapFactory.decodeResource(getResources(),R.drawable.airpods)));
            helper.insert2("Słuchawki bezprzewodowe SAMSUNG Galaxy Buds Pro","4","To dobre słuchawki", (float) 899.32,ImageToString(BitmapFactory.decodeResource(getResources(),R.drawable.galaxybuds)));
            helper.insert2("Powerbank SAMSUNG 10000mAh","5","To dobry powerbank", (float) 309.32,ImageToString(BitmapFactory.decodeResource(getResources(),R.drawable.samsung)));
            helper.insert2("Powerbank APPLE 10000mAh","5","To bardzo dobry powerbank", (float) 509.32,ImageToString(BitmapFactory.decodeResource(getResources(),R.drawable.apple)));
        }
    }
    public void showFragment(int fragment){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, Fragments.getFragment(fragment)).commit();
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<Cart> getCart() {
        return cart;
    }
    public List<Order> getOrders() {
        return orders;
    }
    public boolean isSaved(){
        return isLogged;
    }
    public void Save(boolean logged) {
        isLogged = logged;
    }

    public List<Product> getProducts() {
        return products;
    }
    public void addItemToCart(Product product,int quantity){
        Cart item=new Cart(user.getId(),product.getId(),quantity);
        cart.add(item);
        DbController dbController=new DbController(getApplicationContext());
        dbController.insert5(String.valueOf(item.getUser_id()), String.valueOf(item.getProduct_id()),item.getQuantity());
    }
    public String ImageToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,40,baos);
        byte[] imageBytes=baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    public void StringToImage(String encodedImage, ImageView imageView){
        InputStream stream = new ByteArrayInputStream(Base64.decode(encodedImage.getBytes(), Base64.DEFAULT));
        Bitmap bitmap = BitmapFactory.decodeStream(stream);
        imageView.setImageBitmap(bitmap);
    }

    private void SaveUser(){
        SharedPreferences sharedPReferences=getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPReferences.edit();
        editor.putBoolean("isLogged",isLogged);
        editor.putInt("id",user.getId());
        editor.putString("name",user.getName());
        editor.putString("surname",user.getSurname());
        editor.putString("email",user.getEmail());
        editor.putString("phone",user.getPhone());
        editor.apply();
    }
    private void LoadUser(){
        SharedPreferences sharedPReferences=getSharedPreferences("user",MODE_PRIVATE);
        isLogged=sharedPReferences.getBoolean("isLogged",false);
        if (isLogged){
            user=new User(sharedPReferences.getInt("id",0),sharedPReferences.getString("name",""),sharedPReferences.getString("surname",""),sharedPReferences.getString("email",""),sharedPReferences.getString("phone",""));
        }else {
            user= null;
        }
    }
    private void askForPermission(String permission, int requestCode){
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_DENIED) {

            ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSION_REQUEST_SEND_SMS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, getResources().getString(R.string.permission_grant), Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(MainActivity.this, getResources().getString(R.string.permission_denied), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isLogged){
            SaveUser();
        }else {
            SharedPreferences sharedPReferences=getSharedPreferences("user",MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPReferences.edit();
            editor.clear();
            editor.apply();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isLogged){
            SaveUser();
        }else {
            SharedPreferences sharedPReferences=getSharedPreferences("user",MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedPReferences.edit();
            editor.clear();
            editor.apply();
        }
    }
}
