package util;

import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.entity.Author;
import model.entity.Book;

/**
 * Utility class for different methods
 */
public class Util {

	private Util() {

	}

	/**
	 * Defines {@link Locale} from {@link Cookie}. If locale not stored in cookie,
	 * then sets locale to "en_US"
	 * @param req {@link HttpServletRequest}
	 * @return {@link Locale}
	 */
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

	/**
	 * Defines pagination parameter Sort by. First of all tries to retrieve it from request
	 * parameter "sort". If "sort" is null, then tries to find it in cookie. If cookie
	 * does not contain it, then set default value "b.release_date". Last step - store
	 * this parameter in cookie.  
	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @return pagination parameter Sort by as {@link String}
	 */
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
	
	/**
	 * Defines pagination parameter Order by. First of all tries to retrieve it from request
	 * parameter "order". If "order" is null, then tries to find it in cookie. If cookie
	 * does not contain it, then set default value "desc". Last step - store
	 * this parameter in cookie.  
	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @return pagination parameter Order by as {@link String}
	 */
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

	/**
	 * Builds pagination navigation menu.
	 * @param page current page
	 * @param pagesCount total amount of pages
	 * @param title {@link Book} title
	 * @param aLastname {@link Author} last name
	 * @param aFirstname {@link Author} first name
	 * @return pagination navigation menu as {@link String}
	 */
	public static String buildPaginationNav(int page, int pagesCount, String title, String aLastname,
			String aFirstname) {
		if (pagesCount == 1) {
			return "";
		}
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
