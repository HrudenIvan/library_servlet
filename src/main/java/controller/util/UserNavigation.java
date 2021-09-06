package controller.util;

import java.io.Serializable;

public class UserNavigation implements Serializable {
	private static final long serialVersionUID = 1L;
	private static UserNavigation instance;

	private UserNavigation() {
	}

	public static synchronized UserNavigation getInstance() {
		if (instance == null) {
			instance = new UserNavigation();
		}
		return instance;
	}

	// Global links
	private final String getAllBooks = "<a href=\"main?action=getAllBooks\">Books list</a>";
	private final String privateCabinet = "<a href=\"main?action=prepareCabinet\">Private cabinet</a>";
	private final String logout = "<a href=\"main?action=logout\">Logout</a>";

	// Parameters
	private final String bookEditVis = "none";
	private final String bookOrderVis = "inline";
	private final String userTypeVis = "none";

	public String getBookEditVis() {
		return bookEditVis;
	}

	public String getBookOrderVis() {
		return bookOrderVis;
	}

	@Override
	public String toString() {
		return "<table>" + "<tr>" + "<td>" + getAllBooks + "</td>"  + "<td>" + privateCabinet + "</td>" + "<td>" + logout + "</td>" + "</tr>" + "</table>";
	}

	public String getUserTypeVis() {
		return userTypeVis;
	}

}
