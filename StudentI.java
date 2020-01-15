
public interface StudentI {
	
	//lists method signatures in student class
	public void printFullname();
	
	public void printAllcourses();
	
	public void printNotfullcourses();
	
	public void registerIncourse(String cname, int sec, String fname, String lname);
	
	public void withdrawFromCourse(String id, int sec, String user);
	
	public void printRegisteredClasses(String user);
}
