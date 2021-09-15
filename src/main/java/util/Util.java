package util;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	public static String defineSortAndStoreInCookie(HttpServletRequest request, HttpServletResponse response) {
		String result = request.getParameter("sort");
		if (result == null) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("sort")) {
						result = c.getValue();
					}
				}
			}
		}
		if (result == null) {
			result = "b.release_date";
		}
		Cookie cookie = new Cookie("sort", result);
		response.addCookie(cookie);
		
		return result;
	}
	
	public static String defineOrderAndStoreInCookie(HttpServletRequest request, HttpServletResponse response) {
		String result = request.getParameter("order");
		if (result == null) {
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (Cookie c : cookies) {
					if (c.getName().equals("order")) {
						result = c.getValue();
					}
				}
			}
		}
		if (result == null) {
			result = "desc";
		}
		Cookie cookie = new Cookie("order", result);
		response.addCookie(cookie);
		
		return result;
	}

	public static String buildPaginationNav(int page, int pagesCount, String title, String aLastname,
			String aFirstname) {
		StringBuilder path = new StringBuilder();
		path.append("main?action=getAllBooks")
			.append("&title=")
			.append(title)
			.append("&aLastname=")
			.append(aLastname)
			.append("&aFirstname=")
			.append(aFirstname)
			.append("&page=");
		StringBuilder result = new StringBuilder();
		result.append("<nav>")
			  .append("<ul class=\"pagination pagination-lg\">");
		for (int i = 0; i < pagesCount; i++) {
			if (i == page) {
				result.append("<li class=\"page-item active\"><span class=\"page-link\">")
						.append(i+1)
						.append("</span></li>");
			} else {
				result.append("<li class=\"page-item\"><a class=\"page-link\" href=\"")
						.append(path)
						.append(i)
						.append("\">")
						.append(i+1)
						.append("</a></li>");
			}
		}
		result.append("</ul></nav>");
		return result.toString();
	}
	
}
