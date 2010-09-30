package fr.lr.univ.master.javaee.services;

import fr.lr.univ.master.javaee.entity.TwitterUser;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mathieuancelin
 */
public class SearchService {

    @PersistenceContext
    private EntityManager em;

    public Collection<TwitterUser> searchUser(String query) {
        return null;
    }
}
