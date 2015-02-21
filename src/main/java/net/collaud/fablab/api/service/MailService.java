package net.collaud.fablab.api.service;

import java.util.Map;
import net.collaud.fablab.api.service.impl.MailServiceImpl;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface MailService {

	boolean sendMail(String subject, String content, String... to);
	boolean sendMail(String subject, MailServiceImpl.Template template, Map<String, Object> scopes, String... to);
}
