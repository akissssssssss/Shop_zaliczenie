package com.example.shop.View;

import static com.example.shop.Data.Fragments.getFragment;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shop.Data.DbController;
import com.example.shop.Data.Fragments;
import com.example.shop.Data.User;
import com.example.shop.MainActivity;
import com.example.shop.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LoginFragment extends Fragment {
    MainActivity mainActivity;
    EditText email;
    EditText password;
    Button login;
    TextView register;


    public LoginFragment() {
        super(R.layout.fragment_login);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainActivity=(MainActivity) getActivity();
        mainActivity.bottomNavigationView.setVisibility(View.GONE);

        email=view.findViewById(R.id.login_username);
        password=view.findViewById(R.id.login_password);
        login=view.findViewById(R.id.btn_login);
        register=view.findViewById(R.id.orRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,getFragment(Fragments.FRAGMENT_REGISTER)).commit();
            }
        });
        login.setOnClickListener(v ->{
            String emailText=email.getText().toString();
            String passwordText=password.getText().toString();
            if (emailText.isEmpty() || passwordText.isEmpty()) {
                Toast.makeText(mainActivity, getResources().getString(R.string.missing_info), Toast.LENGTH_SHORT).show();
            } else {
                if (DbController.loginUser(emailText,passwordText).getCount()>0){
                    Toast.makeText(mainActivity, getResources().getString(R.string.loggedin), Toast.LENGTH_SHORT).show();
                    String name= String.valueOf(DbController.getUsername(emailText));
                    String surname= String.valueOf(DbController.getSurname(emailText));
                    String phone= String.valueOf(DbController.getPhone(emailText));
                    int id= DbController.getId(emailText);
                    mainActivity.setUser(new User(id,name,surname,emailText,phone));
                    mainActivity.showFragment(Fragments.FRAGMENT_ACCOUNT);
                    mainActivity.bottomNavigationView.setSelectedItemId(R.id.navigation_account);
                }else{
                    Toast.makeText(mainActivity, getResources().getString(R.string.wrong_info), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
