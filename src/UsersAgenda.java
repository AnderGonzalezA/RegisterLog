import java.util.ArrayList;

public class UsersAgenda {
	private ArrayList<User> users = new ArrayList<User>();

	public ArrayList<User> getUsers() {
		return this.users;
	}

	public void setUsers(ArrayList<User> newUsers) {
		this.users = newUsers;
	}
	public boolean isWord(String entered) {
		String[] enteredTokens = entered.split(" ");
		if (enteredTokens.length == 1) {
			return true;
		}
		return false;
	}
	public int findUsername(String newUsername) {
		for (int i=0;i<this.users.size();i++) {
			if (this.users.get(i).getUsername().equals(newUsername)) {
				return i;
			}
		}
		return -1;
	}
	public boolean isValidPassword(String entered) {
		if (isWord(entered)) {
			if (entered.length() >= 8) {
				boolean isLetter = false;
				boolean isSymbol = false;
				boolean isNumber = false;
				for (int i = 0; i < entered.length(); i++) {
					int intValue = (entered.charAt(i))-'0';
					if (Character.isLetter(entered.charAt(i))) {
						isLetter = true;
					} else if (intValue == 0 || intValue == 1 || intValue == 2 || intValue == 3 || intValue == 4
							|| intValue == 5 || intValue == 6 || intValue == 7 || intValue == 8 || intValue == 9) {
						isNumber = true;
					} else {
						isSymbol = true;
					}
				}
				if (isLetter && isNumber && isSymbol) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean addUser(User newUser) {
		if (findUsername(newUser.getUsername())==-1 && isValidPassword(newUser.getPassword())) {
			this.users.add(newUser);
			return true;
		}
		return false;
	}
	public int logIn(User user) {
		for (int i=0;i<this.users.size();i++) {
			if (this.users.get(i).getUsername().equals(user.getUsername()) && this.users.get(i).getPassword().equals(user.getPassword())) {
				return i;
			}
		}
		return -1;
	}
	public void modifyUser(User newUser) {
		int userIndex=findUsername(newUser.getUsername());
		users.set(userIndex,newUser);
	}
	public boolean removeUser(User remUser) {
		if (findUsername(remUser.getUsername())!=-1) {
			this.users.remove(findUsername(remUser.getUsername()));
			return true;
		}
		return false;
	}
}