package model.DAO;

import java.util.List;

import Exception.DBException;
import model.entity.Book;

public interface BookDAO {
	List<Book> getAllBooks() throws DBException;

	Book getBook(long id) throws DBException;

	void updateBook(Book book) throws DBException;

	void addBook(Book book) throws DBException;

	List<Book> findBooksByTitle(String title) throws DBException;
}
