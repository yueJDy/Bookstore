package nhanam.bean;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {
	private String email;
	private String username;
	private String password;
	private Boolean isAdmin;
	private Boolean verify;
	private List<Product> cart;
	
	public UserInfo() {
		this.cart = new ArrayList<>();
	}

	public UserInfo(String email, String username, String password, Boolean isAdmin, Boolean verify,
			List<Product> cart) {
		this.email = email;
		this.username = username;
		this.password = password;
		this.isAdmin = isAdmin;
		this.verify = verify;
		this.cart = cart;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Product> getCart() {
		return cart;
	}

	public void setCart(List<Product> cart) {
		this.cart.addAll(cart);
	}

	public void insertCart(Product product) {
		this.cart.add(product);
	}
	
	public void removeIndex(int index) {
		this.cart.remove(index);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Boolean getVerify() {
		return verify;
	}

	public void setVerify(Boolean verify) {
		this.verify = verify;
	}
	
}
