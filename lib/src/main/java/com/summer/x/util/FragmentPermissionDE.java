package com.summer.x.util;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.blankj.utilcode.util.ToastUtils;
import com.summer.x.base.ui.XFragment;

public class FragmentPermissionDE {

    public static final int reqcode = 12345;


    public boolean requestPermissions(XFragment fragment, String[] permissions){
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return true;
        }
        if (checkPermissionAllGranted(fragment,permissions)) {
            return true;
        }
        fragment.requestPermissions( permissions, reqcode);
        return false;
    }


    public boolean onRequestPermissionsResult(Fragment fragment, int requestCode, int[] grantResults) {
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
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                fragment.getActivity().startActivity(intent);
                fragment.getActivity().finish();
            }
        }
        return false;
    }

    private static  boolean checkPermissionAllGranted(Fragment fragment, String[] permissions) {
        for (String s : permissions) {
            if (ContextCompat.checkSelfPermission(fragment.getContext(), s) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

}