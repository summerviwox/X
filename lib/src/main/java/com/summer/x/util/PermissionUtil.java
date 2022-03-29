package com.summer.x.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;
import com.summer.x.BuildConfig;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionUtil {

    public static final int reqcode = 12345;


    public boolean requestPermissions(Activity context, String[] permissions){
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return true;
        }
        if (checkPermissionAllGranted(context,permissions)) {
            return true;
        }
        ActivityCompat.requestPermissions(context, permissions, reqcode);
        return false;
    }


    public boolean onRequestPermissionsResult(Activity activity,int requestCode, int[] grantResults) {
        if (requestCode == reqcode) {
            boolean isallgranted = true;
            for (int grnat : grantResults) {
                if (grnat != PackageManager.PERMISSION_GRANTED) {
                    isallgranted = false;
                    break;
                }
            }
            if (isallgranted) {
                return true;
            } else {
                ToastUtils.showShort("请到应用权限管理中找到此应用并手动打开权限");
                gotoAppDetailIntent(activity);
//                Intent intent = new Intent(Settings.ACTION_SETTINGS);
//                activity.startActivity(intent);
//                activity.finish();
            }
        }
        return false;
    }

    private static  boolean checkPermissionAllGranted(Context context, String[] permissions) {
        for (String s : permissions) {
            if (ContextCompat.checkSelfPermission(context, s) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    public static void gotoAppDetailIntent(Activity activity) {
        Intent intent = new Intent();
        intent.setAction(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + activity.getPackageName()));
        activity.startActivityForResult(intent,1);
    }

    public static void gotoPermission(Activity activity) {

        String brand = Build.BRAND;//手机厂商
        if (TextUtils.equals(brand.toLowerCase(), "redmi") || TextUtils.equals(brand.toLowerCase(), "xiaomi")) {
            PermissionUtil.gotoMiuiPermission(activity);//小米
        } else if (TextUtils.equals(brand.toLowerCase(), "meizu")) {
            PermissionUtil.gotoMeizuPermission(activity);
        } else if (TextUtils.equals(brand.toLowerCase(), "huawei") || TextUtils.equals(brand.toLowerCase(), "honor")) {
            PermissionUtil.gotoHuaweiPermission(activity);
        } else {
            activity.startActivityForResult(PermissionUtil.getAppDetailSettingIntent(activity),1);
        }
    }


    /**
     * 跳转到miui的权限管理页面
     */
    private static void gotoMiuiPermission(Activity context) {
        try { // MIUI 8
            Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
            localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.PermissionsEditorActivity");
            localIntent.putExtra("extra_pkgname", context.getPackageName());
            context.startActivityForResult(localIntent,1);
        } catch (Exception e) {
            try { // MIUI 5/6/7
                Intent localIntent = new Intent("miui.intent.action.APP_PERM_EDITOR");
                localIntent.setClassName("com.miui.securitycenter", "com.miui.permcenter.permissions.AppPermissionsEditorActivity");
                localIntent.putExtra("extra_pkgname", context.getPackageName());
                context.startActivityForResult(localIntent,1);
            } catch (Exception e1) { // 否则跳转到应用详情
                context.startActivityForResult(getAppDetailSettingIntent(context),1);
            }
        }
    }

    /**
     * 跳转到魅族的权限管理系统
     */
    private static void gotoMeizuPermission(Activity context) {
        try {
            Intent intent = new Intent("com.meizu.safe.security.SHOW_APPSEC");
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.putExtra("packageName", BuildConfig.LIBRARY_PACKAGE_NAME);
            context.startActivityForResult(intent,1);
        } catch (Exception e) {
            e.printStackTrace();
            context.startActivityForResult(getAppDetailSettingIntent(context),1);
        }
    }

    /**
     * 华为的权限管理页面
     */
    private static void gotoHuaweiPermission(Activity context) {
        try {
            Intent intent = new Intent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            ComponentName comp = new ComponentName("com.huawei.systemmanager", "com.huawei.permissionmanager.ui.MainActivity");//华为权限管理
            intent.setComponent(comp);
            context.startActivityForResult(intent,1);
        } catch (Exception e) {
            e.printStackTrace();
            context.startActivityForResult(getAppDetailSettingIntent(context),1);
        }

    }

    /**
     * 获取应用详情页面intent（如果找不到要跳转的界面，也可以先把用户引导到系统设置页面）
     */
    private static Intent getAppDetailSettingIntent(Activity context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));

        return localIntent;
    }

}