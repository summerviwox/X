package com.summer.record.ui.pictures.picture;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.IntentUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.summer.record.R;
import com.summer.record.data.model.PictureB;
import com.summer.record.tool.DownLoadTool;
import com.summer.record.ui.albums.bean.Album;
import com.summer.record.ui.menu.MenuFrag;
import com.summer.record.ui.pictures.detail.PictureDetailCT;
import com.summer.record.ui.pictures.home.PictureHomeDE;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.XFragment;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.OnClick;
import butterknife.Optional;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 图片详情
 */
public class FragPicture extends XFragment<PictureUI, PictureDE,PictureVA> {


    public static FragPicture getInstance(PictureB data){
        FragPicture fragPicture = new FragPicture();
        fragPicture.getArguments().putSerializable("data",data);
        return fragPicture;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getVA().setPicture((PictureB)(getArguments().getSerializable("data")));
        getUI().initPicture(getVA().getPicture());
    }

    @Optional
    @OnClick({R.id.upload,R.id.share,R.id.download,R.id.type})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.upload:
                if(getVA().isUploading()){
                    ToastUtils.showLong("正在上传 请勿重复点击");
                    return;
                }
                getVA().setUploading(true);
                getVA().getPictureUploadDE().uploadRecords(getVA().getPicture(), new OnProgressI() {

                    @Override
                    public void onProgress(String tag, int status, Object data) {
                        getVA().setUploading(false);
                        ToastUtils.showLong(""+data);
                        switch (status){
                            case SUCCESS:
                                getVA().getPicture().setNetpath(data.toString());
                                getVA().getPicture().save();
                                break;
                            case ERROR:

                                break;
                        }
                    }
                });
                break;
            case R.id.share:
                getAct().startActivity(IntentUtils.getShareImageIntent("嘿嘿",getVA().getPicture().getLocpath()));
                break;
            case R.id.download:
                v.setEnabled(false);
                //下载
                getVA().getPictureDownDE().downloadOne(getVA().getPicture(), new OnProgressI() {
                    @Override
                    public void onProgress(String tag, int status, Object data) {
                        switch (status){
                            case SUCCESS:
                                ToastUtils.showLong(data+"");
                                getUI().initPicture(getVA().getPicture());
                                break;
                            case END:
                                v.setEnabled(true);
                                break;
                        }
                    }
                });
                break;
            case R.id.type:
                //分类
                getDE().getAllAlbums(new OnProgressI() {
                    @Override
                    public void onProgress(String tag, int status, Object data) {
                        switch (status){
                            case SUCCESS:
                                findFragment(PictureDetailCT.class).extraTransaction().startForResultDontHideSelf(MenuFrag.getInstance(getVA().getMenu((ArrayList<Album>) data)),0);
                                break;
                        }
                    }
                });
                break;
        }
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        switch (requestCode){
            case 0:
                if(data==null||data.get("data")==null){
                    return;
                }
                HashMap<String,String> map = (HashMap<String, String>) data.getSerializable("data");
                getDE().addAlbums(map.get("id"), getVA().getPicture().getLocpath(), new OnProgressI() {
                    @Override
                    public void onProgress(String tag, int status, Object data) {
                        switch (status){
                            case SUCCESS:
                                //
                                ToastUtils.showLong("成功");
                                break;
                        }
                    }
                });
                break;
        }
    }
}
