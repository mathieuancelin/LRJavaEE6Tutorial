package com.sample.thatslife.boundary;

import com.sample.thatslife.entity.ThatsLife;
import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * EJB offrant des méthodes de lecture/ecriture sur les objets ThatsLife.
 * Cet EJB expose également ses methodes en tant que services REST renvoyant
 * du XML ou du JSON (le client décide via un header http accept)
 * La fichier de description du WS est disponible à 
 * http://localost:8080/ThatsLife/services/application.wadl
 *
 * @author mathieuancelin
 */
@Stateless
@Path("thatslife")
public class ThatsLifeBean {
    
    @PersistenceContext
    private EntityManager em;

    /**
     * Trouve un objet thatslife dans le bd via son ID.
     */
    @GET
    @Path("{key}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public ThatsLife findById(@PathParam("key") Long thatslifeId) {
        return em.find(ThatsLife.class, thatslifeId);
    }

    /**
     * @return tous les objets thatslife de la bd.
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<ThatsLife> findAll() {
        return em.createNamedQuery("ThatsLife.all")
                .getResultList();
    }

    /**
     * @return tous les objets thatslife de la bd pour une catégorie donnée.
     */
    @GET
    @Path("category/{key}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<ThatsLife> findByCategory(@PathParam("key") Long categoryId) {
        return em.createNamedQuery("ThatsLife.findByCategory")
                .setParameter("id", categoryId).getResultList();
    }

    /**
     * Sauvegarde en base d'un objet thatslife.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public ThatsLife save(ThatsLife t) {
        if (em.contains(t)) {
            return em.merge(t);
        } else {
            em.persist(t);
            return t;
        }
    }

    /**
     * Suppression d'un objet thatslife.
     */
    @DELETE
    @Path("{key}")
    public void delete(Long thatslifeId) {
        em.remove(em.find(ThatsLife.class, thatslifeId));
    }
}
