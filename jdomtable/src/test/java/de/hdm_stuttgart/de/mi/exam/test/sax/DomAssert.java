package de.hdm_stuttgart.de.mi.exam.test.sax;

import java.util.List;

import org.jdom2.Element;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

/**
 * Helper methods for analyzing XML result documents
 *
 */
public class DomAssert {

   final static XPathFactory xpf = XPathFactory.instance();

   /**
    * A given XPath expression is being evaluated for returning an expected number of element nodes
    * 
    * @param msg An informative text explaining an error's cause.
    * @param context XPath evaluation will start from this node 
    * @param xpath 
    * @param expectedNodeCount
    */
   public static void assertNumberOfNodes(final String msg, final Element context,
         final String xpath, int expectedNodeCount) {

      @SuppressWarnings({ "unchecked", "rawtypes" })
      final XPathExpression<Element> xpathExpr = (XPathExpression<Element>) (XPathExpression) xpf
            .compile(xpath);

      final List<Element> matchedNodes = xpathExpr.evaluate(context);

      if (expectedNodeCount != matchedNodes.size()) {
         throw new AssertXpathResult(msg + "\nExpected " + expectedNodeCount
               + " node" + (expectedNodeCount == 1 ? "" : "s") + " for xpath '"
               + xpath + "' but found " + matchedNodes.size());
      }
   }

   /**
    * A given XPath expression is being evaluated for returning exactly one element node of simple text content
    * containing a given text.
    * 
    * @param msg
    * @param context
    * @param xpath
    * @param expectedTagName Will be evaluated unless being set to null
    * @param expectedContent Will be evaluated unless being set to null
    */
   public static void assertSingleNodeContent(final String msg,
         final Element context, final String xpath, final String expectedTagName,
         String expectedContent) {

      @SuppressWarnings({ "unchecked", "rawtypes" })
      final XPathExpression<Element> xpathExpr = (XPathExpression<Element>) (XPathExpression) xpf
            .compile(xpath);
      final List<Element> matchedNodes = xpathExpr.evaluate(context);

      if (1 == matchedNodes.size()) {
         final Element element = matchedNodes.get(0);

         if (null != expectedTagName) {
            final String actualTagName = element.getName();
            if (!expectedTagName.equals(actualTagName)) {
               throw new AssertXpathResult(msg + "\nExpected <" + expectedTagName
                     + "> for xpath '" + xpath + "' but found <" + actualTagName + ">");
            }
         }
         if (null != expectedContent) {
            final String actualContent = element.getValue();
            if (!expectedContent.equals(actualContent)) {
               throw new AssertXpathResult(msg + "\nExpected element content '"
                     + expectedContent + "' for xpath '" + xpath + "' but found '"
                     + actualContent + "'");
            }
         }
      } else {
         throw new AssertXpathResult("Exactly node expected for xpath '" + xpath
               + "' but found " + matchedNodes.size());
      }
   }
}
