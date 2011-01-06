package com.sample.thatslife.bootstrap;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mathieuancelin
 */
@Singleton
@Startup
public class Bootstrap {

    @PersistenceContext
    private EntityManager em;

    @PostConstruct
    public void init() {
        // ici le code pour enregistrer des entités en base
    }

}
