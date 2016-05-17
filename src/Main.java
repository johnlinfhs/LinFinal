import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;


public class Main{
	
	public static void main (String[] args){
		int tableSize = 4;
		String listOfStudents = getFileAsString("input1.txt");
		String [] students = listOfStudents.split("\n");
		int numTables;
		if(students.length % tableSize == 0){
			numTables = students.length / tableSize;
		}
		else{
			numTables = students.length / tableSize + 1;
		}
		ArrayList<Table> tables = new ArrayList<Table>();
		for(int i = 0; i < numTables; i++){
			Table t = new Table(tableSize, i);
			tables.add(t);
		}			
		for( int i = 0; i < students.length; i++){
			System.out.println(students[i]);
		}
		
		
	}
	public static String getFileAsString(String filename) {
		Scanner s;
		StringBuilder sb = new StringBuilder(500000);
		try {
			s = new Scanner(new FileReader(filename));
			s.useDelimiter("");
			while (s.hasNext()) {
				String n = s.next();
				sb.append(n);
			}
		} catch (Exception e) {
		}
		return sb.toString();
	}
	public static void writeStringToFile(String filename, String text) {
		try {
			PrintWriter out = new PrintWriter(filename);
			for (int i = 0; i < text.length(); i++) {
				char n = text.charAt(i);
				out.print(n);
			}
			out.close();
		} catch (Exception e) {
		}
	}
}
