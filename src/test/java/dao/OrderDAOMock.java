package dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Exception.DBException;
import model.DAO.OrderDAO;
import model.entity.BookOrder;
import model.entity.OrderStatus;
import model.entity.OrderType;

public class OrderDAOMock implements OrderDAO {
	private List<BookOrder> listBO;
	private List<OrderType> listOT;
	private List<OrderStatus> listOS;
	
	public OrderDAOMock() {
		listOT = new ArrayList<OrderType>();
		OrderType ot = new OrderType();
		ot.setId(1);
		ot.setType("reading room");
		listOT.add(ot);
		ot = new OrderType();
		ot.setId(2);
		ot.setType("subscription");
		listOT.add(ot);
		
		listOS = new ArrayList<OrderStatus>();
		OrderStatus os = new OrderStatus();
		os.setId(1);
		os.setStatus("new");
		listOS.add(os);
		os = new OrderStatus();
		os.setId(2);
		os.setStatus("ready");
		listOS.add(os);
		os = new OrderStatus();
		os.setId(3);
		os.setStatus("open");
		listOS.add(os);
		os.setId(4);
		os.setStatus("close");
		listOS.add(os);
		
		listBO = new ArrayList<BookOrder>();
		BookOrder bookOrder = new BookOrder();
		bookOrder.setUserId(1);
		bookOrder.setBookId(1);
		bookOrder.setBookTitle("1");
		bookOrder.setCloseDate(LocalDate.now());
		bookOrder.setOpenDate(LocalDate.now());
		bookOrder.setOrderDate(LocalDateTime.now());
		bookOrder.setOrderStatus(listOS.get(1));
		bookOrder.setOrderType(listOT.get(1).getType());
		bookOrder.setPenalty(1);
		bookOrder.setReturnDate(LocalDate.now());
		listBO.add(bookOrder);
		bookOrder = new BookOrder();
		bookOrder.setUserId(2);
		bookOrder.setBookId(2);
		bookOrder.setBookTitle("2");
		bookOrder.setCloseDate(LocalDate.now());
		bookOrder.setOpenDate(LocalDate.now());
		bookOrder.setOrderDate(LocalDateTime.now());
		bookOrder.setOrderStatus(listOS.get(2));
		bookOrder.setOrderType(listOT.get(1).getType());
		bookOrder.setPenalty(1);
		bookOrder.setReturnDate(LocalDate.now());
		listBO.add(bookOrder);
	}

	@Override
	public List<OrderType> getAllOrderTypes() throws DBException {
		return listOT;
	}

	@Override
	public List<OrderStatus> getAllOrderStatuses() throws DBException {
		return listOS;
	}

	@Override
	public void addOrder(Long userId, Long bookId, int orderTypeId) throws DBException {
		BookOrder bookOrder = new BookOrder();
		bookOrder.setBookId(bookId);
		bookOrder.setUserId(userId);
		bookOrder.setOrderType(listOT.get(orderTypeId).getType());
	}

	@Override
	public List<BookOrder> getUserOrders(long id) throws DBException {
		return listBO.stream().filter(e -> e.getUserId() == id).collect(Collectors.toList());
	}

	@Override
	public List<BookOrder> getNewBookOrders() throws DBException {
		return listBO.stream().filter(e -> e.getOrderStatus().getId() == 1).collect(Collectors.toList());
	}

	@Override
	public BookOrder getBookOrder(Long userId, Long bookId) throws DBException {
		return listBO.stream().filter(e -> e.getBookId() == bookId && e.getUserId() == userId).findFirst().orElse(null);
	}

	@Override
	public void updateBookOrder(BookOrder bookOrder) throws DBException {
		BookOrder bookOrderStored = listBO.stream().filter(e -> e.getBookId() == bookOrder.getBookId() && e.getUserId() == bookOrder.getUserId()).findFirst().orElse(null);
		if (bookOrderStored != null) {
			// copy values
		}
	}

	@Override
	public List<BookOrder> getUserOpenBookOrders(Long userId) throws DBException {
		return listBO.stream().filter(e -> e.getOrderStatus().getId() == 3 ).collect(Collectors.toList());
	}

}
