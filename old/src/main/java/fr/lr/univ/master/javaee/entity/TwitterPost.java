package fr.lr.univ.master.javaee.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mathieuancelin
 */
@Entity
@XmlRootElement
@NamedQuery(name = "TwitterPost.all",
    query = "select p from TwitterPost p")
public class TwitterPost implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade=CascadeType.ALL)
    private TwitterUser user;

    private Long tweetDate;

    @Size(max=140, message="Your message is too long")
    private String body;

    private static EntityManager em;

    public static void setEntityManager(EntityManager em) {
        TwitterPost.em = em;
    }

    public void save() {
        em.persist(this);
    }

    public static void persist(TwitterPost post) {
        em.persist(post);
    }

    public void delete() {
        em.remove(this);
    }

    public static void remove(Long id) {
        em.remove(em.find(TwitterPost.class, id));
    }

    public static TwitterPost findById(Long id) {
        return em.find(TwitterPost.class, id);
    }

    public static Collection<TwitterPost> findAll() {
        return em.createNamedQuery("TwitterPost.all").getResultList();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTweetDate() {
        return tweetDate;
    }

    public void setTweetDate(Long tweetDate) {
        this.tweetDate = tweetDate;
    }

    public TwitterUser getUser() {
        return user;
    }

    public void setUser(TwitterUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "TwitterPost{" + "id=" + id + "user=" + user + "tweetDate=" + tweetDate + "body=" + body + '}';
    }

}
