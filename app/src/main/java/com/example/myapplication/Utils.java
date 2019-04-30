package com.example.myapplication;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;

import java.util.Locale;

public class Utils {
    private static final String KEY_APP_LANGUAGE = "KEY_APP_LANGUAGE";

    //更改App语言
    public static void changeAppLanguage(Context context, Locale locale, boolean persistence) {

        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
        resources.updateConfiguration(configuration, metrics);
        if (persistence) {
            saveLanguageSetting(context, locale);
        }
    }


    public static boolean isSameWithSetting(Context context) {
        String lang = context.getResources().getConfiguration().locale.getLanguage();
        return lang.equals(getAppLanguage(context));
    }


    //App 语言持久化
    public static void saveLanguageSetting(Context context,Locale locale){

        SPUtils.put(context,KEY_APP_LANGUAGE,locale.getLanguage());

    }

    public static String getAppLanguage(Context context){
        return (String)SPUtils.get(context,KEY_APP_LANGUAGE,Locale.getDefault().getLanguage());
    }



    public static Locale getAppLocale(Context context){
        String lang = (String)SPUtils.get(context,KEY_APP_LANGUAGE,Locale.getDefault().getLanguage());
        if(!lang.equals(Locale.SIMPLIFIED_CHINESE.getLanguage())&&!lang.equals(Locale.US.getLanguage())){
            lang = Locale.SIMPLIFIED_CHINESE.getLanguage();
        }
        Locale locale = new Locale(lang);
        return locale;
    }
}
