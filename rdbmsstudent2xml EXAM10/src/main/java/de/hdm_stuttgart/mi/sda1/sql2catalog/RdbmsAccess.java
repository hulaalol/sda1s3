package de.hdm_stuttgart.mi.sda1.sql2catalog;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Statement;

/**
 * Reading study course and student data
 *
 */
public class RdbmsAccess {
	
	
	ArrayList<Student> studentList = new ArrayList<>();
	ArrayList<StudyCourse> courseList = new ArrayList<>();
	
	
	
	/**
	 * @param conn Destination RDBMS
	 * @throws SQLException 
	 */
	public RdbmsAccess(final Connection conn) throws SQLException {
		
	final java.sql.Statement getStudents = conn.createStatement();
	ResultSet studentRS = getStudents.executeQuery("SELECT * FROM Student");
	
	final java.sql.Statement getCourses = conn.createStatement();
	ResultSet coursesRS = getCourses.executeQuery("SELECT * FROM StudyCourse");	
	
	
	while(studentRS.next()){
		
		String id = studentRS.getString("id");
		String fullName = studentRS.getString("fullName");
		String email = studentRS.getString("email");
		String studyCourse = studentRS.getString("studyCourse");
				
		studentList.add(new Student(id,fullName,email,studyCourse));
	}
	
	
	while(coursesRS.next()){
		
		String id =coursesRS.getString("id");
		String name = coursesRS.getString("name");
		String shortName = coursesRS.getString("shortName");
		String program = coursesRS.getString("program");

		courseList.add(new StudyCourse(id,name,shortName,program));

	}

	}

}
