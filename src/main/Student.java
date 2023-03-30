package main;

public class Student {
	private String fullName;
	private String className;
	private String teacher;
	private Comment comment;
	
	private double average;
	
	public Student(String fullName, String className, String teacher, double average) {
		this.fullName = fullName;
		this.className = className;
		this.teacher = teacher;
		this.average = average;
		generateComment();
	}
	
	public String getName() {
		return fullName;
	}
	
	public void setName(String name) {
		fullName = name;
	}
	
	public String getClassName() {
		return className;
	}
	
	public String getTeacherName() {
		return teacher;
	}
	
	public double getAverage() {
		return average;
	}
	
	public void setAverage(double average) {
		this.average = average;
	}
	
	public void generateComment() {
		comment = new Comment(average);
	}
	
	public String getComment() {
		return fullName + comment.getComment();
	}
	
	public void setComment(String comment) {
		this.comment.setComment(comment);
	}
}

