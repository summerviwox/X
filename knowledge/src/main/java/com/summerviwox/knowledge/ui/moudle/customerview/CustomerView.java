package com.summerviwox.knowledge.ui.moudle.customerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.summerviwox.knowledge.R;

/**
 * summer 2022/8/16 14:39
 **/
public class CustomerView extends View {

    private String name;
    private Paint paint;
    //代码中new会从这个构造函数开始
    public CustomerView(Context context) {
        super(context);
    }
    //xml布局中会从这个构造函数开始
    public CustomerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //获取xml中定义的属性 属性自定义在attrs的xml
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomerView);
        name = typedArray.getString(R.styleable.CustomerView_name);
        Toast.makeText(context,name,Toast.LENGTH_SHORT).show();

        paint = new Paint();
    }

    //start
    //绘制流程通常重写下面3个方法
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getMode(widthMeasureSpec)),
                MeasureSpec.makeMeasureSpec(MeasureSpec.getSize(widthMeasureSpec),MeasureSpec.getMode(widthMeasureSpec)));

        //MeasureSpec.getSize(widthMeasureSpec);
        //MeasureSpec.getMode(widthMeasureSpec);
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            paint.setColor(Color.BLACK);
//            canvas.drawRoundRect(getLeft()+getPaddingLeft(),
//                    getTop()+getPaddingTop(),
//                    getRight()-getPaddingRight(),
//                    getBottom()-getPaddingBottom(),20,20,paint);
            LogUtils.e(getLeft());
            canvas.drawRoundRect(0,
                    0,
                    getWidth(),
                    getHeight(),120,120,paint);
        }


    }

    //end
}
