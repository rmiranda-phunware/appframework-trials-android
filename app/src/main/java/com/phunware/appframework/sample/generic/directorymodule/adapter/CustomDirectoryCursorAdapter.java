package com.phunware.appframework.sample.generic.directorymodule.adapter;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.View;

import com.phunware.appframework.core.view.FontTextView;
import com.phunware.appframework.directory.adapter.DirectoryCursorAdapter;

/**
 * Created by rodrigomiranda on 4/4/16.
 */
public class CustomDirectoryCursorAdapter extends DirectoryCursorAdapter {

    public CustomDirectoryCursorAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    protected void bindItemView(View view, Context context, Cursor cursor) {
        super.bindItemView(view, context, cursor);
        FontTextView textViewDirectoryTitle = (FontTextView) view.findViewById(com.phunware.appframework.directory.R.id.textViewDirectoryTitle);
        textViewDirectoryTitle.setTextColor(Color.BLUE);
    }

}
