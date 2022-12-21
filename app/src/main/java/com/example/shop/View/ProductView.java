package com.example.shop.View;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shop.Data.DbController;
import com.example.shop.Data.Product;
import com.example.shop.MainActivity;
import com.example.shop.R;

public class ProductView extends Fragment {
    private MainActivity mainActivity;
    private int quantity=1;
    private Product chosenProduct;

    private TextView productTitle;
    private ImageView productImage;
    private TextView productPrice;
    private Button decrementButton;
    private Button incrementButton;
    private EditText quantityText;
    private Button addToCartButton;
    private TextView productDescription;

    public ProductView() {
        super(R.layout.product_view);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainActivity=(MainActivity)getActivity();

        productTitle=view.findViewById(R.id.product_title);
        productImage=view.findViewById(R.id.product_image);
        productPrice=view.findViewById(R.id.product_price);
        decrementButton=view.findViewById(R.id.decrement);
        incrementButton=view.findViewById(R.id.increment);
        quantityText=view.findViewById(R.id.product_quantity);
        addToCartButton=view.findViewById(R.id.add_to_cart);
        productDescription=view.findViewById(R.id.product_description);

        decrementButton.setOnClickListener(v ->decrement());
        incrementButton.setOnClickListener(v ->increment());
        addToCartButton.setOnClickListener(v ->addToCart());

        DbController dbController = new DbController(mainActivity);
        chosenProduct = dbController.getProductById(getArguments().getInt("productId")+1);
        productTitle.setText(chosenProduct.getItem_name());
        productPrice.setText(chosenProduct.getPrice()+" PLN");
        productDescription.setText(chosenProduct.getDescription());
        mainActivity.StringToImage(chosenProduct.getPhoto(),productImage);
    }

    private void decrement() {
        if (quantity - 1 >= 1){
            quantity--;
            quantityText.setText(quantity+"");
        }
    }
    private void increment() {
        quantity++;
        quantityText.setText(quantity+"");
    }
    private void addToCart() {
        mainActivity.addItemToCart(chosenProduct, Integer.parseInt(quantityText.getText().toString()));
        Toast.makeText(mainActivity,getResources().getString(R.string.addtocart),Toast.LENGTH_SHORT).show();
    }

}
