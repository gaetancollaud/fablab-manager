package net.collaud.fablab.api.rest.v1.base;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.api.data.AbstractDataEO;
import net.collaud.fablab.api.exceptions.FablabException;
import net.collaud.fablab.api.service.global.ReadWriteService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 * @param <ENTITY> entity
 * @param <SERVICE> repository
 */
@Slf4j
abstract public class ReadWriteRestWebservice<ENTITY extends AbstractDataEO, SERVICE extends ReadWriteService<ENTITY>> extends ReadRestWebservice {

	@Getter
	private SERVICE service;

	public void setService(SERVICE service) {
		super.setService(service);
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.POST)
	public void create(@RequestBody ENTITY entity) {
		log.debug("create entity " + entity);
		service.save(entity);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public void edit(@PathVariable Integer id, @RequestBody ENTITY entity) {
		log.debug("edit entity " + entity);
		service.save(entity);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void remove(@PathVariable Integer id) throws FablabException {
		log.debug("delete entity with id " + id);
		service.remove(id);
	}

}
