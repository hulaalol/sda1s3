package de.hdm_stuttgart.mi.sda1.hiberjpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import de.hdm_stuttgart.mi.sda1.hiberjpa.model.Lecture;


/**
 * A simple Jpa 2.1 example
 * 
 */
public class PersistDriver {

    /**
     * @param args Unused
     */
    public static void main( String[] args ) {
       final EntityManagerFactory emFactory =  Persistence.createEntityManagerFactory("jpa-recreate");
       
       final EntityManager em = emFactory.createEntityManager();
       
       {
          final EntityTransaction transaction = em.getTransaction();
          transaction.begin();
          
          final Lecture sda1 = new Lecture("Structure data and applications 1");
          em.persist(sda1);
          
          transaction.commit();
       }
       
       em.close();
    }
}
