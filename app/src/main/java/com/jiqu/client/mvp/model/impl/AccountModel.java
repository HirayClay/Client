package com.jiqu.client.mvp.model.impl;

import com.processor.Mapper;
import com.jiqu.client.mvp.model.IAccountModel;

/**
 * Created by CJJ on 2017/3/10.
 *
 */
@Mapper
public class AccountModel implements IAccountModel {

    String name;
    String word;
    String phone;

    boolean isCancellable;

    @Override
    public String name() {
        return null;
    }

    @Override
    public String word() {
        return null;
    }

    @Override
    public String phone() {
        return null;
    }

    @Override
    public boolean isCancellable() {
        return false;
    }
}
