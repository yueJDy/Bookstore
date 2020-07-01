package nhanam.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "NHAXB")
public class NhaXB {
	@Id
	@Column(name = "MANXB")
	private String maNXB;
	
	@Column(name = "TENNXB")
	private String tenNXB;
	
	@Column(name = "TRANGTHAI")
	private Boolean trangthai;
	
	@OneToMany(mappedBy = "nhaXB", fetch = FetchType.EAGER)
	private Collection<Book> booksNXB;
	
	public String getMaNXB() {
		return maNXB;
	}

	public void setMaNXB(String maNXB) {
		this.maNXB = maNXB;
	}

	public String getTenNXB() {
		return tenNXB;
	}

	public void setTenNXB(String tenNXB) {
		this.tenNXB = tenNXB;
	}

	public Collection<Book> getBooksNXB() {
		return booksNXB;
	}

	public void setBooksNXB(Collection<Book> booksNXB) {
		this.booksNXB = booksNXB;
	}

	public Boolean getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(Boolean trangthai) {
		this.trangthai = trangthai;
	}
	
	
}
