package com.summer.record.ui.play;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.summer.record.R;
import com.summer.record.data.model.PictureB;
import com.summer.record.databinding.ItemPlayBinding;
import com.summer.x.GlideApp;
import com.summer.x.base.ui.UI;


public class PlayUI extends UI<ItemPlayBinding> {

    private OrientationUtils orientationUtils;

    public void initPlayer(Activity context, PictureB pictureB){
        StandardGSYVideoPlayer videoPlayer =  getUI().detailPlayer;

        String source1 = pictureB.getLocpath();
        videoPlayer.setUp(source1, true, pictureB.getLocpath());

        //增加封面
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        GlideApp.with(context).load(pictureB.getLocpath()).into(imageView);
        videoPlayer.setThumbImageView(imageView);
        //增加title
        videoPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        //设置返回键
        videoPlayer.getBackButton().setVisibility(View.VISIBLE);
        //设置旋转
        orientationUtils = new OrientationUtils(context, videoPlayer);
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
            }
        });
        //是否可以滑动调整
        videoPlayer.setIsTouchWiget(true);
        //设置返回按键功能
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.onBackPressed();
            }
        });
        //videoPlayer.startPlayLogic();
    }

    public StandardGSYVideoPlayer getPlayer(){
        return getUI().detailPlayer;
    }

    public OrientationUtils getOrientationUtils() {
        return orientationUtils;
    }
}
