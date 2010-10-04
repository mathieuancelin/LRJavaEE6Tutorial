package fr.lr.univ.master.javaee.ctrl;

import fr.lr.univ.master.javaee.boundaries.TwitterService;
import fr.lr.univ.master.javaee.entity.TwitterPost;
import fr.lr.univ.master.javaee.entity.TwitterUser;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
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

    private TwitterUser loggedinUser;

    private TwitterUser visitedUser;

    private String user;

    private String password;

    private String paramUser;

    private Collection<TwitterUser> followers;

    private Collection<TwitterUser> follows;

    private Collection<TwitterPost> timeline;

    ///////////////////////
    // actions
    ///////////////////////
    
    public String doAuthenticate() {
        loggedinUser = service.authenticate(user, password);
        visitedUser = loggedinUser;
        paramUser = visitedUser.getUsername();
        return "twitter";
    }

    public String doDeAuthenticate() {
        ((HttpSession)FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true)).invalidate();
        return "redirect.html";
    }

    public void doNavigate() {
        System.out.println("doNavigate : " + paramUser);
        visitedUser = TwitterUser.findByUserID(paramUser);
        followers = service.followers(visitedUser.getId());
        follows = service.follows(visitedUser.getId());
        timeline = service.timeline(visitedUser.getId());
    }

    //////////////////////////////////
    // getters and setters          //
    //////////////////////////////////

    public void setParamUser(String paramUser) {
        this.paramUser = paramUser;
    }

    public String getParamUser() {
        return paramUser;
    }

    public TwitterUser getLoggedinUser() {
        return loggedinUser;
    }

    public void setLoggedinUser(TwitterUser loggedinUser) {
        this.loggedinUser = loggedinUser;
    }

    public TwitterUser getVisitedUser() {
        return visitedUser;
    }


    public void setVisitedUser(TwitterUser visitedUser) {
        this.visitedUser = visitedUser;
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
