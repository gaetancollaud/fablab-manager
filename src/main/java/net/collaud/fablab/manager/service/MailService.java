package net.collaud.fablab.manager.service;

import java.util.Map;

import net.collaud.fablab.manager.service.impl.MailServiceImpl;

/**
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public interface MailService {

	boolean sendPlainTextMail(String subject, String content, String... to);

	boolean sendHTMLMail(String subject, MailServiceImpl.Template template, Map<String, Object> scopes, String... to);
}
