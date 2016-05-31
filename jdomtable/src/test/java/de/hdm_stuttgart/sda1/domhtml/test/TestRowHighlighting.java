package de.hdm_stuttgart.sda1.domhtml.test;

import org.junit.Test;

import de.hdm_stuttgart.de.mi.exam.test.dom.DomFilterTest;
import de.hdm_stuttgart.de.mi.exam.test.sax.DomAssert;
import de.hdm_stuttgart.mi.sda1.jdom.background.RowHighlighting;

/**
 * Unit testing DOM HTML output
 */
@SuppressWarnings("javadoc")
public class TestRowHighlighting extends DomFilterTest {
   
   static {
      init("src/main/resources/persons.xml", new RowHighlighting(), ".highlight.html");
   }
   @Test public void testNumberOfColumnHeaders() {
      DomAssert.assertNumberOfNodes(
            "Two column headers expected",
            xmlRootElement,
            "tr", 4);
   }
   @Test public void testColumnHeader_Name() {
      DomAssert.assertSingleNodeContent("Checking first <th>", 
            xmlRootElement, 
            "tr/th[1]",
            "th", "Name");
   }
   @Test public void testColumnHeader_Birthday() {
      DomAssert.assertSingleNodeContent("Checking second <th>", 
            xmlRootElement, 
            "tr/th[2]",
            "th", "Birthday");
   }
   @Test public void testNumberOfHeaderRows() {
      DomAssert.assertNumberOfNodes(
            "1 <tr> element containing <th> expected",
            xmlRootElement,
            "tr[th]", 1);
   }
   @Test public void testNumberOfDataRows() {
      DomAssert.assertNumberOfNodes(
            "3 <tr> element containing <td> expected",
            xmlRootElement,
            "tr[td]", 3);
   }
   
   @Test public void testNumberOfHighlightedRows() {
      DomAssert.assertNumberOfNodes(
            "2 <tr style='background-color: #FFFF88;> elements expected",
            xmlRootElement,
            "tr[@style='background-color: #FFFF88;']", 2);
   }
   @Test public void testRowWithoutHighlighing() {
      DomAssert.assertNumberOfNodes(
            "Middle data row shall not contain highlighting",
            xmlRootElement,
            "tr[not(@style) and position() = 3]", 1);
   }
   @Test public void testLastRecord_Name() {
      DomAssert.assertSingleNodeContent("Last record must start with <td>Jim Smith</td>", 
            xmlRootElement, 
            "tr[last()]/td[1]",
            "td", "Jim Smith");
   }
   @Test public void testLastRecord_Date() {
      DomAssert.assertSingleNodeContent("Last record must end with <td>1981-04-13</td>", 
            xmlRootElement, 
            "tr[last()]/td[2]",
            "td", "1981-04-13");
   }
}