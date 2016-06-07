package de.hdm_stuttgart.mi.sda1.hiberjpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * A simple example entity
 *
 */
@Entity
public class Lecture {

   @Id
   @GeneratedValue
   private Integer id;
   
   String name;

   protected Lecture (){}
   
   public Lecture (final String name){
      setName(name);
   }
   
   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getId() {
      return id;
   }
}
