package net.collaud.fablab.api.service.impl;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.api.service.MailService;
import net.collaud.fablab.api.web.SpringPropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

	public static final String PROP_HOST = "mail.smtp.host";
	public static final String PROP_PORT = "mail.smtp.port";
	public static final String PROP_USERNAME = "mail.smtp.username";
	public static final String PROP_PASSWORD = "mail.smtp.password";
	public static final String PROP_FROM = "mail.from";
	public static final String PROP_BASE_URL = "mail.baseurl";
	public static final String PROP_SUBJECT_PREFIX = "mail.subjectprefix";

	public static final String TEMPLATE_MODEL = "mail/model.html";
	
	public enum Template {

		SIGNUP("mail/signup.html"),
		FORGOT_PASSWORD("mail/forgotPassword.html");

		@Getter
		private final String path;

		private Template(String path) {
			this.path = path;
		}
	}

	@Autowired
	private SpringPropertiesUtils propertyUtils;

	@Override
	public boolean sendHTMLMail(String subject, Template template, Map<String, Object> scopes, String... to) {
		return sendMail(subject, buildMailTemplate(template, scopes), "text/html; charset=UTF-8", to);
	}
	
	@Override
	public boolean sendPlainTextMail(String subject, String content, String... to){
		return sendMail(subject, content, "text/plain; charset=UTF-8", to);
	}
	
	public boolean sendMail(String subject, String content, String contentType, String... to){
		try {
			//add prefix to mail subject
			subject = propertyUtils.getProperty(PROP_SUBJECT_PREFIX).orElse("") + subject;

			Properties props = getMailProperties();
			Session session = Session.getInstance(props, null);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(propertyUtils.getProperty(PROP_FROM).orElse("no-reply@no-domain.com")));
			msg.setRecipients(Message.RecipientType.TO, getRecipients(to));
			msg.setSubject(subject);
			msg.setContent(content, contentType);
			msg.setSentDate(new Date());
			Transport t = session.getTransport("smtp");
			t.connect(
					propertyUtils.getProperty(PROP_HOST).orElse("localhost"),
					propertyUtils.getProperty(PROP_USERNAME).orElse(""),
					propertyUtils.getProperty(PROP_PASSWORD).orElse("")
			);
			t.sendMessage(msg, msg.getAllRecipients());
			t.close();
			return true;
		} catch (MessagingException ex) {
			log.error("Cannot send message");
		}
		return false;
	}
	
	private String buildMailTemplate(Template template, Map<String, Object> scopes) {
		if(scopes==null){
			scopes = new HashMap<>();
		}
		addEmailConstantsToScope(scopes);
		String model = buildMailTemplate(TEMPLATE_MODEL, scopes);
		return model.replace("[[[content]]]", buildMailTemplate(template.getPath(), scopes));
	}
	
	private String buildMailTemplate(String path, Map<String, Object> scopes){
		URL url = Thread.currentThread().getContextClassLoader().getResource(path);
		try {
			InputStream is = url.openStream();
			StringWriter writer = new StringWriter();
			MustacheFactory mf = new DefaultMustacheFactory();
			Mustache mustache = mf.compile(new InputStreamReader(is, StandardCharsets.UTF_8), path);
			mustache.execute(writer, scopes);
			writer.flush();
			return writer.toString();
		} catch (NullPointerException | IOException ex) {
			log.error("Cannot open template file " + path, ex);
			return "";
		}
	}

	private String newLineToBr(String in) {
		return in.replaceAll("(\r\n|\n)", "<br />");
	}

	private void addEmailConstantsToScope(Map<String, Object> scopes) {
		scopes.put("baseUrl", propertyUtils.getProperty(PROP_BASE_URL).orElse("http://perdu.com"));
	}

	private Address[] getRecipients(String[] to) throws AddressException {
		List<Address> address = new ArrayList<>(to.length);
		for (String addr : to) {
			address.addAll(Arrays.asList(InternetAddress.parse(addr, false)));
		}
		return address.toArray(new Address[0]);
	}

	private Properties getMailProperties() {
		Properties props = new Properties();
		props.put(PROP_HOST, propertyUtils.getProperty(PROP_HOST).orElse("localhost"));
		props.put(PROP_PORT, propertyUtils.getProperty(PROP_HOST).orElse("25"));
		props.put(PROP_USERNAME, propertyUtils.getProperty(PROP_USERNAME).orElse(""));
		props.put(PROP_PASSWORD, propertyUtils.getProperty(PROP_PASSWORD).orElse(""));
		return props;
	}
}
