package com.example.maria.inventoryapp.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class ProductContract {

    public static final String CONTENT_AUTHORITY = "com.example.maria.inventoryapp";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_PRODUCTS = "products";

    private ProductContract() {
    }

    public static final class ProductEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PRODUCTS);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PRODUCTS;
        /**
         * Name of database table
         */
        public final static String TABLE_NAME = "products";

        //Unique ID number: INTEGER
        public final static String _ID = BaseColumns._ID;
        //Name: STRING
        public final static String COLUMN_PRODUCT_NAME = "name";
        //Price: FLOAT
        public final static String COLUMN_PRODUCT_PRICE = "price";
        //Quantity: INTEGER
        public final static String COLUMN_PRODUCT_QUANT = "quantity";
        //Supplier Name: STRING
        public final static String COLUMN_PRODUCT_SUPP_NAME = "supp_name";
        //Supplier Phone Number: STRING
        public final static String COLUMN_PRODUCT_SUPP_PHONE = "supp_phone";
    }
}