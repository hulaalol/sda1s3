package de.hdm_stuttgart.de.mi.exam.test.dom;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import de.hdm_stuttgart.mi.exam.DomFilter;

public abstract class DomFilterTest {

   public static String xmlInputFileName, xmlOutputFileName;
   static DomFilter domFilter;
   protected static String initializationErrorString = null;

   protected static Element xmlRootElement = null;

   static protected void init (final String xmlInputFileName,
         final DomFilter domFilter, 
         final String resultFileExtension) {
      DomFilterTest.xmlInputFileName = xmlInputFileName;
      DomFilterTest.xmlOutputFileName = xmlInputFileName + resultFileExtension;
      DomFilterTest.domFilter = domFilter;
      processAndParseResult();
   }

   static protected void processAndParseResult() {

      final SAXBuilder builder = new SAXBuilder();
      final Document input;
      try {
         input = builder.build(xmlInputFileName);
      } catch (JDOMException | IOException e) {
         initializationErrorString = "Error parsing input:" + e.getLocalizedMessage();
         return;
      }

      final PrintStream out;
      try {
         out = new PrintStream(xmlOutputFileName);
      } catch (FileNotFoundException e1) {
         initializationErrorString = "Unable to open result file '" + xmlOutputFileName
               + "' for writing";
         return;
      }

      // Print to file
      final Document result = domFilter.process(input);
      xmlRootElement = result.getRootElement();
      final Format outFormat = Format.getPrettyFormat();
      final XMLOutputter printer = new XMLOutputter(outFormat);
      try {
         printer.output(result, out);
      } catch (IOException e) {
         initializationErrorString = "Error serializing result document to file '" + xmlOutputFileName + "': " + e.getLocalizedMessage();
         return;
      }
      // Print to standard out as well
      try {
         printer.output(result, System.out);
      } catch (IOException e) {
         initializationErrorString = "Error serializing result document to standard output: " + e.getLocalizedMessage();
      }
   }
}
