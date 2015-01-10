package net.collaud.fablab.api.rest.v1.data;

import net.collaud.fablab.api.data.MachineEO;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
public class MachineTO extends AbstractTO<MachineEO, MachineTO>{

	private Integer machineId;
	
	private String name;
	
	//FIXME add machine type
	//private MachineTypeEO machineType;
	
	@Override
	public MachineEO fromTO(MachineTO to) {
		throw new RuntimeException("Not implemented yet");
	}

	@Override
	public MachineTO fromEO(MachineEO eo) {
		setMachineId(eo.getMachineId());
		setName(eo.getName());
		return this;
	}

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
