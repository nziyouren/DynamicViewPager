package com.happy.sample;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.happy.widget.DataItem;
import com.happy.widget.DynamicPagerAdapter;
import com.happy.widget.DynamicViewPager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private DynamicViewPager mDynamicViewPager;
    private DynamicPagerAdapter mDynamicPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initData();
    }

    private void initViews() {

        mDynamicViewPager = (DynamicViewPager)findViewById(R.id.dynamicviewpager);
        mDynamicViewPager.setPageItemClickListener(new DynamicViewPager.OnPageItemClickListener() {
            @Override
            public void onPageItemClick(int page, int position, String tag, DataItem item) {
                Toast.makeText(MainActivity.this,"You click page: "+page+" position: "+position,Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initData() {

        int size = 30;

        List<DataItem> dataItemList = new ArrayList<>(size);

        for (int i = 0;i<size;i++){
            DataItem item = new DataItem(""+i,R.drawable.heart,"item "+i);
            dataItemList.add(item);
        }

        mDynamicPagerAdapter = new DynamicPagerAdapter(this,mDynamicViewPager,R.layout.data_root,R.layout.data_item_content,dataItemList);
        mDynamicViewPager.setAdapter(mDynamicPagerAdapter);


    }


}
