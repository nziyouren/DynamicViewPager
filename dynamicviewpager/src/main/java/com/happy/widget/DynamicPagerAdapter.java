package com.happy.widget;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zxx on 2016/5/29.
 */
public class DynamicPagerAdapter extends PagerAdapter {

    private Context mContext;

    private DynamicViewPager mViewPager;

    private int mRow;
    private int mCol;

    private int mPageLayoutResId;
    private int mDataItemLayoutResId;

    private LayoutInflater mInflater;

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int page) {

        View rootView = null;
        rootView = mInflater.inflate(mPageLayoutResId, container, false);
        Resources res=mContext.getResources();

        final List<DataItem> currentPageDataItemList = getDataItemOfPage(page);

        int gridViewId = res.getIdentifier("grid","id",mContext.getPackageName());
        GridView gridView = (GridView) rootView.findViewById(gridViewId);
        gridView.setNumColumns(mCol);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DynamicViewPager.OnPageItemClickListener pageItemClickListener = mViewPager.getPageItemClickListener();
                if (pageItemClickListener != null){
                    DataItem item = currentPageDataItemList.get(position);
                    pageItemClickListener.onPageItemClick(page,position,item.tag,item);
                }
            }
        });
        GridAdapter adapter = new GridAdapter(currentPageDataItemList);
        gridView.setAdapter(adapter);
        container.addView(rootView, 0);

        return rootView;
    }

    private List<DataItem> getDataItemOfPage(int page){
        int countPerPage = mRow * mCol;
        int totalPage = (mDataItemList.size() / countPerPage) + (mDataItemList.size() % countPerPage == 0 ? 0 : 1);
        if (page == totalPage-1){
            return mDataItemList.subList(page*countPerPage,mDataItemList.size());
        }else {
            return mDataItemList.subList(page*countPerPage,(page+1)*countPerPage);
        }

    }

    private List<DataItem> mDataItemList;

    public DynamicPagerAdapter(Context context, DynamicViewPager viewPager, int pageLayoutResId, int dataItemLayoutResId, List<DataItem> dataItemList) {

        mContext = context;
        mViewPager = viewPager;
        mPageLayoutResId = pageLayoutResId;
        mDataItemLayoutResId = dataItemLayoutResId;
        mDataItemList = dataItemList;

        mRow = mViewPager.getRow();
        mCol = mViewPager.getCol();

        mInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        int countPerPage = mRow * mCol;
        return (mDataItemList.size() / countPerPage) + (mDataItemList.size() % countPerPage == 0 ? 0 : 1);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }


    public static class DataHolder {
        public TextView mTextView;
        public ImageView mImageView;
    }

     class GridAdapter extends BaseAdapter{

         private List<DataItem> mDataItemList;

         public GridAdapter(List<DataItem> dataItemList) {
             mDataItemList = dataItemList;
         }

         @Override
         public int getCount() {
             return mDataItemList.size();
         }

        @Override
        public Object getItem(int position) {
            return mDataItemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            DataHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(
                        mDataItemLayoutResId, null);
                Resources res=mContext.getResources();
                int textViewId = res.getIdentifier("text","id",mContext.getPackageName());
                int imageViewId = res.getIdentifier("image","id",mContext.getPackageName());
                holder = new DataHolder();
                holder.mTextView = (TextView) convertView
                        .findViewById(textViewId);
                holder.mImageView = (ImageView) convertView
                        .findViewById(imageViewId);

                convertView.setTag(holder);
            } else {
                holder = (DataHolder) convertView.getTag();
            }

            DataItem item = mDataItemList.get(position);
            if (item != null){
                holder.mTextView.setText(item.text);
                holder.mImageView.setImageResource(item.imageRes);
            }

            return convertView;
        }
    }
}
