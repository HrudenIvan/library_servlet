package model.DAO;

import java.util.List;

import model.entity.Author;

public interface AuthorDAO {
	Author getAuthor(long id);

	List<Author> getAllAuthors();

	void addAuthor(Author author);

	void updateAuthor(Author author);

}
