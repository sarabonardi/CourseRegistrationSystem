import java.util.*;
import java.io.*;

public class Student extends User implements StudentI, Serializable {
	
	//making attributes private
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	
	//constructors
	public Student() {
		
	}
	
	public Student (String user, String pass, String first, String last) {
		username = user;
		password = pass;
		firstname = first;
		lastname = last;
	}
	
	//getters and setters
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String user) {
		username = user;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String pass) {
		password = pass;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String first) {
		firstname = first;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String last) {
		lastname = last;
	}
	
	//overridden method
	public void printUserType() {
		System.out.println("Student");
	}

	public void printFullname() {
		System.out.println(firstname + " " + lastname);
	}
	
	//loops through course list printing names and sections
	public void printAllcourses() {
		for (int i = 0; i < CourseManager.courses.size(); i++) {
			System.out.println(i + 1 + ". " + CourseManager.courses.get(i).getName() + ", Section: " + CourseManager.courses.get(i).getSection());
		}
	}
	
	//checks for empty courses in course list, prints names
	public void printNotfullcourses() {
		for (int i = 0; i < CourseManager.courses.size(); i++) {
			if (CourseManager.courses.get(i).checkFull() == false) {
				System.out.println(i + 1 + ". " + CourseManager.courses.get(i).getName());
			}
		}
	}
	
	//adds student to course studlist if course is not full
	public void registerIncourse(String cname, int sec, String fname, String lname) {
		String user, pass, first, last;
		for (int i = 0; i < CourseManager.courses.size(); i++) {
			if (CourseManager.courses.get(i).getName().equals(cname) && CourseManager.courses.get(i).getSection() == sec) {
				for (int j = 0; j < CourseManager.students.size(); j++) {
					if (CourseManager.students.get(j).getFirstname().equals(fname) && CourseManager.students.get(j).getLastname().equals(lname)) {
						user = CourseManager.students.get(j).getUsername();
						pass = CourseManager.students.get(j).getPassword();
						first = CourseManager.students.get(j).getFirstname();
						last = CourseManager.students.get(j).getLastname();
						Student newstud = new Student(user, pass, first, last);
						CourseManager.courses.get(i).addStudent(newstud);
					}
				}
			}
		}
	}
	
	//removes student from course studlist
	public void withdrawFromCourse(String id, int sec, String user) {
		for (int i = 0; i < CourseManager.courses.size(); i++) {
			if (CourseManager.courses.get(i).getId().equals(id) && CourseManager.courses.get(i).getSection() == sec) {
				for (int j = 0; j < CourseManager.courses.get(i).getStudlist().size(); j++) {
					if (CourseManager.courses.get(i).getStudlist().get(j).getUsername().equals(user)) {
						CourseManager.courses.get(i).getStudlist().remove(j);
					}
				}
			}
		}
	}
	
	//prints courses with student in studlist
	public void printRegisteredClasses(String user) {
		for (int i = 0; i < CourseManager.students.size(); i++) {
			if (CourseManager.students.get(i).getUsername().equals(user)) {
				for (int j = 0; j < CourseManager.courses.size(); j++) {
					for (int k = 0; k < CourseManager.courses.get(j).getStudlist().size(); k++) {
						if (CourseManager.courses.get(j).getStudlist().get(k).getUsername().equals(user)) {
							System.out.println(CourseManager.courses.get(j).getName());
						}
					}
				}
			}
		}
	}
}
