import java.util.ArrayList;


public class Student {
	
	private String name;
	private int number;
	private ArrayList<Student> compatible = new ArrayList<Student>();
	private ArrayList<Student> incompatible = new ArrayList<Student>();
	
	public Student( String n){
		name = n;
	}

	public String getName() {
		return name;
	}
	public int getID(){
		return number;
	}
	public void addCompatible(Student s){
		compatible.add(s);
	}
	public void addimCompatible(Student s){
		incompatible.add(s);
	}
	public void removeCompatible(Student s){
		if(compatible.contains(s))
		compatible.remove(s);
	}
	public void removeinCompatible(Student s){
		if(incompatible.contains(s))
		incompatible.remove(s);
	}

}
