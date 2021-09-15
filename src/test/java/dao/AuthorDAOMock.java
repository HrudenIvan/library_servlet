package dao;

import java.util.ArrayList;
import java.util.List;

import Exception.DBException;
import model.DAO.AuthorDAO;
import model.entity.Author;

public class AuthorDAOMock implements AuthorDAO {
	private List<Author> list;

	public AuthorDAOMock() {
		list = new ArrayList<Author>();
		Author author = new Author();
		author.setId(1);
		author.setFirstName("1");
		author.setLastName("1");
		list.add(author);
		author = new Author();
		author.setId(2);
		author.setFirstName("2");
		author.setLastName("2");
		list.add(author);
	}

	@Override
	public Author getAuthor(long id) throws DBException {
		return list.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
	}

	@Override
	public List<Author> getAllAuthors() throws DBException {
		return list;
	}

	@Override
	public void addAuthor(Author author) throws DBException {
		list.add(author);
	}

	@Override
	public void updateAuthor(Author author) throws DBException {
		Author authorStored = list.stream().filter(e -> e.getId() == author.getId()).findFirst().orElse(null);
		if (authorStored != null) {
			authorStored.setFirstName(author.getFirstName());
			authorStored.setLastName(author.getLastName());
		}
	}
}