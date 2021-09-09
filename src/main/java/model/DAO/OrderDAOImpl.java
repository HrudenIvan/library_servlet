package model.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Constants;
import model.PooledConnections;
import model.entity.BookOrder;
import model.entity.OrderStatus;
import model.entity.OrderType;

public class OrderDAOImpl implements OrderDAO {
	private static OrderDAO instance;

	private OrderDAOImpl() {
	}

	public static synchronized OrderDAO getInstance() {
		if (instance == null) {
			instance = new OrderDAOImpl();
		}
		return instance;
	}

	@Override
	public List<OrderType> getAllOrderTypes() {
		List<OrderType> orderTypes = new ArrayList<OrderType>();
		try (Connection con = PooledConnections.getInstance().getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(Constants.GET_ALL_ORDER_TYPES)) {
			while (rs.next()) {
				OrderType orderType = new OrderType();
				int k = 1;
				orderType.setId(rs.getInt(k++));
				orderType.setType(rs.getString(k++));
				orderTypes.add(orderType);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderTypes;
	}

	@Override
	public List<OrderStatus> getAllOrderStatuses() {
		List<OrderStatus> orderStatuses = new ArrayList<OrderStatus>();
		try (Connection con = PooledConnections.getInstance().getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(Constants.GET_ALL_ORDER_STATUSES)) {
			while (rs.next()) {
				OrderStatus orderStatus = new OrderStatus();
				int k = 1;
				orderStatus.setId(rs.getInt(k++));
				orderStatus.setStatus(rs.getString(k++));
				orderStatuses.add(orderStatus);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orderStatuses;
	}

	@Override
	public void addOrder(Long userId, Long bookId, int orderTypeId) {
		Connection con = null;
		try {
			con = PooledConnections.getInstance().getConnection();
			con.setAutoCommit(false);
			decrimentBookAvailable(con, bookId);
			incertBookOrder(con, userId, bookId, orderTypeId);
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con);
		}

	}

	private void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	private void incertBookOrder(Connection con, Long userId, Long bookId, int orderTypeId) throws SQLException {
		try (PreparedStatement ps = con.prepareStatement(Constants.ADD_BOOK_ORDER);) {
			int k = 1;
			ps.setLong(k++, userId);
			ps.setLong(k++, bookId);
			ps.setInt(k++, orderTypeId);
			ps.executeUpdate();
		}

	}

	private void decrimentBookAvailable(Connection con, Long bookId) throws SQLException {
		try (PreparedStatement ps = con.prepareStatement(Constants.DECRIMENT_BOOK_AVAILABLE);) {
			ps.setLong(1, bookId);
			ps.executeUpdate();
		}

	}

	@Override
	public List<BookOrder> getUserOrders(long userId) {
		List<BookOrder> bookOrders = new ArrayList<BookOrder>();
		try (Connection con = PooledConnections.getInstance().getConnection();
				PreparedStatement st = con.prepareStatement(Constants.GET_ALL_USER_ORDERS)) {
			st.setLong(1, userId);
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					BookOrder bookOrder = new BookOrder();
					int k = 1;
					bookOrder.setUserId(userId);
					bookOrder.setBookId(rs.getLong(k++));
					bookOrder.setOrderDate(rs.getTimestamp(k++).toLocalDateTime());
					bookOrder.setBookTitle(rs.getString(k++));
					bookOrder.setOrderType(rs.getString(k++));
					OrderStatus orderStatus = new OrderStatus();
					orderStatus.setId(rs.getInt(k++));
					orderStatus.setStatus(rs.getString(k++));
					bookOrder.setOrderStatus(orderStatus);
					bookOrder.setOpenDate(defineDate(rs.getTimestamp(k++)));
					bookOrder.setCloseDate(defineDate(rs.getTimestamp(k++)));
					bookOrder.setReturnDate(defineDate(rs.getTimestamp(k++)));
					bookOrder.calculatePenalty();
					bookOrders.add(bookOrder);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookOrders;
	}

	private LocalDate defineDate(Timestamp timestamp) {
		return (timestamp == null) ? null : timestamp.toLocalDateTime().toLocalDate();
	}

	@Override
	public List<BookOrder> getNewBookOrders() {
		List<BookOrder> bookOrders = new ArrayList<BookOrder>();
		try (Connection con = PooledConnections.getInstance().getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(Constants.GET_NEW_BOOK_ORDERS)) {
			while (rs.next()) {
				BookOrder bookOrder = new BookOrder();
				int k = 1;
				bookOrder.setUserId(rs.getLong(k++));
				bookOrder.setBookId(rs.getLong(k++));
				bookOrder.setOrderDate(rs.getTimestamp(k++).toLocalDateTime());
				bookOrder.setBookTitle(rs.getString(k++));
				bookOrder.setOrderType(rs.getString(k++));
				OrderStatus orderStatus = new OrderStatus();
				orderStatus.setId(rs.getInt(k++));
				orderStatus.setStatus(rs.getString(k++));
				bookOrder.setOrderStatus(orderStatus);
				bookOrder.setOpenDate(defineDate(rs.getTimestamp(k++)));
				bookOrder.setCloseDate(defineDate(rs.getTimestamp(k++)));
				bookOrder.setReturnDate(defineDate(rs.getTimestamp(k++)));
				bookOrders.add(bookOrder);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookOrders;
	}

	@Override
	public BookOrder getBookOrder(Long userId, Long bookId) {
		BookOrder bookOrder = null;
		try (Connection con = PooledConnections.getInstance().getConnection();
				PreparedStatement st = con.prepareStatement(Constants.GET_BOOK_ORDER)) {
			st.setLong(1, userId);
			st.setLong(2, bookId);
			try (ResultSet rs = st.executeQuery()) {
				while (rs.next()) {
					bookOrder = new BookOrder();
					int k = 1;
					bookOrder.setBookId(bookId);
					bookOrder.setUserId(userId);
					bookOrder.setOrderDate(rs.getTimestamp(k++).toLocalDateTime());
					bookOrder.setBookTitle(rs.getString(k++));
					bookOrder.setOrderType(rs.getString(k++));
					bookOrder.setOpenDate(defineDate(rs.getTimestamp(k++)));
					bookOrder.setCloseDate(defineDate(rs.getTimestamp(k++)));
					OrderStatus orderStatus = new OrderStatus();
					orderStatus.setId(rs.getInt(k++));
					orderStatus.setStatus(rs.getString(k++));
					bookOrder.setOrderStatus(orderStatus);
					bookOrder.calculatePenalty();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookOrder;
	}

	@Override
	public void updateBookOrder(BookOrder bookOrder) {
		Connection con = null;
		try {
			con = PooledConnections.getInstance().getConnection();
			con.setAutoCommit(false);
			updateBookOrder(bookOrder, con);
			if (bookOrder.getOrderStatus().getId() == 4 && bookOrder.getPenalty() > 0) {
				bookOrder.calculatePenalty();
				updateUserPenalty(bookOrder, con);
			}
			con.commit();
		} catch (SQLException e) {
			rollback(con);
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con);
		}

	}

	private void updateUserPenalty(BookOrder bookOrder, Connection con) throws SQLException {
		try (PreparedStatement ps = con.prepareStatement(Constants.UPDATE_USER_PENALTY)) {
			int k = 1;
			ps.setDouble(k++, bookOrder.getPenalty());
			ps.setLong(k++, bookOrder.getUserId());
			ps.executeUpdate();
		}
		
	}

	private void updateBookOrder(BookOrder bookOrder, Connection con) throws SQLException {
		String query = buildQuery(bookOrder);
		try (PreparedStatement st = con.prepareStatement(query)) {
			st.executeUpdate();
		}
	}

	private String buildQuery(BookOrder bookOrder) {
		StringBuilder result = new StringBuilder();
		result.append("UPDATE book_order ").
				append("SET order_status_id = ").
				append(bookOrder.getOrderStatus().getId());
		if (bookOrder.getOrderStatus().getId() == 3) {
			result.append(", open_date = \"").
				append(LocalDate.now().toString()).
				append("\", close_date = \"").
				append(bookOrder.getCloseDate().toString()).
				append("\"");
		}
		if (bookOrder.getOrderStatus().getId() == 4) {
			result.append(", return_date = \"").
					append(LocalDate.now().toString()).
					append("\"");
		}
		result.append(" WHERE user_Id = ").
				append(bookOrder.getUserId()).
				append(" AND book_Id = ").
				append(bookOrder.getBookId());
		return result.toString();
	}

	@Override
	public List<BookOrder> getUserOpenBookOrders(Long userId) {
		List<BookOrder> bookOrders = new ArrayList<BookOrder>();
		try (Connection con = PooledConnections.getInstance().getConnection();
				PreparedStatement ps = con.prepareStatement(Constants.GET_USER_OPEN_BOOK_ORDER)) {
			ps.setLong(1, userId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					BookOrder bookOrder = new BookOrder();
					int k = 1;
					bookOrder.setUserId(userId);
					bookOrder.setBookId(rs.getLong(k++));
					bookOrder.setOrderDate(rs.getTimestamp(k++).toLocalDateTime());
					bookOrder.setBookTitle(rs.getString(k++));
					bookOrder.setOrderType(rs.getString(k++));
					bookOrder.setOpenDate(defineDate(rs.getTimestamp(k++)));
					bookOrder.setCloseDate(defineDate(rs.getTimestamp(k++)));
					OrderStatus orderStatus = new OrderStatus();
					orderStatus.setId(rs.getInt(k++));
					orderStatus.setStatus(rs.getString(k++));
					bookOrder.setOrderStatus(orderStatus);
					bookOrder.calculatePenalty();
					bookOrders.add(bookOrder);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookOrders;
	}

}
