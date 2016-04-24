package got;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnManager {

	private static ConnManager instance = null;
	// DB credentials
	private final String USERNAME = "root";
	private final String PASSWORD = "root";
	private final String CONN_STRING = "jdbc:mysql://localhost/got";

	// connection object
	private Connection connection = null;

	// constructor
	private ConnManager() {

	}
	public static ConnManager getInstance() {
		if (instance == null) {
			instance = new ConnManager();
		}
		return instance;
	}

	private boolean openConnection() {
		try {
			connection = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
			return true;
		} catch (SQLException e) {
			System.err.println(e);
			return false;
		}
	}

	public Connection getConnection() {
		if (connection == null) {
			if (openConnection()) {
				System.out.println("Connected");
				return connection;
			} else {
				return null;
			}
		}
		return connection;
	}

	void close() {
		System.out.println("Closing connection");
		try {
			connection.close();
			connection = null;
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
