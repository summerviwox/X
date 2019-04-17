package com.summer.record.ui.pictures.mothpicture;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import com.summer.x.constant.Value;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MonthPictureDecoration extends RecyclerView.ItemDecoration {

    private int count = 4;

    private Context context;

    public MonthPictureDecoration(Context context,int count) {
        this.context = context;
        this.count = count;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildAdapterPosition(view);
        outRect.top = 0;
        if(pos<count){
            outRect.top = Value.DP_1*30;
        }
    }
}
