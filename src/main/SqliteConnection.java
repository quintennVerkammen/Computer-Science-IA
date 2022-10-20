package main;
import java.sql.*;
import javax.swing.*;

public class SqliteConnection {
	private Admin curAdmin;
	private Student curStudent;
	public Connection conn;
	
	private Connection connect() {
		String url = "jdbc:sqlite:C:\\Users\\quint\\eclipse-workspace\\IA.db";
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
			Statement stmt = (Statement) conn.createStatement();
			
			String selectSql = "Select * from Admin";
			ResultSet rs = stmt.executeQuery(selectSql);
            while (rs.next()) {
            	if (rs.getString("Username").equals(username) && rs.getString("Password").equals(password)) {
            		curAdmin = new Admin(rs.getString("FullName"));
            		return true;
            	}
            }
		} catch (SQLException e) {
			e.printStackTrace();
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
	
	public void updateStudentData(String studentName, String average, String comment) {
		try {
			Statement stmt = (Statement) conn.createStatement();
			stmt.executeUpdate("UPDATE Student SET FullName = '" + studentName + "', Average = '" + average + "', Comment = '" + comment + "' WHERE Teacher = '" + curAdmin.getFullName()+ "' AND ClassName = '" + curAdmin.getCurClassName() + "' AND FullName = '" + studentName + "'");
			// setCurStudent(studentName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
 