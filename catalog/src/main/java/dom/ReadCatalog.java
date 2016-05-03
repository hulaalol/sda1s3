package dom;

import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import sax.MySaxErrorHandler;


/**
 * Parsing an XML catalog and writing its
 * content to standard output
 *
 */
public class ReadCatalog {

   // Though we are playing DOM here, a SAX parser still assembles our DOM tree.
   private SAXBuilder builder = new SAXBuilder();

   /**
    * Initialize for parsing.
    */
   public ReadCatalog() {
      // Though an ErrorHandler is not strictly required it allows
      // for easier localization of XML document errors
      builder.setErrorHandler(new MySaxErrorHandler(System.out));
   }

   /** Descending a catalog till its &lt;item&gt; elements. For each product
    *  its name and order number are being written to the output.
    *  
    * @param filename The catalog's filename to be parsed
    * 
    * @throws JDOMException XML input parsing error.
    * @throws IOException XML input access error.
    */
   public void process(final String filename) throws JDOMException, IOException {

      // Parsing our XML file being located below "resources"
      final Document docInput = builder.build(
            getClass().getClassLoader().getResource(filename)
      );

      // Accessing the document's root element <catalog>
      final Element docRoot = docInput.getRootElement();

      // Accessing the <item> children of parent element <catalog>
      docRoot.getChildren().stream().
      map(item -> "Article: " + item.getText() +
            ", order number: " + item.getAttributeValue("orderNo")).
      forEach(System.out::println);
   }
}