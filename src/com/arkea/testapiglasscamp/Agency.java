// Publié sous les termes de la Licence MIT
// ARKEA Lab
// eric.grall@arkea.com
// github.com/egrall/testApiGlassCamp

package com.arkea.testapiglasscamp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Agency implements Serializable {
	
		private static final long serialVersionUID = 5978063931481483415L;

		public int _id;
		public String _name;
		public List<Mail> _mail = new ArrayList<Mail>();		//adu
		
		public Agency(int id, String name)	{
			this._id = id;
			this._name = name;
		}
		
		public Agency(int id, String name, ArrayList<Mail> mail)	{
			this._id = id;
			this._name = name;
			this._mail.addAll(mail);
		}		
		
		public void pushMail(Mail mail)	{
			this._mail.add(mail);
		}
		
		public class IAgency	{
	    	public static final String ROOT_JSON = "agency";
	    	public static final String ID = "agencyId";
	    	public static final String NAME = "name";
	    	public static final String MAILS = "emails";
	    }  	
	
}