// Publié sous les termes de la Licence MIT
// ARKEA Lab
// eric.grall@arkea.com
// github.com/egrall/testApiGlassCamp
package com.arkea.testapiglasscamp;

import org.json.JSONException;
import org.json.JSONObject;


public class JSONMailParser extends AbstractJSONParser<Mail> {
	
	public Mail parse(JSONObject json) throws JSONException {
		try {
			//get wire data
			String value = "", kind = "";	
			
			value = json.getString(Mail.IMail.VALUE);
			if(json.has((Mail.IMail.KIND)))  value = json.getString(Mail.IMail.KIND);
							        
		    return new Mail(value, kind);

		} catch (Exception e) {
			throw new JSONException("Unexpected OJSONContentParser parse type encountered.");
		}
	}
}
