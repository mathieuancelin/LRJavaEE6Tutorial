package fr.lr.univ.master.javaee.ctrl;

import fr.lr.univ.master.javaee.boundaries.TwitterService;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

/**
 *
 * @author mathieuancelin
 */
@ManagedBean
@Named
@SessionScoped
public class TwitterBean {

    @EJB
    private TwitterService service;

}
