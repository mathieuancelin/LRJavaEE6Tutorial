package fr.lr.univ.master.javaee.services;

import fr.lr.univ.master.javaee.entity.TwitterUser;
import fr.lr.univ.master.javaee.event.DeleteAccountEvent;
import fr.lr.univ.master.javaee.event.RegistrationEvent;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mathieuancelin
 */
public class AccountService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    private Event<RegistrationEvent> registration;

    @Inject
    private Event<DeleteAccountEvent> unregistration;

    public void createAccount(TwitterUser user) {
        em.persist(user);
        //registration.fire(new RegistrationEvent(user));
    }

    public void deleteAccount(Long id) {
        //unregistration.fire(new DeleteAccountEvent(em.find(TwitterUser.class, id)));
        em.remove(em.find(TwitterUser.class, id));
    }

    public TwitterUser authenticate(String user, String password) {
        TwitterUser u = em.createNamedQuery("authenticate", TwitterUser.class)
                .setParameter("username", user)
                .setParameter("password", password)
                .getSingleResult();
        return u;
    }

}
