package com.summer.record.ui.pictures.test.testa;

import android.view.View;

import com.summer.record.R;
import com.summer.x.base.ui.DE;
import com.summer.x.base.ui.VA;
import com.summer.x.base.ui.XFragment;

import butterknife.OnClick;

public class TestaFrag extends XFragment<TestaUI, DE, VA> {


    @OnClick({R.id.main_container})
    public void onClick(View v) {
        super.onClick(v);
        //getChildFragmentManager()
    }
}
