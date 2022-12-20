package com.example.shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.Data.DbController;
import com.example.shop.Data.Order;
import com.example.shop.Data.Product;

import java.util.List;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.ItemViewHolder> {
    private MainActivity mainActivity;
    private List<Order> orderDetails;
    private Order order;
    private int numberofItems;

    public OrderDetailsAdapter(MainActivity mainActivity, List<Order> orderDetails) {
        this.mainActivity = mainActivity;
        this.orderDetails = orderDetails;
        DbController dbController=new DbController(mainActivity);
        order=dbController.getOrderById(orderDetails.get(0).getId());
        numberofItems=order.readItems().length;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mainActivity).inflate(R.layout.order_view_list,parent,false);
        ItemViewHolder itemViewHolder=new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Product product;
        int quantity;
        String price;
        DbController dbController=new DbController(mainActivity);
        product=dbController.getProductById(order.readItems()[position][0]);
        quantity=order.readItems()[position][1];
        price=String.valueOf(product.getPrice()*quantity)+" PLN";

        holder.order_number.setText(String.valueOf(position+1));
        holder.order_name.setText(product.getItem_name());
        holder.order_quantity.setText(String.valueOf(quantity));
        holder.order_price.setText(price);
    }

    @Override
    public int getItemCount() {
        return numberofItems;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView order_number;
        TextView order_name;
        TextView order_quantity;
        TextView order_price;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            order_number=itemView.findViewById(R.id.order_number);
            order_name=itemView.findViewById(R.id.order_item_name);
            order_quantity=itemView.findViewById(R.id.order_item_quantity);
            order_price=itemView.findViewById(R.id.order_item_price);
        }
    }

}
