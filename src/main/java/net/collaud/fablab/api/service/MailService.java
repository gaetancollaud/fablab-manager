package net.collaud.fablab.api.service;

import java.util.Map;
import net.collaud.fablab.api.service.impl.MailServiceImpl;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface MailService {

	String buildMailTemplate(MailServiceImpl.Template template, Map<String, Object> scopes);

	boolean sendMail(String from, String subject, String content, String... to);
}
