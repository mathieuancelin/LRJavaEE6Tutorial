package fr.lr.univ.master.javaee.services;

import fr.lr.univ.master.javaee.entity.TwitterPost;
import fr.lr.univ.master.javaee.entity.TwitterUser;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mathieuancelin
 */
public class TimelineService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private RelationService relation;

    public void newTweet(Long id, TwitterPost post) {
        em.persist(post);
        em.find(TwitterUser.class, id).getPosts().add(post);
    }

    public Collection<TwitterPost> timeline(Long id) {
        List<TwitterPost> posts = new ArrayList<TwitterPost>();
        for (TwitterUser u : relation.follows(id)) {
            posts.addAll(u.getPosts());
        }
        Collections.sort(posts, new Comparator<TwitterPost>() {

            @Override
            public int compare(TwitterPost o1, TwitterPost o2) {
                if (o1.getTweetDate() > o2.getTweetDate())
                    return -1;
                if (o1.getTweetDate() < o2.getTweetDate())
                    return 1;
                return 0;
            }

        });
        return posts;
    }

}
