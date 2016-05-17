import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;


public class Main{
	
	public static void main (String[] args){
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter the Table Size Number: ");
		int tableSize = reader.nextInt();
		String listOfStudents = getFileAsString("input1.txt");
		String [] s = listOfStudents.split("\n");
		Student [] students = new Student[s.length];
		for( int i = 0; i < s.length; i++){
			Student a = new Student(s[i].trim());
			students[i] = a;
		}
		int numTables = getNumTables(students.length, tableSize);
		
		ArrayList<Table> tables = new ArrayList<Table>();
		for(int i = 0; i < numTables; i++){
			Table t = new Table(tableSize, i);
			tables.add(t);
		}		
		int count = 0;
		while(  count < students.length){
			int t = (int) (Math.random() * tables.size());	
			if(tables.get(t).add(students[count]).equals("added")){
				count++;
			}
		}
//		print(tables);
		printToTextDocument(tables);
		
		
	}
	private static void printToTextDocument(ArrayList<Table> tables) {
		String str= "";
		for( int i = 0; i < tables.size(); i++){
			str += tables.get(i).toString();
		}
		writeStringToFile("output1.txt", str);
		
	}
	private static void print(ArrayList<Table> tables) {
		for( int i = 0; i < tables.size(); i++){
			System.out.print(tables.get(i).toString());
		}
		
	}
	private static int getNumTables(int length, int tableSize) {
		if(length % tableSize == 0){
			return length / tableSize;
		}
		else{
			return length / tableSize + 1;
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
				if (n != '\n') {
					out.print(n);
				} else {
					out.println();
				}
			}
			out.close();
		} catch (Exception e) {
		}
	}
}
