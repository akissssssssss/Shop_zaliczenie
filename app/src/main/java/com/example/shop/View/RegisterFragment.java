package com.example.shop.View;

import android.os.Bundle;
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

public class RegisterFragment extends Fragment {
    MainActivity mainActivity;
    EditText name;
    EditText surname;
    EditText email;
    EditText password;
    EditText phone;
    Button register;
    TextView login;


    public RegisterFragment() {
        super(R.layout.fragment_register);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainActivity=(MainActivity) getActivity();
        mainActivity.bottomNavigationView.setVisibility(View.GONE);

        name=view.findViewById(R.id.reg_username);
        surname=view.findViewById(R.id.reg_surname);
        email=view.findViewById(R.id.reg_email);
        password=view.findViewById(R.id.reg_password);
        register=view.findViewById(R.id.btn_register);
        phone=view.findViewById(R.id.reg_phone);
        login=view.findViewById(R.id.orLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,Fragments.getFragment(Fragments.FRAGMENT_LOGIN)).commit();
            }
        });
        register.setOnClickListener(v ->{
            String nameText=name.getText().toString();
            String surnameText=surname.getText().toString();
            String emailText=email.getText().toString();
            String passwordText=password.getText().toString();
            String phoneText=phone.getText().toString();
            if (nameText.isEmpty() || surnameText.isEmpty() || emailText.isEmpty() || passwordText.isEmpty()|| phoneText.isEmpty()) {
                Toast.makeText(mainActivity, getResources().getString(R.string.missing_info), Toast.LENGTH_SHORT).show();
            } else {

                User.registerUser(mainActivity, nameText, surnameText, emailText, phoneText, passwordText);
                int id= DbController.getId(emailText);
                mainActivity.setUser(new User(id,nameText, surnameText, emailText, phoneText));
                Toast.makeText(mainActivity, getResources().getString(R.string.registered), Toast.LENGTH_SHORT).show();
                mainActivity.showFragment(Fragments.FRAGMENT_ACCOUNT);
                mainActivity.bottomNavigationView.setSelectedItemId(R.id.navigation_account);

            }
        });

    }
}
