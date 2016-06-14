package de.hdm_stuttgart.mi.sda1.sql2catalog;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jdom2.Attribute;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * A simple SAX parser demo
 * 
 */
public class Rdbms2Xml {

   /**
    * @param args Unused
 * @throws SQLException 
    */
   public static void main(String[] args) throws SQLException {
      
      // Register Driver
      final String sqlDriverClassName = "com.mysql.jdbc.Driver"; 
      try {
         Class.forName(sqlDriverClassName);
      } catch (ClassNotFoundException e) {
         Helper.exitWithErrorMessage("Unable to register driver class '" + sqlDriverClassName + "'", 1);
      }

      // Opening a JDBC connection
      // 
      Connection conn = null;
      
      final String jdbcConnectionUrl = "jdbc:mysql://localhost:3306/hdm"; 
      final String userName = "hdmuser"; 
      try {
         conn = DriverManager.getConnection(jdbcConnectionUrl, userName, "XYZ"); 
      } catch (SQLException e) {
         Helper.exitWithErrorMessage("Unable to connect as user '" + userName + "' to '" + 
               jdbcConnectionUrl + "'", 1); 
      }

      
      RdbmsAccess dataGetter = new RdbmsAccess(conn);
      final Element universityElement = new Element("university");
      
      
      
      

      final Element studyCourses = new Element("studyCourses");
      
      for(StudyCourse sc : dataGetter.courseList){
    	  
    	  final Element course = new Element("studyCourse");
    	  course.setAttribute("id", sc.id);
    	  
    	  final Element name = new Element("name");
    	  name.addContent(sc.name);
    	  course.addContent(name);
    	  
    	  
    	  if(sc.shortName != null){
    		  final Element shortName = new Element("shortName");
        	  shortName.addContent(sc.shortName);
        	  course.addContent(shortName);
    	  }
    	  
    	  if(sc.program.equals("B") || sc.program.equals("M")){
    		  course.setAttribute("program", ""+sc.program);
    	  }

    	  studyCourses.addContent(course);
      }
      

      universityElement.addContent(studyCourses);

      
      final Element students = new Element("students");
      
      for(Student s : dataGetter.studentList){
    	  
    	  final Element student = new Element("student");
    	  student.setAttribute("id", s.id);
    	  
    	  final Element fullName = new Element("fullName");
    	  fullName.addContent(s.fullName);
    	  
    	  final Element email = new Element("email");
    	  email.addContent(s.email);
    	  
    	  if(s.studyCourse != null){
    		  student.setAttribute("studyCourse", ""+s.studyCourse);
    	  }
    	  
    	  student.addContent(fullName);
    	  student.addContent(email);
    	  
    	  students.addContent(student);

      }

      universityElement.addContent(students);
      
      // Write XML content to standard output
      XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
      try {
         outputter.output(universityElement, System.out);
      } catch (IOException e) {
         Helper.exitWithErrorMessage("Unable to write XML data:" + e.getLocalizedMessage(), 1);
      }
   }
}
