package net.collaud.fablab.api.service;

import net.collaud.fablab.api.service.impl.*;
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
import net.collaud.fablab.api.dao.PriceRepository;
import net.collaud.fablab.api.data.PriceMachineEO;
import net.collaud.fablab.api.data.PriceRevisionEO;
import net.collaud.fablab.api.data.PriceRevisionEO_;
import net.collaud.fablab.api.service.MailService;
import net.collaud.fablab.api.web.SpringPropertiesUtils;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Service
@Slf4j
@Transactional
public class PriceServiceImpl implements PriceService {

	@Autowired
	private PriceRepository priceRepository;

	@Override
	public List<PriceMachineEO> getAllCurrentMachinePrices() {
		final List<PriceMachineEO> list = getLastPriceRevision().getPriceMachineList();
		Hibernate.initialize(list);
		return list;
	}

	@Override
	public PriceRevisionEO getLastPriceRevision() {
		final Page<PriceRevisionEO> all = priceRepository.findAll(new PageRequest(0, 1, Sort.Direction.DESC, PriceRevisionEO_.dateRevision.getName()));
		return all.getSize() > 0 ? all.getContent().get(0) : null;
	}

}
