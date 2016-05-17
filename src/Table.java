import java.util.ArrayList;


public class Table {
	private int size;
	private int num;
	
	private ArrayList<Student> t = new ArrayList<Student>();
	
	public Table ( int s, int n){
		size = s;
		num = n;
	}
	public String add( Student s){
		if(t.size() < size)	{
			for( int i = 0; i < t.size(); i++){
				if(t.get(i).isinCompatible(s)){
					return s.getName()+ " is incomptible with at least one person in the table group.";
				}
			}
			t.add(s);
		}			
		else  return "Table already filled.";
		return null;
	}
	public void remove( Student s){
		if(t.contains(s)){
			t.remove(s);
		}	
	}
	public int sitInFrontValue(){
		int count = 0;
		for( int i = 0; i < t.size(); i++){
			if(t.get(i).getSitInFront()){
				count++;
			}
		}
		return count;
	}
	public String toString(){
		String str = "";
		for( int i = 0; i < t.size(); i++){
			str += t.get(i).getName() + "\n";
		}
		return str;
		
	}

}
