package fr.lr.univ.master.javaee.boundaries;

import fr.lr.univ.master.javaee.entity.TwitterPost;
import fr.lr.univ.master.javaee.entity.TwitterUser;
import fr.lr.univ.master.javaee.services.AccountService;
import fr.lr.univ.master.javaee.services.RelationService;
import fr.lr.univ.master.javaee.services.SearchService;
import fr.lr.univ.master.javaee.services.TimelineService;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;

/**
 *
 * @author mathieuancelin
 */
@Path("/service")
@Stateless
public class TwitterService {

    @Inject
    private AccountService account;

    @Inject
    private RelationService relation;

    @Inject
    private SearchService search;

    @Inject
    private TimelineService timeline;

    public void createAccount(TwitterUser user) {
        account.createAccount(user);
    }

    public void deleteAccount(Long id) {
        account.deleteAccount(id);
    }

    public TwitterUser authenticate(String user, String password) {
        return account.authenticate(user, password);
    }

    public void newTweet(Long id, TwitterPost post) {
        timeline.newTweet(id, post);
    }

    public void newFollower(Long id, TwitterUser follower) {
        relation.newFollower(id, follower);
    }

    public Collection<TwitterPost> timeline(Long id) {
        return timeline.timeline(id);
    }

    public Collection<TwitterUser> follows(Long id) {
        return relation.follows(id);
    }

    public Collection<TwitterUser> followers(Long id) {
        return relation.followers(id);
    }

    public Collection<TwitterUser> searchUser(String query) {
        return search.searchUser(query);
    }
}
