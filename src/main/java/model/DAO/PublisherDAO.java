package model.DAO;

import java.util.List;

import model.entity.Publisher;

public interface PublisherDAO {
	
	Publisher getPublisher(long id);
	
	List<Publisher> getAllPublishers();
	
	void addPublisher(Publisher publisher);
	
	void updatePublisher(Publisher publisher);

}
