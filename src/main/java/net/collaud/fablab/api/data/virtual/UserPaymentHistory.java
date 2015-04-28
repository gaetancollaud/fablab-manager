package net.collaud.fablab.api.data.virtual;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Gaetan Collaud
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserPaymentHistory {

	private List<HistoryEntry> history;
	private double balance;
}
