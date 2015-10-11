package net.collaud.fablab.manager.rest.v1;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import net.collaud.fablab.manager.annotation.JavascriptAPIConstant;
import net.collaud.fablab.manager.data.virtual.HistoryEntry;
import net.collaud.fablab.manager.data.virtual.HistoryEntryAccounts;
import net.collaud.fablab.manager.export.CsvExporter;
import net.collaud.fablab.manager.export.custom.CsvExporterAccounting;
import net.collaud.fablab.manager.rest.v1.criteria.PeriodSearchCriteria;
import net.collaud.fablab.manager.rest.v1.model.BaseModel;
import net.collaud.fablab.manager.rest.v1.model.DataModel;
import net.collaud.fablab.manager.service.AccountingService;
import net.collaud.fablab.manager.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud
 * <gaetancollaud@gmail.com>
 */
@RestController()
@RequestMapping("/v1/accounting")
@JavascriptAPIConstant("ACCOUNTING_API")
public class AccoutingWS {

    @Autowired
    private AccountingService accountingService;

    @Autowired
    private PaymentService paymentService;

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public BaseModel search(@Validated @RequestBody PeriodSearchCriteria search) {
        return new DataModel(accountingService.getAccountingEntries(search));
    }

    @RequestMapping(value = "byUser", method = RequestMethod.GET)
    public BaseModel byUser(@RequestParam("userId") Integer userId) {
        return new DataModel(accountingService.getAccountingEntries(userId));
    }
	@RequestMapping(value = "export", method = RequestMethod.GET, produces = "text/csv")
	@ResponseBody
	public String export(@RequestParam("dateFrom") Long dateFrom,@RequestParam("dateTo") Long dateTo, HttpServletResponse response) {
        response.setContentType("text/csv");
		Date from = new Date(dateFrom*1000);
		Date to = new Date(dateTo*1000);
		final List<HistoryEntry> list = paymentService.getPaymentEntries(new PeriodSearchCriteria(from, to));
		if (list.isEmpty()) {
			return "";
		}
		CsvExporter exporter = new CsvExporterAccounting();
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s.csv\"",exporter.getFileName()));
		exporter.writeHeader();
		list.forEach(l -> exporter.writeRow(l));
		return exporter.toString();
	}

    @RequestMapping(value = "getAccounts", method = RequestMethod.GET)
    public List<String> getAccounts() {
        return HistoryEntryAccounts.names();
    }

    @RequestMapping(value = "getName", method = RequestMethod.GET)
    public String getAccounts(@RequestParam("name") String name) {
        return HistoryEntryAccounts.getName(name);
    }

}
