package net.collaud.fablab.manager.rest.v1.data;

import lombok.Getter;
import lombok.Setter;
import net.collaud.fablab.manager.data.MachineEO;
import net.collaud.fablab.manager.data.ReservationEO;
import net.collaud.fablab.manager.data.UserEO;

import java.util.Date;

/**
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 */
@Getter
@Setter
public class ChangePasswordTO {

	private String oldPassword;
	private String newPassword;
	private String repeatPassword;


}
