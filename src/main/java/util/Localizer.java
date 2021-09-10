package util;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

public class Localizer {
	private static final String resourceName = "applocalization";

	private Localizer() {
	}

	public static String getString(HttpServletRequest request, String key) {
		Locale locale = (Locale) request.getSession().getAttribute("locale");
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle(resourceName, locale);
			
		return resourceBundle.getString(key);
	}

}
