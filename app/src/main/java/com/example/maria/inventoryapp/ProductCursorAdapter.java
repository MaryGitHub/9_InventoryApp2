package com.example.maria.inventoryapp;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maria.inventoryapp.data.ProductContract;
import com.example.maria.inventoryapp.data.ProductContract.ProductEntry;

public class ProductCursorAdapter extends CursorAdapter {

    private static final String LOG_TAG = ProductCursorAdapter.class.getSimpleName();
    private final static String UPDATE_URI="URI for update: ";
    public ProductCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        // Inflate a list item view using the layout specified in list_item.xml
        return LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        // Find individual views in the list item layout
        TextView nameTextView = view.findViewById(R.id.name);
        TextView priceTextView = view.findViewById(R.id.price);
        final TextView quantityTextView = view.findViewById(R.id.quantity);

        // Find the columns of products
        int nameColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME);
        int priceColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_PRICE);
        int quantityColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_QUANT);

        // Read the product attributes from the Cursor for the current product
        String productName = cursor.getString(nameColumnIndex);
        String productPrice = cursor.getString(priceColumnIndex);
        final String productQuant = cursor.getString(quantityColumnIndex);

        // If the cake price is empty string or 0, then say "Ask for a price", so the TextView isn't blank.
        if (TextUtils.isEmpty(productPrice)) {
            productPrice = context.getString(R.string.ask_price);
        }

        final int quantityTextViewSell = cursor.getInt(quantityColumnIndex);
        final Uri uri = ContentUris.withAppendedId(ProductEntry.CONTENT_URI,cursor.getInt(cursor.getColumnIndexOrThrow(ProductEntry._ID)));


        // Update the TextViews with the attributes for the current product
        nameTextView.setText(productName);
        priceTextView.setText(productPrice);
        quantityTextView.setText(productQuant);

        // If saleButton is pushed reduce the quantity of 1 item
        //  inform the user with a toast message that 1 product is sold
        Button saleButton = view.findViewById(R.id.button_sale);
        saleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantityTextViewSell > 0) {
                    int quantityTextViewNew = quantityTextViewSell - 1;

                    ContentValues values = new ContentValues();
                    values.put(ProductEntry.COLUMN_PRODUCT_QUANT, quantityTextViewNew);
                    context.getContentResolver().update(uri, values, null, null);
                    Log.d(LOG_TAG, UPDATE_URI + uri);
                } else {
                    Toast.makeText(context, context.getString(R.string.sold_out), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}