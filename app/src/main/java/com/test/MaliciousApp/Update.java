package com.test.MaliciousApp;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Update extends AppCompatActivity {
    private static final String TAG = "Update";
    private Context context;
    private ContentResolver contentResolver;
    private Uri uri;
    private String column;
    private String value;
    private String selection;
    private String selectionArgs;

    public Update(){};

    public Update(Context context, ContentResolver contentResolver, Uri uri, String column, String value, String selection, String selectionArgs) {
        this.context = context;
        this.contentResolver = contentResolver;
        this.uri = uri;
        this.column = column.isEmpty() ? null : column;
        this.value = value.isEmpty() ? null : value;
        this.selection = selection.isEmpty() ? null : selection;
        this.selectionArgs = selectionArgs.isEmpty() ? null : selectionArgs;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void doUpdate() {
        column = column != null ? column.trim() : null;
        value = value != null ? value.trim() : null;
        String[] arraySelectionArgs = selectionArgs != null ? selectionArgs.split(",") : null;

        ContentValues contentValues = new ContentValues();
        contentValues.put(column, value);

        try {
//        update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
            int updatedRow = contentResolver.update(uri, contentValues, selection, arraySelectionArgs);
            if (updatedRow <= 0){
                MyUtil.myToast(context, "row is Null");
                return;
            }
            sendResultActivity(updatedRow);

        } catch (Exception e){
            MyUtil.myToast(context, "Failed !");
            e.printStackTrace();
        }
    }

    private void sendResultActivity(int updatedRow) {
        Intent intent = new Intent(context, Result.class);
        intent.putExtra("row", updatedRow);
        context.startActivity(intent);
    }


}