package com.example.shop.View;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shop.Data.DbController;
import com.example.shop.Data.Product;
import com.example.shop.ItemAdapter;
import com.example.shop.MainActivity;
import com.example.shop.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private MainActivity mainActivity;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    List<Product> products;
    DbController controller;

    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        products=new ArrayList<>();
        controller=new DbController(mainActivity);
        products=controller.getProducts();

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mainActivity, DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(mainActivity,R.drawable.divider));

        linearLayoutManager=new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView=view.findViewById(R.id.recycler_view);
        ItemAdapter itemAdapter=new ItemAdapter(mainActivity,products);
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(itemAdapter);


    }

}
