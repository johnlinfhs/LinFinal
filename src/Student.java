import java.util.ArrayList;


public class Student {
	
	private String name;
	private int number = -1;
	private boolean sitInFront = false;
	private ArrayList<Student> compatible = new ArrayList<Student>();
	private ArrayList<Student> incompatible = new ArrayList<Student>();
	
	public Student( String n){
		name = n;
	}
	public Student( String n , int num){
		name = n;
		number = num;
	}

	public String getName() {
		return name;
	}
	public void setName(String n){
		name = n;
	}
	public int getID(){
		return number;
	}
	public void setID(int num){
		number = num;
	}
	public boolean getSitInFront(){
		return sitInFront;
	}
	public void setSitInFront(boolean s){
		sitInFront = s;
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
	public boolean isCompatible(Student s){
		if( compatible.contains(s)){
			return true;
		}
		return false;
	}
	public boolean isinCompatible(Student s){
		if( incompatible.contains(s)){
			return true;
		}
		return false;
	}

}
