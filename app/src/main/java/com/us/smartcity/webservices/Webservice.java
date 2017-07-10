package com.us.smartcity.webservices;

import android.util.Log;



import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Webservice {

	public static String login(String user, String password) {

		HttpClient httpclient = new DefaultHttpClient();
		boolean log_enable=true;
		httpclient.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
				"Caviersales");
		String response = null;
		URI url;
		String login1 = "";
		// String login2 = "";
		try {

			login1 = /*url + */"login_activity" + "&params[user_name]=" + user
					+ "&params[user_password]=" + password;

			url = new URI(login1.replace(" ", "%20"));

			if (log_enable) {
				Log.e("my webservice", "login_activity : " + url);
			}
			if (log_enable) {
				System.out.println(url);
			}
			HttpPost httppost = new HttpPost(url);
			HttpResponse httpResponse = null;

			try {
				httppost.addHeader("device", "Android");
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
						3);

				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

				// Execute HTTP Post Request
				httpResponse = httpclient.execute(httppost);

				response = EntityUtils.toString(httpResponse.getEntity(),
						"UTF-8");

				// this is what we extended for the getting the response string
				// which we going to parese for out use in database //

				if (log_enable) {
					System.out.println("login_activity = : " + response);
				}

			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				if (log_enable) {
					Log.e("Error : ", "Client Protocol exception");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				if (log_enable) {
					Log.e("Error : ", "IO Exception");
				}

			}
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return response;
	}

}