package fr.lr.univ.master.javaee.services;

import fr.lr.univ.master.javaee.entity.TwitterUser;
import fr.lr.univ.master.javaee.event.NewFollowerEvent;
import java.util.Collection;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mathieuancelin
 */
public class RelationService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private Event<NewFollowerEvent> followerEvt;

    public void newFollower(Long id, TwitterUser follower) {
        em.persist(follower);
        em.find(TwitterUser.class, id).getFollowers().add(follower);
        followerEvt.fire(new NewFollowerEvent(em.find(TwitterUser.class, id), follower));
    }
    
    public Collection<TwitterUser> follows(Long id) {
        return null;
    }

    public Collection<TwitterUser> followers(Long id) {
        return em.find(TwitterUser.class, id).getFollowers();
    }
}
