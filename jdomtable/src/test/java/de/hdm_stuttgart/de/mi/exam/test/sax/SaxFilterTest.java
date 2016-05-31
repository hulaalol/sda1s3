package de.hdm_stuttgart.de.mi.exam.test.sax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.Assume;
import org.junit.Before;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import de.hdm_stuttgart.mi.exam.SaxFilter;
import de.hdm_stuttgart.mi.exam.StandardErrorHandler;

/**
 * Turning &lt;memo&gt; documents to HTML as being shown in sample document
 * memo.xml.2.sample.html
 */
@SuppressWarnings("javadoc")
public abstract class SaxFilterTest {

   public static String xmlInputFileName, htmlOutputFileName;
   static SaxFilter saxHandler;
   protected static String initializationErrorString = null;

   protected static Element htmlRootElement = null;

   static protected void init (final String xmlInputFileName,
         final SaxFilter saxHandler, 
         final String resultFileExtension) {
      SaxFilterTest.xmlInputFileName = xmlInputFileName;
      SaxFilterTest.htmlOutputFileName = xmlInputFileName + resultFileExtension;
      SaxFilterTest.saxHandler = saxHandler;

      processAndParseResult();
   }
   
   static protected void processAndParseResult() {
      final PrintStream out;

      try {
         out = new PrintStream(htmlOutputFileName);
      } catch (FileNotFoundException e1) {
         initializationErrorString = "Unable to open file '" + htmlOutputFileName
               + "' for writing";
         return;
      }
      saxHandler.setOutputStream(out);

      final XMLReader xmlReader;
      try {
         final SAXParserFactory saxPf = SAXParserFactory.newInstance();
         final SAXParser saxParser = saxPf.newSAXParser();
         xmlReader = saxParser.getXMLReader();

         xmlReader.setContentHandler(saxHandler);
         xmlReader.setErrorHandler(new StandardErrorHandler());

      } catch (SAXException | ParserConfigurationException e) {
         initializationErrorString = "Unable to create XMLparser instance: " + e.getLocalizedMessage();
         return;
      }
      try {
         xmlReader.parse(xmlInputFileName);
      } catch (SAXException | IOException e) {
         initializationErrorString = "Unable parse file '" + xmlInputFileName
               + "': " + e.getLocalizedMessage();
         return;
      } 
      out.close();
      printResultToStdout();

      // Now parse the result for further analysis
      //
      final SAXBuilder parser = new SAXBuilder();

      try {
         htmlRootElement = parser.build(htmlOutputFileName).getRootElement();
      } catch (JDOMException | IOException e1) {
         System.err.print(
               initializationErrorString = "Unable to parse file '" + htmlOutputFileName
               + "':\n" + e1.getLocalizedMessage());
         return;
      }
   }

   @Before
   public void checkWellFormedness() {
      Assume.assumeTrue("Previous error:" + initializationErrorString, null == initializationErrorString);
      //Assert.assertNull("Unable to parse generated file:" + errorInitString, errorInitString);
   }

   private static void printResultToStdout() {
      try {
         BufferedReader saxResult = new BufferedReader(new FileReader(
               htmlOutputFileName));
         String line;
         while (null != (line = saxResult.readLine())) {
            System.out.println(line);
         }
         saxResult.close();
      } catch (IOException e) {
         initializationErrorString = "Unable to open expected result output file '"
               + htmlOutputFileName + "' :" + e.getLocalizedMessage();
      }
   }

}