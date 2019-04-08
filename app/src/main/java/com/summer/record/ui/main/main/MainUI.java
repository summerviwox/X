package com.summer.record.ui.main.main;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.summer.record.R;
import com.summer.record.databinding.ActMainBinding;
import com.summer.x.base.ui.UI;

public class MainUI extends UI<ActMainBinding> {


    /**
     * 初始化底部菜单
     * @param finishI
     */
    public void initBottomMenu(FinishI finishI){
        getUI().bottommenu
                .addItem(new BottomNavigationItem(R.drawable.vector_drawable_icon_record_bottom_image, "图片").setActiveColor("#1296db").setInActiveColor("#8a8a8a"))
                .addItem(new BottomNavigationItem(R.drawable.vector_drawable_icon_record_bottom_image, "相册").setActiveColor("#1296db").setInActiveColor("#8a8a8a"))
                .addItem(new BottomNavigationItem(R.drawable.vector_drawable_icon_record_bottom_image, "设置").setActiveColor("#1296db").setInActiveColor("#8a8a8a"))
                .setFirstSelectedPosition(0)
                //.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE)
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBarBackgroundColor(R.color.white)
                .setTabSelectedListener(new OnSelectListener(){
                    @Override
                    public void onTabSelected(int position) {
                        super.onTabSelected(position);
                        finishI.onFinished(position);
                    }
                })
                .initialise();
    }

    public int getRootId(){
        return getUI().mainContainer.getId();
    }

}
