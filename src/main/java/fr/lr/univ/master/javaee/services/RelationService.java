package fr.lr.univ.master.javaee.services;

import fr.lr.univ.master.javaee.entity.TwitterUser;
import fr.lr.univ.master.javaee.event.NewFollowerEvent;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 *
 * @author mathieuancelin
 */
public class RelationService {

    @Inject
    private Event<NewFollowerEvent> followerEvt;

    public void newFollower(Long id, TwitterUser follower) {
        follower.save();
        TwitterUser.findById(id).getFollowers().add(follower);
        followerEvt.fire(new NewFollowerEvent(TwitterUser.findById(id), follower));
    }
    
}
