package com.phunware.appframework.sample.generic;


import android.content.Context;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.crashlytics.android.Crashlytics;
import com.phunware.appframework.core.AppFrameworkApplication;
import com.phunware.appframework.core.bootstrap.appbuilder.AppBuilderBootstrapSource;
import com.phunware.appframework.core.util.LogWrap;
import com.phunware.appframework.directory.AppBuilderDirectoryModule;
import com.phunware.appframework.directory.fragment.DirectoryListFragment;
import com.phunware.appframework.events.EventsModule;
import com.phunware.appframework.events.fragment.EventsListFragment;
import com.phunware.appframework.locationmarketing.LocationMarketingModule;
import com.phunware.appframework.locationmarketing.fragment.MessagesFragment;
import com.phunware.appframework.map.MapModule;
import com.phunware.appframework.map.fragment.MapDirectoryInfoFragment;
import com.phunware.appframework.parking.ParkingModule;
import com.phunware.appframework.parking.fragment.ParkingFragment;
import com.phunware.appframework.render.RenderManager;
import com.phunware.appframework.render.RenderModule;
import com.phunware.appframework.render.fragment.SettingsDialogFragment;
import com.phunware.appframework.render.util.ButtonLocalBroadcastReceiver;
import com.phunware.appframework.render.util.OrderEnabledLocalBroadcastManager;
import com.phunware.appframework.render.util.ParcelableSparseArray;
import com.phunware.appframework.render.view.Button;
import com.phunware.core.PwLog;
import io.fabric.sdk.android.Fabric;


/**
 * Created by npike on 12/9/14.
 */
public class BasicApplication extends AppFrameworkApplication {

    public static final int BUTTON_TYPE_DIRECTORY = 36;
    public static final int BUTTON_TYPE_MAPPING = 84;
    public static final int BUTTON_TYPE_OFFER_WALLET = 17;
    public static final int BUTTON_TYPE_EVENTS = 30;
    public static final int BUTTON_TYPE_PARKING = 32;
    public static final int BUTTON_TYPE_SETTINGS = 70;
    public static final int BUTTON_TYPE_POINT_OF_INTEREST = 29;

    @Override
    public int onInitializeApplication() {
        // Debug log everything
        LogWrap.setShouldLog(BuildConfig.DEBUG);
        RenderManager.with(this).debug(BuildConfig.DEBUG);
        PwLog.setShowLog(BuildConfig.DEBUG);
        Fabric.with(this, new Crashlytics());

        addModule(new AppBuilderBootstrapSource());
        addModule(new RenderModule());
        addModule(new AppBuilderDirectoryModule());
        addModule(new MapModule());
        addModule(new EventsModule());
        addModule(new LocationMarketingModule());
        addModule(new ParkingModule());


        // Setup button responses for custom features.
        ButtonLocalBroadcastReceiver appBroadcastReceiver = new ButtonLocalBroadcastReceiver() {

            @Override
            public void onReceiveButtonClick(Context context, int buttonType, String linkUUID, ParcelableSparseArray buttonArgs) {
                if (buttonType == BUTTON_TYPE_DIRECTORY) {
                    Fragment directoryListFragment;
                    String directoryCategory = (String) buttonArgs.get(Button.BUTTON_ARG_1);
                    if (!TextUtils.isEmpty(directoryCategory)) {
                        directoryListFragment = DirectoryListFragment.getInstance(directoryCategory);
                    } else {
                        directoryListFragment = DirectoryListFragment.getInstance();
                    }
                    onHandleNewFragment(directoryListFragment, "frag_directory_list");
                } else if (buttonType == BUTTON_TYPE_MAPPING) {
                    String poiIdStr = (String) buttonArgs.get(Button.BUTTON_ARG_1);
                    long poiId = TextUtils.isEmpty(poiIdStr) ? 0L : Long.valueOf(poiIdStr);
                    onHandleNewFragment(MapDirectoryInfoFragment.getInstance(null, poiId, false), "frag_map");
                } else if (buttonType == BUTTON_TYPE_OFFER_WALLET) {
                    onHandleNewFragment(MessagesFragment.getInstance(), "frag_offer_wallet");
                } else if (buttonType == BUTTON_TYPE_EVENTS) {
                    onHandleNewFragment(EventsListFragment.getInstance(), "frag_events");
                } else if (buttonType == BUTTON_TYPE_PARKING) {
                    onHandleNewFragment(ParkingFragment.getInstance(), "frag_parking");
                } else if (buttonType == BUTTON_TYPE_SETTINGS) {
                    onHandleNewFragment(new SettingsDialogFragment(), "frag_setting");
                } else if (buttonType == BUTTON_TYPE_POINT_OF_INTEREST) {
                    onHandleNewFragment(MapDirectoryInfoFragment.getInstance(null, 0, true, false), "frag_poi");
                }
            }
        };


        IntentFilter intentFilter = new IntentFilter(Button.INTENT_ACTION_BUTTON_CLICK);
        OrderEnabledLocalBroadcastManager.getInstance(this).registerReceiver(appBroadcastReceiver,
                intentFilter);

        return R.xml.appframework_config;
    }
}
