package de.hdm_stuttgart.sda1.domhtml.test;

import org.junit.Test;

import de.hdm_stuttgart.de.mi.exam.test.dom.DomFilterTest;
import de.hdm_stuttgart.de.mi.exam.test.sax.DomAssert;
import de.hdm_stuttgart.mi.sda1.jdom.background.TableRowColumnSwitch;

/**
 * Unit testing dom HTML output
 */
@SuppressWarnings("javadoc")
public class TestTableRowColumnSwitch extends DomFilterTest {
   
   static {
      init("src/main/resources/persons.xml", new TableRowColumnSwitch(), ".switch.html");
   }
   @Test public void testNumberOfColumnHeaders() {
      DomAssert.assertNumberOfNodes(
            "Two <tr> expected",
            xmlRootElement,
            "tr", 2);
   }
   @Test public void testColumnHeader_Name() {
      DomAssert.assertSingleNodeContent("Checking 'Name' <th>", 
            xmlRootElement, 
            "tr[1]/th",
            "th", "Name");
   }

   @Test public void testColumnHeader_Birthday() {
      DomAssert.assertSingleNodeContent("Checking 'Birthday' <th>", 
            xmlRootElement, 
            "tr[2]/th",
            "th", "Birthday");
   }
   
   @Test public void testNumberOfTd() {
      DomAssert.assertNumberOfNodes(
            "6 <td> in two groups of 3 expected",
            xmlRootElement,
            "tr[1]/td[0 < position() and position() < 4]|tr[2]/td[0 < position() and position() < 4]", 6);
   }

   @Test public void testNameFirstDataRecord() {
      DomAssert.assertSingleNodeContent("Checking Name of first data record", 
            xmlRootElement, 
            "tr[1]/*[2]",
            "td", "Scott Geoffrey");
   }
   @Test public void testBirthdayLastDataRecord() {
      DomAssert.assertSingleNodeContent("Checking birthday of last data record", 
            xmlRootElement, 
            "tr[2]/*[last()]",
            "td", "1981-04-13");
   }
}