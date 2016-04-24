package got;

import java.sql.SQLException;
import java.util.ArrayList;

public class Testing {

	public static void main(String[] args) throws SQLException {
		// GoTDead dead = new GoTDead();
		GoTDeadDAO dead = new GoTDeadDAO();
	//	 ArrayList<GoTDead> deceased = dead.deadList();
//		 
//		 for (GoTDead e : deceased) {
//		 dead.printDead(e);
//		 }
	//	dead.printDead(dead.getDeadCharacter());
		
		dead.deleteDeadCharacter(dead.getDeadCharacter());
// dead.updateDeadCharacter(dead.getDeadCharacter());
		//dead.getDeadCharacterBySeason();
		//dead.deadFamilies();
		//dead.execusionStyleList();
		//dead.addToDeadList();
		ConnManager.getInstance().close();
	}

}
