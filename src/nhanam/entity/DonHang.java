package nhanam.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "DONHANG")
public class DonHang {
	@Id
	@Column(name = "MADH")
	private String maDonhang;
	
	@Column(name = "NGAY")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date ngay;
	
	@Column(name = "HOTEN")
	private String hoten;
	
	@Column(name = "SDT")
	private String sdt;
	
	@Column(name = "DIACHI")
	private String diachi;
	
	@Column(name = "GHICHU")
	private String ghichu;
	
	@Column(name = "TRANGTHAI")
	private int trangthai;
	
	@ManyToOne
	@JoinColumn(name = "EMAIL")
	private User user;
	
	@OneToMany(mappedBy = "maDH", fetch = FetchType.EAGER)
	private Collection<CTDH> ctdhDH;

	public String getMaDonhang() {
		return maDonhang;
	}

	public void setMaDonhang(String maDonhang) {
		this.maDonhang = maDonhang;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getGhichu() {
		return ghichu;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Collection<CTDH> getCtdhDH() {
		return ctdhDH;
	}

	public void setCtdhDH(Collection<CTDH> ctdhDH) {
		this.ctdhDH = ctdhDH;
	}

	public int getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(int trangthai) {
		this.trangthai = trangthai;
	}
	
	
}
