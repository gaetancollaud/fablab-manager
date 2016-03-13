package net.collaud.fablab.manager.dao.projection;

import com.mysema.query.types.Expression;
import net.collaud.fablab.manager.data.MachineEO;
import net.collaud.fablab.manager.data.QMachineEO;

/**
 *
 * @author Gaetan Collaud
 */
public class MachineProjection {

	public static final Expression<MachineEO> projectionWithoutContent(QMachineEO machine) {
		return QMachineEO.create(
				machine.id,
				machine.name,
				machine.introduction,
				machine.image_url,
				machine.machineType);
	}

	public static final Expression<MachineEO> projectionWithContent(QMachineEO machine) {
		return QMachineEO.create(
				machine.id,
				machine.name,
				machine.introduction,
				machine.description,
				machine.image_url,
				machine.machineType);
	}
	

}
