package sax;

import java.io.PrintStream;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/** Providing XML instance error messages. */
public class MySaxErrorHandler implements ErrorHandler {
   
   private PrintStream out; //The error handler's output goes here

   static private String getParseExceptionInfo  //Returns a string describing
   (final SAXParseException ex) {               //parse exception details.
     return "Error '" + ex.getMessage() + "' at line " + 
      ex.getLineNumber() + ", column " +
         ex.getColumnNumber();
   }

   /**
    * @param out Report's destination
    */
   public MySaxErrorHandler(final PrintStream out) {
     this.out = out;
   }
   @Override
   public void warning(SAXParseException exception) throws SAXException {
      out.print("Warning:" + getParseExceptionInfo(exception));
   }
   @Override
   public void error(SAXParseException exception) throws SAXException {
      out.print("Error:" + getParseExceptionInfo(exception));
   }
   @Override
   public void fatalError(SAXParseException exception) throws SAXException {
      out.print("Fatal error:" + getParseExceptionInfo(exception));
   }
}