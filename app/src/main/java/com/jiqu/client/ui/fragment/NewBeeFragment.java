package com.jiqu.client.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jiqu.client.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewBeeFragment extends Fragment {


    public NewBeeFragment() {
        // Required empty public constructor
    }

    public static NewBeeFragment newInstance() {

        Bundle args = new Bundle();

        NewBeeFragment fragment = new NewBeeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_bee, container, false);
    }

}
