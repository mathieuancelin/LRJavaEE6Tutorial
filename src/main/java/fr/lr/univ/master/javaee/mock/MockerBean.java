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

        TwitterUser account3 = new TwitterUser();
        account3.setEmail("maurice.plutanfiard@gmail.com");
        account3.setName("PLUTANFIARD");
        account3.setPassword("password");
        account3.setSurname("Maurice");
        account3.setUsername("maurice.plutanfiard");
        account3.setWebsite("www.maurice.plutanfiard.com");

        service.createAccount(account3);

        service.newFollower(account.getId(), account);
        service.newFollower(account.getId(), account2);
        service.newFollower(account2.getId(), account2);
        service.newFollower(account2.getId(), account);
        service.newFollower(account3.getId(), account);

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

        TwitterPost post3 = new TwitterPost();
        post3.setTweetDate(new Date().getTime());
        post3.setUser(account2);
        post3.setBody("hello 3 twitter :)");

        service.newTweet(account3.getId(), post3);

        TwitterPost post4 = new TwitterPost();
        post4.setTweetDate(new Date().getTime());
        post4.setUser(account2);
        post4.setBody("hello 4 twitter :)");

        service.newTweet(account3.getId(), post4);
    }
}
