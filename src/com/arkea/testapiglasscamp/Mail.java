// Publié sous les termes de la Licence MIT
// ARKEA Lab
// eric.grall@arkea.com
// github.com/egrall/testApiGlassCamp
package com.arkea.testapiglasscamp;

import java.io.Serializable;


public class Mail implements Serializable {
	
		private static final long serialVersionUID = 5978063998481483415L;
		public String _value;
		public String _kind;
		
		public Mail(String value)	{
			this._value = value;
		}
		
		public Mail(String value, String kind)	{
			this._value = value;
			this._kind = kind;
		}		
		
		
		public class IMail	{
	    	public static final String ROOT_JSON = "Mail";
	    	public static final String VALUE = "value";
	    	public static final String KIND = "kind";
	    	
	    }  	
	
}