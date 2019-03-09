package net.collaud.fablab.manager.rest.v1;

import lombok.extern.slf4j.Slf4j;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.MachineEO;
import net.collaud.fablab.manager.data.PriceMachineEO;
import net.collaud.fablab.manager.rest.v1.base.ReadWriteRestWebservice;
import net.collaud.fablab.manager.rest.v1.model.BaseModel;
import net.collaud.fablab.manager.rest.v1.model.DataModel;
import net.collaud.fablab.manager.service.MachineService;
import net.collaud.fablab.manager.service.PriceMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@RestController()
@RequestMapping("/api/v1/price-machine")
@JavascriptAPIConstant("PRICE_MACHINE_API")
@Slf4j
public class PriceMachineWS {

	@Autowired
	private PriceMachineService priceMachineService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public BaseModel list() {
		DataModel<List<PriceMachineEO>> model = new DataModel<>();
		model.setData(priceMachineService.findAll());
		return model;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public PriceMachineEO edit(@PathVariable Long id, @RequestBody PriceMachineEO entity) {
		LOG.debug("edit entity " + entity);
		return priceMachineService.save(entity);
	}
}
