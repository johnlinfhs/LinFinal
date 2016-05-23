import java.util.ArrayList;


public class Table {
	private int size;
	private int num;
	
	private ArrayList<Student> t = new ArrayList<Student>();
	
	public Table ( int s, int n){
		size = s;
		num = n+1;
	}
	public String add( Student s){
		if(t.size() < size)	{
			for( int i = 0; i < t.size(); i++){
				if(t.get(i).getName().equals(s.getName())){
					return "added";
				}
			}
			for( int i = 0; i < t.size(); i++){
				if(t.get(i).isCompatible(s)){
					for( int j = 0; j < t.size(); j++){
						if(t.get(i).isinCompatible(s)){
							return s.getName() + "is Incompatible with one or more students";
						}
					}
					t.add(s);
					return "added";
				}
			}	
			t.add(s);
			return "added";
		}			
		return "Table already filled.";
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
		String str = "\nTable " + num + ": ";
		for( int i = 0; i < t.size(); i++){
			str += t.get(i).getName() + ", ";
		}
		return str;
		
	}

}
