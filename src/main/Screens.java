package main;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;

public class Screens {
	SqliteConnection dbConnection = new SqliteConnection();
	private JFrame frame;

	// All UI elements for the home screen
	private JButton btnOldUser;
	private JButton btnNewUser;
	
	// All UI elements for the registration screen

	private JTextField txtUserRegistration;
	private JTextField txtFullName;
	private JTextField txtUsernameReg;
	private JTextField txtPasswordReg;
	private JTextField txtConfirmPassword;
	
	private JFormattedTextField fullNameRegField;
	private JFormattedTextField uNameRegField;

	private JButton btnBackRegScreen;
	private JButton btnSubmitReg;
	
	private JPasswordField passwordRegField;
	private JPasswordField passwordRegConfirmField;
	
	// All UI elements for the login screen
	private JTextField txtLoginPage;
	private JTextField txtUsernameLogin;
	private JTextField txtPasswordLogin;

	private JFormattedTextField usernameLoginField;
	
	private JButton btnLogin;
	private JButton btnBackFromLoginScreen;

	private JPasswordField passwordLoginField;
	
	
	// All UI elements for the admin home screen
	private JTextField txtAllClasses;
	private JTextField txtCreateNewClass;
	private JTextField txtYearGroup;
	private JTextField txtClassName;
	
	private JFormattedTextField classNameField;
	private JFormattedTextField yearGroupField;
	
	private JButton btnCreateClass;
	private JButton btnRefreshClassList;
	private JButton btnViewClass;
	private JButton btnBackToLogin;

	private JList<String> classList;
	private JScrollBar classListScroll;

	// All UI elements for the class managing screen
	private JTextField txtAllStudents;
	private JTextField txtAddNewStudent;
	private JTextField txtStudentName;
	private JTextField txtStudentAverage;
	
	private JFormattedTextField studentNameField;
	private JFormattedTextField studentAverageField;

	private JButton btnAddStudent;
	private JButton btnRefreshStudentList;
	private JButton btnViewStudent;
	private JButton btnBackToClassScreen;

	private JList<String> studentList;
	private JScrollBar studentListScroll;
	
	// All UI elements for the student report screen
	private JTextField txtStudentTitle;
	private JTextField txtNameOfStudent;
	private JTextField txtAverage;
	private JTextField txtComment;
	
	private JFormattedTextField studentNameEditField;
	private JFormattedTextField studentAverageEditField;
	private JFormattedTextField studentCommentEditField;
	
	private JButton btnSaveStudentInfo;
	private JButton btnGenerateComment;
	private JButton btnBackToStudentsScreen;
	
	// Constructor for the window. Initializes the window and shows the first screen
	public Screens() {
		initialize();
		startScreen();
		this.frame.setVisible(true);
	}

	//Initialize the contents of the frame.
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Student Report Card Manager");
		frame.setBounds(500, 500, 1080, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
	
	// Method used to display the starting screen
	public void startScreen() {

		btnOldUser = new JButton("Existing User");
		btnOldUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnOldUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearStartScreen();
				loginScreen();
			}
		});
		btnOldUser.setBounds(395, 156, 264, 105);
		frame.getContentPane().add(btnOldUser);
		
		btnNewUser = new JButton("New User");
		btnNewUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearStartScreen();
				registrationScreen();
			}
		});
		btnNewUser.setBounds(395, 333, 264, 105);
		frame.getContentPane().add(btnNewUser);
		frame.repaint();
	}
	
	
	// Simply removes all buttons on the start screen
	private void clearStartScreen() {
		btnOldUser.setVisible(false);
		frame.remove(btnOldUser);
		
		btnNewUser.setVisible(false);
		frame.remove(btnNewUser);
	}
	
	private void registrationScreen() {
		frame.getContentPane().setLayout(null);
		
		btnBackRegScreen = new JButton("BACK");
		btnBackRegScreen.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnBackRegScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearRegScreen();
				startScreen();
			}
		});
		btnBackRegScreen.setBounds(680, 300, 168, 80);
		btnBackRegScreen.setEnabled(true);
		frame.getContentPane().add(btnBackRegScreen);
		
		btnSubmitReg = new JButton("Submit");
		btnSubmitReg.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSubmitReg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] pwMain = passwordRegField.getPassword();
				String pwordMain = new String (pwMain);
				
				char[] pwCheck = passwordRegConfirmField.getPassword();
				String pwordCheck = new String (pwCheck);
				
				
				if (fullNameRegField.getText().equals("") || uNameRegField.getText().equals("") || pwordMain.equals("") || pwordCheck.equals("")) {
					JOptionPane.showMessageDialog(null, "Not all fields have been filled. Please try again.");
				}
				else if (!pwordMain.equals(pwordCheck)) {
					JOptionPane.showMessageDialog(null, "Passwords do not match. Please try again.");
				}
				else {
					dbConnection.insertAdminDB("'" + fullNameRegField.getText() + "','" + uNameRegField.getText() + "','" + pwordMain + "'");
					JOptionPane.showMessageDialog(null, "Registration Successful!");
					clearRegScreen();
					startScreen();
				}
			}
		});
		btnSubmitReg.setBounds(200, 350, 180, 80);
		btnSubmitReg.setEnabled(true);
		frame.getContentPane().add(btnSubmitReg);
		
		
		txtUserRegistration = new JTextField();
		txtUserRegistration.setHorizontalAlignment(SwingConstants.CENTER);
		txtUserRegistration.setFont(new Font("Tahoma", Font.PLAIN, 36));
		txtUserRegistration.setText("User Registration");
		txtUserRegistration.setBounds(308, 32, 405, 113);
		txtUserRegistration.setEditable(false);
		frame.getContentPane().add(txtUserRegistration);
		txtUserRegistration.setColumns(10);
		
		txtFullName = new JTextField();
		txtFullName.setHorizontalAlignment(SwingConstants.RIGHT);
		txtFullName.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtFullName.setText("Full Name:");
		txtFullName.setColumns(10);
		txtFullName.setEditable(false);
		txtFullName.setBounds(50, 172, 180, 33);
		frame.getContentPane().add(txtFullName);
		
		fullNameRegField = new JFormattedTextField();
		fullNameRegField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		fullNameRegField.setBounds(txtFullName.getX() + 190, txtFullName.getY(), 196, 33);
		frame.getContentPane().add(fullNameRegField);
		
		txtUsernameReg = new JTextField();
		txtUsernameReg.setText("Username:");
		txtUsernameReg.setHorizontalAlignment(SwingConstants.RIGHT);
		txtUsernameReg.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtUsernameReg.setColumns(20);
		txtUsernameReg.setEditable(false);
		txtUsernameReg.setBounds(txtFullName.getX(), txtFullName.getY() + 65, 180, 33);
		frame.getContentPane().add(txtUsernameReg);
		
		uNameRegField = new JFormattedTextField();
		uNameRegField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		uNameRegField.setBounds(txtUsernameReg.getX() + 190, txtUsernameReg.getY(), 196, 33);
		frame.getContentPane().add(uNameRegField);
		
		txtPasswordReg = new JTextField();
		txtPasswordReg.setText("Password");
		txtPasswordReg.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPasswordReg.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtPasswordReg.setColumns(10);
		txtPasswordReg.setEditable(false);
		txtPasswordReg.setBounds(txtFullName.getX() + 400, txtFullName.getY(), 180, 33);
		frame.getContentPane().add(txtPasswordReg);
		
		passwordRegField = new JPasswordField();
		passwordRegField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordRegField.setToolTipText("Enter your password here.");
		passwordRegField.setBounds(txtPasswordReg.getX() + 190, txtFullName.getY(), 196, 33);
		frame.getContentPane().add(passwordRegField);
		
		txtConfirmPassword = new JTextField();
		txtConfirmPassword.setText("Confirm  Password");
		txtConfirmPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		txtConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtConfirmPassword.setColumns(10);
		txtConfirmPassword.setEditable(false);
		txtConfirmPassword.setBounds(txtPasswordReg.getX(), txtPasswordReg.getY() + 65, 180, 33);
		frame.getContentPane().add(txtConfirmPassword);
		
		passwordRegConfirmField = new JPasswordField();
		passwordRegConfirmField.setToolTipText("Enter your password here.");
		passwordRegConfirmField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordRegConfirmField.setBounds(txtConfirmPassword.getX() + 190, txtConfirmPassword.getY(), 196, 33);
		frame.getContentPane().add(passwordRegConfirmField);
		
		frame.repaint();
	}
	
	private void clearRegScreen() {
		txtUserRegistration.setVisible(false);
		frame.remove(txtUserRegistration);
		
		txtFullName.setVisible(false);
		frame.remove(txtFullName);
		
		fullNameRegField.setVisible(false);
		frame.remove(fullNameRegField);
		
		txtUsernameReg.setVisible(false);
		frame.remove(txtUsernameReg);
		
		uNameRegField.setVisible(false);
		frame.remove(uNameRegField);
		
		txtPasswordReg.setVisible(false);
		frame.remove(txtPasswordReg);
		
		passwordRegField.setVisible(false);
		frame.remove(passwordRegField);
		
		txtConfirmPassword.setVisible(false);
		frame.remove(txtConfirmPassword);
		
		passwordRegConfirmField.setVisible(false);
		frame.remove(passwordRegConfirmField);
		
		btnBackRegScreen.setVisible(false);
		frame.remove(btnBackRegScreen);
		
		btnSubmitReg.setVisible(false);
		frame.remove(btnSubmitReg);
	}
	
	private void loginScreen() {
		txtLoginPage = new JTextField();
		txtLoginPage.setHorizontalAlignment(SwingConstants.CENTER);
		txtLoginPage.setFont(new Font("Tahoma", Font.PLAIN, 36));
		txtLoginPage.setText("User Login");
		txtLoginPage.setBounds(308, 32, 405, 113);
		txtLoginPage.setEditable(false);
		frame.getContentPane().add(txtLoginPage);
		txtLoginPage.setColumns(10);
		
		txtUsernameLogin = new JTextField();
		txtUsernameLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtUsernameLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		txtUsernameLogin.setText("Username:");
		txtUsernameLogin.setEditable(false);
		txtUsernameLogin.setBounds(308, 253, 143, 32);
		frame.getContentPane().add(txtUsernameLogin);
		txtUsernameLogin.setColumns(10);
		
		usernameLoginField = new JFormattedTextField();
		usernameLoginField.setBounds(479, 253, 163, 32);
		frame.getContentPane().add(usernameLoginField);
		usernameLoginField.setColumns(10);
		
		txtPasswordLogin = new JTextField();
		txtPasswordLogin.setText("Password");
		txtPasswordLogin.setHorizontalAlignment(SwingConstants.RIGHT);
		txtPasswordLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtPasswordLogin.setColumns(10);
		txtPasswordLogin.setEditable(false);
		txtPasswordLogin.setBounds(308, 327, 143, 32);
		frame.getContentPane().add(txtPasswordLogin);
		
		passwordLoginField = new JPasswordField();
		passwordLoginField.setColumns(10);
		passwordLoginField.setBounds(479, 327, 163, 32);
		frame.getContentPane().add(passwordLoginField);
		
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnLogin.setBounds(385, 420, 196, 81);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] pw = passwordLoginField.getPassword();
				String pwordMain = new String (pw);
				
				if (usernameLoginField.getText().equals("") || pwordMain.equals("")) {
					JOptionPane.showMessageDialog(null, "Not all fields have been entered. Please try again.");
				}
				else if (dbConnection.checkAdminLogin(usernameLoginField.getText(), pwordMain)) {
					JOptionPane.showMessageDialog(null, "Login Successful!");
					clearLoginScreen();
					adminHomeScreen();
				}
				else {
					JOptionPane.showMessageDialog(null, "Username and password do not match. Please try again.");
				}
			}
		});
		frame.getContentPane().add(btnLogin);
		

		btnBackFromLoginScreen = new JButton("BACK");
		btnBackFromLoginScreen.setFont(new Font("Tahoma", Font.BOLD, 24));
		btnBackFromLoginScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearLoginScreen();
				startScreen();
			}
		});
		btnBackFromLoginScreen.setBounds(680, 300, 168, 80);
		btnBackFromLoginScreen.setEnabled(true);
		frame.getContentPane().add(btnBackFromLoginScreen);
		
		frame.repaint();
	}
	
	private void clearLoginScreen() {
		txtLoginPage.setVisible(false);
		frame.getContentPane().remove(txtLoginPage);
		
		txtUsernameLogin.setVisible(false);
		frame.getContentPane().remove(txtUsernameLogin);
		
		usernameLoginField.setVisible(false);
		frame.getContentPane().remove(usernameLoginField);
		
		txtPasswordLogin.setVisible(false);
		frame.getContentPane().remove(txtPasswordLogin);
		
		passwordLoginField.setVisible(false);
		frame.getContentPane().remove(passwordLoginField);
		
		btnLogin.setVisible(false);
		frame.getContentPane().remove(btnLogin);
		
		btnBackFromLoginScreen.setVisible(false);
		frame.getContentPane().remove(btnBackFromLoginScreen);
	}
	
	private void adminHomeScreen() {
		txtAllClasses = new JTextField();
		txtAllClasses.setBackground(new Color(255, 255, 255));
		txtAllClasses.setHorizontalAlignment(SwingConstants.CENTER);
		txtAllClasses.setFont(new Font("Tahoma", Font.BOLD, 36));
		txtAllClasses.setEditable(false);
		txtAllClasses.setText("All Classes");
		txtAllClasses.setBounds(161, 73, 363, 83);
		frame.getContentPane().add(txtAllClasses);
		txtAllClasses.setColumns(10);
		
		classList = new JList();
		frame.getContentPane().add(classList);
		
		updateAdminClassList();
		
		txtCreateNewClass = new JTextField();
		txtCreateNewClass.setText("Create New Class");
		txtCreateNewClass.setHorizontalAlignment(SwingConstants.CENTER);
		txtCreateNewClass.setFont(new Font("Tahoma", Font.BOLD, 36));
		txtCreateNewClass.setEditable(false);
		txtCreateNewClass.setColumns(10);
		txtCreateNewClass.setBackground(Color.WHITE);
		txtCreateNewClass.setBounds(621, 73, 363, 83);
		frame.getContentPane().add(txtCreateNewClass);
		
		txtYearGroup = new JTextField();
		txtYearGroup.setBackground(new Color(255, 255, 255));
		txtYearGroup.setHorizontalAlignment(SwingConstants.RIGHT);
		txtYearGroup.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtYearGroup.setText("Year Group:");
		txtYearGroup.setEditable(false);
		txtYearGroup.setBounds(621, 186, 171, 34);
		frame.getContentPane().add(txtYearGroup);
		txtYearGroup.setColumns(10);
		
		yearGroupField = new JFormattedTextField();
		yearGroupField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		yearGroupField.setBounds(813, 186, 171, 34);
		frame.getContentPane().add(yearGroupField);
		
		txtClassName = new JTextField();
		txtClassName.setText("Class Name:");
		txtClassName.setHorizontalAlignment(SwingConstants.RIGHT);
		txtClassName.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtClassName.setEditable(false);
		txtClassName.setColumns(10);
		txtClassName.setBackground(Color.WHITE);
		txtClassName.setBounds(621, 245, 171, 34);
		frame.getContentPane().add(txtClassName);
		
		classNameField = new JFormattedTextField();
		classNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		classNameField.setBounds(813, 245, 171, 34);
		frame.getContentPane().add(classNameField);
		
		btnCreateClass = new JButton("Create Class");
		btnCreateClass.setFont(new Font("Tahoma", Font.BOLD, 26));
		btnCreateClass.setBounds(684, 321, 257, 97);
		btnCreateClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (yearGroupField.getText().equals("") || classNameField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Not all fields have been entered. Please try again.");
				}
				else {
					dbConnection.createClass(classNameField.getText(), yearGroupField.getText(), dbConnection.getCurAdmin().getFullName());
					JOptionPane.showMessageDialog(null, "Class created successfully!");
				}
			}
		});
		frame.getContentPane().add(btnCreateClass);
		
		
		btnBackToLogin = new JButton("Log Out");
		btnBackToLogin.setFont(new Font("Tahoma", Font.BOLD, 26));
		btnBackToLogin.setBounds(684, 500, 257, 97);
		btnBackToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int logOut = JOptionPane.showConfirmDialog(null, "Do you want to log out? You will need to re-enter your credentials.");
				if (logOut == 0) {
					System.out.println("LOGGED OUT SUCCESSFULLY");
					dbConnection.logOutAdmin();
					clearAdminHomeScreen();
					loginScreen();
				}
				else if (logOut == 1 || logOut == 2) {
					System.out.println("DID NOT LOG OUT");
				}
			}
		});
		frame.getContentPane().add(btnBackToLogin);

		btnViewClass = new JButton("View Class");
		btnViewClass.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnViewClass.setBounds(161, 164, 120, 23);
		btnViewClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("View Class");
				if (classList.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Please select a class to view.");
				}
				else {
					dbConnection.getCurAdmin().setCurClass(classList.getSelectedValue().toString());
					clearAdminHomeScreen();;
					classDataScreen();
				}
			}
		});
		frame.getContentPane().add(btnViewClass);
		
		btnRefreshClassList = new JButton("Refresh");
		btnRefreshClassList.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRefreshClassList.setBounds(435, 164, 89, 23);
		btnRefreshClassList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateAdminClassList();
			}
		});
		frame.getContentPane().add(btnRefreshClassList);
		
		frame.repaint();
	}
	
	private void updateAdminClassList() {
		frame.getContentPane().remove(classList);
		ArrayList<String> classArray = new ArrayList<String>();
		
		dbConnection.getAdminClasses();
		try {
			while (dbConnection.getCurAdmin().getAdminClasses().next()) {
				classArray.add(dbConnection.getCurAdmin().getAdminClasses().getString("YearGroup")/* + " - " + dbConnection.getCurAdmin().getAdminClasses().getString("ClassName")*/);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		classList = new JList(classArray.toArray());
		classList.setBorder(new LineBorder(new Color(0, 0, 0)));
		classList.setBounds(161, 187, 363, 461);
		classList.setToolTipText("All of your classes.");
		classList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		classList.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		frame.getContentPane().add(classList);
		frame.repaint();
	}
	
	private void clearAdminHomeScreen() {
		txtAllClasses.setVisible(false);
		frame.getContentPane().remove(txtAllClasses);

		classList.setVisible(false);
		frame.getContentPane().remove(classList);
	
		txtCreateNewClass.setVisible(false);
		frame.getContentPane().remove(txtCreateNewClass);
	
		txtYearGroup.setVisible(false);
		frame.getContentPane().remove(txtYearGroup);
		
		yearGroupField.setVisible(false);
		frame.getContentPane().remove(yearGroupField);
	
		txtClassName.setVisible(false);
		frame.getContentPane().remove(txtClassName);
		
		classNameField.setVisible(false);
		frame.getContentPane().remove(classNameField);
		
		btnCreateClass.setVisible(false);
		frame.getContentPane().remove(btnCreateClass);
		
		btnBackToLogin.setVisible(false);
		frame.getContentPane().remove(btnBackToLogin);
	
		btnViewClass.setVisible(false);
		frame.getContentPane().remove(btnViewClass);
		
		btnRefreshClassList.setVisible(false);
		frame.getContentPane().remove(btnRefreshClassList);
		
		frame.repaint();
	}
	
	private void classDataScreen() {
		txtAllStudents = new JTextField();
		txtAllStudents.setBackground(new Color(255, 255, 255));
		txtAllStudents.setHorizontalAlignment(SwingConstants.CENTER);
		txtAllStudents.setFont(new Font("Tahoma", Font.BOLD, 24));
		txtAllStudents.setEditable(false);
		txtAllStudents.setText("All Students in class " + dbConnection.getCurAdmin().getCurClassName());
		txtAllStudents.setBounds(161, 73, 363, 83);
		frame.getContentPane().add(txtAllStudents);
		
		studentList = new JList();
		frame.getContentPane().add(studentList);
		updateStudentList();
		
		txtAddNewStudent = new JTextField();
		txtAddNewStudent.setText("Add New Student");
		txtAddNewStudent.setHorizontalAlignment(SwingConstants.CENTER);
		txtAddNewStudent.setFont(new Font("Tahoma", Font.BOLD, 36));
		txtAddNewStudent.setEditable(false);
		txtAddNewStudent.setColumns(10);
		txtAddNewStudent.setBackground(Color.WHITE);
		txtAddNewStudent.setBounds(621, 73, 363, 83);
		frame.getContentPane().add(txtAddNewStudent);
		
		txtStudentName = new JTextField();
		txtStudentName.setBackground(new Color(255, 255, 255));
		txtStudentName.setHorizontalAlignment(SwingConstants.RIGHT);
		txtStudentName.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtStudentName.setText("Student Name");
		txtStudentName.setEditable(false);
		txtStudentName.setBounds(621, 186, 171, 34);
		txtStudentName.setColumns(10);
		frame.getContentPane().add(txtStudentName);
		
		studentNameField = new JFormattedTextField();
		studentNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		studentNameField.setBounds(813, 186, 171, 34);
		frame.getContentPane().add(studentNameField);
		
		txtStudentAverage = new JTextField();
		txtStudentAverage.setText("Student Average:");
		txtStudentAverage.setHorizontalAlignment(SwingConstants.RIGHT);
		txtStudentAverage.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtStudentAverage.setEditable(false);
		txtStudentAverage.setColumns(10);
		txtStudentAverage.setBackground(Color.WHITE);
		txtStudentAverage.setBounds(621, 245, 171, 34);
		frame.getContentPane().add(txtStudentAverage);
		
		studentAverageField = new JFormattedTextField();
		studentAverageField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		studentAverageField.setBounds(813, 245, 171, 34);
		frame.getContentPane().add(studentAverageField);
		
		btnAddStudent = new JButton("Add Student");
		btnAddStudent.setFont(new Font("Tahoma", Font.BOLD, 26));
		btnAddStudent.setBounds(684, 321, 257, 97);
		btnAddStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double average;
				
				// Tries to convert the text field into an number to verify whether it is a valid input
				try {
					average = Double.parseDouble(studentAverageField.getText());
				} catch (NumberFormatException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Please enter a number in the \"Student Average\" field");
					return;
				}
				// Verifies whether the user input all the correct information
				if (studentNameField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter a name for the student.");
				}
				else if (average < 0 || average > 100) {
					JOptionPane.showMessageDialog(null, "Please enter a valid average for the student (Between 0 and 100%).");
				}
				// If no problems were detected, the student is added to the database
				else {
					dbConnection.addStudent(studentNameField.getText(), classList.getSelectedValue().toString(), average);
					studentNameField.setText("");
					studentAverageField.setText("");
					JOptionPane.showMessageDialog(null, "Student added successfully!");
				}
			}
		});
		frame.getContentPane().add(btnAddStudent);
		
		
		btnBackToClassScreen = new JButton("Back");
		btnBackToClassScreen.setFont(new Font("Tahoma", Font.BOLD, 26));
		btnBackToClassScreen.setBounds(684, 500, 257, 97);
		btnBackToClassScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearClassDataScreen();
				adminHomeScreen();
			}
		});
		frame.getContentPane().add(btnBackToClassScreen);

		btnViewStudent = new JButton("View Student");
		btnViewStudent.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnViewStudent.setBounds(161, 164, 120, 23);
		btnViewStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (studentList.getSelectedIndex() == -1) {
					JOptionPane.showMessageDialog(null, "Please select a student to view.");
				}
				else {
					dbConnection.setCurStudent(studentList.getSelectedValue().toString());
					System.out.println(dbConnection.getCurStudent().getName());
					clearClassDataScreen();
					viewStudentScreen();
				}
			}
		});
		frame.getContentPane().add(btnViewStudent);
		
		btnRefreshStudentList = new JButton("Refresh");
		btnRefreshStudentList.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRefreshStudentList.setBounds(435, 164, 89, 23);
		btnRefreshStudentList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStudentList();
			}
		});
		frame.getContentPane().add(btnRefreshStudentList);
		
		frame.repaint();
	}

	private void updateStudentList() {
		frame.getContentPane().remove(studentList);
		ArrayList<String> studentArray = new ArrayList<String>();
		
		dbConnection.getClassStudents();
		// Creates an array with all students who are in the selected class
		try {
			while (dbConnection.getCurAdmin().getCurStudents().next()) {
				studentArray.add(dbConnection.getCurAdmin().getCurStudents().getString("FullName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		studentList = new JList(studentArray.toArray());
		studentList.setBorder(new LineBorder(new Color(0, 0, 0)));
		studentList.setBounds(161, 187, 363, 461);
		studentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		studentList.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		frame.getContentPane().add(studentList);
		
		frame.repaint();
	}
	
	private void clearClassDataScreen() {
		txtAllStudents.setVisible(false);
		frame.getContentPane().remove(txtAllStudents);

		studentList.setVisible(false);
		frame.getContentPane().remove(studentList);
	
		txtAddNewStudent.setVisible(false);
		frame.getContentPane().remove(txtAddNewStudent);
	
		txtStudentName.setVisible(false);
		frame.getContentPane().remove(txtStudentName);
		
		studentNameField.setVisible(false);
		frame.getContentPane().remove(studentNameField);

		txtStudentAverage.setVisible(false);
		frame.getContentPane().remove(txtStudentAverage);
		
		studentAverageField.setVisible(false);
		frame.getContentPane().remove(studentAverageField);
		
		btnAddStudent.setVisible(false);
		frame.getContentPane().remove(btnAddStudent);
		
		btnBackToClassScreen.setVisible(false);
		frame.getContentPane().remove(btnBackToClassScreen);
	
		btnViewStudent.setVisible(false);
		frame.getContentPane().remove(btnViewStudent);
		
		btnRefreshStudentList.setVisible(false);
		frame.getContentPane().remove(btnRefreshStudentList);
		
		frame.repaint();
	}
	
	private void viewStudentScreen() {
		txtStudentTitle = new JTextField("Student data of :" + dbConnection.getCurStudent().getName());
		txtStudentTitle.setBackground(new Color(255, 255, 255));
		txtStudentTitle.setEditable(false);
		txtStudentTitle.setHorizontalAlignment(SwingConstants.CENTER);
		txtStudentTitle.setFont(new Font("Tahoma", Font.BOLD, 24));
		txtStudentTitle.setBounds(373, 42, 328, 107);
		frame.getContentPane().add(txtStudentTitle);
		txtStudentTitle.setColumns(10);
		
		txtNameOfStudent = new JTextField();
		txtNameOfStudent.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtNameOfStudent.setHorizontalAlignment(SwingConstants.RIGHT);
		txtNameOfStudent.setText("Name:");
		txtNameOfStudent.setEditable(false);
		txtNameOfStudent.setBounds(256, 174, 136, 36);
		frame.getContentPane().add(txtNameOfStudent);
		txtNameOfStudent.setColumns(10);
		
		studentNameEditField = new JFormattedTextField(dbConnection.getCurStudent().getName());
		studentNameEditField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		studentNameEditField.setBounds(456, 174, 136, 36);
		frame.getContentPane().add(studentNameEditField);
		
		txtAverage = new JTextField();
		txtAverage.setText("Average:");
		txtAverage.setHorizontalAlignment(SwingConstants.RIGHT);
		txtAverage.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtAverage.setEditable(false);
		txtAverage.setColumns(10);
		txtAverage.setBounds(256, 265, 136, 36);
		frame.getContentPane().add(txtAverage);
		
		studentAverageEditField = new JFormattedTextField(dbConnection.getCurStudent().getAverage());
		studentAverageEditField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		studentAverageEditField.setBounds(456, 265, 136, 36);
		frame.getContentPane().add(studentAverageEditField);
		
		txtComment = new JTextField();
		txtComment.setText("Comment:");
		txtComment.setHorizontalAlignment(SwingConstants.RIGHT);
		txtComment.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtComment.setEditable(false);
		txtComment.setColumns(10);
		txtComment.setBounds(256, 359, 136, 36);
		frame.getContentPane().add(txtComment);
		
		studentCommentEditField = new JFormattedTextField();
		studentCommentEditField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		studentCommentEditField.setBounds(436, 359, 383, 156);
		frame.getContentPane().add(studentCommentEditField);
		
		btnSaveStudentInfo = new JButton("Save Info");
		btnSaveStudentInfo.setFont(new Font("Tahoma", Font.BOLD, 36));
		btnSaveStudentInfo.setBounds(256, 550, 275, 107);
		btnSaveStudentInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double average;
			
				// Tries to convert the text field into an number to verify whether it is a valid input
				try {
					average = Double.parseDouble(studentAverageEditField.getText());
				} catch (NumberFormatException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Please enter a number in the \"Average\" field");
					return;
				}
				
				dbConnection.updateStudentData(studentNameEditField.getText(), studentAverageEditField.getText(), studentCommentEditField.getText());
			}
		});
		frame.getContentPane().add(btnSaveStudentInfo);
		
		btnBackToStudentsScreen = new JButton("Back");
		btnBackToStudentsScreen.setFont(new Font("Tahoma", Font.BOLD, 36));
		btnBackToStudentsScreen.setBounds(567, 550, 275, 107);
		btnBackToStudentsScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearStudentViewScreen();
				classDataScreen();
			}
		});
		frame.getContentPane().add(btnBackToStudentsScreen);
		
		btnGenerateComment = new JButton("Generate");
		btnGenerateComment.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnGenerateComment.setBounds(436, 338, 118, 23);
		btnGenerateComment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		frame.getContentPane().add(btnGenerateComment);
			
		frame.repaint();
	}
	
	void clearStudentViewScreen() {
		txtStudentTitle.setVisible(false);
		frame.getContentPane().remove(txtStudentTitle);

		txtNameOfStudent.setVisible(false);
		frame.getContentPane().remove(txtNameOfStudent);
	
		studentNameEditField.setVisible(false);
		frame.getContentPane().remove(studentNameEditField);
	
		txtStudentName.setVisible(false);
		frame.getContentPane().remove(txtStudentName);
		
		txtAverage.setVisible(false);
		frame.getContentPane().remove(txtAverage);

		studentAverageEditField.setVisible(false);
		frame.getContentPane().remove(studentAverageEditField);
		
		txtComment.setVisible(false);
		frame.getContentPane().remove(txtComment);
		
		studentCommentEditField.setVisible(false);
		frame.getContentPane().remove(studentCommentEditField);
		
		btnSaveStudentInfo.setVisible(false);
		frame.getContentPane().remove(btnSaveStudentInfo);
	
		btnBackToStudentsScreen.setVisible(false);
		frame.getContentPane().remove(btnBackToStudentsScreen);
		
		btnGenerateComment.setVisible(false);
		frame.getContentPane().remove(btnGenerateComment);
		
		frame.repaint();
	}
}