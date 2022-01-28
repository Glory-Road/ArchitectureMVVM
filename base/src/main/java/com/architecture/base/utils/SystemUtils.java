package com.architecture.base.utils;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * 一些系统工具，比如调节亮度啥的
 * <p>
 * <p>
 * Created by Administrator on 2016/8/16 0016.
 */
public class SystemUtils {

    /**
     * 调节当前应用的屏幕亮度
     *
     * @param brightness 亮度值，0-255, 小于0表示使用系统亮度
     */
    public static void setScreenBrightness(Window window, int brightness) {
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (brightness < 0){
            attributes.screenBrightness = -1;
        } else {
            attributes.screenBrightness = brightness / 255.f;
        }
        window.setAttributes(attributes);
    }

    /**
     * 获取系统默认屏幕亮度
     *
     * @param context
     * @return
     */
    public static int getScreenBrightness(Context context) {
        int value = 0;
        ContentResolver cr = context.getContentResolver();
        try {
            value = Settings.System.getInt(cr, Settings.System.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {

        }
        return value;
    }

    /**
     * 获取应用程序人类可读的版本
     *
     * @param context context
     * @return
     */
    public static String getApplicationVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取应用版本序号
     *
     * @param context context
     * @return 版本序号
     */
    public static int getApplicationVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取电量百分比
     *
     * @param context
     * @return
     */
    public static float getDevicePower(Context context, BroadcastReceiver receiver) {
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(receiver, filter);
        if (batteryStatus != null) {
            int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            return level / (float) scale;
        }
        return 1;
    }

    public static void hideSystemUiVisibility(View view) {
        int visibility = view.getSystemUiVisibility();
        visibility |= View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            visibility |= View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            view.setSystemUiVisibility(visibility);
        }
    }

    public static void showSystemUiVisibility(View view) {
        view.postDelayed(() -> {
            int visibility = view.getSystemUiVisibility();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                visibility |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                visibility &= ~View.SYSTEM_UI_FLAG_FULLSCREEN;
                visibility &= ~View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
                view.setSystemUiVisibility(visibility);
            }
        }, 50);
    }

    public static void setImeVisibility(View view, boolean visible) {
        IBinder windowToken = view.getWindowToken();
        InputMethodManager imm = (InputMethodManager) view.getContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (visible) {
            if (imm != null) {
                imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
            }
        } else {
            if (imm != null) {
                imm.hideSoftInputFromWindow(windowToken, 0);
            }
        }
    }

    public static void startDownloadApk(Context context, String url, String name) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setMimeType("application/vnd.android.package-archive");
        request.allowScanningByMediaScanner();
        request.setTitle(name);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, name);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        //设置允许使用的网络类型，这里是移动网络和wifi都可以
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);

        long id = downloadManager.enqueue(request);
    }


    public static void installApk(Context context, Uri uri) {
        Intent installIntent = new Intent(Intent.ACTION_VIEW);
        installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        installIntent.setDataAndType(uri, "application/vnd.android.package-archive");
        installIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(installIntent);
    }

    /**
     * 启动到应用商店app详情界面
     *
     * @param appPkg  App的包名
     * @param shopPkg 应用商店包名 ,如果为"" 则由系统弹出应用商店
     *                SystemUtils.goAppShop(requireContext(),requireActivity().packageName,"com.android.vending")
     */

    public static void goAppShop(Context context, String appPkg, String shopPkg) {
        if (TextUtils.isEmpty(appPkg)) {
            return;
        }
        try {
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(shopPkg)) {
                intent.setPackage(shopPkg);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            // 如果没有该应用商店，则显示系统弹出的应用商店列表供用户选择
            goAppShop(context, appPkg, "");
        }
    }

    public static void gotoGooglePlay(Context context, String packageName) {
        Intent intent = new Intent(Intent.ACTION_VIEW)
                .setData(Uri.parse("https://play.google.com/store/apps/details?id=" + packageName))
                .setPackage("com.android.vending");
        try {
            context.startActivity(intent);
        } catch (Exception e) {
//            ToastUtils.showToast(context, "找不到Google Play");
        }
    }

    /**
     * 判断当前设备时间是否错乱
     *
     * @param context
     * @return
     */
    public static boolean isCurrentTimeBias(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);
            long updatedTime = packageInfo.lastUpdateTime;
            return System.currentTimeMillis() > updatedTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return true;
    }

    /**
     * 获取应用安装时间
     *
     * @param context
     * @return
     */
    public static long getAppInstallTime(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);

            return packageInfo.firstInstallTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return System.currentTimeMillis();
    }

    /**
     * 是否是首次安装
     *
     * @param context
     * @return
     */
    public static boolean isNewInstall(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager()
                    .getPackageInfo(context.getPackageName(), 0);

            return packageInfo.firstInstallTime == packageInfo.lastUpdateTime;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 获取屏幕宽度(px)
     */
    public static int getScreenWidth(Resources resources) {
        return resources.getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕高度(px)
     */
    public static int getScreenHeight(Resources resources) {
        return resources.getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕真实宽度（包含底部导航栏）
     */
    public static int getScreenRealWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        final Display display = windowManager.getDefaultDisplay();
        Point outPoint = new Point();
        if (Build.VERSION.SDK_INT >= 19) {
            // 可能有虚拟按键的情况
            display.getRealSize(outPoint);
        } else {
            // 不可能有虚拟按键
            display.getSize(outPoint);
        }
        int mRealSizeWidth;//手机屏幕真实宽度
        mRealSizeWidth = outPoint.x;
        return mRealSizeWidth;
    }

    /**
     * 获得屏幕真实高度（包含底部导航栏）
     */
    public static int getScreenRealHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        final Display display = windowManager.getDefaultDisplay();
        Point outPoint = new Point();
        if (Build.VERSION.SDK_INT >= 19) {
            // 可能有虚拟按键的情况
            display.getRealSize(outPoint);
        } else {
            // 不可能有虚拟按键
            display.getSize(outPoint);
        }
        int mRealSizeHeight;//手机屏幕真实高度
        mRealSizeHeight = outPoint.y;
        return mRealSizeHeight;
    }


}
