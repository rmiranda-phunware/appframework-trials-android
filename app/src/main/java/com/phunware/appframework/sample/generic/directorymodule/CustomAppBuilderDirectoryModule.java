package com.phunware.appframework.sample.generic.directorymodule;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;

import com.phunware.appframework.directory.AppBuilderDirectoryModule;
import com.phunware.appframework.sample.generic.directorymodule.adapter.CustomDirectoryCursorAdapter;

/**
 * Created by rodrigomiranda on 4/4/16.
 */
public class CustomAppBuilderDirectoryModule extends AppBuilderDirectoryModule {

    @Override
    public CursorAdapter onGetDirectoryListAdapter(Context context, Cursor data) {
        return new CustomDirectoryCursorAdapter(context, data);
    }

}
