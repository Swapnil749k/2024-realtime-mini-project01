package in.swapnilk.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {
	@Autowired
	private JavaMailSender mailsender;
	
	public boolean sendEmail(String subject,String body,String to) {
		boolean isSent=false;
		try {
			MimeMessage mimemsg=mailsender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(mimemsg);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body,true);
			mailsender.send(mimemsg);
			isSent=true;
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
			return isSent;
	}
}
