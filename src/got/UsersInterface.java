package got;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UsersInterface {

	public ArrayList<Users> userList() throws SQLException;

	public Users getUser(String username, String pass) throws SQLException;
	
	public void printUser(Users user);
	
}
