package GoTDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import GoTDTO.GoTDead;
import got.ConnManager;
import got.GoTDeadInterface;

public class GoTDeadDAO implements GoTDeadInterface {
	// connect to the database
	Connection connection = ConnManager.getInstance().getConnection();

	@Override
	// deceased
	public ArrayList<GoTDead> deadList() throws SQLException {
		ArrayList<GoTDead> deceased = new ArrayList<>();
		String query = "select * from got.dead;";
		// create a new ResultSet
		ResultSet rs = null;
		try (Statement statement = connection.createStatement();) {
			rs = statement.executeQuery(query);
			System.out.println("List of dead charachters: ");
			while (rs.next()) {
				deceased.add(new GoTDead(rs.getInt("ID"), rs.getString("name"), rs.getString("lastname"),
						rs.getString("role"), rs.getInt("tod"), rs.getString("execution")));

				System.out.println("ID: " + rs.getInt("ID") + "; Name: " + rs.getString("name") + "; Lastname: "
						+ rs.getString("lastname") + "; Role: " + rs.getString("role") + "; Died in season: "
						+ rs.getInt("tod") + "; Execution style: " + rs.getString("execution"));
			}
		}
		return deceased;
	}

	@Override
	public GoTDead getDeadCharacter() throws SQLException {
		GoTDead dead = null;
		String query = "select * from got.dead where name=? and lastname= ?;";
		// create a new ResultSet
		java.util.Scanner input = new java.util.Scanner(System.in);
		try (
				// java.sql.Statement
				PreparedStatement statement = connection.prepareStatement(query);) {
			ResultSet rs = null;
			System.out.println("Enter name of the character: ");

			String name = input.nextLine();
			System.out.println("Enter lastname of the character: ");

			String lastname = input.nextLine();

			statement.setString(1, name);
			statement.setString(2, lastname);
			// execute the query
			rs = statement.executeQuery();
			if (rs.next()) {
				// populate student
				dead = new GoTDead(rs.getInt("ID"), rs.getString("name"), rs.getString("lastname"),
						rs.getString("role"), rs.getInt("tod"), rs.getString("execution"));

				System.out.println("ID: " + rs.getInt("ID") + ";  Name: " + rs.getString("name") + ";  Lastname: "
						+ rs.getString("lastname") + ";  Role: " + rs.getString("role") + ";  Dead in season: "
						+ rs.getInt("tod") + ";  Dead by: " + rs.getString("execution"));
				// close the ResultSet
				rs.close();
			} else {
				System.out.println("No matching data in the database");
			}
		}
		return dead;
	}

	@Override
	public void addToDeadList() throws SQLException {
		// create an SELECT SQL query
		String query = "INSERT INTO got.dead(name, lastname, role, tod, execution) VALUES (?, ?, ?, ?, ?)";
		// new Scanner
		java.util.Scanner input = new java.util.Scanner(System.in);

		System.out.print("Enter characters first name: ");
		String name = input.next();
		System.out.print("Enter last name: ");
		String lastname = input.next();
		System.out.print("Enter role: ");
		String role = input.next();
		System.out.print("Enter season in which the character died: ");
		int tod = input.nextInt();
		System.out.print("Enter how the character died: ");
		String execution = input.next();
		// close the scanner
		input.close();

		try (
				// java.sql.Statement
				PreparedStatement statement = connection.prepareStatement(query);) {
			statement.setString(1, name);
			statement.setString(2, lastname);
			statement.setString(3, role);
			statement.setInt(4, tod);
			statement.setString(5, execution);
			// execute the query
			statement.executeUpdate();

			System.out.println("Dead character added to the database.");
		}

	}

	@Override
	public void deleteDeadCharacter(GoTDead resurrected) throws SQLException {
		if (resurrected != null) {
			String query = "DELETE FROM got.dead WHERE name = ? and lastname = ?";
			try (
					// java.sql.Statement
					PreparedStatement statement = connection.prepareStatement(query);) {
				statement.setString(1, resurrected.getName());
				statement.setString(2, resurrected.getLastName());

				// execute the query
				statement.executeUpdate();
				// povezati kasnije da doda u alive tabelu tog korisnika...
				System.out.println("Character deleted from the database.");
			}
		}

	}

	@Override
	public void updateDeadCharacter(GoTDead update) throws SQLException {
		if (update != null) {
			// create an SELECT SQL query
			String query = "update got.dead set name = ?, lastname = ?, role = ?, tod = ?, execution= ?  WHERE name = ?;";
			java.util.Scanner input = new java.util.Scanner(System.in);

			System.out.print("Set a new name for dead character (current: " + update.getName() + " ): ");
			String name = input.next();

			System.out.print("Set a new lastname (current: " + update.getLastName() + " ): ");
			String lastname = input.next();

			System.out.print("Set a new role  (current: " + update.getRole() + " ): ");
			String role = input.next();

			System.out.print("Set a new time of death (current: season " + update.getTod() + " ): ");
			int tod = input.nextInt();

			System.out.print("Enter a new style of execution  (current: " + update.getExecution() + " ): ");
			String execution = input.next();
			// close the scanner
			input.close();

			try (
					// java.sql.Statement
					PreparedStatement statement = connection.prepareStatement(query);) {

				statement.setString(1, name);
				statement.setString(2, lastname);
				statement.setString(3, role);
				statement.setInt(4, tod);
				statement.setString(5, execution);
				statement.setString(6, update.getName());

				// execute the query
				statement.executeUpdate();

				System.out.println("Character updated in the database.");
			}
		} else {
			System.out.println("No such character in the database");
		}
	}

	@Override
	public void printDead(GoTDead dead) {
		if (dead != null) {
			System.out.println("ID: " + dead.getID() + ", name: " + dead.getName() + ", lastname: " + dead.getLastName()
					+ ", role: " + dead.getRole() + ", time of death: season " + dead.getTod() + ", execution: "
					+ dead.getExecution());
		} else {
			System.out.println("Character is not in the datadase ");
		}
	}

	public ArrayList<GoTDead> getDeadCharacterBySeason() throws SQLException {
		String query = "select name, lastname, execution from got.dead where tod = ?;";
		ArrayList<GoTDead> deadBySeason = new ArrayList<>();
		java.util.Scanner input = new java.util.Scanner(System.in);

		try (PreparedStatement statement = connection.prepareStatement(query);) {

			System.out.println("Enter a number of the season: ");
			int number = input.nextInt();
			// create a new ResultSet
			ResultSet rs = null;
			statement.setInt(1, number);
			rs = statement.executeQuery();
			System.out.println("List of dead charachters in season " + number + ": ");

			while (rs.next()) {
				deadBySeason
						.add(new GoTDead(rs.getString("name"), rs.getString("lastname"), rs.getString("execution")));

				System.out.println("Name: " + rs.getString("name") + ";  Lastname: " + rs.getString("lastname")
						+ ";  Execution: " + rs.getString("execution"));
			}
		}
		return deadBySeason;

	}

	public ArrayList<GoTDead> deadFamilies() throws SQLException {
		ArrayList<GoTDead> deadFamilies = new ArrayList<>();
		java.util.Scanner input = new java.util.Scanner(System.in);
		String query = "select name, lastname, role, tod, execution from got.dead where lastname = ?;";

		try (PreparedStatement statement = connection.prepareStatement(query);) {
			System.out.println("Choose a family name: ");
			System.out.println(
					"1 - Stark \n2 - Lannister \n3 - Targaryen \n4 - Martell \n5 - Tyrell \n6 - Baratheon \n7 - Tully \n8 - Frey");
			int number = input.nextInt();
			ResultSet rs = null;
			switch (number) {
			case 1: {
				statement.setString(1, "Stark");
				break;
			}
			case 2: {
				statement.setString(1, "Lannister");
				break;
			}
			case 3: {
				statement.setString(1, "Targaryen");
				break;
			}
			case 4: {
				statement.setString(1, "Martell");
				break;
			}
			case 5: {
				statement.setString(1, "Tyrell");
				break;
			}
			case 6: {
				statement.setString(1, "Baratheon");
				break;
			}
			case 7: {
				statement.setString(1, "Tully");
				break;
			}
			case 8: {
				statement.setString(1, "Frey");
				break;
			}
			default:
				break;
			}

			rs = statement.executeQuery();

			System.out.println("List of dead charachters in that family: ");
			while (rs.next()) {
				deadFamilies.add(new GoTDead(rs.getString("name"), rs.getString("lastname")));
				System.out.println("Name: " + rs.getString("name") + ";  Lastname: " + rs.getString("lastname"));
			}
		}
		return deadFamilies;

	}

	public ArrayList<GoTDead> execusionStyleList() throws SQLException {

		ArrayList<GoTDead> executionStyles = new ArrayList<>();
		java.util.Scanner input = new java.util.Scanner(System.in);
		String query = "select name, lastname, execution from got.dead where execution = ?;";
		try (PreparedStatement statement = connection.prepareStatement(query);) {
			System.out.println("Enter execution style you want to check out (exemple: decapitation): ");
			String style = input.next();
			ResultSet rs = null;
			statement.setString(1, style);
			rs = statement.executeQuery();

			System.out.println("List of dead charachters by " + style + ": ");
			while (rs.next()) {
				executionStyles
						.add(new GoTDead(rs.getString("name"), rs.getString("lastname"), rs.getString("execution")));
				System.out.println("Name: " + rs.getString("name") + ";  Lastname: " + rs.getString("lastname")
						+ ";  Execution: " + rs.getString("execution"));
			}
		}
		return executionStyles;
	}

}
