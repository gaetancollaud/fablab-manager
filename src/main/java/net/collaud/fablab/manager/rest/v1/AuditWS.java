package net.collaud.fablab.manager.rest.v1;

import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.rest.v1.criteria.AuditSearchCriteria;
import net.collaud.fablab.manager.rest.v1.model.BaseModel;
import net.collaud.fablab.manager.rest.v1.model.DataModel;
import net.collaud.fablab.manager.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
@RestController()
@RequestMapping("/v1/audit")
@JavascriptAPIConstant("AUDIT_API")
public class AuditWS {

	@Autowired
	private AuditService auditService;

	@RequestMapping(value = "search", method = RequestMethod.POST)
	public BaseModel search(@Validated @RequestBody AuditSearchCriteria search) {
		return new DataModel(auditService.search(search.getUserId(), search.getType(),
				search.getDateFrom(), search.getDateTo(), search.getContent(), search.getLimit()));
	}

//	@RequestMapping(value = "export", method = RequestMethod.GET, produces = "text/csv")
//	@ResponseBody
//	public String export(@RequestParam("dateFrom") Long dateFrom,@RequestParam("dateTo") Long dateTo, HttpServletResponse response) {
//        response.setContentType("text/csv");
//		Date from = new Date(dateFrom*1000);
//		Date to = new Date(dateTo*1000);
//		final List<HistoryEntry> list = paymentService.getPaymentEntries(new PeriodSearchCriteria(from, to));
//		if (list.isEmpty()) {
//			return "";
//		}
//		CsvExporter exporter = new CsvExporter<>(list.get(0).getClass());
//        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s.csv\"",exporter.getFileName()));
//		exporter.writeHeader();
//		list.forEach(l -> exporter.writeRow(l));
//		return exporter.toString();
//	}

}
