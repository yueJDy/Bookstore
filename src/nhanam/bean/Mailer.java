package nhanam.bean;

import java.io.File;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("mailer")
public class Mailer {
	@Autowired
	JavaMailSender mailer;
	
	@Autowired
	ServletContext context;
	
	public void send( String to, String subject, String body) {

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
