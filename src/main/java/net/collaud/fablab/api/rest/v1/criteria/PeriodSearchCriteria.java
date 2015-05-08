package net.collaud.fablab.api.rest.v1.criteria;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Gaetan Collaud
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PeriodSearchCriteria {
	
	private Date dateFrom;
	private Date dateTo;
	
	public boolean isOneDateNull(){
		return dateFrom==null || dateTo==null;
	}
}
