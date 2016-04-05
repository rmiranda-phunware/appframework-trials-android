package com.phunware.appframework.sample.generic.directorymodule;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;

import com.phunware.appframework.core.analytics.AnalyticsManager;
import com.phunware.appframework.directory.AppBuilderDirectoryModule;
import com.phunware.appframework.sample.generic.directorymodule.activity.CustomDirectoryDetailsActivity;
import com.phunware.appframework.sample.generic.directorymodule.adapter.CustomDirectoryCursorAdapter;

import java.util.HashMap;

/**
 * Created by rodrigomiranda on 4/4/16.
 */
public class CustomAppBuilderDirectoryModule extends AppBuilderDirectoryModule {
    public static final String ANALYTICS_TAG_EXHIBIT_ITEM_TAP = "exhibitTaps";
    public static final String ANALYTICS_PARAMETER_NAME_DIRECTORY_ITEM_ID = "directoryItemId";

    @Override
    public CursorAdapter onGetDirectoryListAdapter(Context context, Cursor data) {
        return new CustomDirectoryCursorAdapter(context, data);
    }

    @Override
    public void onDirectoryListItemClick(Activity activity, String directoryItemId) {
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put(ANALYTICS_PARAMETER_NAME_DIRECTORY_ITEM_ID, directoryItemId);
        AnalyticsManager.getInstance().onEventWithParameters(ANALYTICS_TAG_EXHIBIT_ITEM_TAP, parameters);
        CustomDirectoryDetailsActivity.startActivity(activity, directoryItemId);
    }
}
