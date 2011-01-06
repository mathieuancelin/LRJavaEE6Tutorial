package com.sample.thatslife.boundary;

import com.sample.thatslife.entity.Category;
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
 * EJB offrant des méthodes de lecture/ecriture sur les objets Category.
 * Cet EJB expose également ses methodes en tant que services REST renvoyant
 * du XML ou du JSON (le client décide via un header http accept)
 * La fichier de description du WS est disponible à 
 * http://localost:8080/ThatsLife/services/application.wadl
 *
 * @author mathieuancelin
 */
@Stateless
@Path("categories")
public class CategoryBean {
    
    @PersistenceContext
    private EntityManager em;

    /**
     * @return un objet Category dans le bd via son ID.
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Category findById(@PathParam("id") Long id) {
        return em.find(Category.class, id);
    }

    /**
     * @return tous les objets Category de la bd.
     */
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<Category> findAll() {
        return em.createNamedQuery("Category.all")
                .getResultList();
    }

    /**
     * Sauvegarde en base d'un objet Category.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML})
    public Category save(Category t) {
        if (em.contains(t)) {
            return em.merge(t);
        } else {
            em.persist(t);
            return t;
        }
    }

    /**
     * Suppression d'un objet Category.
     */
    @DELETE
    @Path("{key}")
    public void delete(Long tlId) {
        em.remove(em.find(Category.class, tlId));
    }
}
