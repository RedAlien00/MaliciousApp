package com.test.MaliciousApp;

import android.content.Context;
import android.widget.Toast;
import android.os.Handler;

public class MyUtil {
    private static final Handler mHandler = new android.os.Handler();
    private static Toast toast = null;

    public static void myToast(Context context, String input){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (toast != null){
                    toast.cancel();
                }
                toast = Toast.makeText(context, input, Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }
}
