package GoTDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import GoTDTO.Users;
import got.ConnManager;
import got.UsersInterface;

public class UsersDao implements UsersInterface {
	Connection connection = ConnManager.getInstance().getConnection();

	@Override
	public ArrayList<Users> userList() throws SQLException {
		ArrayList<Users> users = new ArrayList<>();
		String query = "select * from got.users;";
		ResultSet rs = null;
		try (Statement statement = connection.createStatement();) {
			rs = statement.executeQuery(query);

			while (rs.next()) {
				users.add(new Users(rs.getInt("ID"), rs.getString("username"), rs.getString("pass"),
						rs.getString("validate")));
				System.out.println(rs.getInt("ID") + " " + rs.getString("username") + " " + rs.getString("pass") + " "
						+ rs.getString("validate"));

			}
			return users;
		}

	}

	@Override
	public Users getUser(String username, String pass) throws SQLException {

		Users user = null;
		// create an SELECT SQL query
		String query = "SELECT * FROM users WHERE username = ? and pass = ?";

		// create a new ResultSet
		ResultSet rs = null;

		try (
				// java.sql.Statement
				PreparedStatement statement = connection.prepareStatement(query);) {

			statement.setString(1, username);
			statement.setString(2, pass);

			// execute the query
			rs = statement.executeQuery();

			// set the cursor
			if (rs.next()) {

				user = new Users(rs.getInt("ID"), rs.getString("username"), rs.getString("pass"),
						rs.getString("validate"));

				// close the ResultSet
				rs.close();
			}
		}

		return user;
	}

	@Override
	public void printUser(Users user) {
		if (user != null) {
			System.out.println("ID: " + user.getId() + ", username: " + user.getUsername() + ", pass: " + user.getPass()
					+ ", validation: " + user.getValidation());
		} else {
			System.out.println("No user to print.");
		}
	}

	public boolean isAdmin(String InputUserName, String InputPass) throws SQLException {
		Connection conn = ConnManager.getInstance().getConnection();
		String query = "select * from got.users;";
		ResultSet rs = null;

		try (
				// java.sql.Statement
				Statement statement = conn.createStatement();) {

			// execute the query
			rs = statement.executeQuery(query);

			// check for users

			while (rs.next()) {
				// int id = rs.getInt("id");
				String username = rs.getString("username");
				String pass = rs.getString("pass");
				String validation = rs.getString("validate");
				if (InputUserName.equals(username) && InputPass.equals(pass) && validation.equals("admin")) {
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	public boolean isUser(String InputUserName, String InputPass) throws SQLException {
		Connection conn = ConnManager.getInstance().getConnection();
		String query = "select * from got.users;";
		ResultSet rs = null;
		String val = "";
		try (
				// java.sql.Statement
				Statement statement = conn.createStatement();) {

			// execute the query
			rs = statement.executeQuery(query);

			// check for users
			while (rs.next()) {
				rs.getString("username");
				rs.getString("pass");
				rs.getString("validate");
				if (InputUserName.equals(rs.getString("username")) && InputPass.equals(rs.getString("pass"))) {
					val = rs.getString("validate");
				}
			}

			if (val.equals("user")) {
				return true;
			} else {
				return false;
			}
		}
	}
}
