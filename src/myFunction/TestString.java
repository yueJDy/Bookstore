package myFunction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class TestString {
	public ArrayList<String> badword = new ArrayList<>();
	
	public TestString() {
		init();
	}
	
	public void init() {
		this.badword.add("<script>");
		this.badword.add("</script>");
		this.badword.add("<");
		this.badword.add("&lt;");
		this.badword.add("&#60;");
		this.badword.add(">");
		this.badword.add("&gt;");
		this.badword.add("&#62;");
	}
	
	public int test_email(String email) {
		String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";// cấu trúc email thông thường
		if (email.length() == 0) {
			return 1;
		}
		if (!email.matches(EMAIL_PATTERN)) {
			return 2;
		}
		return 0;
	}
	
	public int test_pass(String password) {
		String pattern = "^[a-zA-Z0-9!#$_,?+*@]{3,200}$";
		if (password.length() == 0)
			return 1;
		if (password.length() < 3)
			return 2;
		if (!password.matches(pattern)) {
			return 3;
		}
		return 0;
	}
	
	public int test_name(String name) {
		String pattern = "^[a-zA-Z0-9!#_\\-\\.\"\'\\s\\p{L}]{1,50}$";
		if (name.length() == 0)
			return 1;
		if (!name.matches(pattern))
			return 2;
		return 0;
	}
	
	public int test_BookName(String name) {
		String pattern = "^[a-zA-Z0-9=!#,_&\\-\\.\"\'\\s\\p{L}]{1,50}$";
		if (name.length() == 0)
			return 1;
		if (!name.matches(pattern))
			return 2;
		return 0;
	}
	
	public int test_add(String add) {
		String pattern = "^[a-zA-Z0-9 ! # $ _ \\- , ? + / \\s\\p{L}]{3,100}$";
		if (add.length() == 0)
			return 1;
		if (!add.matches(pattern))
			return 2;
		return 0;
	}
	
	public int test_sdt(String sdt){
		String pattern = "^[0-9]{10}$";
		if (sdt.length() == 0)
			return 1;
		if (!sdt.matches(pattern))
			return 2;
		return 0;
	}
	
	public int test_str(String str) {
		String pattern = "^[a-zA-Z0-9 ! # @ $ _ \\- , ? + \" ' *  . : / \\s \\p{L}]{1,2000}$";
		if (str.length() == 0)
			return 1;
		if (!str.matches(pattern))
			return 2;
		return 0;
	}
	
	public int test_number(String number) {
		String pattern = "^[0-9]{1,10}$";
		if (number.length() == 0)
			return 1;
		if (!number.matches(pattern))
			return 2;
		return 0;
	}
	
	public int test_gia(String gia) {
		String pattern = "^[0-9]{4,10}$";
		if (gia.length() == 0)
			return 1;
		if (!gia.matches(pattern))
			return 2;
		return 0;
	}
	
	//Loại bỏ các chữ/ ký tự nguy hiểm
	public String filter_input(String str) {
		String tmp1 = "";
		String tmp2 = "";
		String ret = str;
		int i = 0;
		int index = -1;
		for (int j = 0; j < this.badword.size(); j++) {
			do {
				System.out.println(this.badword.get(j));
				index = str.indexOf(this.badword.get(j));
				System.out.println("Str: " + str);
				System.out.println("index: " + index);
				if(index >=0 ) {
					tmp1 = str.substring(0, index);
					tmp2 = str.substring(index + this.badword.get(j).length());
					ret = tmp1.trim() + " " + tmp2.trim();
					str = ret;
				}
			}while(index != -1);
		}
		return ret;
	}
	
	
}
