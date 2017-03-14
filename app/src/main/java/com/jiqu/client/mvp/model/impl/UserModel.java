package com.jiqu.client.mvp.model.impl;

import com.jiqu.client.mvp.model.IUserModel;

/**
 * Created by CJJ on 2017/3/13.
 * 多数情况下由于后台传递的数据已经是页面数据格式，model层的转换此时显得比较多余，但是仍然有部分页面数据
 * 通过mapper转换之后更适合应用层使用
 */

public class UserModel implements IUserModel{



    @Override
    public String userName() {
        return null;
    }

    @Override
    public String gender() {
        return null;
    }

    @Override
    public String age() {
        return null;
    }

    @Override
    public String motto() {
        return null;
    }

    @Override
    public String identity() {
        return null;
    }
}
