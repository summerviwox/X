package com.summer.record.ui.albums.addalbum;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.summer.record.R;
import com.summer.record.data.model.PictureB;
import com.summer.record.ui.pictures.home.PictureHomeCT;
import com.summer.x.base.i.OnProgressI;
import com.summer.x.base.ui.XFragment;

import java.io.Serializable;
import java.util.ArrayList;

import androidx.annotation.Nullable;
import butterknife.OnClick;
import butterknife.Optional;

/**
 * 添加新相册
 */
public class AddAlbumCT extends XFragment<AddAlbumUI,AddAlbumDE,AddAlbumVA> implements BaseQuickAdapter.OnItemClickListener{

    public static AddAlbumCT getInstance(){
        AddAlbumCT addAlbumCT = new AddAlbumCT();
        return addAlbumCT;
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        getUI().init(getAct(),this);
        getUI().setNewData(getVA().getDatas());
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        extraTransaction().startForResultDontHideSelf(PictureHomeCT.getInstance(PictureHomeCT.MODEL_SELECT,new Long[]{0l,System.currentTimeMillis()}),0);
    }


    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if(data!=null){
            ArrayList<PictureB> datas = (ArrayList<PictureB>) data.getSerializable("datas");
            getVA().setDatas(datas);
            getUI().setNewData(getVA().getDatas());
        }
    }

    @Optional
    @OnClick({R.id.save})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.save:
                getDE().addAlbum(getVA().getAddAlbumReq(getUI().getName(), getVA().getDatas()), new OnProgressI() {
                    @Override
                    public void onProgress(String tag, int status, Object data) {
                        switch (status){
                            case END:
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("datas", (Serializable) data);
                                setFragmentResult(0,bundle);
                                pop();
                                break;
                        }
                    }
                });
                break;
        }
    }
}
