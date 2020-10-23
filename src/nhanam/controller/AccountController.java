package nhanam.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import myFunction.RandomFunc;
import myFunction.TestString;
import myFunction.VerifyUtils;
import nhanam.bean.Mailer;
import nhanam.bean.UserInfo;
import nhanam.bean.User_Log;
import nhanam.bean.User_Res;
import nhanam.entity.DonHang;
import nhanam.entity.User;

@Transactional
@Controller
public class AccountController {
	private TestString testinput = new TestString();
	
	@Autowired
	SessionFactory factory;
	
	@RequestMapping(value = "dang-ky", method = RequestMethod.GET)
	public String dangky(HttpSession session, ModelMap model) {
		model.addAttribute("user", new User_Res());
		return "accounts/regist";
	}
	
	@RequestMapping(value = "dang-ky", method = RequestMethod.POST)
	public String dangky1(@ModelAttribute("user") User_Res user,
			BindingResult errors) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		Boolean test = true;
		int test_email = testinput.test_email(user.getEmail());
		
		if (test_email != 0) {
			test = false;
			switch(test_email) {
				case 1:{
					errors.rejectValue("email", "user", " *Vui lòng nhập email.");
					break;
				}
				case 2:{
					errors.rejectValue("email", "user", " *Hãy nhập đúng định dạng mail!");
					break;
				}
			}
		}
		else {
			String hql = "FROM User u WHERE u.email=:email";
			Query query = session.createQuery(hql);
			query.setParameter("email", user.getEmail());
			List<User> list = query.list();
			if (list.size() > 0) {
				test = false;
				errors.rejectValue("email", "user", " *Email đã được sử dụng. Vui lòng sử dụng email khác!");
			}
		}
		
		int test_pass = testinput.test_pass(user.getPassword());
		if (test_pass != 0) {
			test = false;
			switch(test_pass) {
				case 1:{
					errors.rejectValue("password","user", " *Vui lòng nhập mật khẩu");
					break;
				}
				case 2:{
					errors.rejectValue("password","user", " *Mật khẩu có độ dài tối thiếu 8 ký tự");
					break;
				}
				case 3:{
					errors.rejectValue("password","user", " *Mật khẩu chỉ bao gồm các ký tự: a-z, A-Z, 0-9, ! # $ _ , ? + * @ và không chứa khoảng trắng");
					break;
				}
			}
		}
			
		int test_name = testinput.test_name(user.getName());
		if(test_name != 0) {
			test = false;
			switch(test_name) {
				case 1:{
					errors.rejectValue("name", "user", " *Vui lòng nhập tên");
					break;
				}
				case 2:{
					errors.rejectValue("name", "user", " *Tên chỉ bao gồm các tự: a-z, A-Z, 0-9 ! # _ - . và khoảng trắng");
					break;
				}
			}
		}
		if (test) {
			User user_new = new User();
			user_new.setEmail(user.getEmail());
			user_new.setName(user.getName());
			user_new.setPassword(user.getPassword());
			user_new.setIsAdmin(false);
			user_new.setVerify(false);
			user_new.setTrangthai(false);
			try {
				session.save(user_new);
				t.commit();
				System.out.println("Thêm tài khoản thành công!");
			}
			catch(Exception ex) {
				t.rollback();
				System.out.println("Thêm tài khoản thất bại!");
			}finally {
				session.close();
			}
			return "redirect:dang-nhap.htm";
		}
		else {
			return "accounts/regist";
		}
		
	}
	
	@RequestMapping(value = "dang-nhap", method = RequestMethod.GET)
	public String dangnhap(ModelMap model) {
		model.addAttribute("user", new User_Log());
		model.addAttribute("link", "dang-nhap.htm");
		return "accounts/login";
	}
	
	@RequestMapping(value = "dang-nhap", params="message_login")
	public String dangnhap1(ModelMap model, @RequestParam("sach") String masach) {
		model.addAttribute("message_login", " *Vui lòng đăng nhập để tiếp tục!");
		model.addAttribute("user", new User_Log());
		model.addAttribute("link", "dang-nhap.htm?sach=" + masach);
		return "accounts/login";
	}
	
	@RequestMapping(value = "dang-nhap", method = RequestMethod.POST)
	public String dangnhap1(@ModelAttribute("user") User_Log user, @ModelAttribute("g-recaptcha-response") String gRecaptcha, HttpSession session,
				ModelMap model, @RequestParam(value = "sach", defaultValue = " ") String masach, BindingResult errors) {
		boolean valid;
		String errorString = "";
		Boolean test = true;
		int test_email = testinput.test_email(user.getEmail());
		if (test_email != 0) {
			test = false;
			switch(test_email) {
				case 1:{
					errors.rejectValue("email", "user", " *Vui lòng nhập email.");
					break;
				}
				case 2:{
					errors.rejectValue("email", "user", " *Hãy nhập đúng định dạng mail!");
					break;
				}
			}
			
		}
		
		int test_pass = testinput.test_pass(user.getPassword());
		if (test_pass != 0) {
			test = false;
			switch(test_pass) {
				case 1:{
					errors.rejectValue("password","user", " *Vui lòng nhập mật khẩu");
					break;
				}
				case 2:{
					errors.rejectValue("password","user", " *Mật khẩu có độ dài tối thiếu 8 ký tự");
					break;
				}
				case 3:{
					errors.rejectValue("password","user", " *Mật khẩu chỉ bao gồm các ký tự: a-z, A-Z, 0-9, ! # $ _ , ? + * @ và không chứa khoảng trắng");
					break;
				}
			}
		}	
		if (test) {
			Session s = factory.getCurrentSession();
			String hql = "FROM User u WHERE u.email=:email AND u.password=:password";
			Query query = s.createQuery(hql);
			query.setParameter("email", user.getEmail());
			query.setParameter("password", user.getPassword());
			List<User> list = query.list();
			if (list.size() != 0) {
				System.out.println("Recaptcha: " + gRecaptcha);
				valid = VerifyUtils.verify(gRecaptcha);
				if(!valid) {
					model.addAttribute("user", user);
					model.addAttribute("check_fail", " *Vui lòng thực hiện xác thực để tiếp tục.");
					return "accounts/login";
				}
				UserInfo users = new UserInfo();
				User userlogin = list.get(0);
				users.setUsername(userlogin.getName());
				users.setEmail(user.getEmail());
				users.setPassword(user.getPassword());
				users.setIsAdmin(userlogin.getIsAdmin());
				users.setVerify(userlogin.getVerify());
				session.setAttribute("user", users);
				
				
				if (userlogin.getVerify()) {
					if ( masach.length() > 1) {
						String linktmp = (String) session.getAttribute("tmphtm");
						session.removeAttribute("tmphtm");
						return "redirect:"+linktmp;
					}
					return "redirect:index.htm";
				}
				else {
					return "redirect:/user/xac-thuc-tai-khoan.htm";
				}
			}
			else if (list.size() == 0) {
				model.addAttribute("log_fail", " *Tên đăng nhập hoặc mật khẩu không đúng.");
				model.addAttribute("user", new User_Log());
				return "accounts/login";
			}
		}
		else {
//			model.addAttribute("user", new User_Log());
			return "accounts/login";
		}
		return "home";
	}
	
	@RequestMapping("quen-mat-khau")
	public String forgetPass(@RequestParam("email_forget") String email,
			ModelMap model) {
		Session session = factory.getCurrentSession();
		
		System.out.println(email);
		Boolean test = true;
		int test_email = testinput.test_email(email);
		if (test_email != 0) {
			test = false;
			switch(test_email) {
				case 1:{
					model.addAttribute("message", "*Vui lòng nhập email.");
					break;
				}
				case 2:{
					model.addAttribute("message", "*Hãy nhập đúng định dạng mail!");
					break;
				}
			}
		}
		if (test) {
//			RandomString random = new RandomString();
//			String pass_new = random.randomAll(10);
//			Session session = factory.openSession();
//			Transaction t = session.beginTransaction();
			
			String hql = "FROM User u WHERE u.email=:email";
			Query query = session.createQuery(hql);
			query.setParameter("email", email);
			List<User> list = query.list();
			if (list.size() != 0) {
				User user = list.get(0);
				String body = "Xin chao " + user.getName() + "\n"
						+ "Hệ thống nhận được yêu cầu cấp lại mật khẩu thông qua địa chỉ email " + email + "\n"
						+ "Mật khẩu của bạn là: " + user.getPassword() + "\n"
						+ "Chúc bạn sức khoẻ thành công!\n"
						+ "Ghi chú: Thông báo này được gửi tự động từ hệ thống. Để đảm bảo an toàn bạn cần đổi mật khẩu sau khi đăng nhập thành công.";
			
				System.out.println(body);
				
				String subject = "Thông báo cấp lại mật khẩu đăng nhập Yuean's Bookstore";
				try {
					sendMail(user.getEmail(), subject, body);
					System.out.println("Gửi mail thành công");
				}
				catch(Exception ex){
					System.out.println("Gửi mail thất bại");
				}
			}
			else {
				model.addAttribute("message", " *Email chưa được đăng ký!");
			}
		}
		model.addAttribute("user", new User_Log());
		return "accounts/login";
	}
	
	@RequestMapping(value = "user/xac-thuc-tai-khoan", method = RequestMethod.GET)
	public String verify(HttpSession session){
		RandomFunc random = new RandomFunc();
		int num = random.randomNumber(100000, 999999);
		session.setAttribute("verifyNum", Integer.toString(num) );
		UserInfo user = (UserInfo) session.getAttribute("user");
		String subject = "Yêu cầu xác nhận tài khoản từ Yuean's Bookstore";
		String body = user.getUsername() + ", để hoàn thành việc đăng ký thành viên, bạn phải xác nhận tài khoản với mã dưới đây.\n"
					+ "Mã xác thực: " + num
					+ " Cám ơn bạn đã đăng ký.\r\n";
		sendMail(user.getEmail(), subject, body);
		return "accounts/verify";
	}
	
	@RequestMapping(value = "user/xac-thuc-tai-khoan", method = RequestMethod.POST)
	public String submitVerify(HttpSession session, @RequestParam("verify") String number, ModelMap model) {
		String verifyNum = (String) session.getAttribute("verifyNum");
		String pattern = "[0-9]{6}";
		if (number.matches(pattern)) {
			if(number.equals(verifyNum)) {
				session.removeAttribute("verifyNum");
				Session s = factory.openSession();
				Transaction t = s.beginTransaction();
				UserInfo userinfo = (UserInfo) session.getAttribute("user");
				User user = (User) s.get(User.class, userinfo.getEmail());
				user.setVerify(true);
				userinfo.setVerify(true);
				session.setAttribute("user", userinfo);
				try {
					s.update(user);
					t.commit();
					System.out.println("Cập nhật thành công!");
				}
				catch(Exception ex) {
					t.rollback();
					System.out.println("Cập nhật thất bại!");
				}
				finally {
					s.close();
				}
				return "redirect:index.htm";
			}
			model.addAttribute("verify_fail", " *Mã xác thực không chính xác.");
		}
		model.addAttribute("verify_fail", " *Mã xác thực chỉ gồm 6 chữ số");
		return "accounts/verify";
	}
	
	@RequestMapping("dang-xuat")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:index.htm";
	}
	
	@RequestMapping("gio-hang/lich-su-giao-dich")
	public String giaoDich(HttpSession ss, ModelMap model) {
		UserInfo user = (UserInfo) ss.getAttribute("user");
		Session session = factory.getCurrentSession();
		String hql = "FROM DonHang d WHERE d.user.email=:email";
		Query query = session.createQuery(hql);
		query.setParameter("email", user.getEmail());
		List<DonHang> list = query.list();
		model.addAttribute("DH", list);
		return "accounts/giaodich";
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
