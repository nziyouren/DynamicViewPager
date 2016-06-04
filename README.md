# DynamicViewPager
###It’s a convenient library for creating nine-grid view pager. 

##Feature
* Specify any row and col count in xml
* Can be combined with page indicator
* Less code, easy to use


##Screenshot
<img src="https://raw.githubusercontent.com/nziyouren/DynamicViewPager/master/images/screenshot-2.gif" alt="Drawing" width="450px" />
  
  

#How to use?

### Declare DynamicViewPager in your layout xml file. You can set row and col count attribute.

    <com.happy.widget.DynamicViewPager
        android:id="@+id/dynamicviewpager"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:col="4"
        app:row="3"
        />
        
###Get view and init data item
    
 
        int size = 30;
        List<DataItem> dataItemList = new ArrayList<>(size);

        for (int i = 0;i<size;i++){
            DataItem item = new DataItem(""+i,R.drawable.heart,"item "+i);
            dataItemList.add(item);
        }

        mDynamicPagerAdapter = new DynamicPagerAdapter(this,mDynamicViewPager,R.layout.data_root,R.layout.data_item_content,dataItemList);
        mDynamicViewPager.setAdapter(mDynamicPagerAdapter);


    }

    
###Detect page item click
      mDynamicViewPager.setPageItemClickListener(new DynamicViewPager.OnPageItemClickListener() {
            @Override
            public void onPageItemClick(int page, int position, String tag, DataItem item) {
                Toast.makeText(MainActivity.this,"You click page: "+page+" position: "+position,Toast.LENGTH_SHORT).show();
            }
        });

    
###Notice
DynamicPageAdapter constructor paramter:

* R.layout.data_root must have a gridview with id "grid"
* R.layout.data_item_content must have a imageview with id "image" and a textview with id "text"
* Because dynamicviewpager use these ids to find according view

#Contact
You can reach me by email nziyouren@gmail.com

#License:
Apache License, Version 2.0
