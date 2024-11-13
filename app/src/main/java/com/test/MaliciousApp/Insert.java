package com.test.MaliciousApp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Insert extends AppCompatActivity {
    private static final String TAG = "Insert";
    protected Context context;
    protected ContentResolver contentResolver;
    protected Uri uri;
    protected String column;
    protected String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public Insert(){};

    public Insert(Context context, ContentResolver contentResolver, Uri uri,
                  String column, String value) {
        this.context = context;
        this.uri = uri;
        this.contentResolver = contentResolver;
        this.column = column.isEmpty() ? null : column;
        this.value = value.isEmpty() ? null : value;
    }

    public void doInsert(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(column, value);

        try {
//            ContentResolver.insert(Uri uri, ContentValues values)
            Uri resultUri = contentResolver.insert(uri, contentValues);
            if (resultUri == null){
                MyUtil.myToast(context, "Uri is Null");
                return;
            }
            sendResultActivity(resultUri);
        } catch (Exception e){
            MyUtil.myToast(context, "Failed !");
            e.printStackTrace();
        }
    }

    private void sendResultActivity(Uri resultUri) {
        Intent intent = new Intent(context, Result.class);
        intent.putExtra("str", resultUri.toString());
        context.startActivity(intent);
    }

}