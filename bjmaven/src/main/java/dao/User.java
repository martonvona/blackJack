package dao;

public class User {

	private String username;
	private String password;
	private double money;

	public User(String username, String password, Double money) {
		this.username = username;
		this.password = password;
		this.money = money;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public double getMoney() {
		return money;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setMoney(double money) {
		this.money = money;
	}

}
