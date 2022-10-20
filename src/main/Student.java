package main;

public class Student {
	private String fullName;
	private String className;
	private String teacher;
	private String comment;
	
	private double average;

	private String[] aTierAdjectives = {"fantastic", "very strong", "impressive", ""};
	private String[] bTierAdjectives = {"fantastic", "very strong", "impressive", ""};
	private String[] cTierAdjectives = {"fantastic", "very strong", "impressive", ""};
	private String[] dTierAdjectives = {"fantastic", "very strong", "impressive", ""};
	private String[] fTierAdjectives = {"fantastic", "very strong", "impressive", ""};
	
	
	public Student(String fullName, String className, String teacher, double average) {
		this.fullName = fullName;
		this.className = className;
		this.teacher = teacher;
		this.average = average;
	}
	
	public String getName() {
		return fullName;
	}
	
	public String getClassName() {
		return className;
	}
	
	public String getTeacherName() {
		return teacher;
	}
	
	public String getComment() {
		return comment;
	}
	
	public double getAverage() {
		return average;
	}
	
	public String generateComment() {
		
		
		return "";
	}
}
