package net.collaud.fablab.manager.rest.v1.base;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.manager.data.AbstractDataEO;
import net.collaud.fablab.manager.exceptions.FablabException;
import net.collaud.fablab.manager.service.global.ReadWriteService;
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
	public ENTITY create(@RequestBody ENTITY entity) {
		LOG.debug("create entity " + entity);
		return service.save(entity);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public ENTITY edit(@PathVariable Long id, @RequestBody ENTITY entity) {
		LOG.debug("edit entity " + entity);
		return service.save(entity);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public void remove(@PathVariable Long id) throws FablabException {
		LOG.debug("delete entity with id " + id);
		service.remove(id);
	}

}
