package com.sample.thatslife.boundary;

import com.sample.thatslife.entity.ThatsLife;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mathieuancelin
 */
@Stateless
public class ThatsLifeBean {
    
    @PersistenceContext
    private EntityManager em;

    public ThatsLife save(ThatsLife tl) {
        if (em.contains(tl)) {
            return em.merge(tl);
        } else {
            em.persist(tl);
            return tl;
        }
    }

}
