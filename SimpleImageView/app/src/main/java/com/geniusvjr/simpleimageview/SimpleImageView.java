package com.geniusvjr.simpleimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 对于继承自View类的自定义控件来说，核心的步骤是：
 * 尺寸的测量与绘制，对应的函数是onMeassure,onDraw
 *============================================
 *
 * 简单的Imageview，用于显示图片
 *
 * Created by dream on 16/4/7.
 */
public class SimpleImageView extends View{

    //画笔
    private Paint mBitmapPaint;
    //图片drawable
    private Drawable mDrawable;
    // View的宽度
    private int mWidth;
    //View的高度
    private int mHeight;

    public SimpleImageView(Context context) {
        this(context, null);
    }

    public SimpleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        mBitmapPaint = new Paint();
        mBitmapPaint.setAntiAlias(true);
    }

    private void initAttrs(AttributeSet attrs)
    {
        if(attrs != null)
        {
            TypedArray array = null;
            try {
                array = getContext().obtainStyledAttributes(attrs, R.styleable.SimpleImageView);
                //根据图片id获取到Drawable对象
                mDrawable = array.getDrawable(R.styleable.SimpleImageView_src);
                //测量Drawable对象的宽、高
                meassureDrawable();
            }
            finally {
                if(array != null)
                {
                    array.recycle();
                }
            }

        }
    }

    private void meassureDrawable()
    {
        if(mDrawable == null)
        {
            throw  new RuntimeException("drawable不能为空");
        }
        mWidth = mDrawable.getIntrinsicWidth();
        mHeight = mDrawable.getIntrinsicHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //设置View的宽高
        setMeasuredDimension(mWidth, mHeight);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if(mDrawable == null)
        {
            return;
        }
        //绘制图片
        canvas.drawBitmap(ImageUtils.drawableToBitmap(mDrawable), getLeft(), getTop(), mBitmapPaint);
    }
}
