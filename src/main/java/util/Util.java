package util;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Util {

	private Util() {

	}

	public static Locale defineLocale(HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		String language = null;
		String country = null;
		Locale locale = null;
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("language")) {
					language = c.getValue();
				}
				if (c.getName().equals("country")) {
					country = c.getValue();
				}
			}
		}
		if (country != null && language != null) {
			locale = new Locale(language, country);
		} else {
			locale = new Locale("en", "US");
		}
		return locale;
	}

}
