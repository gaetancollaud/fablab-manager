package net.collaud.fablab.manager.service.impl;

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

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.manager.service.MailService;
import net.collaud.fablab.manager.web.SpringPropertiesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

	@Value("${mail.smtp.host:localhost}")
	private String smtpHost;

	@Value("${mail.smtp.port:25}")
	private String smtpPort;

	@Value("${mail.smtp.username:}")
	private String smtpUser;

	@Value("${mail.smtp.password:}")
	private String smtpPassword;

	@Value("${mail.from:no-reply@@fablab.local}")
	private String mailFrom;

	@Value("${mail.baseurl:localhost/fablab-app}")
	private String baseUrl;

	@Value("${mail.subjectprefix:[FABLAB]}")
	private String mailSubjectPrefix;

	public static final String TEMPLATE_MODEL = "mail/model.html";

	@AllArgsConstructor(access = AccessLevel.PRIVATE)
	public enum Template {

		SIGNUP("mail/signup.html"),
		FORGOT_PASSWORD("mail/forgotPassword.html");

		@Getter
		private final String path;
	}

	@Autowired
	private SpringPropertiesUtils propertyUtils;

	@Override
	public boolean sendHTMLMail(String subject, Template template, Map<String, Object> scopes, String... to) {
		return sendMail(subject, buildMailTemplate(template, scopes), "text/html; charset=UTF-8", to);
	}

	@Override
	public boolean sendPlainTextMail(String subject, String content, String... to) {
		return sendMail(subject, content, "text/plain; charset=UTF-8", to);
	}

	public boolean sendMail(String subject, String content, String contentType, String... to) {
		try {
			//add prefix to mail subject
			subject = mailSubjectPrefix + subject;

			Properties props = getMailProperties();
			Session session = Session.getInstance(props, null);
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(mailFrom));
			msg.setRecipients(Message.RecipientType.TO, getRecipients(to));
			msg.setSubject(subject);
			msg.setContent(content, contentType);
			msg.setSentDate(new Date());
			Transport t = session.getTransport("smtp");
			t.connect(
					smtpHost,
					smtpUser,
					smtpPassword
			);
			t.sendMessage(msg, msg.getAllRecipients());
			t.close();
			return true;
		} catch (MessagingException ex) {
			LOG.error("Cannot send message", ex);
		}
		return false;
	}

	private String buildMailTemplate(Template template, Map<String, Object> scopes) {
		if (scopes == null) {
			scopes = new HashMap<>();
		}
		addEmailConstantsToScope(scopes);
		String model = buildMailTemplate(TEMPLATE_MODEL, scopes);
		return model.replace("[[[content]]]", buildMailTemplate(template.getPath(), scopes));
	}

	private String buildMailTemplate(String path, Map<String, Object> scopes) {
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
			LOG.error("Cannot open template file " + path, ex);
			return "";
		}
	}

	private String newLineToBr(String in) {
		return in.replaceAll("(\r\n|\n)", "<br />");
	}

	private void addEmailConstantsToScope(Map<String, Object> scopes) {
		scopes.put("baseUrl", baseUrl);
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
		props.put("mail.smtp.port", smtpHost);
		props.put("mail.smtp.port", smtpPort);
		props.put("mail.smtp.username", smtpUser);
		props.put("mail.smtp.password", smtpPassword);
		return props;
	}

}
