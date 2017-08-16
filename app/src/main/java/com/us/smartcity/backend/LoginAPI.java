package com.us.smartcity.backend;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.us.smartcity.R;
import com.us.smartcity.adapter.Adapter;
import com.us.smartcity.bean.AreaBean;
import com.us.smartcity.uc.AlertDailogView;
import com.us.smartcity.utils.Config;
import com.us.smartcity.utils.DBSetter;
import com.us.smartcity.utils.Log;
import com.us.smartcity.utils.Pref;
import com.us.smartcity.utils.Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Krunal on 09/03/17.
 */
public class LoginAPI {
    private Context context;
    private HashMap<String, String> mParams = null;
    private Adapter mAdapter = null;
    private ResponseListener responseListener;
    private String API_LOGIN;
    private String TAG_LOGIN;
    private String password;
    int code;
    String mesg;

    public LoginAPI(Context context, ResponseListener responseListener, AreaBean userBean) {
        this.context = context;
        this.mParams = new HashMap<>();
        this.password = "Password";
        TAG_LOGIN = Utils.getResourceSting(context, R.string.loginTAG);
        API_LOGIN = Utils.getHostResourceSting(context, R.string.loginFbJSON);
        mParams.put(Utils.getResourceSting(context, R.string.keyName), userBean.name);
        mParams.put(Utils.getResourceSting(context, R.string.apiDeviceType), "" + Config.DEVICE_TYPE);
        mParams.put(Utils.getResourceSting(context, R.string.apiUdid), Pref.getString(context, Config.PREF_UDID, ""));

        Log.print(this.context.getClass().getName() + API_LOGIN + "====mParams===" + mParams);
        this.responseListener = responseListener;
    }

    public void execute() {
        this.mAdapter = new Adapter(this.context);
        this.mAdapter.doPost(TAG_LOGIN, API_LOGIN, mParams,
                new APIResponseListener() {

                    @Override
                    public void onResponse(String response) {
                        mParams = null;
                        // Parse Response and Proceed Further
                        parse(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mParams = null;
                        if (error instanceof TimeoutError
                                || error instanceof NoConnectionError) {
                            if (!((Activity) context).isFinishing()) {
                                AlertDailogView.showAlert(context, Utils.getResourceSting(context, R.string.Alert),
                                        Utils.getResourceSting(context, R.string.connectionErrorMessage),
                                        Utils.getResourceSting(context, R.string.ok)).show();
                            }
                        } else if (error instanceof AuthFailureError) {
                            //
                        } else if (error instanceof ServerError) {
                            //
                        } else if (error instanceof NetworkError) {
                            //
                        } else if (error instanceof ParseError) {
                            //
                        }
                        // Inform Caller that the API call is failed
                        responseListener.onResponce(TAG_LOGIN, Config.API_FAIL, context.getResources()
                                .getString(
                                        R.string.connectionErrorMessage));
                    }
                });
    }

    /*
     * Parse the response and prepare for callback
     */
    private void parse(String response) {
        int code;
        String mesg;
        JSONObject jsonObject;
        DBSetter dbSetter;

        try {
            jsonObject = new JSONObject(response);
            dbSetter = new DBSetter();
            code = jsonObject.getInt(Utils.getResourceSting(this.context, R.string.apiCode));
            mesg = jsonObject.getString(Utils.getResourceSting(this.context, R.string.apiMsg));

            if (code == 0) {
                Pref.setInt(this.context, Config.PREF_IS_NEW, dbSetter.getInt(jsonObject, "keyIsNew"));
                Pref.setString(this.context, Config.PREF_WEB_LINK, dbSetter.getString(jsonObject, "keyWebLink"));
                Pref.setString(this.context, Config.PREF_FACEBOOK_LINK, dbSetter.getString(jsonObject, "keyFacebookLink"));
                Pref.setString(this.context, Config.PREF_TWITTER_LINK, dbSetter.getString(jsonObject, "keyTwitterLink"));
                Pref.setString(this.context, Config.PREF_LINKEDIN_LINK, dbSetter.getString(jsonObject, "keyLinkedinLink"));
                Pref.setString(this.context, Config.PREF_YOUTUBE_LINK, dbSetter.getString(jsonObject, "keyYoutubeLink"));

                new syncAPI(jsonObject).execute();
            } else {
                doCallBack(code, mesg);
            }
        } catch (Exception e) {
            code = -1;
            mesg = "Exception :: " + this.getClass() + " :: parse() :: "
                    + e.getLocalizedMessage();
            Log.error(this.getClass() + " :: Exception :: ", e);
        }
    }

    /*
     * Send control back to the caller which includes
     *
     * Status: Successful or Failure Message: Its an Object, if required
     */
    private void doCallBack(int code, String mesg) {
        try {
            if (code == 0) {
                responseListener.onResponce(TAG_LOGIN, Config.API_SUCCESS, mesg);
            } else if (code > 0) {
                responseListener.onResponce(TAG_LOGIN,
                        Config.API_FAIL, mesg);
            } else if (code < 0) {
                responseListener.onResponce(TAG_LOGIN,
                        Config.API_FAIL, mesg);
            }
        } catch (Exception e) {
            Log.error(this.getClass() + " :: Exception :: ", e);
        }
    }

    /*
     * Cancel API Request
     */
    public void doCancel() {
        if (mAdapter != null) {
            mAdapter.doCancel(TAG_LOGIN);
        }
    }

    public class syncAPI extends AsyncTask<String, Void, String> {
        public JSONObject jsonObject;
        public JSONArray jsonArray;

        public syncAPI(JSONObject jsonObject) {
            this.jsonObject = jsonObject;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... strings) {
            String done = "";

            try {
                /*
                * ================ QUESTIONNAIRES ================
                * */
                jsonArray = jsonObject.getJSONArray("keyQuestionnaires");
                if (jsonArray != null && jsonArray.length() > 0) {
                    JSONObject jo;
                    DBSetter dbSetter = new DBSetter();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jo = jsonArray.getJSONObject(i);
                        int id = dbSetter.getInt(jo, "keyId");
                        String catId = dbSetter.getString(jo, "keycatId");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return done;
        }

        @Override
        protected void onPostExecute(String result) {
            doCallBack(code, mesg);
        }
    }
}