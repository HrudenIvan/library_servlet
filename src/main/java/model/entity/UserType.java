package model.entity;

import java.io.Serializable;

/**
 * User type entity. Can be in one of 3 state
 *<p><ul><li>id=1 type=admin
 * <li>id=2 type=librarian
 * <li>id=3 type=user
 */
public class UserType  implements Serializable{
	private static final long serialVersionUID = 1L;
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
