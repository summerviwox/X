package com.summer.record.ui.menu;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.summer.x.base.ui.XFragment;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class MenuFrag extends XFragment<MenuUI,MenuDE,MenuVA> implements BaseQuickAdapter.OnItemClickListener{

    public static MenuFrag getInstance(ArrayList<String> items){
        MenuFrag menuFrag = new MenuFrag();
        menuFrag.setArguments(new Bundle());
        menuFrag.getVA().setItems(items);
        return menuFrag;
    }


    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        getUI().initList(getAct(),getVA().getItems(),this);

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("index",position);
        setFragmentResult(0,bundle);
        pop();
    }
}
