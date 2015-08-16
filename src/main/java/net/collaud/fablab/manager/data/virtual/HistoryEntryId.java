package net.collaud.fablab.manager.data.virtual;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import net.collaud.fablab.manager.data.type.HistoryEntryType;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Getter
@Setter
@EqualsAndHashCode(of = {"id", "type"})
public class HistoryEntryId {
	
	private int id;
	private HistoryEntryType type;
	

}
