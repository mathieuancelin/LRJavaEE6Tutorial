package com.sample.thatslife.controller;

import com.sample.thatslife.boundary.ThatsLifeBean;
import com.sample.thatslife.entity.ThatsLife;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Controlleur JSF permettant la gestion de l'affichage d'une seule thatslife.
 *
 * @author mathieuancelin
 */
@Named
@RequestScoped
public class SingleController {

    private Long tlId;

    private ThatsLife current;

    @EJB
    private ThatsLifeBean tlBean;

    @Inject
    private ApplicationController controller;

    /**
     * On affiche une seule thatslife via son id.
     * tlId est mis a jour dans le fichier head.xhtml via la ligne
     * <f:viewParam name="tlid" value="#{singleController.tlId}" required="false"/>
     *
     * @return
     */
    public String viewTl() {
        if (tlId != null) {
            current = tlBean.findById(tlId);
        }
        return "single";
    }

    /**
     * Suppression d'une thatslife.
     */
    public String removeTl() {
        if (tlId != null) {
            tlBean.delete(tlId);
            tlId = null;
            current = null;
            controller.fetchAll();
        }
        return "index";
    }

    ////////////////////////////////////////////////////////////////////////////

    public Long getTlId() {
        return tlId;
    }

    public void setTlId(Long tlId) {
        this.tlId = tlId;
    }

    public ThatsLife getCurrent() {
        return current;
    }

    public void setCurrent(ThatsLife current) {
        this.current = current;
    }
}
