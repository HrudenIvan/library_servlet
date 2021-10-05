package model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * BookOrder entity
 */
public class BookOrder {
	private static final double penaltyDaylyAmount=1.5;
	private long userId;
	private long bookId;
	private LocalDateTime orderDate;
	private String bookTitle;
	private String orderType;
	private OrderStatus orderStatus;
	private LocalDate openDate;
	private LocalDate closeDate;
	private LocalDate returnDate;
	private double penalty;

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public LocalDate getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public double getPenalty() {
		return penalty;
	}

	public void setPenalty(double penalty) {
		this.penalty = penalty;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public LocalDate getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(LocalDate closeDate) {
		this.closeDate = closeDate;
	}

	public void calculatePenalty() {
		if (openDate == null) {
			penalty = 0;
			return;
		}
		if (LocalDate.now().toEpochDay() - closeDate.toEpochDay() > 0) {
			penalty = (LocalDate.now().toEpochDay() - closeDate.toEpochDay()) * penaltyDaylyAmount;
		}
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

}
