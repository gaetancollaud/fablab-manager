package net.collaud.fablab.manager.rest.v1.data;

import lombok.Data;
import net.collaud.fablab.manager.data.type.DoorAction;

@Data
public class DoorEventTO {
	private DoorAction action;
	private String rfid;
	private String name;

}
