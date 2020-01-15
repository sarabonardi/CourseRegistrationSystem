import java.util.*;
import java.io.*;

public class Course implements Serializable{

	//create attributes
	private String name;
	private String id;
	private int maxstudents;
	private ArrayList<Student> studlist = new ArrayList<Student>();
	private int currentstudents = studlist.size();
	private String instructor;
	private int section;
	private String location;
	private boolean full = false;
	
	//create constructor (without studlist and currentstudents
	public Course(String n, String i, int m, String in, int s, String l) {
		name = n;
		id = i;
		maxstudents = m;
		instructor = in;
		section = s;
		location = l;
	}

	//getters and setters
	public int getMaxstudents() {
		return maxstudents;
	}

	public void setMaxstudents(int maxstudents) {
		this.maxstudents = maxstudents;
	}

	public int getCurrentstudents() {
		return currentstudents;
	}

	public void setCurrentstudents(int currentstudents) {
		this.currentstudents = currentstudents;
	}
	
	public ArrayList<Student> getStudlist() {
		return studlist;
	}
	
	//add student method that checks class fullness
	public void addStudent(Student x) {
		if (studlist.size() < maxstudents) {
			studlist.add(x);
		}
		else {
			System.out.println("Class is full.");
		}
	}
	
	public String getInstructor() {
		return instructor;
	}

	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}
	
	public int getListsize() {
		return studlist.size();
	}
	
	//check if class is full
	public boolean checkFull() {
		if (studlist.size() == maxstudents) {
			full = true;
		}
		return full;
	}
}
