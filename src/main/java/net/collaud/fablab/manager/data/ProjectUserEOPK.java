package net.collaud.fablab.manager.data;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectUserEOPK implements Serializable {

	private int projectId;

	private int userId;


}
