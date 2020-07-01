package nhanam.entity;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TACGIA")
public class TacGia {
	@Id
	@Column(name = "MATG")
	private String maTG;
	
	@Column(name = "TENTG")
	private String tenTG;
	
	@Column(name = "GIOITHIEU")
	private String gioiThieu;
	
	@Column(name = "TRANGTHAI")
	private Boolean trangthai;
	
	@OneToMany(mappedBy = "tacgia", fetch = FetchType.EAGER)
	private Collection<Book> booksTG;
	
	public String getMaTG() {
		return maTG;
	}

	public void setMaTG(String maTG) {
		this.maTG = maTG;
	}

	public String getTenTG() {
		return tenTG;
	}

	public void setTenTG(String tenTG) {
		this.tenTG = tenTG;
	}

	public String getGioiThieu() {
		return gioiThieu;
	}

	public void setGioiThieu(String gioiThieu) {
		this.gioiThieu = gioiThieu;
	}

	public Collection<Book> getBooksTG() {
		return booksTG;
	}

	public void setBooksTG(Collection<Book> booksTG) {
		this.booksTG = booksTG;
	}

	public Boolean getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(Boolean trangthai) {
		this.trangthai = trangthai;
	}
	
	
}
