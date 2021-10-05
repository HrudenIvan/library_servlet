package model.DAO;

import java.util.List;

import Exception.DBException;
import model.entity.Book;

/**
 * Interface for {@link Book} DAO
 */
public interface BookDAO {
	List<Book> getAllBooks(int page,int BOOKS_PER_PAGE, String sort, String order, String title, String aLastname, String aFirstname) throws DBException;

	Book getBook(long id) throws DBException;

	void updateBook(Book book) throws DBException;

	void addBook(Book book) throws DBException;
	
	int booksCount(String sort, String order, String title, String aLastname, String aFirstname) throws DBException;

}
