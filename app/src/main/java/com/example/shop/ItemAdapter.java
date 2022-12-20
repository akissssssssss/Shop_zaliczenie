package com.example.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.Data.Product;
import com.example.shop.View.HomeFragment;
import com.example.shop.View.ProductView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private MainActivity mainActivity;
    private List<Product> products;

    public ItemAdapter(MainActivity mainActivity, List<Product> products) {
        this.mainActivity = mainActivity;
        this.products = products;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mainActivity).inflate(R.layout.item_list,parent,false);
        ItemViewHolder itemViewHolder=new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Product product=products.get(position);
        String title=product.getItem_name();
        holder.productName.setText(title);
        holder.productPrice.setText(product.getPrice()+" PLN");
        holder.productDescription.setText(product.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductView fragment = new ProductView();
                Bundle bundle = new Bundle();
                bundle.putInt("productId", product.getId() - 1);
                fragment.setArguments(bundle);

                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView productName;
        TextView productPrice;
        TextView productDescription;
        ImageView productImage;

        public ItemViewHolder(View view) {
            super(view);
            productName = view.findViewById(R.id.product_title);
            productPrice = view.findViewById(R.id.product_price);
            productDescription = view.findViewById(R.id.product_description);
            productImage = view.findViewById(R.id.product_image);


        }
    }
}
