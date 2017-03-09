package com.jiqu.client.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiqu.client.R;

/**
 * Created by CJJ on 2017/3/9.
 *
 */

public class DialogProgressFragment extends DialogFragment {


    public static DialogProgressFragment newInstance() {

        Bundle args = new Bundle();

        DialogProgressFragment fragment = new DialogProgressFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE,0);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loading_progress_fragment_layout, container, true);
        return view;
    }

    public static DialogProgressFragment show(FragmentActivity fragmentActivity) {
        DialogProgressFragment progressFragment = DialogProgressFragment.newInstance();
        progressFragment.show(fragmentActivity.getSupportFragmentManager(), null);
        return progressFragment;
    }

}
