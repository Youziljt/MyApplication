package com.example.myapplication;

import android.util.Log;
import android.view.View;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Calendar;

@Aspect
public class SingleClickAspect {
    private static int TIME_TAG = R.id.click_time;
    private static final int MIN_CLICK_DELAY_TIME = 600;//间隔时间600ms

    @Pointcut("execution(@com.example.myapplication.annotation.SingleClick * *(..))")//根据SingleClick注解找到方法切入点
    public void methodAnnotated() {
    }

    @Around("methodAnnotated()")//在连接点进行方法替换
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.e("SingleClickAspect", "lastClickTime:-------" );
        View view = null;
        for (Object arg : joinPoint.getArgs())
            if (arg instanceof View) view = (View) arg;
        if (view != null) {
            Object tag = view.getTag(TIME_TAG);
            long lastClickTime = ((tag != null) ? (long) tag : 0);
            Log.e("SingleClickAspect", "lastClickTime:" + lastClickTime);
            long currentTime = Calendar.getInstance().getTimeInMillis();
            if (currentTime - lastClickTime > MIN_CLICK_DELAY_TIME) {//过滤掉600毫秒内的连续点击
                view.setTag(TIME_TAG, currentTime);
                Log.e("SingleClickAspect", "currentTime:" + currentTime);
                joinPoint.proceed();//执行原方法
            }
        }
    }
}
