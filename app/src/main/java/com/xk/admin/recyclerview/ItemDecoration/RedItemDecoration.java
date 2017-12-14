package com.xk.admin.recyclerview.ItemDecoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 31716 on 2017/12/14.
 */

public class RedItemDecoration extends RecyclerView.ItemDecoration {

    private final Rect mBounds = new Rect();
    private Drawable mDivider;
    private static final int[] ATTRS = new int[]{ android.R.attr.listDivider };
    private final Paint mPaint;

    public RedItemDecoration(Context context) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left =  parent.getPaddingLeft() ;
        int right = parent.getWidth() - parent.getPaddingRight() ;
        int childCount = parent.getChildCount();
        int bottom  = 0 ;
        int top  = 0 ;
        for (int i = 0; i < childCount; i++) {
           bottom = parent.getChildAt(i).getTop();
           top = bottom - 10 ;
            mBounds.set(left,top,right,bottom);
            c.drawRect(mBounds,mPaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //对应条目的position
        int childAdapterPosition = parent.getChildAdapterPosition(view);
        if (childAdapterPosition!=0 ){
            outRect.top =  10 ;
        }
    }
}
