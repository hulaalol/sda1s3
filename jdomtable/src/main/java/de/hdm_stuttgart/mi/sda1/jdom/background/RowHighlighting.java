package de.hdm_stuttgart.mi.sda1.jdom.background;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import de.hdm_stuttgart.mi.exam.DomFilter;











/**
 * Handling address book data.
 *
 */
public class RowHighlighting implements DomFilter {
	
	
	
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
    * @param xmlFilename The XML input file containing address book data.
    * @throws JDOMException Internal error.
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
      
      Element tableHeadBirth = new Element("th");
      tableHeadBirth.setText("Birthday");
      
     firstRow.addContent(tableHeadName);
     firstRow.addContent(tableHeadBirth);
     
     table.addContent(firstRow);
     
     
     int i=0;
     
     for (Person p : persons){

    	 Element row = new Element("tr");
    	 
    	 if(i%2==0){
    		 row.setAttribute("style", "background-color: #FFFF88;");
    	 }
    	 i++;
    	 
    	 Element name = new Element("td");
    	 Element birth = new Element("td");
    	 
    	 name.setText(p.name);
    	 birth.setText(p.birth);
    	 
    	 row.addContent(name);
    	 row.addContent(birth);
    	 
    	table.addContent(row);

     }

      return htmlResultDoc;
   }
}