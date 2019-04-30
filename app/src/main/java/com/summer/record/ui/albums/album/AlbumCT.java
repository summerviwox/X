package com.summer.record.ui.albums.album;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.summer.record.R;
import com.summer.record.data.model.PictureB;
import com.summer.record.ui.main.main.MainAct;
import com.summer.record.ui.menu.MenuFrag;
import com.summer.record.ui.pictures.detail.PictureDetailCT;
import com.summer.record.ui.pictures.home.PictureHomeCT;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.XFragment;
import com.summer.x.util.HandleUtil;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.Nullable;
import butterknife.OnClick;

public class AlbumCT extends XFragment<AlbumUI,AlbumDE,AlbumVA> implements BaseQuickAdapter.OnItemClickListener,BaseQuickAdapter.OnItemLongClickListener{

    public static AlbumCT getInstance(String albumid){
        AlbumCT albumCT = new AlbumCT();
        albumCT.getVA().setAlbumid(albumid);
        return albumCT;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        getUI().initUI(getAct(),this,this);
        getAllAlbumItemsById();
    }

    public void getAllAlbumItemsById(){
        HandleUtil.getInstance().postDelayed(new Runnable() {
            @Override
            public void run() {
                getDE().getAllAlbumItemsById(getVA().getAlbumid(), new OnProgressI() {
                    @Override
                    public void onProgress(String tag, int status, Object data) {
                        switch (status){
                            case SUCCESS:
                                getVA().setDatas((ArrayList<PictureB>) data);
                                getUI().setNewData(getVA().getDatas());
                                break;
                        }
                    }
                });
            }
        }, 1000);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        extraTransaction().startDontHideSelf( PictureDetailCT.getInstance(getVA().getDatas(),getVA().getDatas().get(position).getLocpath()));
    }



    @Override
    public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
        getVA().setCurrentPictures(getVA().getDatas().get(position));
        extraTransaction().startForResultDontHideSelf(MenuFrag.getOldInstance(getVA().getItemMenus()),0);
        return true;
    }

    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.globalmenu:
                //
                extraTransaction().startForResultDontHideSelf(MenuFrag.getInstance(getVA().getMenus()),0);
                break;
        }
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0:
                if(data==null||data.getSerializable("data")==null){
                    return;
                }
                HashMap<String,String> map = (HashMap<String, String>) data.getSerializable("data");
                switch (map.get("text")){
                    case "新增":
                        //新增
                        extraTransaction().startForResult(PictureHomeCT.getInstance(PictureHomeCT.MODEL_SELECT,null),1);
                        break;
                    case "删除":
                        //删除
                        getDE().deleteAlbumItem(getVA().getAlbumid(),getVA().getCurrentPictures().getId()+"", new OnProgressI() {
                            @Override
                            public void onProgress(String tag, int status, Object data) {
                                switch (status){
                                    case SUCCESS:
                                        getAllAlbumItemsById();
                                        break;

                                }
                            }
                        });
                        break;
                    case "下载全部":
                        //下载全部
                        getVA().getPictureDownDE().downloadList(getVA().getDatas(), 0, new OnProgressI() {
                            @Override
                            public void onProgress(String tag, int status, Object data) {
                                switch (status){
                                    case DOING:
                                        PictureB pictureB = (PictureB) data;
                                        ToastUtils.showLong(pictureB.getLocpath());
                                        getUI().notifyItemChanged(pictureB.getPos());
                                        break;
                                    case END:
                                        ToastUtils.showLong("已全部下载完毕");
                                        break;
                                }
                            }
                        });
                        break;
                    case "下载全部图片":
                        //下载全部图片
                        getVA().getPictureDownDE().downloadList(getDE().getUndownLoadPictures(getDE().getImagePicture(getVA().getDatas())), 0, new OnProgressI() {
                            @Override
                            public void onProgress(String tag, int status, Object data) {
                                switch (status){
                                    case DOING:
                                        PictureB pictureB = (PictureB) data;
                                        ToastUtils.showLong(pictureB.getLocpath());
                                        getUI().notifyItemChanged(pictureB.getId());
                                        break;
                                    case END:
                                        getUI().notifyDataSetChanged();
                                        ToastUtils.showLong("已全部下载完毕");
                                        break;
                                }
                            }
                        });
                        break;
                    case "下载全部视频":
                        //下载全部视频
                        getVA().getPictureDownDE().downloadList(getDE().getUndownLoadPictures(getDE().getVideoPicture(getVA().getDatas())), 0, new OnProgressI() {
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
                    case "设置为封面":
                        //设置为封面
                        getDE().setAlbumHead(getVA().getAlbumid(), getVA().getCurrentPictures().getLocpath(), new OnProgressI() {
                            @Override
                            public void onProgress(String tag, int status, Object data) {
                                switch (status){
                                    case SUCCESS:
                                        //
                                        break;
                                }
                            }
                        });
                        break;
                }
                break;
            case 1:
                if(data==null||data.getSerializable("datas")==null){
                    return;
                }
                ArrayList<PictureB> pictureBS = (ArrayList<PictureB>) data.getSerializable("datas");
                getDE().addAlbums(getVA().getAlbumid(), getDE().getLocPaths(pictureBS), new OnProgressI() {
                    @Override
                    public void onProgress(String tag, int status, Object data) {
                        switch (status){
                            case SUCCESS:
                                getAllAlbumItemsById();
                                break;
                        }
                    }
                });
                break;
        }
    }


}
