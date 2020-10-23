package nhanam.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import nhanam.entity.DonHang;
import nhanam.entity.TacGia;
import nhanam.entity.CTDH;

@Transactional
@Controller
public class HomeController {
	
	
	//Gio Hang
	@RequestMapping("gio-hang")
	public String cart(HttpSession session, ModelMap model) {
		long tong = 0;
		UserInfo user = (UserInfo) session.getAttribute("user");
		if (user != null) {
			List<Product> list = user.getCart();
			if (list.size() > 0) {
				for (Product i : list) {
					tong = tong +  i.getSoluong()*i.getBook().getGia();
				}
				model.addAttribute("sizecart", list.size());
				model.addAttribute("sum", tong);
			}
			else {
				model.addAttribute("sizecart", 0);
			}
			
		}
		return "cart/giohang";
	}
	
	
	@RequestMapping("gio-hang/donhang/{maDH}")
	public String CTDH(ModelMap model, @PathVariable("maDH") String maDH) {
		Session session = factory.getCurrentSession();
		String hql = "FROM CTDH c WHERE c.maDH.maDonhang=:maDH";
		Query query = session.createQuery(hql);
		query.setParameter("maDH", maDH);
		List<CTDH> list = query.list();
		
		long tong = 0;
		for (CTDH i : list) {
			tong = tong + i.getBook().getGia();
		}
		DonHang dh = (DonHang) session.get(DonHang.class, maDH);
		
		model.addAttribute("donhang", dh);
		model.addAttribute("CTDH", list);
		model.addAttribute("sum", tong);
		return "cart/donhang";
	}
	
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("index")
	public String home(ModelMap model) {
//		Session session = factory.getCurrentSession();
//		String hql = "FROM Book b WHERE b.loaisach.maLoai=:loaisach ORDER BY b.ngayXB DESC";
//		Query query = session.createQuery(hql);
//		query.setMaxResults(8);
//		query.setParameter("loaisach", "VHVN");
//		List<Book> VHVN = query.list();
//		model.addAttribute("VHVN", VHVN);
//		
//		query.setParameter("loaisach", "VHNN");
//		List<Book> VHNN = query.list();
//		model.addAttribute("VHNN", VHNN);
//		
//		query.setParameter("loaisach", "TN");
//		List<Book> TN = query.list();
//		model.addAttribute("TN", TN);
//		
//		query.setParameter("loaisach", "KHTN");
//		List<Book> KHTN = query.list();
//		model.addAttribute("KHTN", KHTN);
		return "home";
	}
	
	@ModelAttribute("VHVN")
	public List<Book> getVHVN(){
		Session session = factory.getCurrentSession();
		String hql = "FROM Book b WHERE b.loaisach.maLoai=:loaisach AND b.trangthai=0 ORDER BY b.ngayXB DESC";
		Query query = session.createQuery(hql);
		query.setParameter("loaisach", "VHVN");
		query.setMaxResults(8);
		List<Book> list = query.list();
		return list;
	}
	
	@ModelAttribute("VHNN")
	public List<Book> getVHNN(){
		Session session = factory.getCurrentSession();
		String hql = "FROM Book b WHERE b.loaisach.maLoai=:loaisach AND b.trangthai=0 ORDER BY b.ngayXB DESC";
		Query query = session.createQuery(hql);
		query.setParameter("loaisach", "VHNN");
		query.setMaxResults(8);
		List<Book> list = query.list();
		return list;
	}
	
	@ModelAttribute("TN")
	public List<Book> getTN(){
		Session session = factory.getCurrentSession();
		String hql = "FROM Book b WHERE b.loaisach.maLoai=:loaisach AND b.trangthai=0 ORDER BY b.ngayXB DESC";
		Query query = session.createQuery(hql);
		query.setParameter("loaisach", "TN");
		query.setMaxResults(8);
		List<Book> list = query.list();
		return list;
	}
	
	@ModelAttribute("KHTN")
	public List<Book> getKHTN(){
		Session session = factory.getCurrentSession();
		String hql = "FROM Book b WHERE b.loaisach.maLoai=:loaisach AND b.trangthai=0 ORDER BY b.ngayXB DESC";
		Query query = session.createQuery(hql);
		query.setParameter("loaisach", "KHTN");
		query.setMaxResults(8);
		List<Book> list = query.list();
		return list;
	}
	
	//Danh muc sach
	@RequestMapping("sach-moi-xuat-ban")
	public String sachmoi(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Book b WHERE b.trangthai=0 ORDER BY b.ngayXB DESC";
		Query query = session.createQuery(hql);
		query.setMaxResults(10);
		List<Book> list = query.list();
		model.addAttribute("books", list);
		return "page/sachmoi";
	}
	
	@RequestMapping("chuyenmuc/1/van-hoc-viet-nam")
	public String chuyenMuc1(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Book b WHERE b.loaisach.maLoai=:loaisach AND b.trangthai=0 ORDER BY b.ngayXB DESC";
		Query query = session.createQuery(hql);
		query.setParameter("loaisach", "VHVN");
//		query.setMaxResults(8);
		List<Book> list = query.list();
		model.addAttribute("VHVN", list);
		return "page/VHVN";
	}
	
	@RequestMapping("chuyenmuc/2/van-hoc-nuoc-ngoai")
	public String chuyenMuc2(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Book b WHERE b.loaisach.maLoai=:loaisach AND b.trangthai=0 ORDER BY b.ngayXB DESC";
		Query query = session.createQuery(hql);
		query.setParameter("loaisach", "VHNN");
		List<Book> list = query.list();
		model.addAttribute("VHNN", list);
		return "page/VHNN";
	}
	
	@RequestMapping("chuyenmuc/3/thieu-nhi")
	public String chuyenMuc3(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Book b WHERE b.loaisach.maLoai=:loaisach AND b.trangthai=0 ORDER BY b.ngayXB DESC";
		Query query = session.createQuery(hql);
		query.setParameter("loaisach", "TN");
		List<Book> list = query.list();
		model.addAttribute("TN", list);
		return "page/TN";
	}
	
	@RequestMapping("chuyenmuc/4/khoa-hoc-tu-nhien-nhan-van")
	public String chuyenMuc4(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Book b WHERE b.loaisach.maLoai=:loaisach AND b.trangthai=0 ORDER BY b.ngayXB DESC";
		Query query = session.createQuery(hql);
		query.setParameter("loaisach", "KHTN");
		List<Book> list = query.list();
		model.addAttribute("KHTN", list);
		return "page/KHTN";
	}
	
	@RequestMapping("chuyenmuc/7/sach-moi")
	public String chuyenMuc7(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Book b ORDER BY b.ngayXB DESC";
		Query query = session.createQuery(hql);
		query.setMaxResults(10);
		List<Book> list = query.list();
		model.addAttribute("book", list);
		return "newbook";
	}
		
	//Tim kiem
	@RequestMapping(value ="tim-kiem", method = RequestMethod.POST)
	public String search(ModelMap model, @RequestParam("q") String search) {
		search = search.toLowerCase();
		search = search.trim();
		
		TestString testinput = new TestString();
		Boolean test = true;
//		String tmp1 = testinput.filter_input(search);
//		search = tmp1;
		if (test) {
			String arr[] = search.split(" ");
			int len = arr.length;
			
			Session session = factory.getCurrentSession();
			String hql = "FROM Book";
			Query query = session.createQuery(hql);
			List<Book> list = query.list();
			String tmp = "";
			List<Book> listBook = new ArrayList<>();
			List<Book> list2 = new ArrayList<>();
			for (Book i : list) {
				tmp = i.getTenSach();
				if (tmp.contains(search)) {
					listBook.add(i);
					System.out.println( "khop 1 cum tu: " + i.getTenSach());
				}
				else{
					for (int j = 0; j <len; j++ ) {
						if(tmp.contains(arr[j])) {
							list2.add(i);
							System.out.println("khop 1 chu: " + i.getTenSach());
							break;
						}
					}
				}
			}
			
			String hql2= "FROM TacGia t WHERE t.tenTG=:search";
			Query query2 = session.createQuery(hql2);
			query2.setParameter("search", search);
			List<TacGia> tacgia = query2.list();
			if (tacgia.size() > 0){
				TacGia t = tacgia.get(0);
				model.addAttribute("tacgia", t);
				String hql3 = "FROM Book b WHERE b.tacgia.maTG=:maTG";
				Query query3 = session.createQuery(hql3);
				query3.setParameter("maTG", t.getMaTG());
				List<Book> list3 = query3.list();
				model.addAttribute("book3", list3);
			}
			model.addAttribute("search", search);
			model.addAttribute("book1", listBook);
			model.addAttribute("book2", list2);
//			System.out.println(tmp1);
			return "search";
		}
		else
			
			return home(model);
	}	
	
}
