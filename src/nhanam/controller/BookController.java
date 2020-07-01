package nhanam.controller;

import java.util.Date;
import java.util.List;

import javax.mail.internet.MimeMessage;
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

import myFunction.TestString;
import nhanam.bean.Product;
import nhanam.bean.UserInfo;
import nhanam.entity.Book;
import nhanam.entity.CTDH;
import nhanam.entity.DonHang;
import nhanam.entity.NhaXB;
import nhanam.entity.TacGia;
import nhanam.entity.User;

@Transactional
@Controller
public class BookController {
	private TestString testinput = new TestString();
	
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("sach")
	public String showBook(ModelMap model, @RequestParam("sach") String masach) {
		Session session = factory.getCurrentSession();
		Book book = (Book)session.get(Book.class, masach);
		model.addAttribute("title", book.getTenSach());
		model.addAttribute("book", book);
		System.out.println(book.getNgayXB());
		
		String maTG = book.getTacgia().getMaTG();
		String hql = "FROM Book b WHERE b.trangthai=0 ORDER BY b.ngayXB DESC";
		Query query = session.createQuery(hql);
		query.setMaxResults(6);
		List<Book> list = query.list();
		model.addAttribute("books", list);
		return "book/index";
	}
	
	@RequestMapping("sach/them-vao-gio-hang/{masach}")
	public String addToCart(@PathVariable("masach") String masach, @RequestParam("soluong") Integer soluong, HttpSession session, ModelMap model) {
		UserInfo user = (UserInfo) session.getAttribute("user");
		if (user == null) {
			session.setAttribute("tmphtm", "sach/them-vao-gio-hang/" + masach + ".htm");
			return "redirect:/dang-nhap.htm?message_login&sach="+masach;
		}
		Session ss = factory.getCurrentSession();
		Book book = (Book) ss.get(Book.class, masach);
		if (soluong <= book.getSLcon() && soluong >= 0) {
			Product p = new Product(book, soluong);
			if (user.getCart().size() > 0) {
				List<Product> list = user.getCart();
				for (Product i : list) {
					String kt = i.getBook().getMaSach();
					if (kt.equals(masach)) {
						i.setSoluong(i.getSoluong() + soluong);
						model.addAttribute("book", book);
						return "book/index";
					}
				}
				user.insertCart(p);
			}
			else
				user.insertCart(p);
		}
		else {
			model.addAttribute("soluong_fail", " *Số lượng không phù hợp!");
		}
		model.addAttribute("book", book);
		return "redirect:/sach.htm?sach=" + book.getMaSach();
	}
	
	@RequestMapping("gio-hang/dat-hang")
	public String datHang(HttpSession session, ModelMap model) {
		Session ss = factory.openSession();
		Transaction t = ss.beginTransaction();
		
		DonHang dh = (DonHang) session.getAttribute("DH");
		UserInfo user = (UserInfo) session.getAttribute("user");
		List<Product> p = user.getCart();
		
		Boolean test = true;
		Book b;
		Product tmp;
		String masach = "";
		String error = "";
		for (int i = 0; i < p.size(); i++) {
			b=new Book();
			tmp = new Product();
			tmp = p.get(i);
			masach = tmp.getBook().getMaSach();
			b = (Book)ss.get(Book.class, masach);
			
			if (b.getSLcon() < tmp.getSoluong()) {
				test = false;
				error = error +  " - " + b.getTenSach();
			}
		}
		if (test) {

			
			User user1 = (User) ss.get(User.class, user.getEmail());
			String hql = "FROM DonHang";
			Query query = ss.createQuery(hql);
			List<DonHang> list = query.list();
			String maDH = "DH" + Integer.toString(list.size() +1);
			dh.setMaDonhang(maDH);
			if (dh.getGhichu().isEmpty()) {
				dh.setGhichu("  ");
			}
			dh.setNgay(new Date());
			dh.setUser(user1);
			dh.setTrangthai(1);

			CTDH ct;
			Book book;
			int slcon = 0;
			try {
				ss.save(dh);
				for (Product i : p) {
					ct = new CTDH();
					book = new Book();
					
					book = i.getBook();
					slcon = book.getSLcon() - i.getSoluong();
					System.out.println(slcon);
					System.out.println(book.getMaSach());
					book = (Book) ss.get(Book.class, book.getMaSach());
					book.setSLcon(slcon);
					
					ct.setBook(i.getBook());
					ct.setSoluong(i.getSoluong());
					ct.setMaDH(dh);
					
					ss.save(ct);
					ss.update(book);
				}
				t.commit();
			}
			catch(Exception ex) {
				t.rollback();
				System.out.println("Thêm DH thất bại");
			}
			
			ss.close();
			TBDH(dh, user);
			
			session.removeAttribute("DH");
			int sizep = p.size();
			for ( int j = sizep - 1; j >= 0 ; j-- ) {
				user.removeIndex(j);
			}
			
			return "redirect:/gio-hang/donhang/" + maDH + ".htm";
		}
		else {
			model.addAttribute("message","Số lượng sách không phù hợp:" + error);
			long tong = 0;
			for (Product i : p) {
				tong = tong +  i.getSoluong()*i.getBook().getGia();
			}
			model.addAttribute("sizecart", p.size());
			model.addAttribute("sum", tong);
			return "cart/giohang";
		}
	}
	
	@RequestMapping(value = "gio-hang/{stt}", params = "edit")
	public String editCart(@PathVariable("stt") Integer stt, ModelMap model, HttpSession session) {
		Session ss = factory.getCurrentSession();
		UserInfo user = (UserInfo) session.getAttribute("user");
		Product p = user.getCart().get(stt ); 
		Book b = (Book) ss.get(Book.class, p.getBook().getMaSach());
		p.setBook(b);
		model.addAttribute("product", p);
		model.addAttribute("index", stt);
		return "cart/edit";
	}
	
	@RequestMapping(value = "gio-hang/edit/{index}", method = RequestMethod.POST)
	public String editCart1(ModelMap model, @PathVariable("index") Integer index, HttpSession session, @ModelAttribute("product") Product p) {
		Session ss = factory.getCurrentSession();
		UserInfo user = (UserInfo) session.getAttribute("user");
		List<Product> list = user.getCart();
		Book book = list.get(index).getBook();
		ss.refresh(book);
		int soluong = book.getSLcon();
		if (p.getSoluong() <= soluong) {
			if (p.getSoluong() > 0)
				list.get(index).setSoluong(p.getSoluong());
			else
				user.removeIndex(index);
			return "redirect:/gio-hang.htm";
		}
		else {
			model.addAttribute("message", "Số lượng sách đặt không phù hợp");
			model.addAttribute("product", list.get(index));
			return "cart/edit";
		}
	}
	
	@RequestMapping(value = "gio-hang/{index}", params = "delete")
	public String deleteCart(@PathVariable("index") Integer index, ModelMap model, HttpSession session) {
		UserInfo user = (UserInfo) session.getAttribute("user");
		user.removeIndex(index);
		return "redirect:/gio-hang.htm";
	}
	
	@RequestMapping("gio-hang/thanh-toan.htm")
	public String thanhToan(ModelMap model) {
		model.addAttribute("donhang", new DonHang());
		return "cart/datHang";
	}
	
	@RequestMapping("gio-hang/thong-tin.htm")
	public String thongtinDH(@ModelAttribute("donhang") DonHang donhang, ModelMap model, HttpSession session) {
		model.addAttribute("donhang", donhang);
		long tong = 0;
		UserInfo user = (UserInfo) session.getAttribute("user");
		if (user != null) {
			List<Product> list = user.getCart();
			if (list.size() > 0) {
				for (Product i : list) {
					tong = tong +  i.getSoluong()*i.getBook().getGia();
				}
			}
			model.addAttribute("sum", tong);
		}
		
		Boolean test = true;
		int test_hoten = testinput.test_name(donhang.getHoten());
		if (test_hoten != 0) {
			test = false;
			switch(test_hoten) {
				case 1:{
					model.addAttribute("hoten_fail"," *Vui lòng nhập họ tên.");
					break;
				}
				case 2:{
					model.addAttribute("hoten_fail", " *Tên chỉ bao gồm: chữ số và các ký tự ! # _ - ., khoảng trắng và có từ 2 - 50 ký tự");
					break;
				}
			}
		}
		
		int test_sdt = testinput.test_sdt(donhang.getSdt());
		if (test_sdt != 0) {
			test = false;
			switch(test_sdt) {
				case 1:{
					model.addAttribute("sdt_fail"," *Vui lòng nhập số điện thoại liên hệ.");
					break;
				}
				case 2:{
					model.addAttribute("sdt_fail", "*Số điện thoại chỉ gồm chuỗi có 10 chữ số.");
					break;
				}
			}
		}
		
			
		int test_add = testinput.test_add(donhang.getDiachi());
		if(test_add != 0) {
			test = false;
			switch(test_add) {
				case 1:{
					model.addAttribute("diachi_fail", " *Vui lòng nhập địa chỉ giao hàng");
					break;
				}
				case 2:{
					model.addAttribute("diachi_fail", " *Địa chỉ chỉ bao gồm: chữ, số và các ký tự ! # $ _ , - ? + / và khoảng trắng");
					break;
				}
			}
		}
		
		int test_gc = testinput.test_str(donhang.getGhichu());
		if(test_gc > 1 ) {
			test = false;
			switch(test_gc) {
				case 2:{
					model.addAttribute("ghichu_fail", " *Ghi chú chỉ bao gồm: chữ, số và các ký tự ! @ # $ _ , - ? + \" * . / và khoảng trắng");
					break;
				}
			}
		}
		if (test) {
			session.setAttribute("DH", donhang);
			return "cart/thongtinDH";
		}
		else
			return "cart/datHang";
	}
	
	@RequestMapping("tac-gia/{maTG}")
	public String bookTG(@PathVariable("maTG") String maTG, ModelMap model) {
		Session session = factory.getCurrentSession();
		TacGia tacgia = (TacGia) session.get(TacGia.class, maTG);
		String hql = "FROM Book b WHERE b.tacgia.maTG=:maTG ORDER BY b.ngayXB DESC";
		Query query = session.createQuery(hql);
		query.setParameter("maTG", maTG);
		List<Book> list = query.list();
		Boolean gt;
		if (!tacgia.getGioiThieu().equals(" "))
			gt = true;
		else 
			gt = false;
		model.addAttribute("books", list);
		model.addAttribute("tacgia", tacgia);
		model.addAttribute("gioithieu", gt);
		return "book/bookTG";
	}
	
	@RequestMapping("nha-xuat-ban/{maNXB}")
	public String bookNXB(@PathVariable("maNXB") String maNXB, ModelMap model) {
		Session session = factory.getCurrentSession();
		NhaXB nxb = (NhaXB) session.get(NhaXB.class, maNXB);
		String hql = "FROM Book b WHERE b.nhaXB.maNXB=:maNXB ORDER BY b.ngayXB DESC";
		Query query = session.createQuery(hql);
		query.setParameter("maNXB", maNXB);
		List<Book> list = query.list();
		model.addAttribute("books", list);
		model.addAttribute("nxb", nxb);
		return "book/bookNXB";
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
	
	//gửi mail thông báo đơn hàng
	public void TBDH(DonHang donhang, UserInfo user) {
		String subject = "THÔNG BÁO ĐẶT HÀNG";
		String body = "<h1>THÔNG TIN ĐƠN HÀNG</h1>\r\n" + 
				"			\r\n" + 
				"			<h6><strong>1. Thông tin khách hàng:</strong></h6>\r\n" + 
				"			<p>\r\n" + 
				"				<label>Họ và tên: " + donhang.getHoten() + "</label>\r\n" + 
				"			</p>\r\n" + 
				"			<p>\r\n" + 
				"				<label>Số điện thoại: " + donhang.getSdt() + "</label>\r\n" + 
				"			</p>\r\n" + 
				"			<p>\r\n" + 
				"				<label>Địa chỉ nhận hàng: " + donhang.getDiachi() + "</label>\r\n" + 
				"			</p>\r\n" + 
				"			<p>\r\n" + 
				"				<label>Ghi chú: " + donhang.getGhichu() + "</label>\r\n" + 
				"			</p>\r\n" + 
				"			</c:if>\r\n" + 
				"			<h6><strong>2. Thông tin sản phẩm:</strong></h6>\r\n" + 
				"			<table >\r\n" + 
				"                <tr>\r\n" + 
				"                    <th>STT</th>\r\n" + 
				"                    <th>Tiêu đề</th>\r\n" + 
				"                    <th>Giá</th>\r\n" + 
				"                    <th>Số lượng</th>\r\n" + 
				"                    <th>Tổng cộng</th>\r\n" + 
				"                    \r\n" + 
				"                </tr>\r\n";
		List<Product> list = user.getCart();
		String x = "";
		int count  = 1;
		long sum = 0;
		for (Product p : list) {
			
			x = x + "					<tr>\r\n" + 
			"	                	<td>" + count + "</td>\r\n" + 
			"	                	<td style = \"text-align:left; text-transform: capitalize;\">\r\n" + 
			"	                		" + p.getBook().getTenSach() + " \r\n" + 
			"	                	</td>\r\n" + 
			"	                	<td>" + p.getBook().getGia() + "đ</td>\r\n" + 
			"	                	<td>\r\n" + 
			"	                		" + p.getSoluong() + "\r\n" + 
			"						</td>\r\n" + 
			"	                	<td>" + p.getSoluong() * p.getBook().getGia() + "</td>\r\n" + 
			"	                	\r\n" + 
			"	                </tr>\r\n";
			count++;
			sum = sum + (p.getSoluong() * p.getBook().getGia());
		}
		body = body + x;
		body = body + "                <tfoot>\r\n" + 
				"                	<tr>\r\n" + 
				"                		\r\n" + 
				"                		<th colspan = \"4\" style = \"border-top: 1px solid #adabab\">Tổng cộng:</th>\r\n" + 
				"                		<td>" + sum + "đ</td>\r\n" + 
				"                	</tr>\r\n" + 
				"                </tfoot>\r\n" + 
				"            </table>\r\n" + 
				"            \r\n" + 
				"            <h6><strong>3.Thời gian giao hàng dự kiến</strong></h6>\r\n" + 
				"            <p> 48 giờ kể từ lúc đơn hàng được tiếp nhận.</p>\r\n" + 
				"            \r\n" + 
				"            <h6><strong>4. Hình thức thanh toán</strong></h6>\r\n" + 
				"            <p>Thanh toán tiền mặt khi nhận hàng</p>";
		sendMail(user.getEmail(), subject, body);
	}
}
