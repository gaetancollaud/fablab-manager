package net.collaud.fablab.manager.data.virtual;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import net.collaud.fablab.manager.data.UserEO;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@Getter
@EqualsAndHashCode(of = {"id"})
public class HistoryEntryUser {

    private final int id;
    private final String lastname;
    private final String firstname;

    public HistoryEntryUser(UserEO user) {
        if (user != null) {
            this.id = user.getId();
            this.lastname = user.getLastname();
            this.firstname = user.getFirstname();
        } else {
            this.id = 0;
            this.lastname = "inconnu";
            this.firstname = "inconnu";
        }
    }

}
