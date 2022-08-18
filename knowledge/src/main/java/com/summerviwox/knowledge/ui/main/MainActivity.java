package com.summerviwox.knowledge.ui.main;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.summerviwox.knowledge.R;
import com.summerviwox.knowledge.databinding.ActMainBinding;

/**
 * summer 2022/8/2 15:04
 **/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActMainBinding actMainBinding = ActMainBinding.inflate(LayoutInflater.from(this));
        setContentView(actMainBinding.getRoot());
        actMainBinding.recyclerview.setData();

        //
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
