
abstract class User {
	
	//attributes
	protected String username;
	protected String password;
	protected String firstname;
	protected String lastname;
	
	//overridden methods :)
	public void printAllcourses() {
		for (int i = 0; i < CourseManager.courses.size(); i++) {
			System.out.println(i + 1 + ". " + CourseManager.courses.get(i).getName());
		}
	}
	
	public void printUserType() {
		System.out.println("User");
	}
}