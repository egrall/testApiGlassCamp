// Publié sous les termes de la Licence MIT
// ARKEA Lab
// eric.grall@arkea.com
// github.com/egrall/testApiGlassCamp


package com.arkea.testapiglasscamp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public abstract class AbstractJSONParser<T> {
    
	public Collection<T> CreateAndParse(String root, JSONObject json) throws JSONException {
		try {
			List<T> data = new ArrayList<T>();

		   	try {
		   		JSONArray array = json.optJSONArray(root);
		   		if(array != null) {
		   			data = (List<T>) parse(array);
		   		} else {
					JSONObject objet = json.optJSONObject(root);
					if(objet != null) {
						T obj = parse(objet);
				   		data.add(obj);		 
					}	else {
						T obj = parse(json);
				   		data.add(obj);					
					}
		   		}
			} catch (Exception e) {		
				throw new JSONException("Unexpected OAbstractJSONParser create and parse type encountered.");
			}		   	
		   	
		   	return data;
		} catch (Exception e) {
			throw new JSONException("Unexpected OAbstractJSONParser create and parse type encountered.");
		}
	}
	

	/** 
     * All derived parsers must implement parsing a JSONObject instance of themselves. 
     * @root is the name of JSON object and root of the array
     */
    public JSONArray createArrayParser(String root, JSONObject json) throws JSONException {
	    JSONArray array = json.getJSONArray(root);
	    return array;
	}	
    
	/** 
     * All derived parsers must implement parsing a JSONObject instance of themselves. 
     */
    public T parse(JSONObject json) throws JSONException {
    	throw new JSONException("not implemented");
    }
    
    /**
     * Only the GroupParser needs to implement this.
     * 
     */ 
    public Collection<T> parse(JSONArray array) throws JSONException {
		try {
			List<T> data = new ArrayList<T>();
  	       	for(int i=0;i< array.length();i++) {
				JSONObject json = array.getJSONObject(i);
				T item = parse(json);
                  if(item != null) {
                	 data.add(item);
                 }
  	       	}
			return data;
		} catch (Exception e) {
			throw new JSONException("Unexpected OAbstractJSONParser parse.");
		}    	
    }

    
	/** 
     * All derived parsers must implement parsing a JSONObject instance of themselves. 
     */
    public NameValuePair urlparse(String id, String value) throws JSONException {
		try {
			NameValuePair nameValuePair = new BasicNameValuePair(id, value);
			return nameValuePair;			
		} catch (Exception e) {		
			throw new JSONException("Unexpected OAbstractJSONParser urlparse.");
		}		   	    	
    }
}
