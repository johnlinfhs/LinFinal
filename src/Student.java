import java.util.ArrayList;


public class Student {
	
	private String name;
	private int number = -1;
	private boolean sitInFront = false;
	private boolean naughty = false;
	private boolean inNiceGroup = false;
	private ArrayList<Student> compatible = new ArrayList<Student>();
	private ArrayList<Student> incompatible = new ArrayList<Student>();
	private ArrayList<Student> satWithStudent = new ArrayList<Student>();
	
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
	public boolean getNaughtiness(){
		return naughty;
	}
	public void setNaughtiness(boolean s){
		naughty = s;
	}
	public void setInNiceGroup(boolean s){
		inNiceGroup = s;
	}
	public boolean getInNiceGroup(){
		return inNiceGroup;
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
	public void addSatWithStudent(Student s){
		satWithStudent.add(s);
	}
	public void removeSatWithStudent(Student s){
		if(satWithStudent.contains(s))
		satWithStudent.remove(s);
	}
	public boolean satwithStudent(Student s){
		if( satWithStudent.contains(s)){
			return true;
		}
		return false;
	}

}
