package com.summerviwox.knowledge.ui.main;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.LayoutInflater;

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
    }
}
