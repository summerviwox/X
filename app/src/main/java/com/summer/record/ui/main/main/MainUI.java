package com.summer.record.ui.main.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.summer.record.R;
import com.summer.record.databinding.ActMainBinding;
import com.summer.record.databinding.ItemGlobalmenuBinding;
import com.summer.x.base.ui.UI;
import com.summer.x.base.ui.XActivity;

public class MainUI extends UI<ActMainBinding> {


    public void ImmersionBar(XActivity activity){
        //ImmersionBar.with(activity).statusBarColor(R.color.color_grey_50).init();
        //ImmersionBar.with(activity).statusBarColor(R.color.zxing_transparent).init();
    }

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
                .setBarBackgroundColor(R.color.color_grey_50)
                .setTabSelectedListener(new OnSelectListener(){
                    @Override
                    public void onTabSelected(int position) {
                        super.onTabSelected(position);
                        finishI.onFinished(position);
                    }
                })
                .initialise();
    }

    public void initGlobalMenu(Context context, View.OnClickListener listener){
        ItemGlobalmenuBinding itemGlobalmenuBinding = ItemGlobalmenuBinding.inflate(LayoutInflater.from(context));
        itemGlobalmenuBinding.getRoot().setX(ScreenUtils.getScreenWidth()/2);
        itemGlobalmenuBinding.getRoot().setY(ScreenUtils.getScreenHeight()/2);
        getUI().mainContainer.addView(itemGlobalmenuBinding.getRoot());
        itemGlobalmenuBinding.getRoot().setLayoutParams(new FrameLayout.LayoutParams(SizeUtils.dp2px(40),SizeUtils.dp2px(40)));
        itemGlobalmenuBinding.getRoot().setOnTouchListener(new StackViewTouchListener(itemGlobalmenuBinding.getRoot(), SizeUtils.dp2px(10)));
        itemGlobalmenuBinding.getRoot().setOnClickListener(listener);
        for(int i=0;i<getUI().mainContainer.getChildCount();i++){
            getUI().mainContainer.getChildAt(i);
        }
    }

    public int getRootId(){
        return getUI().mainContainer.getId();
    }

    public void setBottomVisible(boolean visible){
        getUI().bottommenu.setVisibility(visible? View.VISIBLE:View.GONE);
    }

}
