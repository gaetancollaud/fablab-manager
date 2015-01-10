package net.collaud.fablab.api.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public class HibernateAwareObjectMapper extends ObjectMapper{
	 public HibernateAwareObjectMapper() {
        registerModule(new Hibernate4Module());
    }
}
