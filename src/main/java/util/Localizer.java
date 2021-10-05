package util;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

/**
 * Utility class for localization of application messages
 */
public class Localizer {
	private static final String resourceName = "applocalization";

	/**
	 * Protect constructor to deny instantiation
	 */
	private Localizer() {
	}

	/**
	 * Return localized message from property file
	 * @param request {@link HttpServletRequest}
	 * @param key key from property file
	 * @return localized message
	 */
	public static String getString(HttpServletRequest request, String key) {
		Locale locale = (Locale) request.getSession().getAttribute("locale");
		
		ResourceBundle resourceBundle = ResourceBundle.getBundle(resourceName, locale);
			
		return resourceBundle.getString(key);
	}

}
