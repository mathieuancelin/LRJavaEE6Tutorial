package com.sample.thatslife.controller;

import com.sample.thatslife.boundary.CategoryBean;
import com.sample.thatslife.boundary.ThatsLifeBean;
import com.sample.thatslife.entity.Category;
import com.sample.thatslife.entity.ThatsLife;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * Controlleur JSF propre à l'application.
 *
 * @author mathieuancelin
 */
@Named
@RequestScoped
public class ApplicationController implements Serializable {

    private Collection<Category> categs = Collections.emptyList();
    
    private Collection<ThatsLife> thatLifes = Collections.emptyList();

    private Long categId;

    @EJB
    private ThatsLifeBean tlBean;

    @EJB
    private CategoryBean categoryBean;

    /**
     * Lors de la construction de l'objet (à chaque requete)
     * on charge les catégories et les thatslife.
     */
    @PostConstruct
    public void fetchAll() {
        categs = categoryBean.findAll();
        thatLifes = tlBean.findAll();
    }

    /**
     * Affichage d'un catégorie en particulier.
     * CategId est mis a jour depuis le fichier head.xhtml via la ligne
     * <f:viewParam name="categ" value="#{applicationController.categId}" required="false"/>
     */
    public String doShowCateg() {
        fetchAll();
        if (categId != null) {
            thatLifes = tlBean.findByCategory(categId);
        }
        return "index";
    }

    ////////////////////////////////////////////////////////////////////////////

    public Collection<Category> getCategs() {
        return categs;
    }

    public void setCategs(Collection<Category> categs) {
        this.categs = categs;
    }

    public Long getCategId() {
        return categId;
    }

    public void setCategId(Long categId) {
        this.categId = categId;
    }

    public Collection<ThatsLife> getThatLifes() {
        return thatLifes;
    }

    public void setThatLifes(Collection<ThatsLife> thatLifes) {
        this.thatLifes = thatLifes;
    }

}
