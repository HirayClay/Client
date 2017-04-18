package com.jiqu.client.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiqu.client.R;
import com.jiqu.client.di.component.AlertComponent;
import com.jiqu.client.di.component.DaggerAlertComponent;
import com.jiqu.client.mvp.presenter.exchangecard.CheckCodePresenter;
import com.jiqu.client.ui.BaseActivity;
import com.jiqu.client.ui.exchange.BonusCardExchangeActivity;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;

/**
 * @author: CJJ
 * @date 2017/4/17
 */
public class AlertCheckCode extends AlertWindow {

    @Inject
    CheckCodePresenter checkPresenter;
    @Bind(R.id.close_btn)
    ImageView closeBtn;
    @Bind(R.id.query_edittext)
    EditText queryEdittext;
    @Bind(R.id.confirm_query)
    TextDrawable confirmQuery;
    @Bind(R.id.query_result)
    TextView queryResult;

    public static AlertCheckCode newInstance() {

        Bundle args = new Bundle();

        AlertCheckCode fragment = new AlertCheckCode();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BonusCardExchangeActivity activity = (BonusCardExchangeActivity) getActivity();
        DaggerAlertComponent.builder()
                .applicationComponent(activity.getApplicationComponent())
                .build()
                .inject(this);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this, getSheetView());
    }


    @OnClick(R.id.close_btn)
    public void close(){
        dismiss();
    }

    @OnClick(R.id.confirm_query)
    public void query(){
        String q = queryEdittext.getText().toString();
        checkPresenter.query(q);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_check_code_window;
    }

    @Override
    protected boolean touchDismiss() {
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
