package com.summer.app.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.summer.app.databinding.ActivityDialog1Binding;
import com.summer.app.fragment.FragementA;


public class DialogActivity extends FragmentActivity {

    ActivityDialog1Binding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityDialog1Binding.inflate(LayoutInflater.from(this));
        setContentView(mBinding.getRoot());
        mBinding.aa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MySheetDialogFragment mySheetDialogFragment = new MySheetDialogFragment();
                FragementA fragementA = new FragementA();

                mySheetDialogFragment.show(getSupportFragmentManager().beginTransaction(),"",fragementA);
            }
        });
    }
}
