package com.example.shop.Data;

import androidx.fragment.app.Fragment;

import com.example.shop.View.AccountFragment;
import com.example.shop.View.CartFragment;
import com.example.shop.View.HomeFragment;
import com.example.shop.View.LoginFragment;
import com.example.shop.View.RegisterFragment;
import com.example.shop.View.OrdersFragment;

public class Fragments {
    public static final int FRAGMENT_HOME = 0;
//    public static final int FRAGMENT_SEARCH = 1;
    public static final int FRAGMENT_CART = 2;
    public static final int FRAGMENT_ORDERS = 3;
    public static final int FRAGMENT_ACCOUNT = 4;
    public static final int FRAGMENT_LOGIN = 5;
    public static final int FRAGMENT_REGISTER = 6;

    public static Fragment getFragment(int fragmentId) {
        switch (fragmentId) {
            case FRAGMENT_HOME:
                return new HomeFragment();
//            case FRAGMENT_SEARCH:
//                return new SearchFragment();
            case FRAGMENT_CART:
                return new CartFragment();
            case FRAGMENT_ORDERS:
                return new OrdersFragment();
            case FRAGMENT_ACCOUNT:
                return new AccountFragment();
            case FRAGMENT_LOGIN:
                return new LoginFragment();
            case FRAGMENT_REGISTER:
                return new RegisterFragment();
            default:
                return new HomeFragment();
        }
    }
}
