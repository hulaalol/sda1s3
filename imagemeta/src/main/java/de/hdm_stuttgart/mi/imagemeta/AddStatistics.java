package de.hdm_stuttgart.mi.imagemeta;

import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 *
 */
public class AddStatistics {

	public static void main(String[] args) throws JDOMException, IOException {

		final SAXBuilder builder = new SAXBuilder();
		builder.setFeature("http://xml.org/sax/features/validation", false);
		builder.setFeature("http://xml.org/sax/features/namespaces", false);

		final Document htmlDoc = builder.build("text.xhtml");

		final Element html = htmlDoc.getRootElement();
		final Element body = html.getChild("body");

		addStatistics(body);

		final Format outFormat = Format.getPrettyFormat();
		final XMLOutputter printer = new XMLOutputter(outFormat);
		printer.output(htmlDoc, System.out);
	}

	private static void addStatistics(final Element body) {
		// Implement accordingly
	}
}
