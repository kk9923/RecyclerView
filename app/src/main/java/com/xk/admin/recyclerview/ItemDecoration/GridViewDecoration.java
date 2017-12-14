package com.xk.admin.recyclerview.ItemDecoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 31716 on 2017/12/14.
 */

public class GridViewDecoration extends RecyclerView.ItemDecoration {

    private final Rect mBounds = new Rect();
    private Drawable mDivider;
    private static final int[] ATTRS = new int[]{ android.R.attr.listDivider };

    public GridViewDecoration(Context context, int drawableResId) {

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
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager){
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            int spanCount = gridLayoutManager.getSpanCount();
        }

            drawHorizontal(c,parent);
            drawVertical(c,parent);
      //  drawVertical(c,parent);
    }

    /**
     * 绘制水平分割线
     * @param canvas
     * @param parent
     */
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childView.getLayoutParams();
            // 将 线画在每个childView的底部
            int top = childView.getBottom()  +  layoutParams.bottomMargin ;
            int  bottom =top+mDivider.getIntrinsicHeight();
            final int right =childView.getRight()+layoutParams.rightMargin+mDivider.getIntrinsicWidth();
            final int left = childView.getLeft()-layoutParams.leftMargin ;

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
        canvas.restore();
    }

    /**
     * 绘制竖直分割线
     * @param canvas
     * @param parent
     */
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View childView = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childView.getLayoutParams();
            // 将 线画在每个childView的底部
            int top = childView.getTop() -layoutParams.topMargin ;
            int  bottom =childView.getBottom() +layoutParams.bottomMargin ;

             int left = childView.getRight()+ layoutParams.rightMargin ;
             int right =left+mDivider.getIntrinsicWidth();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(canvas);
        }
        canvas.restore();
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        int childAdapterPosition = parent.getChildAdapterPosition(view);
        if (layoutManager instanceof GridLayoutManager){
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            int spanCount = gridLayoutManager.getSpanCount();
            for (int i = 0; i < spanCount; i++) {
               if (childAdapterPosition == i ){
                   outRect.top =0;
                   break;
               }else {
                   outRect.top =  mDivider.getIntrinsicHeight() ;
               }
            }
                if ((childAdapterPosition + 1)%spanCount == 0  ){
                    outRect.right =0;
                }else {
                    outRect.right =  mDivider.getIntrinsicWidth() ;
                }
        }else {
            if (childAdapterPosition!=0 ){
                outRect.top =  mDivider.getIntrinsicHeight() ;
            }
        }
    }
}
