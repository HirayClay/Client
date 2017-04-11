package com.jiqu.client.ui;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.jiqu.client.di.HasComponent;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BaseFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private DialogProgressFragment progressFragment;


    public BaseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    //a helper function help to access host  Component
    @SuppressWarnings("unchecked")
    public <T> T getComponent (Class<T> componentType){

        return componentType.cast( ((HasComponent<T>)getActivity()).getComponent() );

    }

    protected void showLoading() {

//        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        if (progressFragment == null) {
            progressFragment = DialogProgressFragment.show(getActivity());
        } else {
            progressFragment.dismissAllowingStateLoss();
            progressFragment.show(getActivity());
        }

    }

    protected void hideLoading() {
        if (progressFragment == null)
            return;
        else progressFragment.dismissAllowingStateLoss();
    }


}
