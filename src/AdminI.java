
public interface AdminI {

	//lists method signatures in admin class
	public void addCourse(Course c);
	
	public void deleteCourse(String id, int sec);
	
	public void deleteCourse(String name, String inst);
	
	public void editCourse(String id, int sec);
	
	public void printCourseInfo(String id, int sec);
	
	public void registerStudent();
	
	public void printAllCourses();
	
	public void printFullCourses();

	public void writeCoursesToFile();
	
	public void printStudentsInCourse(String id, int sec);
	
	public void printStudentRegisteredCourses(String user);
	
	public void sortCourseByStudents();
	
}
