package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class PayActivity extends AppCompatActivity {
    private final String TAG = "PayActivity-----------";
    private String pay_amount ;
    private String pay_function ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.tv);
        textView.setText("PayActivity");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e(TAG,"===========");

            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        Bundle extra = intent.getExtras();
        Log.e(TAG,"start");
        if(extra != null){
            String pay_amount = extra.getString("pay_amount");
            String pay_function = extra.getString("pay_function");
            String pay_camera_mode = extra.getString("pay_camera_mode");
            String pay_print_receipt_id = extra.getString("pay_print_receipt_id");
            String pay_resp_code = extra.getString("pay_resp_code");
            String pay_resp_error_desc = extra.getString("pay_resp_error_desc");
            Log.e(TAG,pay_amount);
            Log.e(TAG,pay_function);
            Log.e(TAG,pay_camera_mode);
            Log.e(TAG,pay_print_receipt_id);
            Log.e(TAG,pay_resp_code);
            Log.e(TAG,pay_resp_error_desc);
            Log.e(TAG,"end");
        }
    }
}
