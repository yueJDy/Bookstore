package nhanam.bean;

public class User_Res {
	private String email;
	private String password;
	private String name;
	
	public User_Res() {
		// TODO Auto-generated constructor stub
	}

	public User_Res(String email, String password, String name) {
		this.email = email;
		this.password = password;
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
