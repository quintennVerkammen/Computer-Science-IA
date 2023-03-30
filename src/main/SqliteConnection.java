package main;
import java.sql.*;
import javax.swing.*;

public class SqliteConnection {
	private Admin curAdmin;
	private Student curStudent;
	private Connection conn;
	
	private Connection connect() {
		String url = "jdbc:sqlite:IA.db";
		Connection conn;
		try {
			conn = DriverManager.getConnection(url);
			System.out.println("CONNECTION SUCCESSFUL");
			return conn;
		}
		catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	public SqliteConnection() {
		this.conn = connect();
	}

	public void insertAdminDB(String values) {
		try {
			Statement stmt = (Statement) conn.createStatement();
			String insert = "INSERT INTO Admin(FullName,Username,Password) VALUES (" + values + ")";
			stmt.executeUpdate(insert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public boolean checkAdminLogin(String username, String password) {
		try {
			Statement stmt = (Statement) conn.createStatement(); 	// Creates a statement for the query
			String selectSql = "Select * from Admin";	// This query returns all elements in the Admin table of the database
			ResultSet rs = stmt.executeQuery(selectSql);	// All Admin elements are returned in the form of a ResultSet
            while (rs.next()) {	// Loops through the entire ResultSet to check if the input credentials fit any given entry
            	if (rs.getString("Username").equals(username) && rs.getString("Password").equals(password)) {
            		curAdmin = new Admin(rs.getString("FullName"));
            		return true;
            	}
            }
		} catch (SQLException e) {	// If any error were to occur in the data retrieval process the error is 
			e.printStackTrace();	// anticipated so that the program does not crash on the user
			return false;
		}
		return false;
	}
	
	public Admin getCurAdmin() {
		return curAdmin;
	}
	
	public void createClass(String className, String yearGroup, String classTeacher) {
		try {
			Statement stmt = (Statement) conn.createStatement();
			String insert = "INSERT INTO Class(ClassName,ClassTeacher,YearGroup) VALUES ('" + className + "','" + classTeacher + "','" + yearGroup + "'" + ")";
			stmt.executeUpdate(insert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public int countAdminClasses() {
		try {
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT count(*) from Class where ClassTeacher = " + "'" + curAdmin.getFullName()+ "'");
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public void getAdminClasses() {
		try {
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from Class where ClassTeacher = " + "'" + curAdmin.getFullName()+ "'");
			curAdmin.setAdminClasses(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getClassData() {
		try {
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from Student where ClassTeacher = " + "'" + curAdmin.getFullName()+ "'");
			curAdmin.setAdminClasses(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void logOutAdmin() {
		curAdmin.logOut();
	}
	
	public void addStudent(String studentName, String curClassName, double average) {
		try {
			Statement stmt = (Statement) conn.createStatement();
			// curStudent = new Student(studentName, curClass, curAdmin.getFullName(), average);
			String insert = "INSERT INTO Student(FullName,ClassName,Teacher,Average) VALUES ('" + studentName + "','" + curClassName + "','" + curAdmin.getFullName() + "','" + average + "')";
			stmt.executeUpdate(insert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void getClassStudents() {
		try {
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from Student where Teacher = '" + curAdmin.getFullName()+ "' AND ClassName = '" + curAdmin.getCurClassName() + "'");
			curAdmin.setCurStudents(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Student getCurStudent() {
		return curStudent;
	}
	
	public void setCurStudent(String studentName) {
		try {
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * from Student where Teacher = " + "'" + curAdmin.getFullName()+ "' AND ClassName = '" + curAdmin.getCurClassName() + "' AND FullName = '" + studentName + "'");
			curStudent = new Student(rs.getString("FullName"), rs.getString("ClassName"), rs.getString("Teacher"), Double.parseDouble(rs.getString("Average")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateStudentData() {
		try {
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeUpdate("UPDATE Student SET FullName = '" + curStudent.getName() + "', Average = '" + curStudent.getAverage() + "', Comment = '" + curStudent.getComment() + "' WHERE Teacher = '" + curAdmin.getFullName()+ "' AND ClassName = '" + curAdmin.getCurClassName() + "' AND FullName = '" + curStudent.getName() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
