package com.summer.record.ui.pictures.pictures;

//by summer on 2018-04-03.

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import com.blankj.utilcode.util.SizeUtils;
import com.summer.record.R;
import com.summer.record.data.model.PictureB;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class TimeDecoration extends RecyclerView.ItemDecoration {

    ArrayList<PictureB> records;

    final Paint paint = new Paint();

    int linecolor  = R.color.white;

    int num = 3;

    @SuppressLint("ResourceAsColor")
    public TimeDecoration(Context context, ArrayList<PictureB> records, int num){
        this.records = records;
        this.num = num;
        paint.setColor(Color.DKGRAY);
        paint.setTextSize(SizeUtils.sp2px(14));
        paint.setAntiAlias(true);
        linecolor= context.getResources().getColor(R.color.color_main);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        for(int i=0;i<parent.getChildCount();i++){
            int pos = parent.getChildAdapterPosition(parent.getChildAt(i));
            if(pos>=records.size()){
                return;
            }
            if(records.get(pos).isFrist()&&pos%num==0){
//                paint.setColor(linecolor);
//                c.drawRect(ScreenUtil.最小DIMEN*2,parent.getChildAt(i).getTop()-ScreenUtil.最小DIMEN*25,parent.getChildAt(i).getWidth(),parent.getChildAt(i).getTop(),paint);
                //paint.setColor(Color.GRAY);
                c.drawText(records.get(pos).getDateStr(),parent.getChildAt(i).getLeft()+ SizeUtils.dp2px(2),parent.getChildAt(i).getTop()-SizeUtils.dp2px(9),paint);
            }
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int pos = parent.getChildAdapterPosition(view);
        outRect.left = (int) (SizeUtils.dp2px(2));
        outRect.right = (int) (SizeUtils.dp2px(2));
        outRect.top = (int) (SizeUtils.dp2px(2));
        outRect.bottom = (int) (SizeUtils.dp2px(2));

        if(pos>=records.size()){
            return;
        }

        if(records.get(pos).isFrist()){
            outRect.top = (int) (SizeUtils.dp2px(30));
        }
    }
}
