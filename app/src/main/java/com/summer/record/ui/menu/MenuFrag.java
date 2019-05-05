package com.summer.record.ui.menu;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.summer.record.R;
import com.summer.x.base.ui.XFragment;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.Nullable;
import butterknife.OnClick;

public class MenuFrag extends XFragment<MenuUI,MenuDE,MenuVA> implements BaseQuickAdapter.OnItemClickListener{

    public static final String COUNT = "COUNT";

    public static MenuFrag getOldInstance(ArrayList<String> items){
        MenuFrag menuFrag = new MenuFrag();
        menuFrag.setArguments(new Bundle());
        menuFrag.getVA().setItems(items);
        return menuFrag;
    }

    public static MenuFrag getInstance(ArrayList<HashMap<String,String>> items){
        MenuFrag menuFrag = new MenuFrag();
        menuFrag.setArguments(new Bundle());
        menuFrag.getVA().setMaps(items);
        return menuFrag;
    }

    public static MenuFrag getInstance(ArrayList<HashMap<String,String>> items,int count){
        MenuFrag menuFrag = new MenuFrag();
        menuFrag.setArguments(new Bundle());
        menuFrag.getVA().setMaps(items);
        menuFrag.getArguments().putInt(COUNT,count);
        return menuFrag;
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        getVA().setCount(getArguments().getInt(COUNT,1));
        if(getVA().getItems()!=null&&getVA().getItems().size()!=0){
            getVA().changeToMap(getVA().getItems());
        }
        getUI().initList(getAct(),getVA().getMaps(),this,getVA().getCount());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("index",position);
        bundle.putSerializable("data",getVA().getMaps().get(position));
        setFragmentResult(0,bundle);
        pop();
    }

    @OnClick({R.id.menuroot})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.menuroot:
                pop();
                break;
        }
    }
}
