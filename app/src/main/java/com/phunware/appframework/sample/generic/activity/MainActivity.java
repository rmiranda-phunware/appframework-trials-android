package com.phunware.appframework.sample.generic.activity;

import com.phunware.appframework.render.activity.DynamicContentWithDrawerActivity;
import com.phunware.appframework.render.fragment.LobbyPageFragment;
import com.phunware.appframework.sample.generic.R;


public class MainActivity extends DynamicContentWithDrawerActivity {


    @Override
    protected int onInitialFragment() {
        return getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, LobbyPageFragment.getInstance(), "frag_lobby").commit();
    }


}
