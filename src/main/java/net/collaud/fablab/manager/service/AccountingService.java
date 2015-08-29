package net.collaud.fablab.manager.service;

import java.util.List;
import net.collaud.fablab.manager.data.virtual.HistoryEntry;
import net.collaud.fablab.manager.data.virtual.UserAccountEntry;
import net.collaud.fablab.manager.rest.v1.criteria.PeriodSearchCriteria;

/**
 *
 * @author Fabien Vuilleumier
 */
public interface AccountingService {

    List<HistoryEntry> getAccountingEntries(PeriodSearchCriteria search);
    List<UserAccountEntry> getAccountingEntries(Integer userId);
    
}
