package pckg1;

import java.math.BigDecimal;

public class Student {
	
	int id;
	String first_name, last_name, faculty, department;
	BigDecimal gpa;
	
	public Student(int id, String first_name, String last_name, String faculty, String department, BigDecimal gpa) {
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.faculty = faculty;
		this.department = department;
		this.gpa = gpa;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public BigDecimal getGpa() {
		return gpa;
	}

	public void setGpa(BigDecimal gpa) {
		this.gpa = gpa;
	}

}
