package com.summer.record.ui.pictures.picture;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.IntentUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.summer.record.R;
import com.summer.record.data.model.PictureB;
import com.summer.record.ui.pictures.home.PictureHomeDE;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.XFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.OnClick;
import butterknife.Optional;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 图片详情
 */
public class FragPicture extends XFragment<PictureUI, PictureHomeDE,PictureVA> {


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
    @OnClick({R.id.upload,R.id.share})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.upload:
                if(getVA().isUploading()){
                    ToastUtils.showLong("正在上传 请勿重复点击");
                    return;
                }
                getVA().setUploading(true);
                getDE().uploadRecords(getVA().getPicture(), new OnProgressI() {

                    @Override
                    public void onProgress(String tag, int status, Object data) {
                        getVA().setUploading(false);
                        ToastUtils.showLong(""+data);
                        switch (status){
                            case SUCCESS:
                                getVA().getPicture().setIsupload(1);
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
        }
    }
}
