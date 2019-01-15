import java.util.Scanner;

public class RegisterLog {
	public static void main(String[] args) {
		UsersAgenda users = new UsersAgenda();
		Scanner sc = new Scanner(System.in);
		System.out.println("Please, select an option:");
		boolean finished = false;
		while (!finished) {
			System.out.print("1 Register.\n2 Log in.\n0 Exit.");
			// Check that the user enters a number
			if (sc.hasNextInt()) {
				int option = sc.nextInt();
				// Jump the token '/n'
				sc.nextLine();
				switch (option) {
				case 1: /* Register */
					System.out.println("Please, enter the username.");
					boolean usernameEntered = false;
					while (!usernameEntered) {
						String entered = sc.nextLine();
						if (users.isWord(entered)) {
							if (users.findUsername(entered)==-1) {
								usernameEntered = true;
								String username = entered;
								System.out.println(
										"Please, enter the password (It must be no less than 8 characters and must contain letters, simbols and numbers).");
								boolean passwordEntered = false;
								while (!passwordEntered) {
									entered = sc.nextLine();
									if (users.isValidPassword(entered)) {
										passwordEntered = true;
										String password = entered;
										users.addUser(new User(username, password));
										System.out.println("You were registered successfully.");
									} else {
										System.out.println("Please, enter a valid password.");
									}
								}
							}else {
								System.out.println("That username is already used, please enter another one.");
							}
						} else {
							System.out.println("Please, enter just a username.");
						}
					}
					break;
				case 2: /* Log in */
					usernameEntered = false;
					while (!usernameEntered) {
						System.out.println("Please, enter the username.");
						String entered = sc.nextLine();
						if (users.isWord(entered)) {
							String username = entered;
							System.out.println(
									"Please, enter the password.");
							boolean passwordEntered = false;
							while (!passwordEntered) {
								entered = sc.nextLine();
								if (users.isValidPassword(entered)) {
									String password = entered;
									User user = new User(username, password);
									if (users.logIn(user)!=-1) {
										usernameEntered = true;
										passwordEntered = true;
										System.out.println("You loggined in successfully, " + username + ".");
										boolean logOut=false;
										while (!logOut) {
											System.out.print("1 Change the password.\n2 Remove the user.\n0 Log out.");
											// Check that the user enters a number
											if (sc.hasNextInt()) {
												option = sc.nextInt();
												// Jump the token '/n'
												sc.nextLine();
												switch (option) {
												case 1: /* Change password */
													System.out.println("Please, enter the new password (It must be no less than 8 characters and must contain letters, simbols and numbers).");
													passwordEntered = false;
													while (!passwordEntered) {
														entered=sc.nextLine();
														if (users.isValidPassword(entered)) {
															passwordEntered=true;
															User modifiedUser = new User(username,entered);
															users.modifyUser(modifiedUser);
															System.out.println("You changed the password successfully");
														}else {
															System.out.println("Please, enter a valid password.");
														}
													}
													break;
												case 2: /* Remove user */
													users.removeUser(user);
													System.out.println("You removed the user successfully");
													logOut=true;
													break;
												case 0:
													logOut = true;
													break;
												}
											} else {
												System.out.println("You didn't select a possible option");
												// Jump the token '/n'
												sc.nextLine();
											}
										}
									}else {
										System.out.println("The username or the password was incorrect, try it again please.");
										passwordEntered=true;
									}
								} else {
									System.out.println("Please, enter a valid password.");
								}
							}
						} else {
							System.out.println("Please, enter just a username.");
						}
					}
					break;
				case 0:
					finished = true;
					break;
				}
			} else {
				System.out.println("You didn't select a possible option");
				// Jump the token '/n'
				sc.nextLine();
			}
		}
		sc.close();
	}
}