package got;

import java.sql.SQLException;
import java.util.InputMismatchException;

import GoTDAO.GoTDeadDAO;
import GoTDAO.UsersDao;

public class GoTDeadImplementation {

	static java.util.Scanner input = new java.util.Scanner(System.in);

	public static boolean options() throws SQLException {
		UsersDao uDAO = new UsersDao();

		System.out.println("Enter user name: ");
		String username = input.next();
		System.out.println("Enter password: ");
		String pass = input.next();
		System.out.println();
		if (uDAO.isAdmin(username, pass)) {
			AdminOptions();
			return true;
		}
		if (uDAO.isUser(username, pass)) {
			userOptions();
			return true;
		} else {
			System.out.println("Wrong username and/or password");
			return false;
		}
	}

	public static void AdminOptions() throws SQLException {
		java.util.Scanner input = new java.util.Scanner(System.in);
		GoTDeadDAO dead = new GoTDeadDAO();
		try {

			System.out.println();
			System.out.println(
					"Choose an option: \n1 - List all dead characters \n2 - Search by specific data \n3 - add a character \n4 - update a character \n5 -  delete a character \n6 - Logg out");
			int choose = input.nextInt();

			switch (choose) {
			case 1: {
				dead.deadList();
				AdminOptions();
				break;
			}
			case 2: {
				System.out.println(
						"Search by: \n1 dead families \n2 execution style \n3 by season \n4 by specific character ");
				int search = input.nextInt();
				switch (search) {
				case 1: {
					dead.deadFamilies();
					AdminOptions();
					break;
				}
				case 2: {
					dead.execusionStyleList();
					AdminOptions();
					break;
				}
				case 3: {
					dead.getDeadCharacterBySeason();
					AdminOptions();
					break;
				}
				case 4: {
					dead.getDeadCharacter();
					AdminOptions();
					break;
				}

				default:
					break;
				}

			}
				break;
			case 3: {
				dead.addToDeadList();
				AdminOptions();
				break;

			}
			case 4: {
				dead.updateDeadCharacter(dead.getDeadCharacter());
				AdminOptions();
				break;

			}
			case 5: {
				dead.deleteDeadCharacter(dead.getDeadCharacter());
				AdminOptions();
				break;

			}
			case 6: {
				System.out.println("GoodBye");
				ConnManager.getInstance().close();
				System.exit(1);

			}

			default:
				break;
			}

		} catch (InputMismatchException e) {
			System.out.println("Wrong input, start again...");
			AdminOptions();
			input.close();
		}
	}

	public static void userOptions() throws SQLException {
		java.util.Scanner input = new java.util.Scanner(System.in);
		GoTDeadDAO dead = new GoTDeadDAO();
		try {

			System.out.println();
			System.out.println(
					"Choose an option: \n1 - List all dead characters \n2 - Search by specific data \n3 - Logg out");
			int choose = input.nextInt();
			switch (choose) {
			case 1: {
				dead.deadList();
				userOptions();
				break;
			}
			case 2: {
				System.out.println(
						"Search by: \n1 dead families \n2 execution style \n3 by season \n4 by specific character ");
				int search = input.nextInt();
				switch (search) {
				case 1: {
					dead.deadFamilies();
					userOptions();
					break;
				}
				case 2: {
					dead.execusionStyleList();
					userOptions();
					break;
				}
				case 3: {
					dead.getDeadCharacterBySeason();
					userOptions();
					break;
				}
				case 4: {
					dead.getDeadCharacter();
					userOptions();
					break;
				}

				default:
					break;
				}
				break;
			}
			case 3: {
				System.out.println("GoodBye");
				ConnManager.getInstance().close();
				System.exit(1);

			}

			default:
				break;
			}
		} catch (InputMismatchException e) {
			System.out.println("Wrong input, start again...");
			userOptions();
			input.close();
		}
	}

	public static void main(String[] args) throws SQLException {

		while (!options()) {
			options();
		}

	}

}
