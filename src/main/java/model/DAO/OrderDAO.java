package model.DAO;

import java.util.List;
import model.entity.BookOrder;
import model.entity.OrderStatus;
import model.entity.OrderType;

public interface OrderDAO {

	List<OrderType> getAllOrderTypes();

	List<OrderStatus> getAllOrderStatuses();

	void addOrder(Long userId, Long bookId, int orderTypeId);

	List<BookOrder> getUserOrders(long id);

	List<BookOrder> getNewBookOrders();

	BookOrder getBookOrder(Long userId, Long bookId);

	void updateBookOrder(BookOrder bookOrder);

	List<BookOrder> getUserOpenBookOrders(Long userId);

}
