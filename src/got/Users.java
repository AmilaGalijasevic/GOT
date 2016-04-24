package got;

import java.sql.Connection;

public class Users {
	int id;
	String username;
	String pass;
	String validation;
	Connection connection = ConnManager.getInstance().getConnection();

	public Users() {

	}

	public Users(String userName, String pass) {

	}
	public Users(String userName, String pass, String val) {

	}

	public Users(int id, String username, String pass, String validation) {
		super();
		this.id = id;
		this.username = username;
		this.pass = pass;
		this.validation = validation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getValidation() {
		return validation;
	}

	public void setValidation(String validation) {
		this.validation = validation;
	}


}
