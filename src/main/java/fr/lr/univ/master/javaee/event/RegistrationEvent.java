package fr.lr.univ.master.javaee.event;

import fr.lr.univ.master.javaee.entity.TwitterUser;

/**
 *
 * @author mathieuancelin
 */
public class RegistrationEvent extends UserEvent {

    public RegistrationEvent(TwitterUser user) {
        super(user);
    }
}
