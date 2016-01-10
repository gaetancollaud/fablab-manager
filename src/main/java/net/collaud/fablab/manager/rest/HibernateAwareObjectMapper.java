package net.collaud.fablab.manager.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
//import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public class HibernateAwareObjectMapper extends ObjectMapper{
	 public HibernateAwareObjectMapper() {
//        registerModule(new Hibernate4Module());
        registerModule(new Hibernate5Module());
    }
}
