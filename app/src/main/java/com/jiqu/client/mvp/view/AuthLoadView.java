package com.jiqu.client.mvp.view;

/**
 * Created by CJJ on 2017/3/9.
 *
 */

public interface  AuthLoadView{

    void onAuthFail();
    void showLoading();
    void hideLoading();

    void showMessage(String message);
    void showError(String errMsg);
}
