// Publié sous les termes de la Licence MIT
// ARKEA Lab
// eric.grall@arkea.com
// github.com/egrall/testApiGlassCamp
package com.arkea.testapiglasscamp;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONAgencyParser extends AbstractJSONParser<Agency> {
	
	JSONMailParser mailParser = new JSONMailParser();
	
	public Agency parse(JSONObject json) throws JSONException {
		try {
			//get wire data
			String name = "", mail = "";	
			ArrayList<Mail> mails = new ArrayList<Mail>();

			int id = json.getInt(Agency.IAgency.ID);
			if(json.has((Agency.IAgency.NAME)))  name = json.getString(Agency.IAgency.NAME);
			if(json.has((Agency.IAgency.MAILS)))	{
				JSONArray array = json.optJSONArray(Agency.IAgency.MAILS);
				if(array!=null)	{
					mails.addAll((List<Mail>)mailParser.parse(array));
				}
			}
				        
		    return new Agency( id, name,  mails);

		} catch (Exception e) {
			throw new JSONException("Unexpected OJSONContentParser parse type encountered.");
		}
	}
}
