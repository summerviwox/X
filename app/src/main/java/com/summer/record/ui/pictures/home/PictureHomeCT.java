package com.summer.record.ui.pictures.home;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.summer.record.R;
import com.summer.record.data.model.PictureB;
import com.summer.record.ui.loading.LoadingFrag;
import com.summer.record.ui.menu.MenuFrag;
import com.summer.record.ui.pictures.detail.PictureDetailCT;
import com.summer.x.base.i.OnFinishI;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.XFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * 相册首页
 */
public class PictureHomeCT extends XFragment<PictureHomeUI, PictureHomeDE, PictureHomeVA> implements BaseQuickAdapter.OnItemClickListener,BaseQuickAdapter.OnItemLongClickListener,OnProgressI{

    public static int MODEL_SCAN = 0;//浏览模式

    public static int MODEL_SELECT = 1;//选择模式

    public static PictureHomeCT getInstance(int model,Long[] times){
        PictureHomeCT pictureHomeCT = new PictureHomeCT();
        pictureHomeCT.setArguments(new Bundle());
        pictureHomeCT.getVA().setModel(model);
        pictureHomeCT.getVA().setTimes(times);
        return pictureHomeCT;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getUI().setSureVisible(getVA().getModel());
        getUI().initRecord(getAct(),getVA().getPictures(),PictureHomeCT.this,PictureHomeCT.this);
        initData();
    }

    public void initData(){
        getVA().setLoadingfrag(LoadingFrag.getInstance());
        extraTransaction().startDontHideSelf(getVA().getLoadingfrag(),STANDARD);
        getVA().getLoadingfrag().setOnFinishI(new OnFinishI() {
            @Override
            public void onFinished(Object o) {
                getDE().asyncGetPicturesFromDB(getVA().getPictureGetDE(),getDE(),getAct(),getVA().getTimes(),PictureHomeCT.this);
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if(getVA().getModel()==MODEL_SELECT){
            //getVA().addSelect(getVA().getPictures().get(position));
            getVA().getPictures().get(position).setSelected(!getVA().getPictures().get(position).isSelected());
            getUI().notifyItemChanged(position);
        }else{
            start(PictureDetailCT.getInstance(getVA().getNonullPictures(),getVA().getPictures().get(position).getPos()));
        }
        //getAct().start();
        //FragPicture fragment = FragPicture.getOldInstance(getVA().getPictures().get(position));
        // extraTransaction().startDontHideSelf(fragment);
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




    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        extraTransaction().startForResultDontHideSelf(MenuFrag.getOldInstance(getVA().getMenus()),0);
        return true;
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0:
                if(data==null){
                    return;
                }
                int index = data.getInt("index",-1);
                if(index==-1){
                    return;
                }
                switch (index){
                    case 0:
                        if(getVA().isIsdoing()){
                            ToastUtils.showShort("正在上传");
                            return;
                        }
                        getVA().setIsdoing(true);
                        ArrayList<PictureB> datas =  getDE().getUploadPictures(getVA().getPictures());
                        ToastUtils.showShort(datas.size()+"张未上传");
                        getVA().getPictureUploadDE().uploadRecordsAndChangeStatus(datas, new OnProgressI() {
                            @Override
                            public void onProgress(String tag, int status, Object data) {
                                switch (status){
                                    case DOING:
                                        PictureB pictureB = (PictureB) data;
                                        ToastUtils.showShort(""+pictureB.getNetpath());
                                        break;
                                    case END:
                                        getVA().setIsdoing(false);
                                        ToastUtils.showShort("全部上传成功");
                                        break;
                                }
                            }
                        });
                        break;
                    case 1:
                        //下载全部
                        ArrayList<PictureB> undownloaddatas = getVA().getAlbumDE().getUndownLoadPictures(getVA().getPictures());
                        getVA().getPictureDownDE().downloadList(undownloaddatas, 0, new OnProgressI() {
                            @Override
                            public void onProgress(String tag, int status, Object data) {
                                switch (status){
                                    case DOING:
                                        PictureB pictureB = (PictureB) data;
                                        ToastUtils.showLong(pictureB.getNetpath());
                                        getUI().scrollToPosition(pictureB.id);
                                        getUI().notifyItemChanged(pictureB.id);
                                        break;
                                    case END:
                                        ToastUtils.showLong("已全部下载完毕");
                                        break;
                                }
                            }
                        });
                    case 2:
                        //下载全部图片
                        getVA().getPictureDownDE().downloadList(getVA().getAlbumDE().getUndownLoadPictures(getVA().getAlbumDE().getImagePicture(getVA().getPictures())), 0, new OnProgressI() {
                            @Override
                            public void onProgress(String tag, int status, Object data) {
                                switch (status){
                                    case DOING:
                                        PictureB pictureB = (PictureB) data;
                                        ToastUtils.showLong(pictureB.getLocpath());
                                        getUI().notifyItemChanged(pictureB.getId());
                                        break;
                                    case END:
                                        ToastUtils.showLong("已全部下载完毕");
                                        break;
                                }
                            }
                        });
                        break;
                    case 3:
                        //下载全部视频
                        getVA().getPictureDownDE().downloadList(getVA().getAlbumDE().getUndownLoadPictures(getVA().getAlbumDE().getVideoPicture(getVA().getPictures())), 0, new OnProgressI() {
                            @Override
                            public void onProgress(String tag, int status, Object data) {
                                switch (status){
                                    case DOING:
                                        PictureB pictureB = (PictureB) data;
                                        ToastUtils.showLong(pictureB.getLocpath());
                                        getUI().notifyItemChanged(pictureB.getId());
                                        break;
                                    case END:
                                        ToastUtils.showLong("已全部下载完毕");
                                        break;
                                }
                            }
                        });
                        break;
                }
                break;
        }
    }

    @Optional
    @OnClick({R.id.sure})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.sure:
                Bundle bundle = new Bundle();
                bundle.putSerializable("datas",getVA().getSelectPictures(getVA().getPictures()));
                setFragmentResult(1,bundle);
                pop();
                break;
            case R.id.globalmenu:

                //悬浮菜单
                break;
        }
    }

    @Override
    public void onProgress(String tag, int status, Object data) {
        switch (tag){
            case "aysncDeal":
                getVA().setPictures((ArrayList<PictureB>) data);
                getVA().setNonullPictures(getDE().removeNullPictures(getVA().getPictures()));
                getUI().refreshRecord(getAct(),getVA().getPictures());
                getVA().getLoadingfrag().pop();
                break;
        }
    }
}
