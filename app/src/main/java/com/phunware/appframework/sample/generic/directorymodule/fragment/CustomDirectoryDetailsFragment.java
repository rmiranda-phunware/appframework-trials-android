package com.phunware.appframework.sample.generic.directorymodule.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.content.Loader;

import com.phunware.appframework.core.analytics.AnalyticsManager;
import com.phunware.appframework.directory.fragment.DirectoryDetailsFragment;

/**
 * Created by rodrigomiranda on 5/4/16.
 */
public class CustomDirectoryDetailsFragment extends DirectoryDetailsFragment {
    public static final String ANALYTICS_TAG_EXHIBIT_DETAIL = "exhibitDetail";
    public static final String ANALYTICS_TAG_EXHIBIT_DETAIL_NAME = ANALYTICS_TAG_EXHIBIT_DETAIL + "_%S";
    public static final String KEY_DIRECTORY_ITEM_ID = "directory_item_id";

    public static CustomDirectoryDetailsFragment getInstance(String directoryItemId) {
        CustomDirectoryDetailsFragment frag = new CustomDirectoryDetailsFragment();
        Bundle args = new Bundle();
        args.putString(KEY_DIRECTORY_ITEM_ID, directoryItemId);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        super.onLoadFinished(loader, data);
        String exhibitName = data.getString(data.getColumnIndex("name"));
        AnalyticsManager.getInstance().onScreenViewed(String.format(ANALYTICS_TAG_EXHIBIT_DETAIL_NAME, exhibitName));
        AnalyticsManager.getInstance().startTimedEvent(ANALYTICS_TAG_EXHIBIT_DETAIL);
    }

    @Override
    public void onStop() {
        super.onStop();
        AnalyticsManager.getInstance().stopTimedEvent(ANALYTICS_TAG_EXHIBIT_DETAIL);
    }
}
