package de.hdm_stuttgart.mi.sda1.scripts;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.DOMBuilder;
import org.jdom2.input.SAXBuilder;
import org.jdom2.input.sax.XMLReaderJDOMFactory;


public class DbHandler {

	Connection conn;
	
	/**
	 * Opening a database connection by using fixed properties
	 * JDBC URL "jdbc:mysql://localhost:3306/hdm", user  "hdmuser" and
	 * password "XYZ"
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException 
	 */
	public DbHandler() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/hdm", "hdmuser", "XYZ");
	}
	
	public static void main(String[] args) {
		
		try {
			DbHandler handler = new DbHandler();
			Driver.xml2rdbms(handler.conn);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}

}