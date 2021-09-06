package controller.util;

import java.io.Serializable;

public class LibrarianNavigation implements Serializable {
	private static final long serialVersionUID = 1L;
	private static LibrarianNavigation instance;

	private LibrarianNavigation() {
	}

	public static synchronized LibrarianNavigation getInstance() {
		if (instance == null) {
			instance = new LibrarianNavigation();
		}
		return instance;
	}

	// Global links
	private final String logout = "<a href=\"main?action=logout\">Logout</a>";
	private final String librarianPage = "<a href=\"main?action=prepareLibrarian\">Librarian page</a>";

	@Override
	public String toString() {
		return "<table>" + "<tr>" + "<td>" + librarianPage + "</td>" + "<td>" + logout + "</td>" + "</tr>" + "</table>";
	}
}
