package fr.lr.univ.master.javaee.services;

import fr.lr.univ.master.javaee.entity.TwitterUser;
import fr.lr.univ.master.javaee.event.DeleteAccountEvent;
import fr.lr.univ.master.javaee.event.RegistrationEvent;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 *
 * @author mathieuancelin
 */
public class AccountService {

    @Inject
    private Event<RegistrationEvent> registration;

    @Inject
    private Event<DeleteAccountEvent> unregistration;

    public void createAccount(TwitterUser user) {
        user.save();
        registration.fire(new RegistrationEvent(user));
    }

    public void deleteAccount(Long id) {
        unregistration.fire(new DeleteAccountEvent(TwitterUser.findById(id)));
        TwitterUser.findById(id).delete();
    }

}
