package fr.lr.univ.master.javaee.services;

import fr.lr.univ.master.javaee.entity.TwitterPost;
import fr.lr.univ.master.javaee.entity.TwitterUser;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mathieuancelin
 */
public class TimelineService {

    @PersistenceContext
    private EntityManager em;

    public void newTweet(Long id, TwitterPost post) {
        em.persist(post);
        em.find(TwitterUser.class, id).getPost().add(post);
    }

    public Collection<TwitterPost> timeline(Long id) {
        return em.find(TwitterUser.class, id).getPost();
    }

}
