package fr.lr.univ.master.javaee.event;

import fr.lr.univ.master.javaee.entity.TwitterUser;

/**
 *
 * @author mathieuancelin
 */
public class DeleteAccountEvent extends UserEvent {

    public DeleteAccountEvent(TwitterUser user) {
        super(user);
    }
}
