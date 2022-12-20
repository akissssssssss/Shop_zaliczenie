package com.example.shop.Data;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    private FeedReaderContract(){}

    public static class FeedEntry implements BaseColumns{
        public static final String TABLE_NAME="accounts";
        public static final String COLUMN_NAME="name";
        public static final String COLUMN_SURNAME="surname";
        public static final String COLUMN_EMAIL="email";
        public static final String COLUMN_PHONE="phone";
        public static final String COLUMN_PASS="password";
    }

    public static class FeedEntry2 implements BaseColumns{
        public static final String TABLE_NAME="items";
        public static final String COLUMN_NAME="item_name";
        public static final String COLUMN_PRODUCTS_DESCRIPTION = "description";
        public static final String COLUMN_PRICE="price";
        public static final String COLUMN_PHOTO="photo";
        public static final String COLUMN_CATEGORY="category";
    }

    public static class FeedEntry4 implements BaseColumns{
        public static final String TABLE_NAME="category";
        public static final String COLUMN_CATEGORY_NAME="category_name";
    }

    public static class FeedEntry3 implements BaseColumns{
        public static final String TABLE_NAME="orders";
        public static final String COLUMN_NAME="account_id";
        public static final String COLUMN_ITEMS="items";
        public static final String COLUMN_PRICE="price";
        public static final String COLUMN_DATA="data";
    }
    public static class FeedEntry5 implements BaseColumns{
        public static final String TABLE_NAME="cart";
        public static final String COLUMN_NAME="account_id";
        public static final String COLUMN_ITEM_ID="item_id";
        public static final String COLUMN_QUANTITY="quantity";
    }
}
