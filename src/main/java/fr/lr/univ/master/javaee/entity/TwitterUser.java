package fr.lr.univ.master.javaee.entity;

import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
    @NamedQuery(name = "authenticate",
        query = "select u from TwitterUser u where u.username = :username and u.password = :password"),
    @NamedQuery(name = "follows",
        query = "select distinct u from TwitterUser u where exists ("
            + "select follower from u.followers follower where follower.id = :id)")
        })
public class TwitterUser {

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
