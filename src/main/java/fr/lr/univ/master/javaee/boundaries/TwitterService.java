package fr.lr.univ.master.javaee.boundaries;

import fr.lr.univ.master.javaee.entity.TwitterPost;
import fr.lr.univ.master.javaee.entity.TwitterUser;
import fr.lr.univ.master.javaee.services.AccountService;
import fr.lr.univ.master.javaee.services.RelationService;
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

    public void createAccount(TwitterUser user) {
        account.createAccount(user);
    }

    public void deleteAccount(Long id) {
        account.deleteAccount(id);
    }

    public void newFollower(Long id, TwitterUser follower) {
        relation.newFollower(id, follower);
    }

    public TwitterUser authenticate(String user, String password) {
        return TwitterUser.authenticate(user, password);
    }

    public void newTweet(Long id, TwitterPost post) {
        TwitterUser.findById(id).newTweet(post);
    }

    public Collection<TwitterPost> timeline(Long id) {
        return TwitterUser.findById(id).timeline();
    }

    public Collection<TwitterUser> follows(Long id) {
        return TwitterUser.follows(id);
    }

    public Collection<TwitterUser> followers(Long id) {
        return TwitterUser.followers(id);
    }

    public Collection<TwitterUser> searchUser(String query) {
        return TwitterUser.search(query);
    }

}
