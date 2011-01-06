package com.sample.thatslife.controller;

import com.sample.thatslife.boundary.CategoryBean;
import com.sample.thatslife.boundary.ThatsLifeBean;
import com.sample.thatslife.entity.ThatsLife;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Controlleur JSF permettant la gestion du formulaire d'ajout de thatslife.
 *
 * @author mathieuancelin
 */
@Named
@RequestScoped
public class FormController {

    @Inject
    private ApplicationController app;

    @EJB
    private ThatsLifeBean tlBean;

    @EJB
    private CategoryBean categoryBean;

    private String author;

    private String text;

    private String category;

    /**
     * On charge une catégorie a la création de l'objet.
     */
    @PostConstruct
    public void init() {
        category = categoryBean.findAll().iterator().next().getName();
    }

    /**
     * Lors de la soumission du formulaire, on créé l'object thatslife et on le persiste.
     */
    public String doPost() {
        ThatsLife tl = new ThatsLife();
        tl.setAuthor(author);
        tl.setText(text);
        tl.setTlDate(new Date());
        tl.setCategory(categoryBean.findById(app.getCategId()));
        tlBean.save(tl);
        author = "";
        text = "";
        category = tl.getCategory().getName();
        return "index";
    }

    ////////////////////////////////////////////////////////////////////////////

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
