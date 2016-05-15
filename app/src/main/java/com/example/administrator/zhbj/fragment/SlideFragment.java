package com.example.administrator.zhbj.fragment;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.zhbj.MainActivity;
import com.example.administrator.zhbj.R;
import com.example.administrator.zhbj.base.impl.NewsContentPage;
import com.example.administrator.zhbj.damain.NewsMenu;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/9 0009.
 */
public class SlideFragment extends BaseFragment {

    private MyAdapter mAdapter;
    private LinearLayout flLeftMenu;    //menu布局
    private ListView lvFragmentLeft;
    private ArrayList<String> list;
    private int mCurrentPoint;    //当前选择第几个

    @Override
    public View initView() {
        View view = View.inflate(mActivity, R.layout.fragment_left_menu, null);
        flLeftMenu = (LinearLayout) view.findViewById(R.id.fl_left_menu);
        lvFragmentLeft = (ListView) view.findViewById(R.id.lv_fragment_left);
        return view;
    }

    @Override
    public void initData() {
        mCurrentPoint = 0;
    }

    public void setMenuData(NewsMenu newsMenu) {
        list = new ArrayList();
        ArrayList<NewsMenu.NewsMenuData> newsMenuDatas;    //网络数据
        newsMenuDatas = newsMenu.data;
        Log.i("newsMenuDatas", "" + newsMenuDatas.size());
        for (int i = 0; i < newsMenuDatas.size(); i++) {
            String title = newsMenuDatas.get(i).title;
            list.add(title);
        }
        mAdapter = new MyAdapter();
        lvFragmentLeft.setAdapter(mAdapter);
        lvFragmentLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentPoint = position;    //监听当前选择的点
                mAdapter.notifyDataSetChanged();// 刷新listview

                //获取并传递新闻详情页数据
                setMenuDetailPager(position);

                // 收起侧边栏
                toggle();
            }
        });
    }

    public void setMenuDetailPager(int position) {
        MainActivity mainUI= (MainActivity) mActivity;    //得到Activity
        ContentFragment contentFragment = mainUI.getContentFragment();    //获取Fragment
        //获取fragment里面的布局类
        NewsContentPage newsContentPage=contentFragment.getNewsContentPage();
        //将选择的指针传过去
        newsContentPage.setCurrentDetailData(position);
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public String getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = View.inflate(mActivity, R.layout.left_menu_item, null);
            TextView textView = (TextView) view.findViewById(R.id.tv_menu_item);
            textView.setText(list.get(position));
            if (position == mCurrentPoint) {
                textView.setEnabled(true);
            } else {
                textView.setEnabled(false);
            }
            return view;
        }

    }
}
