import java.util.ArrayList;


public class Table {
	private int size;
	
	private ArrayList<Student> t = new ArrayList<Student>();
	
	public Table ( int s){
		size = s;
	}
	public String add( Student s){
		if(t.size() < size)		t.add(s);
		else  return "Table already filled.";
		return null;
	}
	public void remove( Student s){
		if(t.contains(s)){
			t.remove(s);
		}	
	}
	public String toString(){
		String str = "";
		for( int i = 0; i < t.size(); i++){
			str += t.get(i).getName();
		}
		return str;
		
	}

}
