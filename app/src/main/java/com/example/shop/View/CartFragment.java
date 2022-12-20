package com.example.shop.View;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.CartAdapter;
import com.example.shop.Data.Cart;
import com.example.shop.Data.DbController;
import com.example.shop.Data.Fragments;
import com.example.shop.Data.Order;
import com.example.shop.Data.Product;
import com.example.shop.Data.User;
import com.example.shop.MainActivity;
import com.example.shop.R;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class CartFragment extends Fragment {
    MainActivity mainActivity;
    RecyclerView recyclerView;
    ImageButton shareButton;
    TextView totalCost;
    Button buyButton;
    List<Product> products;
    List<Cart> cart_items;
    User user;
    float finalPrice = 0;
    LocalDateTime date;
    DateTimeFormatter format;

    public CartFragment() {
        super (R.layout.fragment_cart);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainActivity=(MainActivity) getActivity();
        user=mainActivity.getUser();

        recyclerView=view.findViewById(R.id.recycler_view_cart);
        shareButton=view.findViewById(R.id.share);
        totalCost=view.findViewById(R.id.total_price);
        buyButton=view.findViewById(R.id.buy);

        buyButton.setOnClickListener(view1 -> buyFromCart());
        shareButton.setOnClickListener(view1 -> shareCart());

        DbController dbController=new DbController(mainActivity);
        products=dbController.getProducts();
        cart_items=mainActivity.getCart();
        CartAdapter cartAdapter=new CartAdapter(mainActivity,cart_items,this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mainActivity, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(cartAdapter);
        if (cart_items.size()==0){
            buyButton.setEnabled(false);
            shareButton.setEnabled(false);
        }
        else{
            buyButton.setEnabled(true);
            shareButton.setEnabled(true);
        }
        totalCost.setText(String.valueOf(finalPrice)+" PLN");
    }
    public void FinalPrice(float price){
        finalPrice+=price;
        totalCost.setText(String.valueOf(finalPrice)+" PLN");
    }

    private void buyFromCart() {
        date = LocalDateTime.now();
        format = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        String formattedDate = date.format(format);

        DbController dbController=new DbController(mainActivity);
        String items="";
        for (Cart cart:cart_items){
            items+=cart.getItems()+"&";
        }
        dbController.insert3(user.getId(),items,finalPrice,formattedDate);

        CartAdapter cartAdapter=new CartAdapter(mainActivity,cart_items,this);
        recyclerView.setAdapter(null);
        recyclerView.setLayoutManager(null);
        recyclerView.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();
        cart_items.clear();
        sendSms(formattedDate,finalPrice);
        finalPrice=0;
        totalCost.setText(String.valueOf(finalPrice)+" PLN");
        buyButton.setEnabled(false);
        shareButton.setEnabled(false);
    }
    private void shareCart() {
        DbController dbController=new DbController(mainActivity);
        String[] email={user.getEmail()};
        Toast.makeText(mainActivity, ""+user.getEmail(), Toast.LENGTH_SHORT).show();
        String items="";
        String message="";
        Product product;
        for (Cart cart:cart_items){
            items=cart.getItems()+"\n";
            product=dbController.getProductById(Integer.parseInt(items.substring(0,1)));
            message+=items.substring(2,3)+"x "+product.getItem_name()+"\n";
        }
        Intent intent=new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL,email);
        intent.putExtra(Intent.EXTRA_SUBJECT,getResources().getString(R.string.your_cart));
        intent.putExtra(Intent.EXTRA_TEXT,message);
        startActivity(intent);
    }
    private void sendSms(String formattedDate,float finalPrice) {
        if (ContextCompat.checkSelfPermission(mainActivity, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(mainActivity,new String[]{Manifest.permission.SEND_SMS},1);
        }
        else{
            String message=getResources().getString(R.string.order_accepted)+"\n"+
                    getResources().getString(R.string.date_purchase)+" "+
                    formattedDate+"\n"+
                    getResources().getString(R.string.order_price)+" "+finalPrice+" PLN";
            String destinationAddress= user.getPhone();
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(destinationAddress,null,message,null,null);
        }
    }
}
