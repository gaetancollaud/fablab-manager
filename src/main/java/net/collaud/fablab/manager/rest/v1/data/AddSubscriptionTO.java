package net.collaud.fablab.manager.rest.v1.data;

import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Gaetan Collaud
 */
@NoArgsConstructor
@Getter
@Setter
public class AddSubscriptionTO {
	private Integer userId;
	private Date startDate;
	private Date paymentDate;
	private String comment;
	private boolean paidDirecly;
}
