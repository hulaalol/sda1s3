package de.hdm_stuttgart.mi.sda1.hiberjpa;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import de.hdm_stuttgart.mi.sda1.hiberjpa.model.Lecture;


/**
 * A simple Jpa 2.1 example
 * 
 */
public class PersistApp {

	static String[] lectureNames= {"Databases","SE1","SDA1"};
	
	
    public static void main( String[] args ) {

       final EntityManagerFactory emFactory =  Persistence.createEntityManagerFactory("jpa-recreate");
       
       final EntityManager em = emFactory.createEntityManager();
       
       for(String name : lectureNames){
    	   insertObject(em,name);
       }
       em.close();
    }
    
    
    public static void insertObject(EntityManager em, String lecture) {
    	
    	EntityTransaction transaction = em.getTransaction();
    	
    	transaction.begin();
    	em.persist(new Lecture(lecture));
    	transaction.commit();
    }
    
    
    
    
}
