package de.hdm_stuttgart.mi.sda1.jdom.background;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import de.hdm_stuttgart.mi.exam.DomFilter;
import de.hdm_stuttgart.mi.sda1.jdom.background.RowHighlighting.Person;

/**
 * Address book data.
 *
 */
public class TableRowColumnSwitch implements DomFilter {
	
	
class Person{
		
		String name;
		String birth;
		
		public Person(String name, String birth) {
			super();
			this.name = name;
			this.birth = birth;
		}
	
	}
	

   /**
    * Read XML file data and create corresponding HTML output. For the sake of
    * simplicity we only create a html table rather then a "true" html/head/body document.
    * 
    * Reading XML input an create corresponding HTML output
    * @param xmlFilename XML file input data
    * @throws JDOMException Internal errors
    * @throws IOException IO related errors.
    */
   @Override
   public Document process(Document addressbook) {
	   
	   	Element docRoot = addressbook.getRootElement();
	      
	      List<Element> personElements = docRoot.getChildren();
	      ArrayList<Person> persons = new ArrayList<Person>();
	      
	      for(Element person : personElements){ 	  
	    	  persons.add(new Person(person.getChild("name").getValue(),person.getChild("birthday").getValue()));
	      }

	      Element table = new Element("table");
	      
	      final Document htmlResultDoc = new  Document(table); 
	      
	      
	      Element firstRow = new Element("tr");
	      Element tableHeadName = new Element("th");
	      tableHeadName.setText("Name");
	      firstRow.addContent(tableHeadName);
	      
	      Element secondRow = new Element("tr");
	      Element tableHeadBirth = new Element("th");
	      tableHeadBirth.setText("Birthday");
	      secondRow.addContent(tableHeadBirth);
	      
	      
	      
	      
	      for (Person p : persons){

	     	 Element name = new Element("td");
	     	 name.setText(p.name);
	     	 firstRow.addContent(name);
	     	 
	     	 Element birth = new Element("td");
	     	 birth.setText(p.birth);
	     	 secondRow.addContent(birth);
	      }
	      
	      table.addContent(firstRow);
	      table.addContent(secondRow);
	      
      return htmlResultDoc;
   }
}