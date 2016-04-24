package got;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GoTDeadInterface {
	// return all dead characters
	public ArrayList<GoTDead> deadList() throws SQLException;

	// return a specific character by name & last name
	public GoTDead getDeadCharacter() throws SQLException;

//	// returns the season in which the specific character has died
//	public GoTDead getDeadCharacterBySeason(String inputs) throws SQLException;

//	// returns list of dead people in specific family
//	public ArrayList<GoTDead> deadFamilies(String family) throws SQLException;

//	// returns list of dead people by the style of execution
//	public ArrayList<GoTDead> execusionStyleList(String execution) throws SQLException;

//	// returns list of dead people by specific season
//	public ArrayList<GoTDead> timeOfDeathList(int season) throws SQLException;

	// adds new person to the list
	public void addToDeadList() throws SQLException;

	// deletes the resurrected character
	public void deleteDeadCharacter(GoTDead dead) throws SQLException;

	// updates the dead character
	public void updateDeadCharacter(GoTDead update) throws SQLException;
	
	public void printDead(GoTDead dead);
}
