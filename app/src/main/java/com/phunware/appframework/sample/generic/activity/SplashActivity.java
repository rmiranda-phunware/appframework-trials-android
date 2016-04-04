package com.phunware.appframework.sample.generic.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.View;
import android.widget.VideoView;

import com.phunware.appframework.core.util.LogWrap;
import com.phunware.appframework.render.activity.BasicSplashActivity;
import com.phunware.appframework.render.view.MaterialIndeterminateProgressBar;
import com.phunware.appframework.sample.generic.R;

public class SplashActivity extends BasicSplashActivity {
    private MaterialIndeterminateProgressBar mProgressBarSplash;
    private VideoView mVideoViewSplash;

    @Override
    protected void onCreateSplashActivity() {
        setContentView(R.layout.activity_splash);
        mProgressBarSplash = (MaterialIndeterminateProgressBar) findViewById(R.id.progressBarSplash);
        mVideoViewSplash = (VideoView) findViewById(R.id.videoViewSplash);
        mVideoViewSplash.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                LogWrap.d();
                doFetchCMEConfiguration();
            }
        });
        mVideoViewSplash.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                LogWrap.d();
                doFetchCMEConfiguration();
                return false;
            }
        });
    }

    @Override
    protected void onFetchCMEConfiguration() {
        //Dont immediately fetch CME config
    }

    @Override
    public void onResume() {
        super.onResume();

        Uri videoURI = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.introvideo);
        LogWrap.d(videoURI.toString());
        mVideoViewSplash.setVideoURI(videoURI);
        mVideoViewSplash.start();
    }

    @Override
    public Intent getLobbyActivity() {
        return new Intent(this, MainActivity.class);
    }

    @Override
    protected void onBootstrapFinished() {
        mProgressBarSplash.setVisibility(View.GONE);
    }
}
