import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class CourseManager implements Serializable{
	
	//creating global variables for use in all methods and classes
	public static ArrayList<Course> courses = new ArrayList<Course>();
	public static ArrayList<Student> students = new ArrayList<Student>();
	public static Admin admin = new Admin();
	public static String fullfile = "fullcourses.txt";
	public static String line;
	
	public static void main(String[] args) {
		//checking if serialization file has been created yet
		File f = new File("courselist.ser");
		if (f.isFile()) {
			try
	        {
				//deserializing arraylists that contains courses and students
				//adapted from geeksforgeeks.org
	            FileInputStream file = new FileInputStream("courselist.ser"); 
	            ObjectInputStream in = new ObjectInputStream(file); 

	            courses = (ArrayList<Course>)in.readObject();
	            
	            in.close(); 
	            file.close();
	            
	            FileInputStream file2 = new FileInputStream("studentlist.ser"); 
	            ObjectInputStream in2 = new ObjectInputStream(file2); 

	            students = (ArrayList<Student>)in2.readObject();
	            
	            in2.close(); 
	            file2.close();
	              
	            System.out.println("Course and Student lists have been deserialized."); 
	        }
	        catch(IOException ex) 
	        { 
	            System.out.println("IOException is caught."); 
	        } 
	        catch(ClassNotFoundException ex) 
	        { 
	            System.out.println("ClassNotFoundException is caught."); 
	        } 
		}
		else {
			//reading csv if first time program run (if it has not been serialized before)
			courses = readCSV("MyUniversityCourses.csv");
		}
		
		//asking for login info
		Scanner input = new Scanner(System.in);
		System.out.println("Are you a student or an admin?");
		String usertype = input.nextLine();
		System.out.println("Please enter your username.");
		String user = input.nextLine();
		System.out.println("Please enter your password.");
		String pass = input.nextLine();
		
		if (usertype.equalsIgnoreCase("admin")) {
			if (user.equals("Admin") && pass.equals("Admin001")) {
				System.out.println("Please enter your full name.");
				String first = input.next();
				String last = input.nextLine();
				admin = new Admin (user, pass, first, last);
				adminmenu(admin);
			}
		}
		else if (usertype.equalsIgnoreCase("student")) {
			if (students.size() != 0) {
				for (int i = 0; i < students.size(); i++) {
					if (students.get(i).getUsername().equals(user) && students.get(i).getPassword().equals(pass)) {
						System.out.println("Welcome, " + students.get(i).getFirstname());
						studentmenu(students.get(i));
					}
				}
			}
			else {
				System.out.println("Student list empty.");
			}
			System.out.println("If you have not been offered a menu, please have an admin register you.");
		}
		else {
			System.out.println("Invalid user type. Please exit the program and try again.");
		}
		
		System.out.println("Thank you for using the Course Manager!");

		//serialization adapted from beginnersbook.com
		try{
			FileOutputStream stweam = new FileOutputStream("courselist.ser");
			ObjectOutputStream objstweam = new ObjectOutputStream(stweam);
			objstweam.writeObject(courses);
			objstweam.close();
			stweam.close();
			
			FileOutputStream stweam2 = new FileOutputStream("studentlist.ser");
			ObjectOutputStream objstweam2 = new ObjectOutputStream(stweam2);
			objstweam2.writeObject(students);
			objstweam2.close();
			stweam2.close();
		}
		catch(IOException exc){
			exc.printStackTrace();
	    }
	}
	
	//menu to offer specific admin menus
	public static void adminmenu(Admin admin) {
		int option = 0;
		while (option != 3) {
			Scanner input = new Scanner(System.in);
			System.out.println("Select an option.");
			System.out.println("1. Manage courses.");
			System.out.println("2. Obtain course report.");
			System.out.println("3. Exit.");
			option = input.nextInt();
			line = input.nextLine();
			if (option == 1) {
				managemenu(admin);
			}
			else if (option == 2) {
				reportmenu(admin);
			}
			else if (option > 3) {
				System.out.println("Invalid option.");
			}
		}
	}
	
	//menu for admin to edit courses or create student objects
	//calls methods from admin class
	public static void managemenu(Admin admin) {
		Scanner input = new Scanner(System.in);
		int choice = 0;
		while (choice != 6) {
			System.out.println("Select an option.");
			System.out.println("1. Create a course.");
			System.out.println("2. Delete a course.");
			System.out.println("3. Edit a course.");
			System.out.println("4. Display course information.");
			System.out.println("5. Register a student.");
			System.out.println("6. Exit.");
			choice = input.nextInt();
			line = input.nextLine();		
			if (choice == 1) {
				System.out.println("Please enter the course name.");
				String name = input.nextLine();
				System.out.println("Please enter the course id.");
				String id = input.nextLine();
				System.out.println("Please enter the max students.");
				int max = input.nextInt();
				line = input.nextLine();
				System.out.println("Please enter the instructor name.");
				String inst = input.nextLine();
				System.out.println("Please enter the section number.");
				int sec = input.nextInt();
				line = input.nextLine();
				System.out.println("Please enter the location.");
				String loc = input.nextLine();
				Course c = new Course(name, id, max, inst, sec, loc);
				admin.addCourse(c);
			}
			else if (choice == 2) {
				System.out.println("Please enter ID to delete.");
				String id = input.nextLine();
				System.out.println("Please enter section to delete.");
				int sec = input.nextInt();
				line = input.nextLine();
				admin.deleteCourse(id, sec);
			}
			else if (choice == 3) {
				System.out.println("Please enter ID to edit.");
				String id = input.nextLine();
				System.out.println("Please enter section to edit.");
				int sec = input.nextInt();
				line = input.nextLine();
				admin.editCourse(id, sec);
			}
			else if (choice == 4) {
				System.out.println("Please enter ID to display information for.");
				String id = input.nextLine();
				System.out.println("Please enter section to display.");
				int sec = input.nextInt();
				line = input.nextLine();
				admin.printCourseInfo(id, sec);
			}
			else if (choice == 5) {
				admin.registerStudent();
			}
			else if (choice > 6) {
				System.out.println("Please enter a valid option.");
			}
		}
	}
	
	//menu for admin to print course info
	//calls methods from admin class
	public static void reportmenu(Admin admin) {
		Scanner input = new Scanner(System.in);
		int choice = 0;
		while (choice != 6) {
			System.out.println("Select an option.");
			System.out.println("1. View all courses.");
			System.out.println("2. View full courses.");
			System.out.println("3. Write full courses to file.");
			System.out.println("4. View students registered in course.");
			System.out.println("5. Sort courses by registered students.");
			System.out.println("6. Exit.");
			choice = input.nextInt();
			line = input.nextLine();
			if (choice == 1) {
				admin.printAllCourses();
			}
			else if (choice == 2) {
				admin.printFullCourses();
			}
			else if (choice == 3) {
				admin.writeCoursesToFile();
			}
			else if (choice == 4) {
				System.out.println("Please enter course ID to view registered students.");
				String id = input.nextLine();
				System.out.println("Please enter section of class.");
				int sec = input.nextInt();
				line = input.nextLine();
				admin.printStudentsInCourse(id, sec);
			}
			else if (choice == 5) {
				admin.sortCourseByStudents();
			}
			else if (choice > 6) {
				System.out.println("Please enter a valid option.");
			}
		}
	}
	
	//menu to perform student functions
	//calls methods from student class
	public static void studentmenu(Student s) {
		Scanner input = new Scanner(System.in);
		int choice = 0;
		while (choice != 6) {
			System.out.println("Select an option.");
			System.out.println("1. View all courses.");
			System.out.println("2. View not full courses.");
			System.out.println("3. Register in a course.");
			System.out.println("4. Withdraw from a course.");
			System.out.println("5. View currently registered courses.");
			System.out.println("6. Exit.");
			choice = input.nextInt();
			line = input.nextLine();
			if (choice == 1) {
				s.printAllcourses();
			}
			else if (choice == 2) {
				s.printNotfullcourses();
			}
			else if (choice == 3) {
				System.out.println("Enter course name.");
				String cname = input.nextLine();
				System.out.println("Enter section number.");
				int sec = input.nextInt();
				line = input.nextLine();
				String fname = s.getFirstname();
				String lname = s.getLastname();
				s.registerIncourse(cname, sec, fname, lname);
			}
			else if (choice == 4) {
				System.out.println("Enter id of course to withdraw from.");
				String cid = input.nextLine();
				System.out.println("Enter your class section.");
				int sec = input.nextInt();
				line = input.nextLine();
				String currentUser = s.getUsername();
				s.withdrawFromCourse(cid, sec, currentUser);
			}
			else if (choice == 5) {
				s.printRegisteredClasses(s.getUsername());
			}
			else if (choice > 6) {
				System.out.println("Please enter a valid option.");
			}
		}
	}
	
	//reads data from string array created by each line of the csv file
	public static Course createCourse(String[] data) {
		String name = data[0];
		String id = data[1];
		int max = Integer.parseInt(data[2]);
		String inst = data[5];
		int sec = Integer.parseInt(data[6]);
		String loc = data[7];
		return new Course(name, id, max, inst, sec, loc);
	}
	
	//creates string arrays for each line of csv file
	public static ArrayList<Course> readCSV(String file) {
		Path pathToFile = Paths.get(file);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			String headerLine = br.readLine();
			String line = br.readLine();
			while (line != null) {
				String[] parameters = line.split(",");
				Course newc = createCourse(parameters);
				courses.add(newc);
				line = br.readLine();
			}
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return courses;
	}
}
