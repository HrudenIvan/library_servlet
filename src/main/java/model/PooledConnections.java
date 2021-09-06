package model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PooledConnections {
	private static DataSource instance;

	private PooledConnections() {
	}

	public static DataSource getInstance() {
		return instance;
	}

	public static void init() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			instance = (DataSource) envContext.lookup("jdbc/LibraryDB");
		} catch (NamingException ex) {
			throw new IllegalStateException("Cannot init DBManager", ex);
		}
	}

}
