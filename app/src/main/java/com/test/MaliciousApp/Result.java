package com.test.MaliciousApp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Result extends AppCompatActivity {
    private static final String TAG = "Result";
    protected Intent intent = null;
    protected TextView TV_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }
        TV_result = findViewById(R.id.TV_result);

        intent = getIntent();
        if (intent != null){
            Bundle bundle = intent.getExtras();
            if( bundle != null){
                setTV_result(intent.getExtras());
            }
        }
    }
    // 둘다 str
    // Insert 클래스 : intent.putExtra("str", resultUri.toString());
    // Query 클래스 : intent.putExtra("str", result);
    private void setTV_result(Bundle bundle) {
        Log.i(TAG, "setTV_result()");
        StringBuilder stringBuilder = new StringBuilder();
        String result = "null";

        if (bundle.containsKey("str")){
            result = bundle.getString("str");
        } else if (bundle.containsKey("row")) {
            int a = bundle.getInt("row");
            stringBuilder.append("Updated row : ");
            stringBuilder.append(a);
            result = stringBuilder.toString();
        }
        TV_result.setText(result);
    }



    // Update 클래스 : intent.putExtra("row", updatedRow);
    // Delete 클래스 : intent.putExtra("row", updatedRow);


}