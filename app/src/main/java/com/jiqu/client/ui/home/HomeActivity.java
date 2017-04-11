package com.jiqu.client.ui.home;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.jiqu.client.R;
import com.jiqu.client.ui.fragment.HomeFragment;
import com.jiqu.client.ui.fragment.JoyFragment;
import com.jiqu.client.ui.fragment.MineFragment;
import com.jiqu.client.ui.fragment.NewBeeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @Bind(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @Bind(R.id.home_page)
    RadioButton homePage;
    @Bind(R.id.joy_page)
    RadioButton joyPage;
    @Bind(R.id.new_bee_page)
    RadioButton newBeePage;
    @Bind(R.id.mine_page)
    RadioButton minePage;
    @Bind(R.id.action_group)
    RadioGroup actionGroup;
    private List<Fragment> fragments;

    public static final int HOME_INDEX = 0;
    public static final int JOY_INDEX = 1;
    public static final int NEW_BEE_INDEX = 2;
    public static final int MINE_INDEX = 3;

    @IntDef({HOME_INDEX, JOY_INDEX, NEW_BEE_INDEX, MINE_INDEX})
    public @interface PageIndex {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance());
        fragments.add(JoyFragment.newInstance());
        fragments.add(NewBeeFragment.newInstance());
        fragments.add(MineFragment.newInstance());
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, fragments.get(0)).commit();

        actionGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.home_page:
                        select(HOME_INDEX);
                        break;
                    case R.id.joy_page:
                        select(JOY_INDEX);
                        break;
                    case R.id.new_bee_page:
                        select(NEW_BEE_INDEX);
                        break;
                    case R.id.mine_page:
                        select(MINE_INDEX);
                        break;
                }
            }
        });
    }

    private void select(@PageIndex int index) {
        Fragment target = fragments.get(index);
        if (target.isVisible())
            return;
        for (int i = 0; i < fragments.size(); i++) {
            Fragment f = fragments.get(i);
            FragmentManager fm = getSupportFragmentManager();
            if (f != target && f.isVisible()) {
                fm.beginTransaction().hide(f).commit();
            } else if (f == target) {
                if (!f.isAdded()) {
                    fm.beginTransaction().add(R.id.fragment_container, f).commit();
                } else {
                    fm.beginTransaction().show(f).commit();
                }
            }

        }
    }
}
