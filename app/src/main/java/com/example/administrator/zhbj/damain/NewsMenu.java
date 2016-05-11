package com.example.administrator.zhbj.damain;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/10.
 *
 * 使用Gson解析时,对象书写技巧:
 * 1. 逢{}创建对象, 逢[]创建集合(ArrayList)
 * 2. 所有字段名称要和json返回字段高度一致
 */
public class NewsMenu {

    public int retcode;

    public ArrayList<Integer> extend;

    public ArrayList<NewsMenuData> data;

    public class NewsMenuData{
        public int id;
        public String title;
        public int type;
        public ArrayList<NewsTabData> children;
    }

    public class NewsTabData{
        public int id;
        public String title;
        public int type;
        public String url;
    }
}
