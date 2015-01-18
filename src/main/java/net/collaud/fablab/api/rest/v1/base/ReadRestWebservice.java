package net.collaud.fablab.api.rest.v1.base;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import net.collaud.fablab.api.data.AbstractDataEO;
import net.collaud.fablab.api.rest.v1.model.BaseModel;
import net.collaud.fablab.api.rest.v1.model.DataModel;
import net.collaud.fablab.api.service.global.ReadService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 * @param <ENTITY> entity
 * @param <SERVICE> repository
 */
abstract public class ReadRestWebservice<ENTITY extends AbstractDataEO, SERVICE extends ReadService<ENTITY>> {
	
	@Setter
	@Getter
	private SERVICE service;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public BaseModel list() {
		DataModel<List<ENTITY>> model = new DataModel<>();
		model.setData(service.findAll());
		return model;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BaseModel get(@PathVariable("id") Integer id) {
		DataModel<ENTITY> model = new DataModel<>();
		model.setData(service.getById(id).orElse(null));
		return model;
	}

}
