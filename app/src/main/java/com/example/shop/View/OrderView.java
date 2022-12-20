package com.example.shop.View;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.Data.DbController;
import com.example.shop.Data.Order;
import com.example.shop.ItemAdapter;
import com.example.shop.MainActivity;
import com.example.shop.OrderDetailsAdapter;
import com.example.shop.R;

import java.util.ArrayList;
import java.util.List;

public class OrderView extends Fragment {
    private RecyclerView recyclerView;
    private MainActivity mainActivity;
    private LinearLayoutManager linearLayoutManager;
    private List<Order> orders;
    private DbController controller;

    public OrderView() {
        super(R.layout.order_view);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        recyclerView=view.findViewById(R.id.recycler_view_order_details);
        orders=new ArrayList<>();
        controller=new DbController(mainActivity);
        orders=controller.getOrdersById(getArguments().getInt("orderId")+1);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mainActivity, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(mainActivity,R.drawable.divider));
        linearLayoutManager=new LinearLayoutManager(getActivity().getApplicationContext());

        OrderDetailsAdapter orderDetailsAdapter=new OrderDetailsAdapter(mainActivity,orders);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(orderDetailsAdapter);
    }
}
