package com.test.MaliciousApp;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class Query extends AppCompatActivity {
    private static final String TAG = "Query";
    private final Context context;
    private ContentResolver contentResolver;
    private Uri uri;
    private String projection;
    private String selection;
    private String selectionArgs;
    private String sortOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public Query() {
        this.context = null;
    }

    public Query(Context context, ContentResolver contentResolver, Uri uri, String projection,
                 String selection, String selectionArgs, String sortOrder) {
        this.context = context;
        this.contentResolver = contentResolver;
        this.uri = uri;
        this.projection = projection.isEmpty() ? null : projection;
        this.selection = selection.isEmpty() ? null : selection;
        this.selectionArgs = selectionArgs.isEmpty() ? null : selectionArgs;
        this.sortOrder = sortOrder.isEmpty() ? null : sortOrder;
    }

    public void doQuery() {
        String[] arrayProjection = projection != null ?  projection.split(",") : null;
        String[] arraySelectionArgs = selectionArgs != null ? selectionArgs.split(",") : null;
        try {
//            query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
            Cursor cursor = contentResolver.query(uri, arrayProjection, selection, arraySelectionArgs, sortOrder);
            if (cursor == null){
                MyUtil.myToast(context, "Cursor is Null");
                return;
            }
            String result = cursorToString(cursor);
            sendResultActivity(result);
        } catch (Exception e){
            MyUtil.myToast(context, "Failed !");
            e.printStackTrace();
        }
    }

    private String cursorToString(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (cursor.moveToNext()){
            for (int i=0; i<cursor.getColumnCount(); i++){
                String value = cursor.getString(i);
                stringBuilder.append(value).append("\t");
            }
            stringBuilder.append("\n\n");
        }
        Log.i(TAG, stringBuilder.toString());
        return stringBuilder.toString();
    }

    private void sendResultActivity(String result) {
        Intent intent = new Intent(context, Result.class);
        intent.putExtra("str", result);
        context.startActivity(intent);
    }

}