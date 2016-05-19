import java.util.ArrayList;


public class NiceGroup {
	
	private ArrayList<Student> students = new ArrayList<Student>();
	
	public NiceGroup(){
		
	}
	public void addStudent(Student s){
		students.add(s);
	}
	public void removeStudent(Student s){
		students.remove(s);
	}
}

