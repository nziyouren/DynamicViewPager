package com.happy.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by zxx on 2016/5/29.
 */
public class DynamicViewPager extends ViewPager {

    public OnPageItemClickListener getPageItemClickListener() {
        return mPageItemClickListener;
    }

    public void setPageItemClickListener(OnPageItemClickListener pageItemClickListener) {
        this.mPageItemClickListener = pageItemClickListener;
    }

    public interface OnPageItemClickListener{
        void onPageItemClick(int page, int position, String tag, DataItem item);
    }

    private int mRow = DEFAULT_ROW;

    public int getRow() {
        return mRow;
    }

    public int getCol() {
        return mCol;
    }

    private int mCol = DEFAULT_COL;

    private final static int DEFAULT_ROW = 3;
    private final static int DEFAULT_COL = 3;

    private OnPageItemClickListener mPageItemClickListener;

    public DynamicViewPager(Context context) {
        super(context);
    }

    public DynamicViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.DynamicViewPager);
        initByAttributes(attributes);
        attributes.recycle();
    }

    private void initByAttributes(TypedArray attributes) {

        mRow = attributes.getInt(R.styleable.DynamicViewPager_row, DEFAULT_ROW);
        mCol = attributes.getInt(R.styleable.DynamicViewPager_col, DEFAULT_COL);
    }
}
