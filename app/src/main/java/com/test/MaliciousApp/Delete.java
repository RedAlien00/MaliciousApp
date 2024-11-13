package com.test.MaliciousApp;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Delete extends AppCompatActivity {
    private static final String TAG = "Delete";
    private Context context;
    private ContentResolver contentResolver;
    private Uri uri;
    private String selection;
    private String selectionArgs;

    public Delete() {}

    public Delete(Context context, ContentResolver contentResolver, Uri uri, String selection, String selectionArgs) {
        this.context = context;
        this.contentResolver = contentResolver;
        this.uri = uri;
        this.selection = selection.isEmpty() ? null : selection;
        this.selectionArgs = selectionArgs.isEmpty() ? null : selection;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void doDelete() {
        String[] arraySelectionArgs = selectionArgs != null ? selectionArgs.split(",") : null;
        selection = selection != null ? selection.trim() : null;

//        delete(Uri uri, String selection, String[] selectionArgs) {
        try {
            int deletedRow = contentResolver.delete(uri, selection, arraySelectionArgs);
            if (deletedRow <= 0){
                MyUtil.myToast(context, "row is Null");
                return;
            }
            sendResultActivity(deletedRow);
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