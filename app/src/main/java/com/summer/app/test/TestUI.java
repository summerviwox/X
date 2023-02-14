package com.summer.app.test;

import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.lifecycle.Observer;

import com.blankj.utilcode.util.LogUtils;
import com.summer.app.R;
import com.summer.app.databinding.ActivityTestBinding;
import com.summer.x.base.ui.UI;
import com.summer.x.base.ui.DA;

/**
 * tangjie 2023/1/28 11:33
 **/
public class TestUI extends UI<ActivityTestBinding> {

    public static final String TAG = "TestUI";


    @Override
    public void initUI(DA va) {
        super.initUI(va);
        TestDA testDA = (TestDA) va;
        testDA.text.observe(getXActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer s) {
                getUI().edit.setText(s+"");
                LogUtils.e(s);
            }
        });
    }

    @Override
    public void refreshUI(DA va) {
        super.refreshUI(va);
        Rect rect = new Rect();
        getUI().edit.getWindowVisibleDisplayFrame(rect);
        LogUtils.e(rect);
        getXActivity().getWindow().setBackgroundDrawableResource(R.drawable.ic_launcher_background);
        ViewGroup decorView = (ViewGroup) getXActivity().getWindow().getDecorView();
        //decorView.setVisibility(View.GONE);
        decorView.setBackgroundColor(getXActivity().getResources().getColor(R.color.darkorchid));
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager)getXActivity().getApplication().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displayMetrics);
        DisplayMetrics displayMetrics1 = new DisplayMetrics();
        getXActivity().getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics1);
        LogUtils.e(decorView,decorView.getMeasuredHeight(),displayMetrics.heightPixels,displayMetrics1.heightPixels,getXActivity().getResources().getDisplayMetrics().heightPixels);
//        for(int i=0;i<decorView.getChildCount();i++){
//            LogUtils.e(TAG,decorView.getChildAt(i),decorView.getChildAt(i).getLayoutParams().height);
//            if(! (decorView.getChildAt(i) instanceof ViewGroup)){
//                break;
//            }
//            ViewGroup viewGroup = (ViewGroup) decorView.getChildAt(i);
//            for(int j=0;j<viewGroup.getChildCount();j++){
//                if(j==0){
//                    ViewStub viewStub = (ViewStub) viewGroup.getChildAt(j);
//                    viewStub.setVisibility(View.GONE);
//                    viewGroup.getChildAt(j).setBackgroundColor(getXActivity().getResources().getColor(R.color.aqua));
//                }
//                if(j==1){
//                    viewGroup.getChildAt(j).setBackgroundColor(getXActivity().getResources().getColor(R.color.bisque));
//                }
//                LogUtils.e(TAG,viewGroup.getChildAt(j),viewGroup.getChildAt(j).getLayoutParams().height);
//            }
//        }


        int id = getXActivity().getResources().getIdentifier("status_bar_height","dimen","android");
        if(id!=0){
            LogUtils.e(getXActivity().getResources().getDimensionPixelSize(id));
        }
    }
}
