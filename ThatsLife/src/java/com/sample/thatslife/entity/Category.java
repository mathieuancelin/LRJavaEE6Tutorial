/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sample.thatslife.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author mathieuancelin
 */
@Entity
@NamedQuery(name = "Category.all",
    query = "select c from Category c")
public class Category implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private static EntityManager em;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @OneToMany(mappedBy = "category")
    private List<ThatsLife> thatsLifes;

    private String name;

    public static void setEm(EntityManager em) {
        Category.em = em;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ThatsLife> getThatsLifes() {
        return thatsLifes;
    }

    public void setThatsLifes(List<ThatsLife> thatsLifes) {
        this.thatsLifes = thatsLifes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Category)) {
            return false;
        }
        Category other = (Category) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Collection<Category> findAll() {
        return em.createNamedQuery("Category.all").getResultList();
    }

    public static Category findById(Long id) {
        return em.find(Category.class, id);
    }

}
