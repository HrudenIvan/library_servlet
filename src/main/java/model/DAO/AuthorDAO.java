package model.DAO;

import java.util.List;

import Exception.DBException;
import model.entity.Author;

/**
 * Interface for {@link Author} DAO
 */
public interface AuthorDAO {
	Author getAuthor(long id) throws DBException;

	List<Author> getAllAuthors() throws DBException;

	void addAuthor(Author author) throws DBException;

	void updateAuthor(Author author) throws DBException;

}
