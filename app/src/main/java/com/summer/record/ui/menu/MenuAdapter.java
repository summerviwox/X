package com.summer.record.ui.menu;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.summer.record.R;
import com.summer.record.databinding.ItemMenuBinding;

import java.util.HashMap;

import androidx.databinding.DataBindingUtil;

public class MenuAdapter extends BaseQuickAdapter<HashMap<String,String>, BaseViewHolder> {

    public MenuAdapter() {
        super(R.layout.item_menu);
    }

    @Override
    protected void convert(BaseViewHolder helper, HashMap<String,String> item) {
        ItemMenuBinding itemMenuBinding = DataBindingUtil.bind(helper.itemView);
        itemMenuBinding.text.setText(item.get("text"));
    }
}
