package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Exception.DBException;
import model.DAO.BookDAO;
import model.entity.Author;
import model.entity.Book;
import model.entity.Publisher;

public class BookDAOMock implements BookDAO{
	private List<Book> list;
	
	public BookDAOMock() {
		list= new ArrayList<Book>();
		Book book = new Book();
		book.setId(1);
		book.setTitle("1");
		book.setAvailable(1);
		book.setQuantity(1);
		book.setReleaseDate(1);
		Author author = new Author();
		author.setId(1);
		author.setFirstName("1");
		author.setLastName("1");
		book.setAuthor(author);
		Publisher publisher = new Publisher();
		publisher.setId(1);
		publisher.setName("1");
		book.setPublisher(publisher);
		list.add(book);
		book = new Book();
		book.setId(2);
		book.setTitle("2");
		book.setAvailable(2);
		book.setQuantity(2);
		book.setReleaseDate(2);
		author = new Author();
		author.setId(2);
		author.setFirstName("2");
		author.setLastName("2");
		book.setAuthor(author);
		publisher = new Publisher();
		publisher.setId(2);
		publisher.setName("2");
		book.setPublisher(publisher);
		list.add(book);
	}

	@Override
	public List<Book> getAllBooks() throws DBException {
		return list;
	}

	@Override
	public Book getBook(long id) throws DBException {
		return list.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
	}

	@Override
	public void updateBook(Book book) throws DBException {
		Book bookStored = list.stream().filter(e -> e.getId() == book.getId()).findFirst().orElse(null);
		if (bookStored != null) {
			bookStored.setTitle(book.getTitle());
			bookStored.setAvailable(book.getAvailable());
			bookStored.setQuantity(book.getQuantity());
			bookStored.setReleaseDate(book.getReleaseDate());
			bookStored.setPublisher(book.getPublisher());
			bookStored.setAuthor(book.getAuthor());
		}
	}

	@Override
	public void addBook(Book book) throws DBException {
		list.add(book);
	}

	@Override
	public List<Book> findBooksByTitle(String title) throws DBException {
		// TODO Auto-generated method stub
		return list.stream().filter(e -> e.getTitle().equals(title)).collect(Collectors.toList());
	}
}
