package com.sample.thatslife.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entité en base représentant une thatslife.
 *
 * @author mathieuancelin
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "ThatsLife.all", query = "select t from ThatsLife t order by t.tlDate DESC"),
    @NamedQuery(name = "ThatsLife.findByCategory",
        query = "select t from ThatsLife t where t.category.id = :id order by t.tlDate DESC")
})
@XmlRootElement
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

    ////////////////////////////////////////////////////////////////////////////

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

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
