package com.summer.record.ui.play;

import android.os.Bundle;
import android.view.View;

import com.blankj.utilcode.util.IntentUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.summer.record.R;
import com.summer.record.data.model.PictureB;
import com.summer.record.ui.albums.bean.Album;
import com.summer.record.ui.menu.MenuFrag;
import com.summer.record.ui.pictures.detail.PictureDetailCT;
import com.summer.record.ui.pictures.picture.PictureDE;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.XFragment;

import java.util.ArrayList;
import java.util.HashMap;

import androidx.annotation.Nullable;
import butterknife.OnClick;
import butterknife.Optional;

public class PlayCT extends XFragment<PlayUI, PictureDE,PlayVA> {

    public static PlayCT getInstance(PictureB pictureB){
        PlayCT playCT = new PlayCT();
        playCT.setArguments(new Bundle());
        playCT.getVA().setPictureB(pictureB);
        playCT.getArguments().putSerializable("data",playCT.getVA());
        return playCT;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        getUI().initPlayer(getAct(),getVA().getPictureB());
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
                getVA().getPictureUploadDE().uploadRecords(getVA().getPictureB(), new OnProgressI() {

                    @Override
                    public void onProgress(String tag, int status, Object data) {
                        getVA().setUploading(false);
                        ToastUtils.showLong(""+data);
                        switch (status){
                            case SUCCESS:
                                getVA().getPictureB().setNetpath(data.toString());
                                getVA().getPictureB().save();
                                break;
                            case ERROR:

                                break;
                        }
                    }
                });
                break;
            case R.id.share:
                getAct().startActivity(IntentUtils.getShareImageIntent("嘿嘿",getVA().getPictureB().getLocpath()));
                break;
            case R.id.download:
                v.setEnabled(false);
                //下载
                getVA().getPictureDownDE().downloadOne(getVA().getPictureB(), new OnProgressI() {
                    @Override
                    public void onProgress(String tag, int status, Object data) {
                        switch (status){
                            case SUCCESS:
                                ToastUtils.showLong(data+"");
                                //getUI().initPicture(getVA().getPicture());
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
                getDE().addAlbums(map.get("id"), getVA().getPictureB().getLocpath(), new OnProgressI() {
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
