package com.example.shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.Data.DbController;
import com.example.shop.Data.Order;
import com.example.shop.Data.User;
import com.example.shop.View.OrderView;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ItemViewHolder> {
    private MainActivity mainActivity;
    private List<Order> orders;
    private DbController dbController;
    private User user;


    public OrdersAdapter(MainActivity mainActivity, List<Order> orders) {
        this.mainActivity = mainActivity;
        this.orders = orders;
        dbController=new DbController(mainActivity);
        user=mainActivity.getUser();

    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mainActivity).inflate(R.layout.order_list,parent,false);
        ItemViewHolder itemViewHolder=new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Order order=orders.get(position);
        holder.order_number.setText(String.valueOf(position+1));
        holder.order_date.setText(String.valueOf(order.getData()));
        holder.order_price.setText(String.valueOf(order.getPrice())+" PLN");
        holder.order_email.setText(user.getEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderView fragment = new OrderView();
                Bundle bundle = new Bundle();
                bundle.putInt("orderId", order.getId() - 1);
                fragment.setArguments(bundle);

                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(null).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView order_number;
        TextView order_email;
        TextView order_date;
        TextView order_price;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            order_number=itemView.findViewById(R.id.order_id);
            order_email=itemView.findViewById(R.id.email_order);
            order_date=itemView.findViewById(R.id.date_order);
            order_price=itemView.findViewById(R.id.total_price);
        }
    }
}
