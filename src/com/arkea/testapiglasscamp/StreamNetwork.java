// Publié sous les termes de la Licence MIT
// ARKEA Lab
// eric.grall@arkea.com
// github.com/egrall/testApiGlassCamp
package com.arkea.testapiglasscamp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class StreamNetwork {
	
	private static final String TAG = "StreamNetwork";

	private static final boolean DEBUG = Build.DEBUG;			
	private static final boolean PROXY = Build.PROXY;	
	
	
    protected URL urlProxy;
    protected String proxyhost = "";
    protected String proxyport = "";
    protected String proxylogin ="";
    protected String proxypass = "";   			
	
    
	public InputStream getInputStream(String url) {	
		InputStream is = null;
		URL _URL;
 		try {
			_URL = new URL(url);
		    if(_URL!=null) {
	        	if(PROXY)	{
	        	    proxyhost = "xxxxxxxx";
	        	    proxyport = "xxxxxxxx";
	        	    proxylogin ="xxxxxxxx";
	        	    proxypass = "xxxxxxxx";   	
	        	    
	        		Authenticator.setDefault(new SimpleAuthenticator(proxylogin,proxypass));
	           		Properties systemProperties = System.getProperties();
	    			systemProperties.setProperty("http.proxyHost", proxyhost);
	    			systemProperties.setProperty("http.proxyPort", proxyport);
		        	HttpURLConnection conn = (HttpURLConnection )_URL.openConnection();
		        	if(conn!=null) {
		        		conn.connect();   
		        		is = conn.getInputStream();
		        	}
	        	}	else	{
		        	URLConnection conn = _URL.openConnection();
		        	if(conn!=null) {
		        		conn.connect();
		        		is = conn.getInputStream();
		        	}
	        	}
		    }			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
		return is;
	}    
    
	public Bitmap downloadImage(String url) {
	    //---------------------------------------------------
	    Bitmap bm = null;

		try {
		    InputStream is = getInputStream(url);
		    if(is!=null) {
		    	BufferedInputStream bis = new BufferedInputStream(is);
		    	if(bis!=null)	{
		    		bm = BitmapFactory.decodeStream(bis);			
		    		bis.close();
		    	}
		    	is.close();
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}

	    return bm;
	}	
	
	public BufferedReader downloadBuffer(String url) {
	    //---------------------------------------------------
		BufferedReader reader = null;	    
        try {
    	    InputStream is = getInputStream(url);
    	    if(is!=null)	{
    	    	reader = new BufferedReader(new InputStreamReader(is));       	
    	    	is.close();
    	    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return reader;
	}
	
	public String downloadString(String url) {
	    //---------------------------------------------------
	    String stringInStream = "";
	    
        try {
    	    InputStream is = getInputStream(url);
    	    if(is!=null)	{
    	    	stringInStream = convertStreamToString(is);		        	
    	    	is.close();
    	    }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return stringInStream;
	}


	private static String convertStreamToString(InputStream is) {
	    //To convert the InputStream to String we use the BufferedReader.readLine()
	    // method. We iterate until the BufferedReader return null which means
	    // there's no more data to read. Each line will appended to a StringBuilder
	    // and returned as String.
	    //
	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();
	    if((reader!=null) || (sb!=null)) {
		    String line = null;
		    try {
		        while ((line = reader.readLine()) != null) {
		            sb.append(line + "\n");
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            is.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
	    }

	    return sb.toString();
	}
}