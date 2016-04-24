package got;

public class GoTDead {

	private int ID;
	private String name;
	private String lastName;
	private String role;
	private int tod;
	private String execution;

	public GoTDead() {

	}

	public GoTDead(String name, String lastName) {
		this.name = name;
		this.lastName = lastName;
	}

	public GoTDead(int iD, String name, String lastName, String role, int tod, String execution) {
		super();
		ID = iD;
		this.name = name;
		this.lastName = lastName;
		this.role = role;
		this.tod = tod;
		this.execution = execution;
	}

	public GoTDead(String string, String string2, String string3) {

	}

	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getRole() {
		return role;
	}

	public int getTod() {
		return tod;
	}

	public String getExecution() {
		return execution;
	}

}
