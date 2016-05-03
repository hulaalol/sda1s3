package dom;

import org.jdom2.Element;
import org.jdom2.Text;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class CreateDocument {

	   public static void main(String[] args) throws Exception {

	      // Create the root element
	      final Element titel = new Element("titel");
	      
	      final Element tLong = new Element("long");
	      tLong.setText("The long version of this title");

	      final Element tShort = new Element("short");
	      tShort.setText("short version");
	      
	      titel.addContent(tLong);
	      titel.addContent(tShort);
	      
	      // Set formatting for the XML output
	      final Format outFormat = Format.getPrettyFormat();
	      
	      // Serialize to console
	      final XMLOutputter printer = new XMLOutputter(outFormat);
	      printer.output(titel, System.out);
	   }
	}




