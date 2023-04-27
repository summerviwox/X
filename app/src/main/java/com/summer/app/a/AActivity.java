package com.summer.app.a;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.summer.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AActivity extends FragmentActivity {

    private AViewModelBusiImp mAViewModelBusiImp;
    @BindView(R.id.tv_name)
    TextView mNameTv;
    @BindView(R.id.tv_type)
    TextView mTypetv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_a);
        ButterKnife.bind(this);
        mAViewModelBusiImp = new ViewModelProvider(this).get(AViewModelBusiImp.class);
        mAViewModelBusiImp.getABeanLiveData().observe(this, new Observer<ABean>() {
            @Override
            public void onChanged(ABean aBean) {
                mNameTv.setText(aBean.name);
            }
        });

        mAViewModelBusiImp.getBBeanLiveData().observe(this, new Observer<BBean>() {
            @Override
            public void onChanged(BBean bBean) {
                mTypetv.setText(bBean.type);
            }
        });

        mNameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAViewModelBusiImp.goToOnlineServer(AActivity.this);
            }
        });

        mTypetv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAViewModelBusiImp.precessPlanContent();
            }
        });

        mAViewModelBusiImp.startBusi();
    }
}
