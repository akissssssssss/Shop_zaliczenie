package com.example.shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.Data.Cart;
import com.example.shop.Data.DbController;
import com.example.shop.Data.Product;
import com.example.shop.View.CartFragment;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ItemViewHolder> {
    private MainActivity mainActivity;
    private List<Cart> cart_items;
//    private List<Product> products;
    private CartFragment cartFragment;
    private DbController dbController;
    public float price;

    public CartAdapter(MainActivity mainActivity, List<Cart> cart_items, CartFragment cartFragment) {
        this.mainActivity = mainActivity;
        this.cart_items = cart_items;
        this.cartFragment = cartFragment;
        dbController = new DbController(mainActivity);
    }

    @NonNull
    @Override
    public CartAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mainActivity).inflate(R.layout.cart_view,parent,false);
        ItemViewHolder itemViewHolder=new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ItemViewHolder holder, int position) {
        Cart cart = cart_items.get(position);
        Product product = dbController.getProductById(cart.getProduct_id());
        holder.cart_title.setText(product.getItem_name());
        holder.cart_quantity.setText(cart.getQuantity()+"");

        price = product.getPrice()*cart.getQuantity();
        holder.cart_price.setText(String.format("%.02f", price) + " PLN");
        cartFragment.FinalPrice(price);
        holder.cart_number.setText(String.valueOf(position+1));


    }

    public float getPrice() {
        return price;
    }

    @Override
    public int getItemCount() {
        return cart_items.size();
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView cart_number;
        TextView cart_title;
        TextView cart_price;
        TextView cart_quantity;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            cart_number = itemView.findViewById(R.id.cart_number);
            cart_title = itemView.findViewById(R.id.cart_title);
            cart_price = itemView.findViewById(R.id.cart_price);
            cart_quantity = itemView.findViewById(R.id.cart_quantity);

        }
    }
}
