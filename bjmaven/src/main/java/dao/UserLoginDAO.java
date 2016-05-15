package dao;

import java.sql.Connection;

public interface UserLoginDAO {

	public Connection connectToDatabase();
	public User selectUser(Connection connection, String username, String password);
	public boolean updateUser(Connection connection, String username, double money);
	public User insertUser(Connection connection, String username, String password);

}
