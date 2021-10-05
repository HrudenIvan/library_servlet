package model.DAO;

import java.util.List;

import Exception.DBException;
import model.entity.BookOrder;
import model.entity.OrderStatus;
import model.entity.OrderType;

/**
 * Interface for {@link BookOrder} DAO
 */
public interface OrderDAO {

	List<OrderType> getAllOrderTypes() throws DBException;

	List<OrderStatus> getAllOrderStatuses() throws DBException;

	void addOrder(Long userId, Long bookId, int orderTypeId) throws DBException;

	List<BookOrder> getUserOrders(long id) throws DBException;

	List<BookOrder> getNewBookOrders() throws DBException;

	BookOrder getBookOrder(Long userId, Long bookId) throws DBException;

	void updateBookOrder(BookOrder bookOrder) throws DBException;

	List<BookOrder> getUserOpenBookOrders(Long userId) throws DBException;

}
