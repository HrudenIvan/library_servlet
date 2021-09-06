package controller.util;

import java.io.Serializable;

public class GuestNavigation implements Serializable {
	private static final long serialVersionUID = 1L;
	private static GuestNavigation instance;

	private GuestNavigation() {
	}

	public static synchronized GuestNavigation getInstance() {
		if (instance == null) {
			instance = new GuestNavigation();
		}
		return instance;
	}

	// Global links
	private final String getAllBooks = "<a href=\"main?action=getAllBooks\">Books list</a>";
	private final String login = "<a href=\"login.jsp\">Login</a>";
	private final String register = "<a href=\"main?action=register\">Register</a>";

	// Local links
	/*
	private final String bookUpdateLinkPref = null;
	private final String bookUpdateLinkPost = null;*/

	// Parameters
	private final String bookEditVis = "none";
	private final String bookOrderVis =  "none";

	public String getBookEditVis() {
		return bookEditVis;
	}
	
	public String getBookOrderVis() {
		return bookOrderVis;
	}
/*
	public String getBookUpdateLinkPref() {
		return bookUpdateLinkPref;
	}

	public String getBookUpdateLinkPost() {
		return bookUpdateLinkPost;
	}*/

	@Override
	public String toString() {
		return "<table>" + "<tr>" + "<td>" + getAllBooks + "</td>" + "<td>" + login + "</td>" + "<td>" + register
				+ "</td>" + "</tr>" + "</table>";
	}
}
