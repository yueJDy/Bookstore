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
@Table(name = "BOOK")
public class Book {
	

	@Id
	@Column(name = "MASACH")
	private String maSach;
	
	@Column(name = "TENSACH")
	private String tenSach;
	
	@Column(name = "SOTRANG")
	private int soTrang;
	
	@Column(name = "NGAYXB")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date ngayXB;
	
	@Column(name = "WIDTH")
	private Double width;
	
	@Column(name = "HEIGHT")
	private Double height;
	
	@Column(name = "GIOITHIEUSACH")
	private String gioithieu;
	
	@Column(name = "PHOTO")
	private String photo;
	
	@Column(name = "GIA")
	private Long gia;
	
	@ManyToOne
	@JoinColumn(name = "MATG")
	private TacGia tacgia;
	
	@ManyToOne
	@JoinColumn(name = "MANXB")
	private NhaXB nhaXB;
	
	@ManyToOne
	@JoinColumn(name = "MALOAI")
	private LoaiSach loaisach;

	@OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
	private Collection<CTDH> ctdhB;
	
	@Column(name = "STATUS")
	private Boolean trangthai;
	
	@Column(name = "SOLUONGCON")
	private int SLcon;
	
	public String getMaSach() {
		return maSach;
	}

	public void setMaSach(String maSach) {
		this.maSach = maSach;
	}

	public String getTenSach() {
		return tenSach;
	}

	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}
	
	public int getSoTrang() {
		return soTrang;
	}

	public void setSoTrang(int soTrang) {
		this.soTrang = soTrang;
	}

	public Date getNgayXB() {
		return ngayXB;
	}

	public void setNgayXB(Date ngayXB) {
		this.ngayXB = ngayXB;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public String getGioithieu() {
		return gioithieu;
	}

	public void setGioithieu(String gioithieu) {
		this.gioithieu = gioithieu;
	}

	public TacGia getTacgia() {
		return tacgia;
	}

	public void setTacgia(TacGia tacgia) {
		this.tacgia = tacgia;
	}

	public NhaXB getNhaXB() {
		return nhaXB;
	}

	public void setNhaXB(NhaXB nhaXB) {
		this.nhaXB = nhaXB;
	}

	public LoaiSach getLoaisach() {
		return loaisach;
	}

	public void setLoaisach(LoaiSach loaisach) {
		this.loaisach = loaisach;
	}

	public Collection<CTDH> getCtdhB() {
		return ctdhB;
	}

	public void setCtdhB(Collection<CTDH> ctdhB) {
		this.ctdhB = ctdhB;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Long getGia() {
		return gia;
	}

	public void setGia(Long gia) {
		this.gia = gia;
	}

	public Boolean getTrangthai() {
		return trangthai;
	}

	public void setTrangthai(Boolean trangthai) {
		this.trangthai = trangthai;
	}

	public int getSLcon() {
		return SLcon;
	}

	public void setSLcon(int sLcon) {
		SLcon = sLcon;
	}
	
}
