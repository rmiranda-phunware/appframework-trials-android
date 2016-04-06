package com.phunware.appframework.sample.generic.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.phunware.appframework.core.analytics.AnalyticsManager;
import com.phunware.appframework.core.view.FontButton;
import com.phunware.appframework.sample.generic.R;

/**
 * Created by rodrigomiranda on 5/4/16.
 */
public class LocalAttractionsFragment extends Fragment {
    public static final String ANALYTICS_TAG_LOCAL_ATTRACTIONS_FRAGMENT = "localAttractions";
    public static final int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1000;
    public static final String TAG = LocalAttractionsFragment.class.getName();

    public static LocalAttractionsFragment getInstance() {
        return new LocalAttractionsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.local_attractions_fragment, container, false);

        FontButton fontButtonFindAttractions = (FontButton) v.findViewById(R.id.fontButtonFindAttractions);
        fontButtonFindAttractions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findPlace();
            }
        });
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle(getString(R.string.local_attractions_title));

        return v;
    }

    public void findPlace() {
        try {
            AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                    .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                    .build();

            Intent intent =
                    new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_FULLSCREEN)
                            .setFilter(typeFilter)
                            .build(getActivity());
            startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
        } catch (GooglePlayServicesRepairableException e) {
            // TODO: Handle the error.
        } catch (GooglePlayServicesNotAvailableException e) {
            // TODO: Handle the error.
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(getActivity(), data);
                Log.i(TAG, "Place: " + place.getName());
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(getActivity(), data);
                // TODO: Handle the error.
                Log.i(TAG, status.getStatusMessage());

            } else if (resultCode == Activity.RESULT_CANCELED) {
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AnalyticsManager.getInstance().onScreenViewed(ANALYTICS_TAG_LOCAL_ATTRACTIONS_FRAGMENT);
    }

    @Override
    public void onStart() {
        super.onStart();
        AnalyticsManager.getInstance().startTimedEvent(ANALYTICS_TAG_LOCAL_ATTRACTIONS_FRAGMENT);
    }

    @Override
    public void onStop() {
        super.onStop();
        AnalyticsManager.getInstance().stopTimedEvent(ANALYTICS_TAG_LOCAL_ATTRACTIONS_FRAGMENT);
    }

}
