package got;

public class GoTAlive {
	private int ID;
	private String name;
	private String lastName;
	private String role;

	public GoTAlive(int iD, String name, String lastName, String role) {
		super();
		ID = iD;
		this.name = name;
		this.lastName = lastName;
		this.role = role;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
