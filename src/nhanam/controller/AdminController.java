package nhanam.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import myFunction.TestString;
import nhanam.entity.Book;
import nhanam.entity.DonHang;
import nhanam.entity.LoaiSach;
import nhanam.entity.NhaXB;
import nhanam.entity.TacGia;
import nhanam.entity.User;

@Transactional
@Controller
public class AdminController {
	private TestString testinput = new TestString();
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("admin/chuyenmuc/9/quan-ly")
	public String quanly() {
		return "admin/quanly";
	}
	
	@RequestMapping("admin/chuyenmuc/10/quan-ly-sach")
	public String sach(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Book b WHERE b.trangthai=0";
		Query query = session.createQuery(hql);
		List<Book> list = query.list();
		model.addAttribute("books", list);
		return "admin/sach/index";
	}
	
	@RequestMapping("admin/chuyenmuc/11/quan-ly-thanh-vien")
	public String thanhvien(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM User u WHERE u.trangthai=0";
		Query query = session.createQuery(hql);
		List<User> list = query.list();
		model.addAttribute("users", list);
		return "admin/taikhoan/index";
	}
	
	@RequestMapping("admin/chuyenmuc/12/quan-ly-NXB")
	public String nhaXB(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhaXB n WHERE n.trangthai=0";
		Query query = session.createQuery(hql);
		List<NhaXB> list = query.list();
		model.addAttribute("NXB", list);
		model.addAttribute("nxb", new NhaXB());
		return "admin/NXB/index";
	}
	
	@RequestMapping("admin/chuyenmuc/13/quan-ly-tac-gia")
	public String tacgia(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM TacGia t WHERE t.trangthai=0 ORDER BY t.tenTG";
		Query query = session.createQuery(hql);
		List<TacGia> list = query.list();
		model.addAttribute("tacgia", list);
		return "admin/tacgia/index";
	}
	
	// ----------------------------- SÁCH ------------------------------------------
	@RequestMapping(value = "admin/sach/{maSach}", params = "addSL")
	public String addSPSach(ModelMap model, @PathVariable("maSach") String masach) {
		Session session = factory.getCurrentSession();
		Book book = (Book) session.get(Book.class, masach);
		model.addAttribute("book", book);
		return "admin/sach/themSP";
	}
	
	@RequestMapping(value = "admin/sach/addSL/{masach}", method = RequestMethod.POST)
	public String addSPSach(@RequestParam(value = "soluong", defaultValue = "0") Integer soluong, @PathVariable("masach") String masach) {
		if (soluong > 0) {
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			Book book = (Book) session.get(Book.class, masach);
			int add = soluong + book.getSLcon();
			book.setSLcon(add);
			try {
				session.update(book);
				t.commit();
			}
			catch(Exception ex) {
				t.rollback();
				System.out.println("Thêm SP thất bại!");
			}
			finally {
				session.close();
			}
			
		}
		return "redirect:/admin/chuyenmuc/10/quan-ly-sach.htm";
	}
	
	@RequestMapping(value = "admin/sach/insert", method = RequestMethod.GET)
	public String insertSach(ModelMap model) {
		model.addAttribute("book", new Book());
		return "admin/sach/insert";
	}
	
	@Autowired
	ServletContext context;
	
	@RequestMapping(value = "admin/sach/insert-thong-tin", method = RequestMethod.POST)
	public String insertSach1(ModelMap model,
				@ModelAttribute("book") Book book,
				@RequestParam(value = "ngay", defaultValue = "") String ngay,
				HttpSession session)  {
		
		System.out.println("gioi thieu: " + book.getGioithieu() + " " + book.getGioithieu().length());
		System.out.println("so luong: " +book.getSLcon());
		System.out.println("gia: " + book.getGia());
		System.out.println("so trang: " + book.getSoTrang());
		System.out.println("ten sach: " + book.getTenSach());
		System.out.println("Loai sach: " + book.getLoaisach());
		System.out.println("ngay XB: " + ngay);
		
		Boolean test = true;
		int test_tensach = testinput.test_name(book.getTenSach());
		if (test_tensach != 0) {
			test = false;
			switch(test_tensach) {
				case 1:{
					model.addAttribute("ten_fail", " *Vui lòng nhập tên sách.");
					break;
				}
				case 2:{
					model.addAttribute("ten_fail", " *Tên sách chỉ gồm chữ, số và  ! # _ - . ");
					break;
				}
			}
		}
		
		int test_soluong = testinput.test_number(Integer.toString(book.getSLcon()));
		if (test_soluong != 0) {
			test = false;
			switch(test_soluong) {
				case 1:{
					model.addAttribute("SLcon_fail", " *Vui lòng nhập số lượng sản phẩm");
					break;
				}
				case 2:{
					model.addAttribute("SLcon_fail", " *Số lượng sản phẩm chỉ gồm các số");
					break;
				}
			}
		}
		
		int test_sotrang = testinput.test_number(Integer.toString(book.getSoTrang()));
		if (test_sotrang != 0) {
			test = false;
			switch(test_sotrang) {
				case 1:{
					model.addAttribute("ST_fail", " *Vui lòng nhập số trang");
					break;
				}
				case 2:{
					model.addAttribute("ST_fail", " *Số trang sách chỉ gồm các số");
					break;
				}
			}
		}
		
		if (ngay.length() == 0) {
			test = false;
			model.addAttribute("date_fail", " *Vui lòng nhập ngày xuất bản");
		}
		
		int test_gt = testinput.test_str(book.getGioithieu());
		if (test_gt != 0) {
			test = false;
			switch(test_gt) {
				case 1:{
					model.addAttribute("gioithieu_fail", " *Vui lòng nhập giới thiệu sách");
					break;
				}
				case 2:{
					model.addAttribute("gioithieu_fail", " *Chỉ gồm các ký tự chữ, số và !#$\".,");
					break;
				}
			}
		}
					
		if (book.getGia() != null) {
			int test_gia = testinput.test_gia(Long.toString(book.getGia()));
			if (test_gia != 0) {
				test = false;
				switch(test_gia) {
					case 1:{
						model.addAttribute("gia_fail", " *Vui lòng nhập đơn giá sách");
						break;
					}
					case 2:{
						model.addAttribute("gia_fail", " *Giá sách chỉ gồm các số và có ít nhất 5 chữ số ( VD: 15000)");
						break;
					}
				}
			}
		}
		else {
			test = false;
			model.addAttribute("gia_fail", " *Vui lòng nhập đơn giá sách");
		}
		
		if (test) {
			//chuyen kieu du lieu ngay
			LocalDate date = LocalDate.parse(ngay);
			Date date1 = java.sql.Date.valueOf(date);
			
			book.setNgayXB(date1);
			book.setHeight(20.5);
			book.setWidth(14.0);
			
			Session ss = factory.getCurrentSession();
			String hql = "FROM Book";
			Query query = ss.createQuery(hql);
			List<Book> list = query.list();
			Book book1 = list.get(list.size() - 1);
			Long masach = Long.parseLong(book1.getMaSach());
			book.setMaSach(Long.toString(masach+1));
			book.setTrangthai(false);
			
			session.setAttribute("insert_book", book);
			return "admin/sach/insert-photo";
//			return "home";
		}
		else {
			model.addAttribute("book", new Book());
			return "admin/sach/insert";
		}
	}
	
	@RequestMapping(value = "admin/sach/insert-photo", method = RequestMethod.POST)
	public String insertSach2(ModelMap model,
			@RequestParam("photo") MultipartFile photo,
			HttpSession session) {
		Boolean test = true;
		if (photo.isEmpty()) {
			test = false;
			model.addAttribute("photo_fail", " *Vui lòng chọn file!");
		}
		else {
			try {
				String photoPath = photo.getOriginalFilename();
				File file = new File("F:/Code/Java/web/Bookstore/WebContent/files/images/book", photoPath);
				photo.transferTo(file);
			}
			catch(Exception ex) {
				System.out.println("Lỗi lưu file!");
			}
		}
		if (test) {
			Book book = (Book)session.getAttribute("insert_book");
			book.setPhoto(photo.getOriginalFilename());

			Session ss = factory.openSession();
			Transaction t = ss.beginTransaction();
			try {
				
				ss.save(book);
				t.commit();
			}
			catch(Exception ex) {
				t.rollback();
				System.out.println("Insert Book fail");
			}
			finally {
				ss.close();
			}
			session.removeAttribute("insert_book");
			return "redirect:/admin/chuyenmuc/10/quan-ly-sach.htm";
//			return "home";
		}
		else{
			return "admin/sach/insert-photo";
		}
		
	}
	
	@RequestMapping(value = "admin/sach/{maSach}", params = "edit")
	public String editsach(@PathVariable("maSach") String masach, ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Book b WHERE b.maSach=:masach";
		Query query = session.createQuery(hql);
		query.setParameter("masach", masach);
		List<Book> list = query.list();
		if (list.size() == 1) {
			Book book = list.get(0);
			model.addAttribute("book", book);
			LocalDate ld = new java.sql.Date(book.getNgayXB().getTime()).toLocalDate();
			String date = ld.toString();
			model.addAttribute("ngay", date);
			return "admin/sach/edit";
		}
		return "redirect:/admin/chuyenmuc/10/quan-ly-sach.htm";
	}
	
	@RequestMapping(value = "admin/sach/edit/{masach}", method = RequestMethod.POST)
	public String editSach2(@ModelAttribute("book") Book book, 
					@RequestParam(value = "ngay", defaultValue = "")String ngay, 
					@PathVariable("masach") String masach,
					ModelMap model) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Book updateB = (Book) session.get(Book.class, masach); 		
		
		System.out.println("gioi thieu: " + book.getGioithieu() + " " + book.getGioithieu().length());
		System.out.println("so luong: " +book.getSLcon());
		System.out.println("gia: " + book.getGia());
		System.out.println("so trang: " + book.getSoTrang());
		System.out.println("ten sach: " + book.getTenSach());
		System.out.println("Loai sach: " + book.getLoaisach());
		System.out.println("ngay XB: " + ngay);
		
		Boolean test = true;
		if (book.getTenSach() != null) {
			int test_tensach = testinput.test_name(book.getTenSach());
			if (test_tensach > 1) {
				test = false;
				switch(test_tensach) {
					case 2:{
						model.addAttribute("ten_fail", " *Tên sách chỉ gồm chữ, số và  ! # _ - \" . ");
						break;
					}
				}
			}
			else
				updateB.setTenSach(book.getTenSach());
		}
			
		if (book.getSoTrang() != 0)
			updateB.setSoTrang(book.getSoTrang());
		
		if (!ngay.isEmpty()) {
			LocalDate date = LocalDate.parse(ngay);
			Date date1 = java.sql.Date.valueOf(date);
			updateB.setNgayXB(date1);
		}
		
		if (book.getGioithieu() != null) {
			int test_gt = testinput.test_str(book.getGioithieu());
			if (test_gt > 1) {
				test = false;
				switch(test_gt) {
					case 2:{
						model.addAttribute("gioithieu_fail", " *Chỉ gồm các ký tự chữ, số, ! # $ _ - \" '. , : và khoảng trắng");
						break;
					}
				}
			}
			else
				updateB.setGioithieu(book.getGioithieu());
		}
		
//		System.out.println(book.getLoaisach());
		if (book.getLoaisach() != null)
			updateB.setLoaisach(book.getLoaisach());
//		System.out.println(book.getTacgia());
		if (book.getTacgia() != null)
			updateB.setTacgia(book.getTacgia());
		if (book.getNhaXB() != null)
			updateB.setNhaXB(book.getNhaXB());
		if (test) {
			try {
				session.update(updateB);
				t.commit();
			}
			catch(Exception ex) {
				t.rollback();
				System.out.println("Update book fail!");
			}
			finally {
				session.close();
			}
			return "redirect:/admin/chuyenmuc/10/quan-ly-sach.htm";
//			return "home";
		}
		else {
			session.refresh(updateB);
			model.addAttribute("book", updateB);
			LocalDate ld = new java.sql.Date(updateB.getNgayXB().getTime()).toLocalDate();
			String date = ld.toString();
			model.addAttribute("ngay", date);
			return "admin/sach/edit";
		}
	}
	
	@RequestMapping(value = "admin/sach/{masach}", params = "delete")
	public String deleteSach(@PathVariable("masach") String masach) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		System.out.println(masach);
		Book book = (Book) session.get(Book.class, masach);
		book.setTrangthai(true);
		try {
//			session.delete(book);
			session.update(book);
			t.commit();
			System.out.println("xoá thành công!");
		}
		catch(Exception ex) {
			t.rollback();
			System.out.println("Xoá thất bại!");
		}
		finally {
			session.close();
		}
		return "redirect:/admin/chuyenmuc/10/quan-ly-sach.htm";
//		return "home";
	}
	
	@RequestMapping("admin/sach/luu-tru")
	public String luuTruSach(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Book b WHERE b.trangthai=1";
		Query query = session.createQuery(hql);
		List<TacGia> list = query.list();
		model.addAttribute("books", list);
		return "admin/sach/luutru";
	}
	
	@RequestMapping(value = "admin/sach/{masach}", params = "restore")
	public String restoreSach(ModelMap model, @PathVariable("masach") String masach) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Book book = (Book) session.get(Book.class, masach);
		book.setTrangthai(false);
		try {
			session.update(book);
			t.commit();
		}
		catch(Exception ex){
			t.rollback();
			System.out.println("Update Book fail");
		}
		finally {
			session.close();
		}
		return luuTruSach(model);
	}
	
	
	//--------------- TÁC GIẢ ----------------------------------
	
	@RequestMapping(value = "admin/tacgia/insert", method = RequestMethod.GET)
	public String insertTG(ModelMap model) {
		model.addAttribute("tacgia", new TacGia());
		return "admin/tacgia/insert";
	}
	
	@RequestMapping(value = "admin/tacgia/insert", method = RequestMethod.POST)
	public String insertTG2(@ModelAttribute("tacgia") TacGia tacgia, ModelMap model, HttpSession ss){
		Boolean test = true;
		String ten = tacgia.getTenTG();
		ten = ten.toLowerCase();
		ten = ten.trim();
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		int test_tenTG = testinput.test_name(ten);
		if (test_tenTG != 0) {
			test = false;
			switch(test_tenTG) {
				case 1:{
					model.addAttribute("ten_fail", " *Vui lòng nhập tên Tác giả.");
					break;
				}
				case 2:{
					model.addAttribute("ten_fail", " *Tên tác giả chỉ gồm chữ, số và  ! # _ - \" . và khoảng trắng");
					break;
				}
			}
		}
		else {
			// Kiem tra ten Tac Gia da co trong CSDL chua
			String hql = "FROM TacGia t WHERE t.tenTG=:ten";
			Query query = session.createQuery(hql);
			query.setParameter("ten", ten);
			List<TacGia> list = query.list();
			if (list.size() != 0) {
				test = false;
				model.addAttribute("ten_fail", "Tác giả này đã có!");
			}
		}
		
		int test_gt = testinput.test_str(tacgia.getGioiThieu());
		if (test_gt > 1) {
			test = false;
			switch(test_gt) {
				case 1:{
					model.addAttribute("gt_fail", " *Vui lòng nhập giới thiệu sách");
					break;
				}
				case 2:{
					model.addAttribute("gt_fail", " *Chỉ gồm các ký tự chữ, số, ! # $ _ - \" '. , : và khoảng trắng");
					break;
				}
			}
		}
		
		if (test) {
			tacgia.setTenTG(ten);
			String hql2 = "FROM TacGia";
			Query query2 = session.createQuery(hql2);
			List<TacGia> list2 = query2.list();
			String maTG = "TG" + Integer.toString(list2.size() + 1);
			tacgia.setMaTG(maTG);
			tacgia.setTrangthai(false);
			String gt = tacgia.getGioiThieu();
//				System.out.println(tacgia.getMaTG());
//				System.out.println(tacgia.getTenTG());
//				System.out.println(gt);
			
			if (gt.isEmpty()) {
				tacgia.setGioiThieu("  ");
			}
			try {
				session.save(tacgia);
				t.commit();
			}
			catch(Exception ex) {
				t.rollback();
				System.out.println("insert TG fail");
			}
			finally {
				session.close();
			}
//			return "redirect:/admin/chuyenmuc/13/quan-ly-tac-gia.htm";
			return "home";
		}
		else {
			model.addAttribute("tacgia", new TacGia());
			return "admin/tacgia/insert";
		}
	}
	
	@RequestMapping(value = "admin/tacgia/{maTG}", params = "edit")
	public String editTG(ModelMap model, @PathVariable("maTG") String maTG) {
		Session session = factory.getCurrentSession();
		TacGia t = (TacGia)session.get(TacGia.class, maTG);
		model.addAttribute("tacgia", t);
		return "admin/tacgia/edit";
	}
	
	@RequestMapping(value = "admin/tacgia/edit/{maTG}", method = RequestMethod.POST)
	public String editTG2(@PathVariable("maTG") String maTG, 
				@ModelAttribute("tacgia")TacGia tacgia,
				ModelMap model) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		TacGia updateTG = (TacGia) session.get(TacGia.class, maTG);
		String gt = tacgia.getGioiThieu();
//			System.out.println(tacgia.getMaTG());
//			System.out.println(tacgia.getTenTG());
//			System.out.println(gt);
		Boolean test = true;
		if (!gt.isEmpty()) {
			int test_gt = testinput.test_str(tacgia.getGioiThieu());
			if (test_gt > 1) {
				test = false;
				switch(test_gt) {
					case 2:{
						model.addAttribute("gt_fail", " *Chỉ gồm các ký tự chữ, số, ! # $ _ - \" '. , : và khoảng trắng");
						break;
					}
				}
			}	
		}
		if(test) {
			updateTG.setGioiThieu(gt);
			try {
				session.save(updateTG);
				t.commit();
			}
			catch(Exception ex) {
				t.rollback();
				System.out.println("update TG fail");
			}
			finally {
				session.close();
			}
			return "redirect:/admin/chuyenmuc/13/quan-ly-tac-gia.htm";
//			return "home";
		}
		else {
			model.addAttribute("tacgia", updateTG);
			return "admin/tacgia/edit";
		}
	}
	
	
	@RequestMapping(value = "admin/tacgia/{maTG}", params = "delete")
	public String deleteTG(@PathVariable("maTG") String maTG) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		System.out.println(maTG);
		TacGia tacgia = (TacGia) session.get(TacGia.class, maTG);
		tacgia.setTrangthai(true);
		try {
			session.update(tacgia);
			t.commit();
		}
		catch(Exception ex) {
			t.rollback();
			System.out.println("Delete TG fail!");
		}
		finally {
			session.close();
		}
		return "redirect:/admin/chuyenmuc/13/quan-ly-tac-gia.htm";
//		return "home";
	}
	
	@RequestMapping("admin/tacgia/luu-tru")
	public String luuTruTG(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM TacGia t WHERE t.trangthai=1";
		Query query = session.createQuery(hql);
		List<TacGia> list = query.list();
		model.addAttribute("tacgia", list);
		return "admin/tacgia/luutru";
	}
	
	@RequestMapping(value = "admin/tacgia/{maTG}", params = "restore")
	public String restoreTG(ModelMap model, @PathVariable("maTG") String maTG) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		TacGia tg = (TacGia) session.get(TacGia.class, maTG);
		tg.setTrangthai(false);
		try {
			session.update(tg);
			t.commit();
		}
		catch(Exception ex){
			t.rollback();
			System.out.println("Update TG fail");
		}
		finally {
			session.close();
		}
		return "redirect:/admin/tacgia/luu-tru.htm";
	}
	
	//---------------------------- NHÀ XUẤT BẢN ----------------------------------
		
	@RequestMapping(value = "admin/nxb/insert", method = RequestMethod.POST)
	public String insertNXB2(@ModelAttribute("nxb") NhaXB nxb, ModelMap model, HttpSession ss){
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		Boolean test = true;
		String ten = nxb.getTenNXB();
		ten = ten.toLowerCase();
		ten = ten.trim();
		int test_tenNXB = testinput.test_name(ten);
		if (test_tenNXB != 0) {
			test = false;
			switch(test_tenNXB) {
				case 1:{
					model.addAttribute("ten_fail", " *Vui lòng nhập tên Nhà xuất bản.");
					break;
				}
				case 2:{
					model.addAttribute("ten_fail", " *Tên nhà xuất bản chỉ gồm chữ, số và  ! # _ - \" . và khoảng trắng");
					break;
				}
			}
		}
		else {
			// Kiem tra ten Tac Gia da co trong CSDL chua
			String hql = "FROM NhaXB n WHERE n.tenNXB=:ten";
			Query query = session.createQuery(hql);
			query.setParameter("ten", ten);
			List<TacGia> list = query.list();
			if (list.size() != 0) {
				test = false;
				model.addAttribute("message", "Nhà xuất bản này đã có!");
			}
		}
		if(test) {
			nxb.setTenNXB(ten);
			String hql2 = "FROM NhaXB";
			Query query2 = session.createQuery(hql2);
			List<TacGia> list2 = query2.list();
			String maNXB = "XB" + Integer.toString(list2.size() + 1);
			nxb.setMaNXB(maNXB);
			nxb.setTrangthai(false);
			System.out.println(nxb.getMaNXB());
			System.out.println(nxb.getTenNXB());
			try {
				session.save(nxb);
				t.commit();
			}
			catch(Exception ex) {
				t.rollback();
				System.out.println("insert NXB fail");
			}
			finally {
				session.close();
			}
			return "redirect:/admin/chuyenmuc/12/quan-ly-NXB.htm";
//			return "home";
		}
		else
			return "admin/NXB/index";
	}
	
	@RequestMapping(value = "admin/nxb/{maNXB}", params = "delete")
	public String deleteNXB(@PathVariable("maNXB") String maNXB) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		System.out.println(maNXB);
		NhaXB nxb = (NhaXB) session.get(NhaXB.class, maNXB);
		
		String hql = "FROM Book b WHERE b.nhaXB.maNXB=:maNXB";
		Query query = session.createQuery(hql);
		query.setParameter("maNXB", maNXB);
		List<Book> list = query.list();
		if (list.size() == 0) {
			try {
				session.delete(nxb);
				t.commit();
			}
			catch(Exception ex) {
				t.rollback();
				System.out.println("Delete NXB fail!");
			}
		}
		else {
			try {
				nxb.setTrangthai(true);
				session.update(nxb);
				t.commit();
			}
			catch(Exception ex) {
				t.rollback();
				System.out.println("Delete NXB fail!");
			}
		}
		session.close();
		return "redirect:/admin/chuyenmuc/12/quan-ly-NXB.htm";
//			return "home";
	}
	
	@RequestMapping("admin/nxb/luu-tru")
	public String luuTruNXB(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM NhaXB n WHERE n.trangthai=1";
		Query query = session.createQuery(hql);
		List<NhaXB> list = query.list();
		model.addAttribute("nxb", list);
		return "admin/NXB/luutru";
	}
	
	@RequestMapping(value = "admin/nxb/{maNXB}", params = "restore")
	public String restoreNXB(ModelMap model, @PathVariable("maNXB") String maNXB) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		NhaXB nxb = (NhaXB) session.get(NhaXB.class, maNXB);
		nxb.setTrangthai(false);
		try {
			session.update(nxb);
			t.commit();
		}
		catch(Exception ex){
			t.rollback();
			System.out.println("Update NXB fail");
		}
		finally {
			session.close();
		}
		return "redirect:/admin/nxb/luu-tru.htm";
	}
	
	
	// ------------------------------------------ THÀNH VIÊN ------------------------------------------
	
	@RequestMapping(value = "admin/user/insert", method = RequestMethod.GET)
	public String insertUser1(ModelMap model) {
		model.addAttribute("user", new User());
		return "admin/taikhoan/insert";
	}
	
	@RequestMapping(value = "admin/user/insert", method = RequestMethod.POST)
	public String insertUser2(@ModelAttribute("user") User user, ModelMap model) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		Boolean test = true;
		int test_email = testinput.test_email(user.getEmail());
		if (test_email != 0) {
			test = false;
			switch(test_email) {
				case 1:{
					model.addAttribute("email_fail", " *Vui lòng nhập email.");
					break;
				}
				case 2:{
					model.addAttribute("email_fail", " *Hãy nhập đúng định dạng mail!");
					break;
				}
			}
		}
		else {
			String hql = "FROM User u WHERE u.email=:email";
			Query query = session.createQuery(hql);
			query.setParameter("email", user.getEmail());
			List<User> list = query.list();
			if (list.size() != 0) {
				test = false;
				model.addAttribute("email_fail", " *Email đã được sử dụng. Vui lòng sử dụng email khác!");
			}
		}
		
		int test_name = testinput.test_name(user.getName());
		if(test_name != 0) {
			test = false;
			switch(test_name) {
				case 1:{
					model.addAttribute("name_fail", " *Vui lòng nhập tên hiển thị");
					break;
				}
				case 2:{
					model.addAttribute("name_fail", " *Tên hiển thị chỉ gồm chữ, số và  ! # _ - \" . và khoảng trắng");
					break;
				}
			}
		}
		if(user.getIsAdmin() == null) {
			test = false;
			model.addAttribute("isAdmin_fail", " *Vui lòng chọn loại tài khoản!");
		}
		if (test) {
			user.setPassword("user123456");
			user.setVerify(false);
			user.setTrangthai(false);
			try {
				session.save(user);
				t.commit();
				String body = "Xin chào, " + user.getName() + "\n"
						+ "Để đăng nhập vào trang web Nhã Nam, vui lòng sử dụng địa chỉ email " + user.getEmail() + "\n"
						+ "Mật khẩu mặc định: " + user.getPassword() + "\n"
						+ "Chúc bạn sức khoẻ thành công!\n"
						+ "Ghi chú: Thông báo này được gửi tự động từ hệ thống. Để đảm bảo an toàn bạn cần đổi mật khẩu sau khi đăng nhập thành công.";
				
				String subject = "Thông báo tài khoản đăng nhập Yuean'Bookstore.";
				sendMail(user.getEmail(), subject, body);
			}
			catch(Exception ex) {
				t.rollback();
				System.out.println("Insert user fail");
			}
			finally {
				session.close();
			}
			return "redirect:/admin/chuyenmuc/11/quan-ly-thanh-vien.htm";
//			return "home";
		}
		else {
			model.addAttribute("user", new User());
			return "admin/taikhoan/insert";
		}
	}
	
	@RequestMapping(value = "admin/user/{email}", params = "edit")
	public String editUser1(ModelMap model, @PathVariable("email") String email) {
		Session session = factory.getCurrentSession();
		User user = (User) session.get(User.class, email);
		model.addAttribute("user", user);
		return "admin/taikhoan/edit";
	}
	
	@RequestMapping(value = "admin/user/edit/{email}", method = RequestMethod.POST)
	public String editUser2(@PathVariable("email") String email, @ModelAttribute("user") User user) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		User updateU = (User) session.get(User.class, email);
		updateU.setIsAdmin(user.getIsAdmin());
		try {
			session.update(updateU);
			t.commit();
		}
		catch(Exception ex) {
			t.rollback();
			System.out.println("update user fail!");
		}
		finally {
			session.close();
		}
		return "redirect:/admin/chuyenmuc/11/quan-ly-thanh-vien.htm";
//		return "home";
	}
	
	@RequestMapping(value = "admin/user/{email}", params = "delete")
	public String deleteUser(@PathVariable("email")String email) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		User user = (User) session.get(User.class, email);
		String hql = "FROM DonHang d WHERE d.user.email=:email";
		Query query = session.createQuery(hql);
		query.setParameter("email", email);
		List<DonHang> list = query.list();
		if (list.size() == 0) {
			try {
				session.delete(user);
				t.commit();
			}
			catch(Exception ex){
				t.rollback();
				System.out.println("Delete user fail.");
			}
			finally {
				session.close();
			}
			
		}
		else {
			user.setTrangthai(true);
			try {
				session.update(user);
				t.commit();
			}
			catch(Exception ex) {
				t.rollback();
				System.out.println("Delete User fail");
			}
			finally {
				session.close();
			}
		}
		return "redirect:/admin/chuyenmuc/11/quan-ly-thanh-vien.htm";
	}
	
	@RequestMapping("admin/user/luu-tru")
	public String luuTruUser(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM User u WHERE u.trangthai=1";
		Query query = session.createQuery(hql);
		List<User> list = query.list();
		model.addAttribute("users", list);
		return "admin/taikhoan/luutru";
	}
	
	@RequestMapping(value = "admin/user/{email}", params = "restore")
	public String restoreUser(ModelMap model, @PathVariable("email") String email) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		User user = (User) session.get(User.class, email);
		user.setTrangthai(false);
		try {
			session.update(user);
			t.commit();
		}
		catch(Exception ex){
			t.rollback();
			System.out.println("Update User fail");
		}
		finally {
			session.close();
		}
		return "redirect:/admin/user/luu-tru.htm";
	}
	
	//----------------------------------------------------------------------------------------------------
	
	@ModelAttribute("LS")
	public List<LoaiSach> getLS(){
		Session session = factory.getCurrentSession();
		String hql = "FROM LoaiSach";
		Query query = session.createQuery(hql);
		List<LoaiSach> list = query.list();
		return list;
	}
	
	@ModelAttribute("TG")
	public List<TacGia> getTG(){
		Session session = factory.getCurrentSession();
		String hql = "FROM TacGia t WHERE t.trangthai=0 ORDER BY t.tenTG";
		Query query = session.createQuery(hql);
		List<TacGia> list = query.list();
		return list;
	}
	
	@ModelAttribute("NXB")
	public List<NhaXB> getNXB(){
		Session session = factory.getCurrentSession();
		String hql = "FROM NhaXB n WHERE n.trangthai=0 ORDER BY n.tenNXB";
		Query query = session.createQuery(hql);
		List<NhaXB> list = query.list();
		return list;
	}
	
	@Autowired
	JavaMailSender mailer;
	public void sendMail( String to, String subject, String body) {
		String from = "judy.mylinh1999@gmail.com";
		try {
			MimeMessage mail = mailer.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");
			helper.setFrom(from, from);
			helper.setTo(to);
			helper.setReplyTo(from, from);
			helper.setSubject(subject);
			helper.setText(body, true);
			
			mailer.send(mail);
		}
		catch(Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	
}
