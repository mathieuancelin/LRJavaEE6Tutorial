package fr.lr.univ.master.javaee.event;

import fr.lr.univ.master.javaee.entity.TwitterUser;

/**
 *
 * @author mathieuancelin
 */
public class UserEvent {

    private final TwitterUser user;

    public UserEvent(TwitterUser user) {
        this.user = user;
    }

    public TwitterUser getUser() {
        return user;
    }
}
