import java.util.ArrayList;


public class NaughtyGroup {
	
	private ArrayList<Student> students = new ArrayList<Student>();
	
	public NaughtyGroup(){
		
	}
	public void addStudent(Student s){
		students.add(s);
	}
	public void removeStudent(Student s){
		students.remove(s);
	}
	public int getSize(){
		return students.size();
	}
	public Student get(int i) {
		return students.get(i);
	}
}
