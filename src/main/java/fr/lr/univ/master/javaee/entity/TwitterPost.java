package fr.lr.univ.master.javaee.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mathieuancelin
 */
@Entity
@XmlRootElement
public class TwitterPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade=CascadeType.ALL)
    private TwitterUser user;

    private Long tweetDate;

    @Size(max=140, message="Your message is too long")
    private String body;

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
