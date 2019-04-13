package com.summer.record.ui.albums.albums;

//by summer on 2018-04-03.

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import com.summer.record.R;
import com.summer.record.data.model.PictureB;
import com.summer.x.constant.Value;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class AlbumDecoration extends RecyclerView.ItemDecoration {

    int num = 4;



    @SuppressLint("ResourceAsColor")
    public AlbumDecoration(Context context, int num){
        this.num = num;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildAdapterPosition(view);
        outRect.left = Value.DP_1*2;
        outRect.right = Value.DP_1*2;
        outRect.top = Value.DP_1*2;
        outRect.bottom = Value.DP_1*2;
        if(pos<4){
            outRect.top = Value.DP_1*30;
        }
    }
}
