package fr.lr.univ.master.javaee.ctrl;

import fr.lr.univ.master.javaee.boundaries.TwitterService;
import fr.lr.univ.master.javaee.entity.TwitterPost;
import fr.lr.univ.master.javaee.entity.TwitterUser;
import java.io.Serializable;
import java.util.Collection;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mathieuancelin
 */
@ManagedBean
@Named("controller")
@SessionScoped
public class TwitterBean implements Serializable {

    @EJB
    private TwitterService service;

    private TwitterUser currentUser;

    private String user;

    private String password;

    private Collection<TwitterUser> followers;

    private Collection<TwitterUser> follows;

    private Collection<TwitterPost> timeline;

    public String doAuthenticate() {
        currentUser = service.authenticate(user, password);
        followers = service.followers(currentUser.getId());
        timeline = service.timeline(currentUser.getId());
        return "twitter";
    }

    public String doDeAuthenticate() {
        ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        currentUser = null;
        user = null;
        password = null;
        followers = null;
        follows = null;
        timeline = null;
        return "twitter";
    }

    public TwitterUser getCurrentUser() {
        return currentUser;
    }

    public String getPassword() {
        return password;
    }

    public String getUser() {
        return user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Collection<TwitterUser> getFollowers() {
        return followers;
    }

    public void setFollowers(Collection<TwitterUser> followers) {
        this.followers = followers;
    }

    public Collection<TwitterUser> getFollows() {
        return follows;
    }

    public void setFollows(Collection<TwitterUser> follows) {
        this.follows = follows;
    }

    public Collection<TwitterPost> getTimeline() {
        return timeline;
    }

    public void setTimeline(Collection<TwitterPost> timeline) {
        this.timeline = timeline;
    }

}
