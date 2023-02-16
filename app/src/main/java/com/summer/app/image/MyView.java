package com.summer.app.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.summer.app.R;

/**
 * tangjie 2023/2/15 15:14
 **/
public class MyView extends androidx.appcompat.widget.AppCompatImageView {

    Paint paint;
    Bitmap bitmap;
    BitmapShader bitmapShader;
    int border = 50;

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        paint = new Paint();
        paint.setAntiAlias(true);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        bitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight());
        LogUtils.e(bitmap.getWidth(),bitmap.getHeight());

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        float newW = getWidth();
        float newH = getHeight();
        Matrix matrix = new Matrix();
        matrix.setScale(newW/bitmap.getWidth(),newH/bitmap.getHeight());
        bitmap = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(), bitmap.getHeight(),matrix,true);
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        LogUtils.e(bitmap.getWidth(),bitmap.getHeight());

        int flag = canvas.saveLayer(0,0,getWidth(),getHeight(),paint,Canvas.ALL_SAVE_FLAG);
//        paint.setColor(Color.RED);
//        canvas.drawRect(100,100,getWidth()-100,getHeight()-100,paint);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        canvas.drawBitmap(bitmap,0,0,paint);
        paint.setShader(bitmapShader);
        //canvas.drawRect(100,100,getWidth()-100,getHeight()-100,paint);
        //canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2,paint);
        canvas.drawRoundRect(new RectF(0,0,getWidth(),getHeight()),border,border,paint);
        canvas.restoreToCount(flag);
    }
}
