package nhanam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CTDH")
public class CTDH {
	@Id @GeneratedValue
	@Column(name = "ID")
	private String id;
	
	@Column(name = "SOLUONG")
	private int soluong;
	
	@ManyToOne
	@JoinColumn(name = "MADH")
	private DonHang maDH;
	
	@ManyToOne
	@JoinColumn(name = "MASACH")
	private Book book;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public DonHang getMaDH() {
		return maDH;
	}

	public void setMaDH(DonHang maDH) {
		this.maDH = maDH;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
}
