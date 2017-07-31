package com.us.smartcity.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.us.smartcity.R;
import com.us.smartcity.utils.Config;
import com.us.smartcity.utils.Utils;


/**
 * Created by Bhaskar on 05-11-2015.
 */
public class UserGuideActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_guide_activity);

        context = UserGuideActivity.this;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txvIntoduction:
                intent = new Intent(context, YoutubeActivity.class);
                intent.putExtra(Utils.getResourceSting(context, R.string.intentVideoId), Config.YOUTUBE_INTRODUCTION);
                startActivity(intent);
                break;

            case R.id.txvLoginProcess:
                intent = new Intent(context, YoutubeActivity.class);
                intent.putExtra(Utils.getResourceSting(context, R.string.intentVideoId), Config.YOUTUBE_LOGIN_REGISTRATION);
                startActivity(intent);
                break;

            case R.id.txvComplaintRegistrationProcess:
                intent = new Intent(context, YoutubeActivity.class);
                intent.putExtra(Utils.getResourceSting(context, R.string.intentVideoId), Config.YOUTUBE_COMPLAINT_REGISTRATION);
                startActivity(intent);
                break;

            case R.id.txvChangePasswordProcess:
                intent = new Intent(context, YoutubeActivity.class);
                intent.putExtra(Utils.getResourceSting(context, R.string.intentVideoId), Config.YOUTUBE_CHANGE_PASSWORD);
                startActivity(intent);
                break;

            case R.id.txvCityInformationProcess:
                intent = new Intent(context, YoutubeActivity.class);
                intent.putExtra(Utils.getResourceSting(context, R.string.intentVideoId), Config.YOUTUBE_CITY_INFORMATION);
                startActivity(intent);
                break;
        }
    }
}






