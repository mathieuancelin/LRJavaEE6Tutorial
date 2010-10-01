package fr.lr.univ.master.javaee.mock;

import fr.lr.univ.master.javaee.boundaries.TwitterService;
import fr.lr.univ.master.javaee.entity.TwitterPost;
import fr.lr.univ.master.javaee.entity.TwitterUser;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author Mathieu ANCELIN
 */
@Startup
@Singleton
public class MockerBean {

    @EJB
    private TwitterService service;

    @PostConstruct
    public void mock() {
        TwitterUser account = new TwitterUser();
        account.setEmail("mathieu.ancelin@gmail.com");
        account.setName("ANCELIN");
        account.setPassword("password");
        account.setSurname("Mathieu");
        account.setUsername("mathieu.ancelin");
        account.setWebsite("www.mathieuancelin.com");

        service.createAccount(account);

        TwitterUser account2 = new TwitterUser();
        account2.setEmail("kevin.pollet@gmail.com");
        account2.setName("POLLET");
        account2.setPassword("password");
        account2.setSurname("Kevin");
        account2.setUsername("kevin.pollet");
        account2.setWebsite("www.kevinpollet.com");

        service.createAccount(account2);

        service.newFollower(account.getId(), account);
        service.newFollower(account.getId(), account2);
        service.newFollower(account2.getId(), account2);
        service.newFollower(account2.getId(), account);

        TwitterPost post = new TwitterPost();
        post.setTweetDate(new Date().getTime());
        post.setUser(account);
        post.setBody("hello twitter :)");

        service.newTweet(account.getId(), post);

        TwitterPost post2 = new TwitterPost();
        post2.setTweetDate(new Date().getTime());
        post2.setUser(account2);
        post2.setBody("hello 2 twitter :)");

        service.newTweet(account2.getId(), post2);
    }
}
