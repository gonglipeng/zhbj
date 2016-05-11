package com.example.administrator.zhbj.fragment;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.zhbj.R;
import com.example.administrator.zhbj.damain.NewsMenu;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/9 0009.
 */
public class SlideFragment extends BaseFragment {

    private MyAdapter mAdapter;
    private LinearLayout flLeftMenu;
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
        mCurrentPoint=0;
    }

    public void setMenuData(NewsMenu newsMenu) {
        list=new ArrayList();
        ArrayList<NewsMenu.NewsMenuData> newsMenuDatas=newsMenu.data;
        Log.i("newsMenuDatas",""+newsMenuDatas.size());
        for(int i=0;i<newsMenuDatas.size();i++){
            String title=newsMenuDatas.get(i).title;
            list.add(title);
        }
        mAdapter=new MyAdapter();
        lvFragmentLeft.setAdapter(mAdapter);
        lvFragmentLeft.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentPoint=position;    //监听当前选择的点
                mAdapter.notifyDataSetChanged();// 刷新listview

                // 收起侧边栏
                toggle();
            }
        });
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
            View view=View.inflate(mActivity,R.layout.left_menu_item,null);
            TextView textView= (TextView) view.findViewById(R.id.tv_menu_item);
            textView.setText(list.get(position));
            if(position==mCurrentPoint){
                textView.setEnabled(true);
            }else{
                textView.setEnabled(false);
            }
            return view;
        }

    }
}
