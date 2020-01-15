import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Admin extends User implements AdminI {
	
	//constructors
	public Admin() {
		
	}
	
	public Admin(String user, String pass, String first, String last) {
		username = user;
		password = pass;
		firstname = first;
		lastname = last;
	}
	
	//getters and setters
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getFirstName() {
		return firstname;
	}
	
	public String getLastName() {
		return lastname;
	}
	
	//overridden method
	public void printUserType() {
		System.out.println("Admin");
	}
	
	//uses arraylist method add to courses arraylist
	public void addCourse(Course c) {
		CourseManager.courses.add(c);
	}
	
	//uses arraylist method remove from courses arraylist
	public void deleteCourse(String id, int sec) {
		for (int i = 0; i < CourseManager.courses.size(); i++) {
			if (CourseManager.courses.get(i).getId().equals(id) && CourseManager.courses.get(i).getSection() == sec) {
				CourseManager.courses.remove(i);
			}
		}
	}
	
	//overloaded, uses arraylist method remove from courses arraylist
	public void deleteCourse(String name, String inst) {
		for (int i = 0; i < CourseManager.courses.size(); i++) {
			if (CourseManager.courses.get(i).getName().equals(name) && CourseManager.courses.get(i).getInstructor() == inst) {
				CourseManager.courses.remove(i);
			}
		}
	}
	
	//uses getters and setters to edit course attributes
	public void editCourse(String id, int sec) {
		Scanner input = new Scanner(System.in);
		int choice = 0;
		for (int i = 0; i < CourseManager.courses.size(); i++) {
			if (CourseManager.courses.get(i).getId().equals(id) && CourseManager.courses.get(i).getSection() == sec) {
				while (choice != 5) {
					System.out.println("What would you like to edit?");
					System.out.println("1. Maximum number of students.");
					System.out.println("2. Instructor name.");
					System.out.println("3. Location.");
					System.out.println("4. Section number.");
					System.out.println("5. Exit");
					choice = input.nextInt();
					String line = input.nextLine();
					
					if (choice == 1) {
						System.out.println("Please enter a new maximum.");
						int max = input.nextInt();
						CourseManager.courses.get(i).setMaxstudents(max);
					}
					
					else if (choice == 2) {
						System.out.println("Please enter new instructor name.");
						String inst = input.nextLine();
						CourseManager.courses.get(i).setInstructor(inst);
					}
					
					else if (choice == 3) {
						System.out.println("Please enter new location.");
						String loc = input.nextLine();
						CourseManager.courses.get(i).setLocation(loc);
					}
					
					else if (choice == 4) {
						System.out.println("Please enter a new section number.");
						int sect = input.nextInt();
						CourseManager.courses.get(i).setSection(sect);
					}
					
					else {
						System.out.println("Invalid option.");
					}
				}
			}
		}		
	}
	
	//loops through courses to find course, uses getters to print attributes
	public void printCourseInfo(String id, int sec) {
		for (int i = 0; i < CourseManager.courses.size(); i++) {
			if (CourseManager.courses.get(i).getId().equals(id) && CourseManager.courses.get(i).getSection() == sec) {
				System.out.println("ID: " + CourseManager.courses.get(i).getId());
				System.out.println("Course Name: " + CourseManager.courses.get(i).getName());
				System.out.println("Location: " + CourseManager.courses.get(i).getLocation());
				System.out.println("Instructor: " + CourseManager.courses.get(i).getInstructor());
				System.out.println("Current Number of Students: " + CourseManager.courses.get(i).getCurrentstudents());
				System.out.println("Maximum Number of Students: " + CourseManager.courses.get(i).getMaxstudents());
			}
		}
	}
	
	//creates student object in students arraylist
	public void registerStudent() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the username.");
		String newuser = input.nextLine();
		System.out.println("Please enter the password.");
		String newpass = input.nextLine();
		System.out.println("Please enter the first name.");
		String newfirst = input.nextLine();
		System.out.println("Please enter the last name.");
		String newlast = input.nextLine();
		Student s = new Student(newuser, newpass, newfirst, newlast);
		CourseManager.students.add(s);
	}
	
	//loops through courses arraylist to print required info
	public void printAllCourses() {
		for (int i = 0; i < CourseManager.courses.size(); i++) {
			int k = CourseManager.courses.get(i).getStudlist().size()-1;
			System.out.println(i + 1 + ". " + CourseManager.courses.get(i).getName());
			if (CourseManager.courses.get(i).getStudlist().size() > 0) {
				for (int j = 0; j < CourseManager.courses.get(i).getStudlist().size()-1; j++) {
					System.out.print("Student List: " + CourseManager.courses.get(i).getStudlist().get(j).getFirstname() + " " + CourseManager.courses.get(i).getStudlist().get(j).getLastname() + " " + CourseManager.courses.get(i).getStudlist().get(j).getUsername() + ", ");
				}
				System.out.println(CourseManager.courses.get(i).getStudlist().get(k).getFirstname() + " " + CourseManager.courses.get(i).getStudlist().get(k).getLastname() + " " + CourseManager.courses.get(i).getStudlist().get(k).getUsername());
			}
			System.out.println("Current Students: " + CourseManager.courses.get(i).getCurrentstudents());
			System.out.println("Maximum Students: " + CourseManager.courses.get(i).getMaxstudents());
		}
	}
	
	//searches courses arraylist for full courses, prints names
	public void printFullCourses() {
		for (int i = 0; i < CourseManager.courses.size(); i++) {
			if (CourseManager.courses.get(i).checkFull() == true) {
				System.out.println(i + 1 + ". " + CourseManager.courses.get(i).getName());
			}
		}
	}

	//uses buffered writer to write course list to file
	public void writeCoursesToFile() {
		BufferedWriter bw = null;
		FileWriter fw = null;
		String text = "";
		
		try {
			fw = new FileWriter(CourseManager.fullfile);
			bw = new BufferedWriter(fw);
			for (int i = 0; i < CourseManager.courses.size(); i++) {
				if (CourseManager.courses.get(i).checkFull() == true) {
					text = CourseManager.courses.get(i).getName() + " " + CourseManager.courses.get(i).getSection() + "\n";
					bw.write(text, 0, CourseManager.courses.get(i).getName().length() + 1 + String.valueOf(CourseManager.courses.get(i).getSection()).length());
				}
			}
			System.out.println("Write to file complete.");
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//searches for course, loops through student list printing names
	public void printStudentsInCourse(String id, int sec) {
		for (int i = 0; i < CourseManager.courses.size(); i++) {
			if (CourseManager.courses.get(i).getId().equals(id) && CourseManager.courses.get(i).getSection() == sec) {
				for (int j = 0; j < CourseManager.courses.get(i).getStudlist().size(); j++) {
					CourseManager.courses.get(i).getStudlist().get(j).printFullname();
				}
			}
		}
	}
	
	//searches courses for specific student, prints course names
	public void printStudentRegisteredCourses(String user) {
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
	
	//uses linear sort to sort courses by current student count
	public void sortCourseByStudents() {
		int n = CourseManager.courses.size(); 
		for (int i=1; i<n; ++i){ 
			Course key = CourseManager.courses.get(i); 
			int j = i-1; 
			while (j>=0 && CourseManager.courses.get(j).getCurrentstudents() > key.getCurrentstudents()) { 
				CourseManager.courses.set(j+1, CourseManager.courses.get(j)); 
				j = j-1; 
			} 
			CourseManager.courses.set(j+1, key); 
		}  
	}
}
	


