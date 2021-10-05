package model.entity;

/**
 * Order status entity. Can be in one of 4 states:
 * <p><ul><li>id=1 status=new
 * <li>id=2 status=ready
 * <li>id=3 status=open
 * <li>id=4 status=closed
 */
public class OrderStatus {
	private int id;
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
