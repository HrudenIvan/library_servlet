package dao;

import java.util.ArrayList;
import java.util.List;

import Exception.DBException;
import model.DAO.PublisherDAO;
import model.entity.Publisher;

public class PublisherDAOMock implements PublisherDAO{
	private List<Publisher> list;
	
	public PublisherDAOMock() {
		list = new ArrayList<Publisher>();
		Publisher publisher = new Publisher();
		publisher.setId(1);
		publisher.setName("1");
		list.add(publisher);
		publisher = new Publisher();
		publisher.setId(2);
		publisher.setName("2");
		list.add(publisher);
	}

	@Override
	public Publisher getPublisher(long id) throws DBException {
		return list.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
	}

	@Override
	public List<Publisher> getAllPublishers() throws DBException {
		return list;
	}

	@Override
	public void addPublisher(Publisher publisher) throws DBException {
		list.add(publisher);
	}

	@Override
	public void updatePublisher(Publisher publisher) throws DBException {
		Publisher publisherStored = list.stream().filter(e -> e.getId() == publisher.getId()).findFirst().orElse(null);
		if (publisherStored != null) {
			publisherStored.setId(publisher.getId());
			publisherStored.setName(publisher.getName());
		}
	}

}
