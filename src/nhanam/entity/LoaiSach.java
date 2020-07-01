package nhanam.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LOAISACH")
public class LoaiSach {
	@Id
	@Column(name = "MALOAI")
	private String maLoai;
	
	@Column(name = "TENLOAI")
	private String tenLoai;

	@OneToMany(mappedBy = "loaisach", fetch = FetchType.EAGER)
	private Collection<Book> booksLS; 
	
	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	public Collection<Book> getBooksLS() {
		return booksLS;
	}

	public void setBooksLS(Collection<Book> booksLS) {
		this.booksLS = booksLS;
	}
	
}
