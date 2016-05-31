package de.hdm_stuttgart.mi.exam;

import org.jdom2.Document;

/**
 * Transform a given Dom Tree
 *
 */
public interface DomFilter {
   
   /**
    * transform DOM input to output
    * 
    * @param input Input DOM tree
    * @return output DOM tree
    */
   public Document process(final Document input);

}
