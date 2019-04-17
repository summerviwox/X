package com.summer.record.ui.pictures.home;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.summer.record.R;
import com.summer.record.databinding.ItemImageImageBinding;
import com.summer.record.databinding.ItemMonthTitleBinding;
import com.summer.record.ui.pictures.home.model.ItemEntity;
import com.summer.record.ui.pictures.home.model.TitleEntity;
import com.summer.x.GlideApp;

import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;

public class PictureMonthAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {

    private Context context;

    private RequestOptions requestOptions;

    public PictureMonthAdapter(Context context) {
        super(null);
        this.context = context;
        requestOptions = new RequestOptions();
        requestOptions.encodeQuality(10).placeholder(R.color.zxing_transparent).skipMemoryCache(false);
        addItemType(0, R.layout.item_month_title);
        addItemType(1,R.layout.item_image_image);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()){
            case 0:
                TitleEntity titleEntity = (TitleEntity) item;
                ItemMonthTitleBinding itemMonthTitleBinding = DataBindingUtil.bind(helper.itemView);
                itemMonthTitleBinding.text.setText(titleEntity.getTitle());
                itemMonthTitleBinding.text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(((TitleEntity) item).isExpanded()){
                            collapse(helper.getAdapterPosition());
                        }else{
                            expand(helper.getAdapterPosition());
                        }
                    }
                });
                break;
            case 1:
                ItemEntity itemEntity = (ItemEntity) item;
                if(TextUtils.isEmpty(itemEntity.getItem().getAtype())){
                    helper.itemView.setVisibility(View.INVISIBLE);
                    break;
                }
                helper.itemView.setVisibility(View.VISIBLE);
                ItemImageImageBinding itemImageImageBinding = DataBindingUtil.bind(helper.itemView);
                GlideApp.with(context).asBitmap().load(itemEntity.getItem().getLocpath()).apply(requestOptions).into(itemImageImageBinding.ivVideo);
                ViewCompat.setTransitionName(itemImageImageBinding.ivVideo,String.valueOf(helper.getLayoutPosition()+"_image"));
                if(itemEntity.getItem().isSelected()){
                    itemImageImageBinding.ivVideo.setAlpha(0.3f);
                }else{
                    itemImageImageBinding.ivVideo.setAlpha(1f);
                }
                break;
        }
    }
}
