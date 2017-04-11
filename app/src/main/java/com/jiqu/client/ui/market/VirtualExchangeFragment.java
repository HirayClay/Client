package com.jiqu.client.ui.market;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiqu.client.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class VirtualExchangeFragment extends Fragment {


    public VirtualExchangeFragment() {
        // Required empty public constructor
    }


    public static VirtualExchangeFragment newInstance() {

        Bundle args = new Bundle();

        VirtualExchangeFragment fragment = new VirtualExchangeFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_virtual_exchange, container, false);
    }

}
