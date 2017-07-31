package com.us.smartcity.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.us.smartcity.R;
import com.us.smartcity.utils.Config;
import com.us.smartcity.utils.Log;
import com.us.smartcity.utils.Utils;

/**
 * Created by user on 29/6/17 in SmartCity.
 */

public class YoutubeActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private Context context;
    private YouTubePlayerView ytpUserGuide;
    private String videoId;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.youtube_activity);
        context = YoutubeActivity.this;
        videoId = getIntent().getStringExtra(Utils.getResourceSting(context, R.string.intentVideoId));
        Log.print("============= videoId ======= " + videoId);

        //city information : https://www.youtube.com/watch?v=_ydOo4qN2HM
        //Usser Login & password : https://www.youtube.com/watch?v=p7dR8aTxW-g
        // Complaint Registration : https://www.youtube.com/watch?v=jblWzLIwZgc
        //change password and feedback : https://www.youtube.com/watch?v=YQA3oqU6a4g
        //introduction : https://www.youtube.com/watch?v=praTYXn6CtM
        //Channel id : https://www.youtube.com/channel/UChmi_KJC0_iVHkl6SdhCmkA

        ytpUserGuide = (YouTubePlayerView) findViewById(R.id.ytpUserGuide);

        // Initializing video player with developer key
        ytpUserGuide.initialize(Config.YOUTUBE_API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if (!wasRestored) {

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically
            youTubePlayer.loadVideo(videoId);

            // Hiding player controls
            youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
            youTubePlayer.setFullscreen(true);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()) {
            youTubeInitializationResult.getErrorDialog(this, Config.YOUTUBE_RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(getString(R.string.error_player), youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }
}
