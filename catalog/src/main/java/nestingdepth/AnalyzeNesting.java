package nestingdepth;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.text.html.HTML.Attribute;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import sax.MySaxErrorHandler;

public class AnalyzeNesting {

	int index = 1;

	// Though we are playing DOM here, a SAX parser still assembles our DOM
	// tree.
	private SAXBuilder builder = new SAXBuilder();

	/**
	 * Initialize for parsing.
	 * 
	 * @return
	 */
	public void initializeParser() {
		// Though an ErrorHandler is not strictly required it allows
		// for easier localization of XML document errors
		builder.setErrorHandler(new MySaxErrorHandler(System.out));
	}

	/**
	 * Descending a catalog till its &lt;item&gt; elements. For each product its
	 * name and order number are being written to the output.
	 * 
	 * @param filename
	 *            The catalog's filename to be parsed
	 * 
	 */
	public void process(final String filename) throws JDOMException,
	IOException {

		// Parsing our XML file being located below "resources"
		final Document docInput = builder.build(getClass().getClassLoader()
				.getResource(filename));

		class attribute2 {
			String name;
			String value;

			attribute2(String name, String value) {
				this.name = name;
				this.value = value;
			}
		}

		class analyzedElement {
			String elementname;
			int nestDepth;
			ArrayList<attribute2> attributes = new ArrayList<attribute2>();

			analyzedElement(Element element) {
				this.elementname = element.getName();

				Element current = element;

				while (current.getParent() instanceof Element) {
					element = current;
					this.nestDepth++;

					if (element.getParent() instanceof Element) {
						current = element.getParentElement();
					}
				}

				element.getAttributes()
				.stream()
				.forEach(
						attribute -> this.attributes
						.add(new attribute2(
								attribute.getName(), attribute
								.getValue())));
			}

		}

		HashMap<Integer, analyzedElement> children = new HashMap<Integer, analyzedElement>();

		final Element docRoot = docInput.getRootElement();
		children.put(index, new analyzedElement(docRoot));

		for (int i = 0; i < docRoot.getChildren().size(); i++) {

			Element el = docRoot.getChildren().get(i);
			children.put(++index, new analyzedElement(el));

			if (el.getChildren().size() > 0) {

				for (int k = 0; k < el.getChildren().size(); k++) {
					children.put(++index, new analyzedElement(el.getChildren()
							.get(k)));
				}

			}

		}

		children.values().stream().forEach(element -> {

			for (int p = 0; p < element.nestDepth; p++) {
				System.out.print("   ");
			}

			System.out.print(element.elementname + " nD=" + element.nestDepth);

			if (element.attributes.size() > 0) {

				System.out.print("    Attributes: ");

				for (attribute2 a : element.attributes) {
					System.out.print(a.name + "=\'" + a.value + "\' ");
				}
			}

			System.out.print("\n");

		});
	}
}
