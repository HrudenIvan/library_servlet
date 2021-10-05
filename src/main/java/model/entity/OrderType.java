package model.entity;

/**
 * Order type entity. Can be in one of 2 state
 *<p><ul><li>id=1 type=reading room
 * <li>id=2 type=subscription
 */
public class OrderType {
	private int id;
	private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
