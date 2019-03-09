package com.android.mydemoapp.util;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.android.mydemoapp.base.BaseApp;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zhujc on 2018/11/5.
 */

public  class NotchScreenUtils {
    //华为刘海屏适配官方文档 https://developer.huawei.com/consumer/cn/devservice/doc/50114?from=timeline
    //小米刘海屏适配官方文档 https://dev.mi.com/console/doc/detail?pId=1293
    //OPPO刘海屏适配官方文档 https://open.oppomobile.com/wiki/doc#id=10159
    //VIVO刘海屏适配官方文档 https://dev.vivo.com.cn/documentCenter/doc/103

    /**
     * 获取刘海高度如果没有刘海返回状态栏高度
     *
     * @return 状态栏高度
     */
    public static int getNotchHeight() {
        int height=0;
        height=getOnlyNotchHeight();//获取纯刘海高度
        if (height>0){
            return height;
        }else {
            return getStatusBarHeight();
        }
    }
    /**
     * 获取纯刘海高度
     *
     * @return 状态栏高度
     */
    public static int getOnlyNotchHeight() {
        int height=0;
        if (hasNotchAtHuawei()){
            //华为
            height= getHuaweiNotchHeight();
        }else if (getInt()==1){
            //小米
            height = getMiNotchHeight();
        }else if (hasNotchAtOPPO()){
            //OPPO 固定80像素
            height=80;
        }else if (hasNotchAtVivo()){
            //VIVO 固定27dp
            height= (int) (27* BaseApp.getContext().getResources().getDisplayMetrics().density+0.5f);
        }
        return height;
    }

    private static int getMiNotchHeight() {
        int resourceId =BaseApp.getContext().getResources().getIdentifier("notch_height", "dimen", "android");
        return BaseApp.getContext().getResources().getDimensionPixelSize(resourceId);
    }

    private static int getHuaweiNotchHeight() {
        int[] ret = new int[]{0, 0};

        try {

            ClassLoader cl = BaseApp.getContext().getClassLoader();

            Class HwNotchSizeUtil = cl.loadClass("com.huawei.android.util.HwNotchSizeUtil");

            Method get = HwNotchSizeUtil.getMethod("getNotchSize");

            ret = (int[]) get.invoke(HwNotchSizeUtil);

        } catch (ClassNotFoundException e) {

            Log.e("NotchScreenUtils", "getNotchSize ClassNotFoundException");

        } catch (NoSuchMethodException e) {

            Log.e("NotchScreenUtils", "getNotchSize NoSuchMethodException");

        } catch (Exception e) {

            Log.e("NotchScreenUtils", "getNotchSize Exception");

        } finally {

            return ret[1];

        }
    }

    /**
     * 获取状态栏高度
     *
     * @return 状态栏高度
     */
    public static int getStatusBarHeight() {
        // 获得状态栏高度
        int resourceId =BaseApp.getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        return BaseApp.getContext().getResources().getDimensionPixelSize(resourceId);
    }
    /**
     * 判断是否是刘海屏
     * @return
     */
    public static boolean hasNotchScreen(){
        if (getInt() == 1 || hasNotchAtHuawei() || hasNotchAtOPPO()
                || hasNotchAtVivo() ){
            return true;
        }

        return false;
    }

    /**
     * Android P 刘海屏判断
     * @param activity
     * @return
     */
//    public static DisplayCutout isAndroidP(Activity activity){
//        View decorView = activity.getWindow().getDecorView();
//        if (decorView != null && android.os.Build.VERSION.SDK_INT >= 28){
//            WindowInsets windowInsets = decorView.getRootWindowInsets();
//            if (windowInsets != null)
//                return windowInsets.getDisplayCutout();
//        }
//        return null;
//    }

    /**
     * 小米刘海屏判断.
     * @return 0 if it is not notch ; return 1 means notch
     * @throws IllegalArgumentException if the key exceeds 32 characters
     */
    public static int getInt() {
        int result = 0;
        if (isXiaomi()){
            try {
                ClassLoader classLoader = BaseApp.getContext().getClassLoader();
                @SuppressWarnings("rawtypes")
                Class SystemProperties = classLoader.loadClass("android.os.SystemProperties");
                //参数类型
                @SuppressWarnings("rawtypes")
                Class[] paramTypes = new Class[2];
                paramTypes[0] = String.class;
                paramTypes[1] = int.class;
                Method getInt = SystemProperties.getMethod("getInt", paramTypes);
                //参数
                Object[] params = new Object[2];
                params[0] = new String("ro.miui.notch");
                params[1] = new Integer(0);
                result = (Integer) getInt.invoke(SystemProperties, params);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 华为刘海屏判断
     * @return
     */
    public static boolean hasNotchAtHuawei() {
        boolean ret = false;
        try {
            ClassLoader classLoader = BaseApp.getContext().getClassLoader();
            Class HwNotchSizeUtil = classLoader.loadClass("com.huawei.android.util.HwNotchSizeUtil");
            Method get = HwNotchSizeUtil.getMethod("hasNotchInScreen");
            ret = (boolean) get.invoke(HwNotchSizeUtil);
        } catch (ClassNotFoundException e) {
            Log.e("NotchScreenUtils","hasNotchAtHuawei ClassNotFoundException");
        } catch (NoSuchMethodException e) {
            Log.e("NotchScreenUtils","hasNotchAtHuawei NoSuchMethodException");
        } catch (Exception e) {
            Log.e( "NotchScreenUtils","hasNotchAtHuawei Exception");
        } finally {
            return ret;
        }
    }

    public static final int VIVO_NOTCH = 0x00000020;//是否有刘海
    public static final int VIVO_FILLET = 0x00000008;//是否有圆角

    /**
     * VIVO刘海屏判断
     * @return
     */
    public static boolean hasNotchAtVivo() {
        boolean ret = false;
        try {
            ClassLoader classLoader = BaseApp.getContext().getClassLoader();
            Class FtFeature = classLoader.loadClass("android.util.FtFeature");
            Method method = FtFeature.getMethod("isFeatureSupport", int.class);
            ret = (boolean) method.invoke(FtFeature, VIVO_NOTCH);
        } catch (ClassNotFoundException e) {
            Log.e( "NotchScreenUtils","hasNotchAtVivo ClassNotFoundException");
        } catch (NoSuchMethodException e) {
           Log.e("NotchScreenUtils",  "hasNotchAtVivo NoSuchMethodException");
        } catch (Exception e) {
            Log.e(  "NotchScreenUtils","hasNotchAtVivo Exception");
        } finally {
            return ret;
        }
    }
    /**
     * OPPO刘海屏判断
     * @return
     */
    public static boolean hasNotchAtOPPO() {
        return  BaseApp.getContext().getPackageManager().hasSystemFeature("com.oppo.feature.screen.heteromorphism");
    }
    // 是否是小米手机
    public static boolean isXiaomi() {
        return "Xiaomi".equals(Build.MANUFACTURER);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void setChengjin(Activity activity){
        Window window = activity.getWindow();

        View decorView = window.getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
    }
}
