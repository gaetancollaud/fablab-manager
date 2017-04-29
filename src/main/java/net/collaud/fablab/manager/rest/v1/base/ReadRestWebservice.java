package net.collaud.fablab.manager.rest.v1.base;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;
import net.collaud.fablab.manager.data.AbstractDataEO;
import net.collaud.fablab.manager.export.CsvExporter;
import net.collaud.fablab.manager.rest.v1.model.BaseModel;
import net.collaud.fablab.manager.rest.v1.model.DataModel;
import net.collaud.fablab.manager.service.global.ReadService;
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

	@RequestMapping(value = "/export", method = RequestMethod.GET, produces = "text/csv")
	@ResponseBody
	public String export(HttpServletResponse response) {
        response.setContentType("text/csv");
		
		List<ENTITY> list = service.findAll();
		if (list.isEmpty()) {
			return "";
		}
		CsvExporter exporter = new CsvExporter<>(list.get(0).getClass());
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s.csv\"",exporter.getFileName()));
		exporter.writeHeader();
		list.forEach(l -> exporter.writeRow(l));
		return exporter.toString();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public BaseModel get(@PathVariable("id") Long id) {
		DataModel<ENTITY> model = new DataModel<>();
		model.setData(service.getById(id).orElse(null));
		return model;
	}

}
