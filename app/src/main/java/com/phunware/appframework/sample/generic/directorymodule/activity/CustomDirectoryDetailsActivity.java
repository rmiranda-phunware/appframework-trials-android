package com.phunware.appframework.sample.generic.directorymodule.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.phunware.appframework.sample.generic.directorymodule.fragment.CustomDirectoryDetailsFragment;

/**
 * Created by rodrigomiranda on 5/4/16.
 */
public class CustomDirectoryDetailsActivity extends AppCompatActivity {
    private static final String EXTRA_DIRECTORY_ITEM_ID = "directory_item_id";

    public CustomDirectoryDetailsActivity() {
    }

    public static Intent startActivity(Context context, String directoryItemId) {
        Intent launchIntent = new Intent(context, CustomDirectoryDetailsActivity.class);
        launchIntent.putExtra("directory_item_id", directoryItemId);
        context.startActivity(launchIntent);
        return launchIntent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(com.phunware.appframework.directory.R.layout.appframe__directory__activity_directory_details);
        if(savedInstanceState == null) {
            this.getSupportFragmentManager().beginTransaction().replace(com.phunware.appframework.directory.R.id.fragment_placeholder,
                    CustomDirectoryDetailsFragment.getInstance(this.getIntent().getStringExtra(EXTRA_DIRECTORY_ITEM_ID))).commit();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home) {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
