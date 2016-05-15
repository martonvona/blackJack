package dao;

public class UserHandler {

	static User user;

	public static User getUser() {
		return user;
	}

	public static void setUser(User user) {
		UserHandler.user = user;
	}

}
