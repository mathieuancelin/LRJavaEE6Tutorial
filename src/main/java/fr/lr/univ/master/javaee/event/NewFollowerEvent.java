package fr.lr.univ.master.javaee.event;

import fr.lr.univ.master.javaee.entity.TwitterUser;

/**
 *
 * @author mathieuancelin
 */
public class NewFollowerEvent extends UserEvent {

    private final TwitterUser follower;

    public NewFollowerEvent(TwitterUser user, TwitterUser follower) {
        super(user);
        this.follower = follower;
    }

    public TwitterUser getFollower() {
        return follower;
    }
    
}
