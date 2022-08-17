package com.summerviwox.knowledge.ui.moudle.activity;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.summerviwox.knowledge.R;

/**
 * summer 2022/8/17 15:07
 **/
public class WindowViewActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_windowview);
        ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
        //Toast.makeText(this,((ViewGroup)(decorView.getChildAt(0))).getChildAt(1)+"",Toast.LENGTH_SHORT).show();
        Toast.makeText(this, ((ViewGroup)findViewById(android.R.id.content)).getChildAt(0)+"", Toast.LENGTH_SHORT).show();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.transparent));
        }
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        ((ViewGroup)findViewById(android.R.id.content)).getChildAt(0).setFitsSystemWindows(true);

    }
}
