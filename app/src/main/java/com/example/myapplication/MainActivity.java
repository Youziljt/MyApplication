package com.example.myapplication;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.annotation.SingleClick;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity-----------";
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setLange();在加载之前初始化本地设置
        setLange();
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv1);

        findViewById(R.id.search_close_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Locale appLocale = Utils.getAppLocale(MainActivity.this);
                String appLanguage = Utils.getAppLanguage(MainActivity.this);
                //更改app语言  布尔值为true保存进入SPUTils
                Utils.changeAppLanguage(MainActivity.this,appLocale,true);
                changeLange();
            }
        });
        textView.setText(getString(R.string.lange));
    }

    @SingleClick
    public void changePage(View view){
        Log.e(TAG,"-------------------------");
    }

    public void changeLange(View view){
        changeLange();
    }

    public void setLange(){
        String lan = Utils.getAppLanguage(this);
        if(lan.equals("en")){
            setLocale(Locale.US);
        }else if(lan.equals("zh")){
            setLocale(Locale.SIMPLIFIED_CHINESE);
        }
    }

    private void changeLange(){
        String lan = Utils.getAppLanguage(this);
        Log.e("本地语言类型","lan==========="+lan);
        if(lan.equals("zh")){
            setLocale(Locale.US);
        }else if(lan.equals("en")){
            setLocale(Locale.SIMPLIFIED_CHINESE);
        }
        //重新刷新
        recreate();
    }


    public void setLocale(Locale myLocale){
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Utils.saveLanguageSetting(this,myLocale);
    }

}
