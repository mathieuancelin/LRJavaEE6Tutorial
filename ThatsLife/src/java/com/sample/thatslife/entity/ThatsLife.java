/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.sample.thatslife.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author mathieuancelin
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "ThatsLife.all", query = "select t from ThatsLife t order by t.tlDate DESC"),
    @NamedQuery(name = "ThatsLife.findByCategory",
        query = "select t from ThatsLife t where t.category.id = :id order by t.tlDate DESC")
})
public class ThatsLife implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date tlDate;

    private Long iAgree = new Long(0);

    private Long iDisagree = new Long(0);

    @Size(max=300)
    private String text;

    @Size(max=30)
    private String author;

    @ManyToOne
    @NotNull
    private Category category;

    @OneToMany(mappedBy = "thatsLife")
    private Collection<Comment> comments;

    private static EntityManager em;

    public static Collection<ThatsLife> findAll() {
        return em.createNamedQuery("ThatsLife.all").getResultList();
    }

    public static ThatsLife findById(Long id) {
        return em.find(ThatsLife.class, id);
    }

    public static Collection<ThatsLife> findByCategory(Long id) {
        return em.createNamedQuery("ThatsLife.findByCategory").setParameter("id", id).getResultList();
    }

    public static Collection<ThatsLife> findByCategoryBis(Long id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ThatsLife> criteria = builder.createQuery(
            ThatsLife.class);
        Root<ThatsLife> personRoot = criteria.from(ThatsLife.class);
        criteria.select(personRoot);
        ParameterExpression<Long> idParam = builder.parameter(Long.class);
        criteria.where(
                builder.equal(
                    ((Root<Category>)personRoot.get("category")
                    .as(Category.class)).get("id").as(Category.class), idParam)
        );
        TypedQuery<ThatsLife> query = em.createQuery(criteria);
        query.setParameter(idParam, id);
        return query.getResultList();

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    ////////////////////////////////////////////////////////////////////////////

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Collection<Comment> getComments() {
        return comments;
    }

    public void setComments(Collection<Comment> comments) {
        this.comments = comments;
    }

    public Long getiAgree() {
        return iAgree;
    }

    public void setiAgree(Long iAgree) {
        this.iAgree = iAgree;
    }

    public Long getiDisagree() {
        return iDisagree;
    }

    public void setiDisagree(Long iDisagree) {
        this.iDisagree = iDisagree;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTlDate() {
        return tlDate;
    }

    public void setTlDate(Date tlDate) {
        this.tlDate = tlDate;
    }

    public static void setEm(EntityManager em) {
        ThatsLife.em = em;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ThatsLife)) {
            return false;
        }
        ThatsLife other = (ThatsLife) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sample.thatslife.entity.ThatsLife[id=" + id + "]";
    }

}
