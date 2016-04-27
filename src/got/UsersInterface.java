package got;

import java.sql.SQLException;
import java.util.ArrayList;

import GoTDTO.Users;

public interface UsersInterface {

	public ArrayList<Users> userList() throws SQLException;

	public Users getUser(String username, String pass) throws SQLException;
	
	public void printUser(Users user);
	
}
