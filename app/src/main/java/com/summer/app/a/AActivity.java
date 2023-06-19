package com.summer.app.a;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.gyf.barlibrary.ImmersionBar;
import com.summer.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).init();
        setContentView(R.layout.layout_a);
        findViewById(R.id.a).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PromptDialog.newInstance("aaa",getResources().getString(R.string.midtext)).show(getSupportFragmentManager(),"");
            }
        });
    }
}
