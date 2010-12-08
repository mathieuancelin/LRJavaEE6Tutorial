package com.sample.thatslife.controller;

import com.sample.thatslife.boundary.ThatsLifeBean;
import com.sample.thatslife.entity.Category;
import com.sample.thatslife.entity.ThatsLife;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author mathieuancelin
 */
@Named
@RequestScoped
public class ApplicationController implements Serializable {

    private Collection<Category> categs = Collections.emptyList();
    
    private Collection<ThatsLife> thatLifes = Collections.emptyList();

    private Long categId;

    private String author;

    private String text;

    private String category;

    @EJB
    private ThatsLifeBean bean;
    
    public void doNavigate() {
        author = "";
        text = "";
        category = null;
        categs = Category.findAll();
        if (categId != null) {
            thatLifes = ThatsLife.findByCategory(categId);
        } else {
            thatLifes = ThatsLife.findAll();
        }
    }

    public String doPost() {
        ThatsLife tl = new ThatsLife();
        tl.setAuthor(author);
        tl.setText(text);
        tl.setTlDate(new Date());
        tl.setCategory(Category.findById(Long.valueOf(category)));
        bean.save(tl);
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
