package com.summer.record.ui.main.page.base;

import android.graphics.Xfermode;

import com.blankj.utilcode.util.ToastUtils;
import com.summer.record.ui.main.page.first.FirstPageUI;
import com.summer.record.ui.main.page.first.FristPageFrag;
import com.summer.x.base.ui.DE;
import com.summer.x.base.ui.UI;
import com.summer.x.base.ui.VA;
import com.summer.x.base.ui.XFragment;

public class BasePageFrag<A extends UI,B extends DE,C extends VA> extends XFragment<A,B,C> {

    @Override
    public boolean onBackPressedSupport() {
        if(getChildFragmentManager().getBackStackEntryCount()>1){
            popChild();
        }else{
            if(this instanceof FristPageFrag){
                getAct().finish();
            }else{
                ToastUtils.showShort("");
            }
        }
        return true;
    }
}
