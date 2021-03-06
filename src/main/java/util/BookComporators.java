package util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Class that builds and returns as {@link List} pagination parameters "Sort by" and "Sort order"
 */
public class BookComporators {

	private BookComporators() {
	}

	/**
	 * Class that encapsulates pagination parameter "Sort by"
	 */
	public static class SortBy {
		private String title;
		private String value;
		
		public SortBy(String title, String value) {
			this.title = title;
			this.value = value;
		}

		public String getTitle() {
			return title;
		}

		public String getValue() {
			return value;
		}

	}
	
	/**
	 * Class that encapsulates pagination parameter "Order by"
	 */
	public static class OrderBy {
		private String title;
		private String value;
		
		public OrderBy(String title, String value) {
			this.title = title;
			this.value = value;
		}

		public String getTitle() {
			return title;
		}

		public String getValue() {
			return value;
		}

	}
	
	/**
	 * Returns list of available pagination parameters "Sort by" 
	 * @param request {@link HttpServletRequest}
	 * @return {@link List} of {@link SortBy}
	 */
	public static List<SortBy> getAllSortBy(HttpServletRequest request) {
		List<SortBy> result = new ArrayList<BookComporators.SortBy>();
		result.add(new SortBy(Localizer.getString(request, "title"),"b.title"));
		result.add(new SortBy(Localizer.getString(request, "authorLastname"),"a.last_name"));
		result.add(new SortBy(Localizer.getString(request, "publisher"),"p.name"));
		result.add(new SortBy(Localizer.getString(request, "releaseDate"),"b.release_date"));
		return result;
	}
	
	/**
	 * Returns list of available pagination parameters "Order by" 
	 * @param request {@link HttpServletRequest}
	 * @return {@link List} of {@link OrderBy}
	 */
	public static List<OrderBy> getAllOrderBy(HttpServletRequest request) {
		List<OrderBy> result = new ArrayList<BookComporators.OrderBy>();
		result.add(new OrderBy(Localizer.getString(request, "ascending"),"asc"));
		result.add(new OrderBy(Localizer.getString(request, "descending"),"desc"));
		return result;
	}
		
}
