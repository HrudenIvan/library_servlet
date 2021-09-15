package model;

public class Constants {

	private Constants() {
	}

	//UserDAO
	
	public static final String GET_USER_BY_LOGIN =
			  "SELECT u.id, ut.id, ut.type, u.password, u.salt, u.is_blocked "
			+ "FROM library.user u "
			+ "JOIN library.user_type ut "
			+ "WHERE u.login = ? AND ut.id = u.user_type_id";
	
	public static final String GET_USER_BY_ID = 
			"SELECT u.id, u.login, u.first_name, u.last_name, u.penalty, u.is_blocked, ut.id , ut.type "
			+ "FROM library.user u "
			+ "JOIN library.user_type ut "
			+ "WHERE u.id = ? AND ut.id = u.user_type_id";

	public static final String GET_ALL_USERS =
			  "SELECT u.id, u.login, u.first_name, u.last_name, ut.id, ut.type, u.penalty, u.is_blocked "
			+ "FROM library.user u " + "JOIN library.user_type ut " + "WHERE ut.id = u.user_type_id";

	public static final String UPDATE_USER =
			  "UPDATE library.user u "
			+ "SET u.login= ? , u.first_name= ? , u.last_name = ? , u.user_type_id = ? , u.password = ? , u.salt = ? , u.is_blocked = ? "
			+ "WHERE u.id= ?";
	
	public static final String ADD_USER =
			  "INSERT INTO library.user "
			+ "VALUES (DEFAULT, ? , ? , ? , ? , ? , DEFAULT, DEFAULT, DEFAULT)";
	
	public static final String GET_ALL_USERTYPES =
			  "SELECT ut.id, ut.type "
			+ "FROM library.user_type ut";
	
	public static final String GET_ALL_USER_WITH_OPEN_ORDERS = 
			"SELECT DISTINCT u.id, u.login, u.first_name, u.last_name "
			+ "FROM library.user u "
			+ "JOIN library.book_order bo "
			+ "WHERE u.id = bo.user_id AND bo.order_status_id <> 1 AND bo.order_status_id <> 4 "
			+ "ORDER BY 4";
	
	public static final String UPDATE_USER_PENALTY = 
			"UPDATE library.user "
			+ "SET penalty = penalty + ? "
			+ "WHERE id = ?";
	
	//BookDAO

	public static final String GET_BOOK_BY_ID =
			  "SELECT b.id AS bid, b.title, b.available, b.total, a.id AS aid, a.first_name, a.last_name, p.id AS pid, p.name, b.release_date "
			+ "FROM library.book b " + "JOIN library.author a " + "JOIN library.publisher p "
			+ "WHERE b.author_id = a.id and b.publisher_id = p.id and b.id = ?";

	public static final String UPDATE_BOOK =
			  "UPDATE library.book b "
			+ "SET b.title = ? , b.available = ? , b.total = ? , b.author_id = ? , b.publisher_id = ? , b.release_date = ? "
			+ "WHERE b.id = ?";

	public static final String ADD_BOOK =
			  "INSERT INTO library.book "
			+ "VALUES (DEFAULT, ? , ? , ? , ? , ?, ?)";
	
	public static final String DECRIMENT_BOOK_AVAILABLE = 
			"UPDATE library.book "
			+ "SET available = available - 1 "
			+ "WHERE id= ?";
	
	//AuthorDAO
	
	public static final String GET_AUTHOR_BY_ID =
			  "SELECT a.id, a.first_name, a.last_name "
			+ "FROM library.author a "
			+ "WHERE a.id = ?";

	public static final String GET_ALL_AUTHORS =
			  "SELECT a.id, a.first_name, a.last_name "
			+ "FROM library.author a";

	public static final String ADD_AUTHOR =
			  "INSERT INTO library.author "
			+ "VALUES (DEFAULT , ? , ? )";

	public static final String UPDATE_AUTHOR =
			  "UPDATE library.author a "
			+ "SET a.first_name = ? , a.last_name = ? "
			+ "WHERE a.id = ?";

	//PublisherDAO

	public static final String GET_PUBLISHER_BY_ID =
			  "SELECT p.id, p.name "
			+ "FROM library.publisher p "
			+ "WHERE p.id = ?";

	public static final String GET_ALL_PUBLISHERS =
			  "SELECT p.id, p.name "
			+ "FROM library.publisher p";

	public static final String ADD_PUBLISHER =
			  "INSERT INTO library.publisher "
			+ "VALUES (DEFAULT, ? )";

	public static final String UPDATE_PUBLISHER =
			  "UPDATE library.publisher p "
			+ "SET p.name = ? "
			+ "WHERE p.id = ?";

	//OrderDAO
	
	public static final String GET_ALL_ORDER_TYPES =
			  "SELECT ot.id, ot.type "
			+ "FROM library.order_type ot";

	public static final String GET_ALL_ORDER_STATUSES =
			  "SELECT os.id, os.status "
			+ "FROM library.order_status os "
			+ "ORDER BY 1";

	public static final String ADD_BOOK_ORDER =
			  "INSERT INTO library.book_order (user_id, book_id, order_date, order_type_id, order_status_id) "
			+ "VALUES ( ? , ? , DEFAULT, ? , DEFAULT)";

	public static final String GET_ALL_USER_ORDERS = 
			"SELECT bo.book_id, bo.order_date, b.title, ot.type, os.id, os.status, bo.open_date, bo.close_date, bo.return_date "
			+ "FROM library.book_order bo "
			+ "JOIN library.book b "
			+ "JOIN library.order_status os "
			+ "JOIN library.order_type ot "
			+ "WHERE bo.order_type_id = ot.id and bo.order_status_id = os.id and bo.book_id = b.id and bo.user_id = ? and bo.order_status_id <> 4 "
			+ "ORDER BY 2";

	public static final String GET_NEW_BOOK_ORDERS = 
			"SELECT bo.user_id, bo.book_id, bo.order_date, b.title, ot.type, os.id, os.status, bo.open_date, bo.close_date, bo.return_date "
			+ "FROM library.book_order bo "
			+ "JOIN library.book b "
			+ "JOIN library.order_status os "
			+ "JOIN library.order_type ot "
			+ "WHERE bo.order_type_id = ot.id and bo.order_status_id = os.id and bo.book_id = b.id and bo.order_status_id = 1 "
			+ "ORDER BY 3";

	public static final String GET_BOOK_ORDER = 
			"SELECT bo.order_date, b.title, ot.type, bo.open_date, bo.close_date, os.id, os.status "
			+ "FROM library.book_order bo "
			+ "JOIN library.book b "
			+ "JOIN library.order_type ot "
			+ "JOIN library.order_status os "
			+ "WHERE bo.user_id = ? and bo.book_id = ? and bo.book_id = b.id and bo.order_type_id = ot.id and bo.order_status_id = os.id";

	public static final String GET_USER_OPEN_BOOK_ORDER = 
			"SELECT bo.book_id, bo.order_date, b.title, ot.type, bo.open_date, bo.close_date, os.id, os.status "
			+ "FROM library.book_order bo "
			+ "JOIN library.book b "
			+ "JOIN library.order_type ot "
			+ "JOIN library.order_status os "
			+ "WHERE bo.user_id = ? and bo.book_id = b.id and bo.order_type_id = ot.id and bo.order_status_id = os.id AND bo.order_status_id <> 1 AND bo.order_status_id <> 4 "
			+ "ORDER BY 1";

}
