package com.xk.admin.recyclerview.ItemDecoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xk.admin.recyclerview.R;

/**
 * Created by 31716 on 2017/12/14.
 */

public class LinearLayoutDecoration extends RecyclerView.ItemDecoration {

    private final Rect mBounds = new Rect();
    private Drawable mDivider;
    private static final int[] ATTRS = new int[]{ android.R.attr.listDivider };

    public LinearLayoutDecoration(Context context,int drawableResId) {

        mDivider = ContextCompat.getDrawable(context,drawableResId);
        if (mDivider == null){
            throw  new IllegalArgumentException("can not init mDivider Drawable by drawableResId ");
        }
       // final TypedArray a = context.obtainStyledAttributes(ATTRS);
     //   mDivider = a.getDrawable(0);
     //   a.recycle();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left =  parent.getPaddingLeft() ;
        int right = parent.getWidth() - parent.getPaddingRight() ;
        int childCount = parent.getChildCount();
        int bottom  = 0 ;
        int top  = 0 ;
        for (int i = 0; i < childCount; i++) {
            View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childView.getLayoutParams();
            bottom =childView.getTop() - layoutParams.topMargin;
           top = bottom -  mDivider.getIntrinsicHeight()  ;
            mBounds.set(left,top,right,bottom);
            mDivider.setBounds(mBounds);
            mDivider.draw(c);
        }
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //对应条目的position
        int childAdapterPosition = parent.getChildAdapterPosition(view);
        if (childAdapterPosition!=0 ){
            outRect.top =  mDivider.getIntrinsicHeight() ;
        }
    }
}
