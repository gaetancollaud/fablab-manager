package net.collaud.fablab.api.rest.v1.base;

import java.util.List;
import lombok.Setter;
import net.collaud.fablab.api.data.AbstractDataEO;
import net.collaud.fablab.api.rest.v1.model.BaseModel;
import net.collaud.fablab.api.rest.v1.model.DataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 * @param <T> entity
 * @param <REP> repository
 */
abstract public class ReadRestWebservice<T extends AbstractDataEO, REP extends JpaRepository<T, Integer>> {

	
	@Setter
	private REP repository;

	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public BaseModel list() {
		DataModel<List<T>> model = new DataModel<>();
		model.setData(repository.findAll());
		return model;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BaseModel get(@PathVariable("id") Integer id) {
		DataModel<T> model = new DataModel<>();
		model.setData(repository.findOne(id));
		return model;
	}

}
