package com.phunware.appframework.sample.generic.directorymodule.fragment;

import android.os.Bundle;

import com.phunware.appframework.core.analytics.AnalyticsManager;
import com.phunware.appframework.directory.fragment.DirectoryListFragment;

/**
 * Created by rodrigomiranda on 4/4/16.
 */
public class CustomDirectoryListFragment extends DirectoryListFragment {
    public static final String ANALYTICS_TAG_EXHIBIT_LIST = "exhibitList";

    public static CustomDirectoryListFragment getInstance() {
        return new CustomDirectoryListFragment();
    }

    public static CustomDirectoryListFragment getInstance(String category) {
        return getInstance(category, (String)null);
    }

    public static CustomDirectoryListFragment getInstance(String category, String customTitle) {
        CustomDirectoryListFragment frag = new CustomDirectoryListFragment();
        Bundle args = new Bundle();
        args.putString("category", category);
        args.putString("custom_title", customTitle);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnalyticsManager.getInstance().onScreenViewed(ANALYTICS_TAG_EXHIBIT_LIST);
    }

    @Override
    public void onStart() {
        super.onStart();
        AnalyticsManager.getInstance().startTimedEvent(ANALYTICS_TAG_EXHIBIT_LIST);
    }

    @Override
    public void onStop() {
        super.onStop();
        AnalyticsManager.getInstance().stopTimedEvent(ANALYTICS_TAG_EXHIBIT_LIST);
    }

}
