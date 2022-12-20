package com.example.shop.View;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shop.Data.Fragments;
import com.example.shop.MainActivity;
import com.example.shop.R;

public class AccountFragment extends Fragment {
    MainActivity mainActivity;
    TextView username;
    Button logout;
    CheckBox remember;

    public AccountFragment() {
       super (R.layout.fragment_account);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        mainActivity.bottomNavigationView.setVisibility(View.VISIBLE);

        username = view.findViewById(R.id.Username);
        logout = view.findViewById(R.id.logout_button);
        remember = view.findViewById(R.id.checkbox);

        logout.setOnClickListener(v -> {
            mainActivity.setUser(null);
            mainActivity.showFragment(Fragments.FRAGMENT_LOGIN);
        });
        remember.setOnClickListener(v -> {mainActivity.Save(!mainActivity.isSaved());});
        if (mainActivity.isSaved()){
            remember.setChecked(true);
        } else {
            remember.setChecked(false);
        }

        username.setText(mainActivity.getUser().getName()+" "+mainActivity.getUser().getSurname());


    }
}
