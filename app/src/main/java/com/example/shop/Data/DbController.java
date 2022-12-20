package com.example.shop.Data;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;
import android.widget.Adapter;

import com.example.shop.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class DbController{
    static FeedReaderDbHelper dbHelper;
    static SQLiteDatabase sqLiteDatabase;
    Context context;
    public static String name;
    public static String surname;
    public static String phone;
    public static String pass;
    public static int id;

    public DbController(Context context) {
        this.dbHelper = new FeedReaderDbHelper(context);
    }


    public void insert1(String name,String surname,String email,String phone, String pass){
        this.sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME, name);
        values.put(FeedReaderContract.FeedEntry.COLUMN_SURNAME, surname);
        values.put(FeedReaderContract.FeedEntry.COLUMN_EMAIL, email);
        values.put(FeedReaderContract.FeedEntry.COLUMN_PHONE, phone);
        values.put(FeedReaderContract.FeedEntry.COLUMN_PASS, pass);

        long newRowId = sqLiteDatabase.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
        Log.i("TAG", "insert: "+newRowId);
    }

    public void insert2(String item_name,String category_id,String description, Float price, String photo){
        this.sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry2.COLUMN_NAME, item_name);
        values.put(FeedReaderContract.FeedEntry2.COLUMN_CATEGORY, category_id);
        values.put(FeedReaderContract.FeedEntry2.COLUMN_PRODUCTS_DESCRIPTION, description);
        values.put(FeedReaderContract.FeedEntry2.COLUMN_PRICE, price);
        values.put(FeedReaderContract.FeedEntry2.COLUMN_PHOTO, photo);

        long newRowId = sqLiteDatabase.insert(FeedReaderContract.FeedEntry2.TABLE_NAME, null, values);
        Log.i("TAG", "insert: "+newRowId);
    }

    public void insert3(int account_id,String items, Float price, String data){
        this.sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry3.COLUMN_NAME, account_id);
        values.put(FeedReaderContract.FeedEntry3.COLUMN_ITEMS, items);
        values.put(FeedReaderContract.FeedEntry3.COLUMN_PRICE, price);
        values.put(FeedReaderContract.FeedEntry3.COLUMN_DATA, data);

        long newRowId = sqLiteDatabase.insert(FeedReaderContract.FeedEntry3.TABLE_NAME, null, values);
        Log.i("TAG", "insert: "+newRowId);
    }

    public void insert4(String category){
        this.sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(FeedReaderContract.FeedEntry4.COLUMN_CATEGORY_NAME,category);

        long newRowId = sqLiteDatabase.insert(FeedReaderContract.FeedEntry4.TABLE_NAME, null, values);
        Log.i("TAG", "insert: "+newRowId);
    }
    public void insert5(String account_id, String item_id, int quantity){
        this.sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(FeedReaderContract.FeedEntry5.COLUMN_NAME,account_id);
        values.put(FeedReaderContract.FeedEntry5.COLUMN_ITEM_ID,item_id);
        values.put(FeedReaderContract.FeedEntry5.COLUMN_QUANTITY,quantity);

        long newRowId = sqLiteDatabase.insert(FeedReaderContract.FeedEntry5.TABLE_NAME, null, values);
        Log.i("TAG", "insert: "+newRowId);
    }

    public void delete1(String id){
        String selection= BaseColumns._ID+id;
        int deletedRows = sqLiteDatabase.delete(FeedReaderContract.FeedEntry.TABLE_NAME, selection, null);
        Log.i("TAG", "delete: "+deletedRows);
    }

    public void delete2(String id){
        String selection= BaseColumns._ID+id;
        int deletedRows = sqLiteDatabase.delete(FeedReaderContract.FeedEntry2.TABLE_NAME, selection, null);
        Log.i("TAG", "delete: "+deletedRows);
    }

    public void delete3(String id){
        String selection= BaseColumns._ID+id;
        int deletedRows = sqLiteDatabase.delete(FeedReaderContract.FeedEntry3.TABLE_NAME, selection, null);
        Log.i("TAG", "delete: "+deletedRows);
    }

    public void destroy(){
        dbHelper.onUpgrade(sqLiteDatabase,1,1);
    }

    public Cursor getCategories(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM category ", null);
        if (c.moveToFirst()){
            do {
                String column1 = c.getString(0);
            } while(c.moveToNext());
        }
        c.close();
        return c;
    }

    public static String getUsername(String email){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT name FROM accounts WHERE email = '"+email+"'", null);
        if (c.moveToFirst()){
            do {
                name = c.getString(0);
            } while(c.moveToNext());
        }
        c.close();
        return name;
    }
    public static String getSurname(String email){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT surname FROM accounts WHERE email = '"+email+"'", null);
        if (c.moveToFirst()){
            do {
                surname = c.getString(0);
            } while(c.moveToNext());
        }
        c.close();
        return surname;
    }
    public static String getPhone(String email){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT phone FROM accounts WHERE email = '"+email+"'", null);
        if (c.moveToFirst()){
            do {
                phone = c.getString(0);
            } while(c.moveToNext());
        }
        c.close();
        return phone;
    }
    public static Cursor getPass(String email){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT pass FROM accounts WHERE email = '"+email+"'", null);
        if (c.moveToFirst()){
            do {
                pass = c.getString(0);
            } while(c.moveToNext());
        }
        c.close();
        return c;
    }
    public static int getId(String email){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT _id FROM accounts WHERE email = '"+email+"'", null);
        if (c.moveToFirst()){
            do {
                id = c.getInt(0);
            } while(c.moveToNext());
        }
        c.close();
        return id;
    }

    public static Cursor loginUser(String email, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM accounts WHERE email = ? AND password = ?", new String[]{email, password});
        if (c.moveToFirst()) {
            String column1 = c.getString(0);
        }
        c.close();
        return c;
    }


    public boolean registerUser(String name, String surname, String email,String phone, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedReaderContract.FeedEntry.COLUMN_NAME, name);
        values.put(FeedReaderContract.FeedEntry.COLUMN_SURNAME, surname);
        values.put(FeedReaderContract.FeedEntry.COLUMN_EMAIL, email);
        values.put(FeedReaderContract.FeedEntry.COLUMN_PHONE, phone);
        values.put(FeedReaderContract.FeedEntry.COLUMN_PASS, password);

        long newRowId = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, values);
        return newRowId != -1;

    }

    public List<Product> getProducts(){
        List<Product> products = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM items", null);
        if (c.moveToFirst()){
            do {
                products.add(new Product(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getFloat(3),
                        c.getString(4)
                ));
            } while(c.moveToNext());
        }
        c.close();
        return products;
    }
    public Product getProductById(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM items WHERE _id = "+id, null);
        List<Product> product = new ArrayList<>();
        if (c.moveToFirst()){
            do {
                product.add(new Product(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getFloat(3),
                        c.getString(4)
                ));
            } while(c.moveToNext());
        }
        c.close();
        return product.get(0);
    }
    public User getUserById(int id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM accounts WHERE _id = "+id, null);
        List<User> user = new ArrayList<>();
        if (c.moveToFirst()){
            do {
                user.add(new User(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getString(4)));
            } while(c.moveToNext());
        }
        c.close();
        return user.get(0);
    }

    public List<Order> getOrdersByUSerId(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM orders WHERE account_id = "+id, null);
        List<Order> orders = new ArrayList<>();
        if (c.moveToFirst()){
            do {
                orders.add(new Order(
                        c.getInt(0),
                       c.getInt(1),
                       c.getString(2),
                       c.getFloat(3),
                       c.getString(4)
                ));
            }while (c.moveToNext());
        }
        c.close();
        return orders;
    }

    public List<Order> getOrdersById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM orders WHERE _id = "+id, null);
        List<Order> orders = new ArrayList<>();
        if (c.moveToFirst()){
            do {
                orders.add(new Order(
                        c.getInt(0),
                        c.getInt(1),
                        c.getString(2),
                        c.getFloat(3),
                        c.getString(4)
                ));
            }while (c.moveToNext());
        }
        c.close();
        return orders;
    }

    public Order getOrderById(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM orders WHERE _id="+id, null);
        Order order = new Order();
        if (c.moveToFirst()){
            do {
                order=new Order(
                        c.getInt(0),
                        c.getInt(1),
                        c.getString(2),
                        c.getFloat(3),
                        c.getString(4)
                );
            }while (c.moveToNext());
        }
        c.close();
        return order;
    }
}
