package net.collaud.fablab.manager.rest.v1.criteria;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.collaud.fablab.manager.data.type.AuditObject;

/**
 *
 * @author Gaetan Collaud
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuditSearchCriteria extends PeriodSearchCriteria {
	private Long userId;
	private List<AuditObject> type;
	private String content;
	private int limit;
}
