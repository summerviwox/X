package com.summer.record.ui.pictures.pictures;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.summer.record.data.model.PictureB;
import com.summer.record.tool.FileTool;
import com.summer.record.ui.pictures.picture.FragPicture;
import com.summer.x.base.i.OnFinishI;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.XFragment;

import java.util.ArrayList;

import androidx.annotation.Nullable;

/**
 * 相册首页
 */
public class FragPictureHome extends XFragment<PictureHomeUI, PictureHomeDE, PictureHomeVA> implements BaseQuickAdapter.OnItemClickListener{

    public static FragPictureHome getInstance(){
        FragPictureHome fragPictureHome = new FragPictureHome();
        fragPictureHome.setArguments(new Bundle());
        return fragPictureHome;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        getUI().initRecord(getAct(),getVA().getPictures(),this);
//        getDE().getPictures(new OnProgressI() {
//            @Override
//            public void onProgress(String tag,int status, Object data) {
//              switch (status){
//                  case OnProgressI.SUCCESS:
//                      getVA().setPictures((ArrayList<PictureB>) data);
//                      getUI().refreshRecord(getVA().getPictures());
//                      break;
//              }
//            }
//        });
        getDE().getLocalPictures(getAct(), new OnProgressI() {
            @Override
            public void onProgress(String tag, int status, Object data) {
                switch (status){
                    case OnProgressI.END:
                        getVA().setPictures(getDE().dealRecord((ArrayList<PictureB>) data,PictureHomeUI.SPANCOUNT));
                        getUI().refreshRecord(getVA().getPictures());
                        break;
                }
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        FragPicture fragment = FragPicture.getInstance(getVA().getPictures().get(position));
        extraTransaction().startDontHideSelf(fragment);
//        // LOLLIPOP(5.0)系统的 SharedElement支持有 系统BUG， 这里判断大于 > LOLLIPOP
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
//            setExitTransition(new Fade());
//            fragment.setEnterTransition(new Fade());
//            fragment.setSharedElementReturnTransition(new DetailTransition());
//            fragment.setSharedElementEnterTransition(new DetailTransition());
//
//            // 25.1.0以下的support包,Material过渡动画只有在进栈时有,返回时没有;
//            // 25.1.0+的support包，SharedElement正常
//            ItemImageImageBinding itemImageImageBinding = DataBindingUtil.bind(view);
//            extraTransaction()
//                    .addSharedElement(itemImageImageBinding.ivVideo, "image_transition")
//                    .start(fragment);
//        } else {
//            start(fragment);
//        }
    }


}
