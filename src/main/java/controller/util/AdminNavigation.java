package controller.util;

import java.io.Serializable;

public class AdminNavigation implements Serializable {
	private static final long serialVersionUID = 1L;
	private static AdminNavigation instance;

	private AdminNavigation() {
	}

	public static synchronized AdminNavigation getInstance() {
		if (instance == null) {
			instance = new AdminNavigation();
		}
		return instance;
	}

	// Global links
	private final String logout = "<a href=\"main?action=logout\">Logout</a>";
	private final String adminPage = "<a href=\"main?action=default\">Admin page</a>";
	private final String updateUsers = "<a href=\"main?action=getAllUsers\">Update users</a>";
	private final String addBook = "<a href=\"main?action=addBookLink\">Add book</a>";
	private final String updateBooks = "<a href=\"main?action=getAllBooks\">Update books</a>";
	private final String addAuthor = "<a href=\"main?action=addAuthorLink\">Add author</a>";
	private final String updateAuthor = "<a href=\"main?action=getAllAuthors\">Update authors</a>";
	private final String addPublisher = "<a href=\"main?action=addPublisherLink\">Add publisher</a>";
	private final String updatePublisher = "<a href=\"main?action=getAllPublishers\">Update publisher</a>";

	// Parameters
	private final String bookEditVis = "inline";
	private final String bookOrderVis = "none";
	private final String userTypeVis = "inline";

	@Override
	public String toString() {
		return "<table>" + "<tr>" + "<td>" + adminPage + "</td>" + "<td>" + updateUsers + "</td>" + "<td>" + addBook
				+ "</td>" + "<td>" + updateBooks + "</td>" + "<td>" + addAuthor + "</td>" + "<td>" + updateAuthor
				+ "</td>" + "<td>" + addPublisher + "</td>" + "<td>" + updatePublisher + "</td>" + "<td>" + logout
				+ "</td>" + "</tr>" + "</table>";
	}

	public String getBookEditVis() {
		return bookEditVis;
	}

	public String getBookOrderVis() {
		return bookOrderVis;
	}

	public String getUserTypeVis() {
		return userTypeVis;
	}

}
