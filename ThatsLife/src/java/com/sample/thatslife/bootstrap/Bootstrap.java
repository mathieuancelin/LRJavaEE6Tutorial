package com.sample.thatslife.bootstrap;

import com.sample.thatslife.entity.Category;
import com.sample.thatslife.entity.Comment;
import com.sample.thatslife.entity.ThatsLife;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

        Category.setEm(em);
        ThatsLife.setEm(em);

        Category categ = new Category();
        categ.setName("Autre");
        em.persist(categ);

        ThatsLife t2 = new ThatsLife();
        t2.setTlDate(new Date());
        t2.setAuthor("Mick");
        t2.setText("Linux c'est pas très sécure, y'a pas d'antivirus !");
        t2.setCategory(categ);
        em.persist(t2);

        categ = new Category();
        categ.setName("Boulot");
        em.persist(categ);

        t2 = new ThatsLife();
        t2.setTlDate(new Date());
        t2.setAuthor("Fred");
        t2.setText("Mais en fait, ca sert à quoi logue-quatre-ji ?");
        t2.setCategory(categ);
        em.persist(t2);

        categ = new Category();
        categ.setName("Truc");
        em.persist(categ);

        ThatsLife tl = new ThatsLife();
        tl.setTlDate(new Date());
        tl.setAuthor("John");
        tl.setText("En fait, Git c'est comme gem !!!");
        tl.setCategory(categ);
        em.persist(tl);

        

        Comment comment = new Comment();
        comment.setAuthor("Maurice");
        comment.setComment("Tres drole");
        comment.setThatsLife(tl);
        em.persist(comment);

        Comment comment2 = new Comment();
        comment2.setAuthor("Arthur");
        comment2.setComment("Marrant en effet");
        comment2.setThatsLife(tl);
        em.persist(comment2);

        List<Comment> comments = new ArrayList<Comment>();
        comments.add(comment);
        comments.add(comment2);

        tl.setComments(comments);
        em.merge(tl);

        em.flush();

    }

}
