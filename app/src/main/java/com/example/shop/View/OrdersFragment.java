package com.example.shop.View;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.Data.DbController;
import com.example.shop.Data.Order;
import com.example.shop.Data.User;
import com.example.shop.MainActivity;
import com.example.shop.OrdersAdapter;
import com.example.shop.R;

import java.util.ArrayList;
import java.util.List;

public class OrdersFragment extends Fragment {
    MainActivity mainActivity;
    RecyclerView recyclerView;
    List<Order> orders;
    DbController dbController;
    User user;


    public OrdersFragment() {
        super(R.layout.fragment_orders);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainActivity=(MainActivity)getActivity();
        user=mainActivity.getUser();
        orders=new ArrayList<>();
        dbController=new DbController(mainActivity);

        orders=dbController.getOrdersByUSerId(user.getId());
        recyclerView=view.findViewById(R.id.recycler_view_orders);
        OrdersAdapter ordersAdapter=new OrdersAdapter(mainActivity,orders);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mainActivity, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(mainActivity, R.drawable.divider));
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(ordersAdapter);

    }

}
