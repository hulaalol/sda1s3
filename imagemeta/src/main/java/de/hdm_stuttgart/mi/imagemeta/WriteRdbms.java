package de.hdm_stuttgart.mi.imagemeta;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 * Writing XML data to an RDBMS
 */
public class WriteRdbms {

	static {// Register Database driver
		final String jdbcDriverClassName = "com.mysql.jdbc.Driver";
		try {
			Class.forName(jdbcDriverClassName);
		} catch (ClassNotFoundException e) {
			System.err.println("Unable to register driver '" + jdbcDriverClassName + "'");
		}
	}
	
	public static void main(String[] args) throws JDOMException, IOException {

		
		final SAXBuilder builder = new SAXBuilder();
		builder.setFeature("http://xml.org/sax/features/validation", false);
		builder.setFeature("http://xml.org/sax/features/namespaces", false);

		final Document htmlDoc = builder.build("text.xhtml");

		final Element html = htmlDoc.getRootElement();
		final Element body = html.getChild("body");

		writeRdbms(body);
	}
	
	private static void writeRdbms(final Element body) {
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hdm",
					"hdmuser", "XYZ");
		} catch (SQLException e) {
			System.err.println("Unable to connect to database server");
			return;
		}
		// Complete this implementation accordingly
	}
}