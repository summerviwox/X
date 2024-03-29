package com.summer.app.test.viewcontrol.viewcontrol;

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import butterknife.OnClick
import com.blankj.utilcode.util.LogUtils
import com.github.florent37.viewanimator.ViewAnimator
import com.summer.app.R
import com.summer.x.base.ui.XActivity

class ViewControlCT : XActivity<ViewControlUI,ViewControlDE,ViewControlVA>(){


    @OnClick(R.id.text)
    override fun onClick(v: View?) {
        super.onClick(v)
        when(v!!.id){
            R.id.text->{
                var aView = AView(this,null)
                ui.ui.ctroot.addView(aView,ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT))
                ViewAnimator.animate(aView).slideRightIn().duration(500).accelerate().start()
            }
        }
    }

    fun onBackPressedSupport() {
        var aview = ui.ui.ctroot.getChildAt(ui.ui.ctroot.childCount-1)
        if(aview is AView){
            ViewAnimator.animate(aview).translationX(0F, aview.width.toFloat()).duration(500).accelerate().onStop {
                ui.ui.ctroot.removeView(aview)
            }.start()
        }
    }

}