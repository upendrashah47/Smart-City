package com.us.smartcity.webservices;

import android.util.Log;



import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

public class RestClient 
{
	byte[] data = null;
    private ArrayList<NameValuePair> params;
    private ArrayList<NameValuePair> headers;
	private final int GET = 0, POST = 1, PUT = 2, DELETE = 3;
    private String url;
    boolean log_enable=true;
    
    public final static int TIMEOUT_CONNECTION = 60000;
	public final static int TIMEOUT_SOCKET = 60000;
 
    private int responseCode;
    private String message;
 
    private String response;
 
    public String getResponse() {
        return response;
    }
 
    public String getErrorMessage() {
        return message;
    }
 
    public int getResponseCode() {
        return responseCode;
    }
 
    public RestClient(String url)
    {
        this.url = url;
        params = new ArrayList<NameValuePair>();
        headers = new ArrayList<NameValuePair>();
    }
 
    public void addParam(String name, String value)
    {
        params.add(new BasicNameValuePair(name, value));
    }
 
    public void addHeader(String name, String value)
    {
        headers.add(new BasicNameValuePair(name, value));
    }
 
    /**
     * 0=GET,<br>1=POST,<br>2=PUT,<br>3=DELETE.
     * @param methodType
     * @throws Exception
     */
    public void execute(int methodType) throws Exception
    {
        switch(methodType) 
        {
            case GET:
            {
                //add parameters
                String combinedParams = "";
                if(!params.isEmpty()){
                    combinedParams += "?";
                    for(NameValuePair p : params)
                    {
                        String paramString = p.getName() + "=" + URLEncoder.encode(p.getValue(),"UTF-8");
                        if(combinedParams.length() > 1)
                        {
                            combinedParams  +=  "&" + paramString;
                        }
                        else
                        {
                            combinedParams += paramString;
                        }
                    }
                }
 
                HttpGet request = new HttpGet(url + combinedParams);
 
                if(log_enable){Log.e("Blancspot", "Url----"+url + combinedParams);}
                
                
                //add headers
                for(NameValuePair h : headers)
                {
                    request.addHeader(h.getName(), h.getValue());
//                    if(log_enable){Log.e("Blancspot", "Header----"+ h.getName()+ "-" + h.getValue());}
                }
 
                executeRequest(request, url);
                break;
            }
            case POST:
            {
                HttpPost request = new HttpPost(url);
 
                //add headers
                for(NameValuePair h : headers)
                {
                    request.addHeader(h.getName(), h.getValue());
                }
 
                if(!params.isEmpty()){
                    request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                }
 
                executeRequest(request, url);
                break;
            }
            case PUT:
            	break;
            	
            case DELETE:
            	HttpDelete request = new HttpDelete(url);
            	for(NameValuePair h : headers)
                {
                    request.addHeader(h.getName(), h.getValue());
                }
 
                executeRequest(request, url);
 
            	break;
        }
    }
    
    private void executeRequest(HttpUriRequest request, String url) throws IOException {
    	HttpParams httpParameters = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT_CONNECTION);
		HttpConnectionParams.setSoTimeout(httpParameters, TIMEOUT_SOCKET);
		
		HttpClient client = getHttpClient(httpParameters);             
        HttpResponse httpResponse;
        HttpEntity entity = null;
        try 
        {
            httpResponse = client.execute(request);
            responseCode = httpResponse.getStatusLine().getStatusCode();
            message = httpResponse.getStatusLine().getReasonPhrase();

            entity = httpResponse.getEntity();

            if (entity != null)
            {
                InputStream instream = entity.getContent();
                response = convertStreamToString(instream);
                instream.close();
            }
        }
        catch (SocketTimeoutException e) {
            e.printStackTrace();
            response = "Connection time out";
            throw e;
        }
        catch (ConnectTimeoutException e)
        {
            e.printStackTrace();
            response = "Connection time out";
            throw e;
        }
        catch (ClientProtocolException e)
        {
        	response = "Connection time out";
            e.printStackTrace();
            throw e;
        }
        catch (IOException e)
        {
        	response = "Connection time out";
            e.printStackTrace();
            throw e;
        } finally {
        	try{
        	entity.consumeContent();
        	}catch(NullPointerException e){}
            client.getConnectionManager().shutdown();
        }
    }
    
    public void executeWithImageRequest(MultipartEntity reqEntity2) throws IOException {
    	HttpPost request = new HttpPost(url);
    	 
        //add headers
        for(NameValuePair h : headers)
        {
            request.addHeader(h.getName(), h.getValue());
        }
       
    	request.setEntity(reqEntity2);

        executeRequest(request, url);
    }
 
    private static String convertStreamToString(InputStream is) throws IOException {
 
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
 
        String line = null;
        try {
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
        } finally {
            try 
            {
                is.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
    
    public static HttpClient getHttpClient(HttpParams httpParameters) 
	 {   
		 try 
		 {   
			 return new DefaultHttpClient(httpParameters);   
			 
//			 KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());   
//			 trustStore.load(null, null);   
//			 SSLSocketFactory sf = new AndroidSSLSocketFactory(trustStore);   
//			 sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);   
//			 HttpProtocolParams.setVersion(httpParameters, HttpVersion.HTTP_1_1);   
//			 HttpProtocolParams.setContentCharset(httpParameters, HTTP.UTF_8);   
//			 SchemeRegistry registry = new SchemeRegistry();   
//			 registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));   
//			 registry.register(new Scheme("https", sf, 443));   
//			 ClientConnectionManager ccm = new ThreadSafeClientConnManager(httpParameters, registry);   
//			 return new DefaultHttpClient(ccm, httpParameters);   
		 } 
		 catch (Exception e) 
		 {   
			 return new DefaultHttpClient(httpParameters);   
		 }   
	 }   
    
    public String postDataToServer(JSONObject object) throws IOException {
        String response = null;
        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT_CONNECTION);
        HttpConnectionParams.setSoTimeout(httpParameters, TIMEOUT_SOCKET);
        HttpClient client = getHttpClient(httpParameters);

        HttpPost postMethod = new HttpPost(url);

        StringEntity se = new StringEntity(object.toString());
        se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        postMethod.setEntity(se);
//        postMethod.setHeader("Accept", "application/json");
//        postMethod.setHeader("Content-type", "application/json");
//        postMethod.setHeader("Accept-Encoding", "gzip");

        HttpResponse httpResponse;
        HttpEntity entity1 = null;
        try
        {
            httpResponse = client.execute(postMethod);
            responseCode = httpResponse.getStatusLine().getStatusCode();
            message = httpResponse.getStatusLine().getReasonPhrase();

            entity1 = httpResponse.getEntity();

            if (entity1 != null)
            {
                InputStream instream = entity1.getContent();
                Header contentEncoding = httpResponse.getFirstHeader("Content-Encoding");
                if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
                    instream = new GZIPInputStream(instream);
                }
                response = convertStreamToString(instream);
                instream.close();
            }
        }
        catch (SocketTimeoutException e)
        {
            e.printStackTrace();
            response = "Connection time out";
            throw e;
        }
        catch (ConnectTimeoutException e)
        {
            client.getConnectionManager().shutdown();
            response = "Connection time out";
            throw e;
        }
        catch (ClientProtocolException e)
        {
            e.printStackTrace();
            throw e;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            throw e;
        }
        finally {
				try {
					entity1.consumeContent();
				} catch (NullPointerException e) {
				}
			client.getConnectionManager().shutdown();
		}
        return response;
    }
    
    public String updateDataToServer(JSONObject object) throws IOException {
        String response = null;
        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT_CONNECTION);
        HttpConnectionParams.setSoTimeout(httpParameters, TIMEOUT_SOCKET);
        HttpClient client = getHttpClient(httpParameters);

        HttpPut putMethod = new HttpPut(url);

        if(log_enable){Log.i("Blancspot", "----------------------------------------- PUT METHOD "+url);}

        StringEntity se = new StringEntity(object.toString(), "UTF-8");
        putMethod.setEntity(se);
        putMethod.setHeader("Accept", "application/json");
        putMethod.setHeader("Content-type", "application/json");
        putMethod.setHeader("Accept-Encoding", "gzip");

        HttpResponse httpResponse;
        HttpEntity entity1=null;
        try
        {
            httpResponse = client.execute(putMethod);
            responseCode = httpResponse.getStatusLine().getStatusCode();
            message = httpResponse.getStatusLine().getReasonPhrase();

            entity1 = httpResponse.getEntity();

            if (entity1 != null)
            {
                InputStream instream = entity1.getContent();
                Header contentEncoding = httpResponse.getFirstHeader("Content-Encoding");
                if (contentEncoding != null && contentEncoding.getValue().equalsIgnoreCase("gzip")) {
                    instream = new GZIPInputStream(instream);
                }
                response = convertStreamToString(instream);
                instream.close();
            }
        }
        catch (SocketTimeoutException e)
        {
            e.printStackTrace();
            response = "Connection time out";
            throw e;
        }
        catch (ConnectTimeoutException e)
        {
            e.printStackTrace();
            response = "Connection time out";
            throw e;
        }
        catch (ClientProtocolException e)
        {
            e.printStackTrace();
            throw e;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            throw e;
        } finally {
        	try{
            	entity1.consumeContent();
            	}catch(NullPointerException e){}
            client.getConnectionManager().shutdown();
        }

        return response;
    }
    
    public String deleteUserSession() throws IOException {
        HttpParams httpParameters = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParameters, TIMEOUT_CONNECTION);
        HttpConnectionParams.setSoTimeout(httpParameters, TIMEOUT_SOCKET);

        HttpClient client = getHttpClient(httpParameters);

        HttpDelete deleteMethod = new HttpDelete(url);
        if(log_enable){Log.i("Blancspot", "------------------- Url "+url);}

        HttpResponse httpResponse;
        try
        {
            httpResponse = client.execute(deleteMethod);
            responseCode = httpResponse.getStatusLine().getStatusCode();
            message = httpResponse.getStatusLine().getReasonPhrase();

        }
        catch (SocketTimeoutException e)
        {
            message = "Connection time out";
            throw e;
        }
        catch (ConnectTimeoutException e)
        {
            response = "Connection time out";
            throw e;
        }
        catch (ClientProtocolException e)
        {
            e.printStackTrace();
            throw e;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            throw e;
        } finally {
            client.getConnectionManager().shutdown();
        }

        return message;
    }
}