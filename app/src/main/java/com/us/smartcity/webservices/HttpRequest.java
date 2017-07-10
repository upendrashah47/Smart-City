package com.us.smartcity.webservices;

import android.util.Log;


import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HttpRequest {

	DefaultHttpClient httpClient;
	HttpContext localContext;

	HttpResponse response = null;
	HttpPost httpPost = null;
	HttpGet httpGet = null;
	boolean log_enable=true;

	public HttpRequest() {
		HttpParams myParams = new BasicHttpParams();

		HttpConnectionParams.setConnectionTimeout(myParams, 10000);
		HttpConnectionParams.setSoTimeout(myParams, 10000);
		httpClient = new DefaultHttpClient(myParams);
		localContext = new BasicHttpContext();
	}

	public void clearCookies() {
		httpClient.getCookieStore().clear();
	}

	public void abort() {
		try {
			if (httpClient != null) {
				if(log_enable){System.out.println("Abort.");}
				httpPost.abort();
			}
		} catch (Exception e) {
			if(log_enable){System.out.println("abort" + e);}
		}
	}

	public String doPost(String url, HashMap<String, String> valueMap)
			throws Exception {
		HttpClient client = new DefaultHttpClient();
		client.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION,
				HttpVersion.HTTP_1_1);
		client.getParams().setParameter(CoreProtocolPNames.USER_AGENT,
				"");

		if(log_enable){System.out.println("URL :: " + url);}
		HttpPost post = new HttpPost(url);
		post.addHeader("device", "Android");
		MultipartEntity entity = new MultipartEntity(
				HttpMultipartMode.BROWSER_COMPATIBLE);

		Set<Map.Entry<String, String>> set = valueMap.entrySet();

		Iterator<Map.Entry<String, String>> i = set.iterator();

		String response = "";

		while (i.hasNext()) {
			Map.Entry<String, String> me = (Map.Entry<String, String>) i.next();
			if(log_enable){System.out.println(me.getKey() + " : " + me.getValue());}
			entity.addPart(me.getKey(), new StringBody(me.getValue(),
					"text/plain", Charset.forName("UTF-8")));
		}

		post.setEntity(entity);

		response = EntityUtils.toString(client.execute(post).getEntity(),
				"UTF-8");

		return response;
	}

	public String doPostWithFile(String url, String filekey, String filepath,
			HashMap<String, String> valueMap) throws Exception {
		if(log_enable){Log.e("", "Photo Post start");}
		
		HttpParams httpParameters = new BasicHttpParams();
        // Set the timeout in milliseconds until a connection is established.
        // The default value is zero, that means the timeout is not used. 
        int timeoutConnection = 30000;
        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
        // Set the default socket timeout (SO_TIMEOUT) 
        // in milliseconds which is the timeout for waiting for data.
        int timeoutSocket = 50000;
        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		
		HttpClient client = new DefaultHttpClient(httpParameters);
		client.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION,
				HttpVersion.HTTP_1_1);
		
		

		if(log_enable){System.out.println("URL :: " + url);}
		HttpPost post = new HttpPost(url);
		post.addHeader("device", "Android");
		MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);

		Set<Map.Entry<String, String>> set = valueMap.entrySet();

		Iterator<Map.Entry<String, String>> i = set.iterator();

		String response;

		while (i.hasNext()) {
			Map.Entry<String, String> me = (Map.Entry<String, String>) i.next();
			if(log_enable){System.out.println(me.getKey() + " : " + me.getValue());}
			entity.addPart(me.getKey(), new StringBody(me.getValue(),
					"text/plain", Charset.forName("UTF-8")));
		}

		if (!filepath.equals("")) {
			if(log_enable){Log.e("", "In photo");}
			entity.addPart(filekey, new FileBody(new File(filepath), "image/jpg"));
			if(log_enable){System.out.println(filekey + " : " + filepath);}
			if(log_enable){Log.e("", "In Key:::" + filekey + ".............." + "Path::" + filepath);}

		}
		post.setEntity(entity);
		HttpResponse httpResponse = httpClient.execute(post);
		response = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		/*
		 * response = EntityUtils.toString(client.execute(post).getEntity(),
		 * "UTF-8");
		 */
		return response;
	}

	public String sendGet(String url) {
		httpGet = new HttpGet(url);
		String ret = "";
		try {
			response = httpClient.execute(httpGet);
		} catch (Exception e) {
			if(log_enable){Log.e("Your App Name Here", e.getMessage());}
		}

		// int status = response.getStatusLine().getStatusCode();

		// we assume that the response body contains the error message
		try {
			ret = EntityUtils.toString(response.getEntity());
		} catch (IOException e) {
			if(log_enable){Log.e("Your App Name Here", e.getMessage());}
		}

		return ret;
	}

	public String postData(String url, List<NameValuePair> nameValuePairs)
			throws Exception {

		ResponseHandler<String> res = new BasicResponseHandler();
		HttpPost postMethod = new HttpPost(url);
		postMethod.addHeader("device", "Android");
		if(log_enable){System.out.println("URL " + url);}
		postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs, "utf-8"));
		String response = httpClient.execute(postMethod, res);
		return response;
	}
	
	public String doPostWithFile1(String url, String filekey, String filepath,
			HashMap<String, String> valueMap) throws Exception {
		
		
		HttpParams httpParameters = new BasicHttpParams();
        // Set the timeout in milliseconds until a connection is established.
        // The default value is zero, that means the timeout is not used. 
        int timeoutConnection = 30000;
        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
        // Set the default socket timeout (SO_TIMEOUT) 
        // in milliseconds which is the timeout for waiting for data.
        int timeoutSocket = 50000;
        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
		
		HttpClient client = new DefaultHttpClient(httpParameters);
		client.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION,
				HttpVersion.HTTP_1_1);
		
		if(log_enable){Log.e("", "Photo Post start");}
		
		if(log_enable){System.out.println("URL :: " + url);}
		
		RestClient restClient = new RestClient(url);
		
		MultipartEntity entity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);

		Set<Map.Entry<String, String>> set = valueMap.entrySet();

		Iterator<Map.Entry<String, String>> i = set.iterator();

		String response;

		while (i.hasNext()) {
			Map.Entry<String, String> me = (Map.Entry<String, String>) i.next();
			if(log_enable){Log.i("",me.getKey() + " : " + me.getValue());}
			entity.addPart(me.getKey(), new StringBody(me.getValue()));
		}
	
		if (!filepath.equals("")) {
			File f = new File(filepath);	
    		FileBody fb = new FileBody(f);
			entity.addPart(filekey, fb);
		}
		if(log_enable){Log.i("",filekey + " : " + filepath);}
		if(log_enable){Log.e("", "In Key:::" + filekey + ".............." + "Path::" + filepath);}
//		post.setEntity(entity);
//		HttpResponse httpResponse = httpClient.execute(post);
//		response = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		
		restClient.executeWithImageRequest(entity);
		
		response = restClient.getResponse();
		
		/*
		 * response = EntityUtils.toString(client.execute(post).getEntity(),
		 * "UTF-8");
		 */
		return response;
	}
}