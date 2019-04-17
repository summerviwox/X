package com.summer.record.ui.pictures.mothpicture;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.summer.record.R;
import com.summer.record.databinding.ItemMonthpictureBinding;

import androidx.databinding.DataBindingUtil;

public class MothPictureAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    Context context;

    public MothPictureAdapter(Context context) {
        super(R.layout.item_monthpicture);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        ItemMonthpictureBinding binding = DataBindingUtil.bind(helper.itemView);
        binding.text.setText(item);
    }
}
