package com.wepr.watchshop.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ItemDAO {
    public void insertItem(Item item) {
        EntityManager em;
        em = ConnectionUtil.getEMF().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(item);
            trans.commit();
        }catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
        }
        finally {
            em.close();
        }
    }
}
