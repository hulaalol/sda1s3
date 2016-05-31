package de.hdm_stuttgart.mi.exam;

import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.ErrorHandler;

/** Handle parsing errors */
public class StandardErrorHandler implements ErrorHandler {
  /** @see ErrorHandler#warning(SAXParseException) */
  public void warning(SAXParseException e) {
    System.err.println("[Warning]" + getLocationString(e));
  }
  /** @see ErrorHandler#error(SAXParseException) */
  public void error(SAXParseException e) {
    System.err.println("[Error]" + getLocationString(e));
  }
  /** @see ErrorHandler#fatalError(SAXParseException) */
  public void fatalError(SAXParseException e) throws SAXException{
    System.err.println("[Fatal Error]" + getLocationString(e));
  }
  private String getLocationString(SAXParseException e) {
    return " line " + e.getLineNumber() +
    	", column " + e.getColumnNumber()+ ":" +  e.getMessage();
  }
}