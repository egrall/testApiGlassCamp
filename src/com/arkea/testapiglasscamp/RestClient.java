// Publié sous les termes de la Licence MIT
// ARKEA Lab
// eric.grall@arkea.com
// github.com/egrall/testApiGlassCamp
package com.arkea.testapiglasscamp;

import java.util.Collection;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class RestClient {
	
	private static final String TAG = "RestClient";

  
    public Collection<?> connect(String url)    {
		try {
                // A Simple JSON Response Read
                String result= new StreamNetwork().downloadString(url);

                // A Simple JSONObject Creation
                JSONObject json;
				json = new JSONObject(result);

                // A Simple JSONObject Parsing
				JSONArray nameArray=json.names();
                JSONArray AgencyArray=json.getJSONArray(Agency.IAgency.ROOT_JSON);
        		JSONAgencyParser agencyParser = new JSONAgencyParser();
        		List<Agency> listAgency = (List<Agency>)agencyParser.parse(AgencyArray);
        		
                return listAgency;
                
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		return null;
		}
    }

}