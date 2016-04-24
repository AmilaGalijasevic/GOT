package got;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GoTDeadInterface {
	// return all dead characters
	public ArrayList<GoTDead> deadList() throws SQLException;

	// return a specific character by name & last name
	public GoTDead getDeadCharacter() throws SQLException;

	// adds new person to the list
	public void addToDeadList() throws SQLException;

	// deletes the resurrected character
	public void deleteDeadCharacter(GoTDead dead) throws SQLException;

	// updates the dead character
	public void updateDeadCharacter(GoTDead update) throws SQLException;
	
	public void printDead(GoTDead dead);
}
