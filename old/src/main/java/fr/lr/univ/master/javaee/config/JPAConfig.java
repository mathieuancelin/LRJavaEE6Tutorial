package fr.lr.univ.master.javaee.config;

import fr.lr.univ.master.javaee.entity.TwitterPost;
import fr.lr.univ.master.javaee.entity.TwitterUser;
import fr.lr.univ.master.javaee.mock.MockerBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mathieu ANCELIN
 */
@Singleton
@Startup
public class JPAConfig {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private MockerBean mocks;

    @PostConstruct
    public void init() {
        TwitterUser.setEntityManager(em);
        TwitterPost.setEntityManager(em);
        mocks.mock();
    }

}
