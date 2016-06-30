package de.hdm_stuttgart.mi.sda1.scripts;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class Driver {

	static void xml2rdbms(Connection conn) {

		SAXBuilder builder = new SAXBuilder();
		Document xmlDoc = null;

		try {
			xmlDoc = builder.build(new File("Schema/sampledata.xml"));

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Element top = xmlDoc.getRootElement();
		final List<Element> Languages = top.getChild("languages").getChildren("language");
		Iterator<Element> langIt = Languages.iterator();

		while (langIt.hasNext()) {

			Element lang = (Element) langIt.next();
			
			try {
				PreparedStatement langInsert = conn.prepareStatement("INSERT INTO languages(language) VALUES(?);");
				langInsert.setString(1, lang.getText());
				langInsert.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		final List<Element> scriptList = top.getChild("scripts").getChildren("script");
		Iterator<Element> scriptIterator = scriptList.iterator();

		String sqlScript = "INSERT INTO scripts(script_id,description,sourcecode) VALUES(?,?,?);";
		PreparedStatement prepInsScript = null;
		try {
			prepInsScript = conn.prepareStatement(sqlScript);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		while (scriptIterator.hasNext()) {

			Element script = (Element) scriptIterator.next();

			String id = script.getAttributeValue("id");
			String desc = script.getChildText("description");
			String src = script.getChildText("sourcecode");

			try {
				prepInsScript.setString(1, id);
				prepInsScript.setString(2, desc);
				prepInsScript.setString(3, src);
				prepInsScript.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}

			String scriptLangString = "INSERT INTO script_language(script_id,language) VALUES(?,?);";
			PreparedStatement scriptLangInsert = null;

			if (script.getChild("languages") != null) {

				final List<Element> scriptLangList = script.getChild("languages").getChildren("language");
				Iterator<Element> scriptLangIterator = scriptLangList.iterator();

				while (scriptLangIterator.hasNext()) {

					Element lang = (Element) scriptLangIterator.next();

					try {
						scriptLangInsert = conn.prepareStatement(scriptLangString);
						scriptLangInsert.setString(1, id);
						scriptLangInsert.setString(2, lang.getText());
						scriptLangInsert.executeUpdate();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			} else if (script.getChild("language") != null) {

				try {
					scriptLangInsert = conn.prepareStatement(scriptLangString);
					scriptLangInsert.setString(1, id);
					scriptLangInsert.setString(2, script.getChild("language").getText());
					scriptLangInsert.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
