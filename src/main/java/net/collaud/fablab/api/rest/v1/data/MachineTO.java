package net.collaud.fablab.api.rest.v1.data;

import net.collaud.fablab.api.data.MachineTypeEO;

/**
 *
 * @author Gaétan
 */
public class MachineTO extends AbstractTO{
	private Integer machineId;
	
	private String name;
	
	//FIXME add machine type
	//private MachineTypeEO machineType;

	public Integer getMachineId() {
		return machineId;
	}

	public void setMachineId(Integer machineId) {
		this.machineId = machineId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
