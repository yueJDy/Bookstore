package nhanam.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {
	@Id
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "ADMIN")
	private Boolean isAdmin;
	
	@Column(name = "VERIFICATION")
	private Boolean verify;
	
	@Column(name = "STATUS")
	private Boolean trangthai;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private Collection<DonHang> donhang;
	
	public User(String email, String password, String name, Boolean isAdmin) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.isAdmin = isAdmin;
	}

	public User(String email, String password, String name, Boolean isAdmin, Collection<DonHang> donhang) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.isAdmin = isAdmin;
		this.donhang = donhang;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
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

	public Collection<DonHang> getDonhang() {
		return donhang;
	}

	public void setDonhang(Collection<DonHang> donhang) {
		this.donhang = donhang;
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

	public Boolean getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(Boolean trangthai) {
		this.trangthai = trangthai;
	}
	
	
}
