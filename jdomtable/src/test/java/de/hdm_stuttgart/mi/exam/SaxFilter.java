package de.hdm_stuttgart.mi.exam;

import java.io.PrintStream;

import org.xml.sax.ContentHandler;

/**
 * Simplifies testing of XML output generating applications
 *
 */
public interface SaxFilter extends ContentHandler {

   /**
    * Allow output redirection. All filter related output will be
    * redirected from System.out to an arbitrary stream
    * 
    * @param out Output will be redirected here.
    */
   public void setOutputStream(PrintStream out);

}
