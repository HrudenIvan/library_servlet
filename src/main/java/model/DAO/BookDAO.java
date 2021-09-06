package model.DAO;

import java.util.List;

import model.entity.Book;

public interface BookDAO {
	List<Book> getAllBooks();

	Book getBook(long id);

	void updateBook(Book book);
	
	void addBook(Book book);

	List<Book> findBooksByTitle(String title);
}
