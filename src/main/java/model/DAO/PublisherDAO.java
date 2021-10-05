package model.DAO;

import java.util.List;

import Exception.DBException;
import model.entity.Publisher;

/**
 * Interface for {@link Publisher} DAO
 */
public interface PublisherDAO {
	
	Publisher getPublisher(long id) throws DBException;
	
	List<Publisher> getAllPublishers() throws DBException;
	
	void addPublisher(Publisher publisher) throws DBException;
	
	void updatePublisher(Publisher publisher) throws DBException;

}
