package fr.lr.univ.master.javaee.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mathieuancelin
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TwitterUser.all",
        query = "select u from TwitterUser u"),
    @NamedQuery(name = "TwitterUser.findByUserid",
        query = "select u from TwitterUser u where u.username = :username"),
    @NamedQuery(name = "authenticate",
        query = "select u from TwitterUser u where u.username = :username and u.password = :password"),
    @NamedQuery(name = "follows",
        query = "select distinct u from TwitterUser u where exists ("
            + "select follower from u.followers follower where follower.id = :id)")
        })
public class TwitterUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min=8)
    private String username;

    private String password;

    private String email;

    private String name;

    private String surname;

    private String website;

    @OneToMany(cascade=CascadeType.ALL)
    private Collection<TwitterPost> posts;

    @ManyToMany(cascade=CascadeType.ALL)
    private Collection<TwitterUser> followers;

    private static EntityManager em;

    public static void setEntityManager(EntityManager em) {
        TwitterUser.em = em;
    }

    public void save() {
        em.persist(this);
    }

    public static void persist(TwitterUser user) {
        em.persist(user);
    }

    public void delete() {
        em.remove(this);
    }

    public static void remove(Long id) {
        em.remove(em.find(TwitterUser.class, id));
    }

    public static TwitterUser findById(Long id) {
        return em.find(TwitterUser.class, id);
    }

    public static Collection<TwitterUser> findAll() {
        return em.createNamedQuery("TwitterUser.all").getResultList();
    }

    public static TwitterUser authenticate(String user, String password) {
        TwitterUser u = em.createNamedQuery("authenticate", TwitterUser.class)
                .setParameter("username", user)
                .setParameter("password", password)
                .getSingleResult();
        return u;
    }

    public static TwitterUser findByUserID(String user) {
        TwitterUser u = em.createNamedQuery("TwitterUser.findByUserid", TwitterUser.class)
                .setParameter("username", user)
                .getSingleResult();
        return u;
    }

    public static  Collection<TwitterUser> follows(Long id) {
        TwitterUser user = em.find(TwitterUser.class, id);
        Collection<TwitterUser> u = em.createNamedQuery("follows")
                .setParameter("id", id)
                .getResultList();
        u.remove(user);
        return u;
    }

    public static Collection<TwitterUser> followers(Long id) {
        TwitterUser user = em.find(TwitterUser.class, id);
        Collection<TwitterUser> u = em.find(TwitterUser.class, id).getFollowers();
        u.remove(user);
        return u;
    }

    public void newTweet(TwitterPost post) {
        em.persist(post);
        this.posts.add(post);
    }

    public Collection<TwitterPost> timeline() {
        List<TwitterPost> timelinePosts = new ArrayList<TwitterPost>();
        for (TwitterUser u : follows(id)) {
            timelinePosts.addAll(u.getPosts());
        }
        timelinePosts.addAll(posts);
        Collections.sort(timelinePosts, new Comparator<TwitterPost>() {

            @Override
            public int compare(TwitterPost o1, TwitterPost o2) {
                if (o1.getTweetDate() > o2.getTweetDate())
                    return -1;
                if (o1.getTweetDate() < o2.getTweetDate())
                    return 1;
                return 0;
            }

        });
        return timelinePosts;
    }

    public static Collection<TwitterUser> search(String query) {
        return null;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<TwitterUser> getFollowers() {
        return followers;
    }

    public void setFollowers(Collection<TwitterUser> followers) {
        this.followers = followers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<TwitterPost> getPosts() {
        return posts;
    }

    public void setPosts(Collection<TwitterPost> post) {
        this.posts = post;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    @Override
    public String toString() {
        return "TwitterUser{" + "id=" + id + "username=" + username + "password=" + password + "email=" + email + "name=" + name + "surname=" + surname + "website=" + website + "post=" + posts + "followers=" + followers + '}';
    }

}
