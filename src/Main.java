import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;


public class Main{
	
	public static void main (String[] args){
		//Program Start
		Scanner reader = new Scanner(System.in);
		System.out.println("***NOTE***\nIf the program does not terminate in 10 seconds"
				+ " manually terminate the program and re-run the program.\n");
		System.out.println("Enter the Table Size Number: ");
		int tableSize = reader.nextInt();
		
		//Complete Putting Students in Array
		String listOfStudents = getFileAsString("class.txt");
		String[] s = listOfStudents.split("\n");
				Student [] students = new Student[s.length];
		for( int i = 0; i < s.length; i++){
			Student a = new Student(s[i].trim());
			students[i] = a;
		}
		//Create Helpees and Helpers
		ArrayList<Student> helpers = new ArrayList<Student>();
		String canHelp = getFileAsString("helpers.txt");
		String[] seperateHelpers = canHelp.split("\n");
		fillHelp(seperateHelpers, helpers, students);
		
		ArrayList<Student> helpees = new ArrayList<Student>();
		String needHelp = getFileAsString("helpees.txt");
		String[] seperateHelpees = needHelp.split("\n");
		fillHelp(seperateHelpees, helpees, students);
		
		//Completing Pairing of Helpers and Helpees
		ArrayList<NiceGroup> niceGroups = new ArrayList<NiceGroup>();
		fillNiceGroups(niceGroups,helpers, helpees);
		
		//Add niceGroups into Compatible
		for( int i = 0; i < students.length; i++){
			for( int j = 0 ; j < niceGroups.size(); j++){
				for(int k = 0; k < niceGroups.get(j).getSize(); k++){
					if(students[i].getName().equals(niceGroups.get(j).get(k).getName())){
						for(int l = 0; l < niceGroups.get(j).getSize(); l++){
							if(!(students[i].getName().equals(niceGroups.get(j).get(l).getName()))){
								students[i].addCompatible(niceGroups.get(j).get(l));
								students[i].setInNiceGroup(true);
							}
						}
					}					
				}
			}
		}
	
		//Completing Grouping of Naughty Students
		ArrayList<NaughtyGroup> naughtyGroups = new ArrayList<NaughtyGroup>();
		String listOfNaughtyStudents = getFileAsString("naughtylist.txt");
		String [] seperateIntoGroups = listOfNaughtyStudents.split("\n");
		fillNaughtyGroups(naughtyGroups, seperateIntoGroups, students);	
		
		//Add Naughty Groups into incompatible
		for( int i = 0; i < students.length; i++){
			for( int j = 0 ; j < naughtyGroups.size(); j++){
				for(int k = 0; k < naughtyGroups.get(j).getSize(); k++){
					if(students[i].getName().equals(naughtyGroups.get(j).get(k).getName())){
						for(int l = 0; l < naughtyGroups.get(j).getSize(); l++){
							if(!(students[i].getName().equals(naughtyGroups.get(j).get(l).getName()))){
								students[i].addinCompatible(naughtyGroups.get(j).get(l));
							}
						}
					}					
				}
			}
		}
		
		//Complete Creating Tables
		int numTables = getNumTables(students.length, tableSize);		
		ArrayList<Table> tables = new ArrayList<Table>();
		for(int i = 0; i < numTables; i++){
			Table t = new Table(tableSize, i);
			tables.add(t);
		}	

		// Fill tables with nice groups first
		for( int i = 0; i < niceGroups.size(); i++){
			int count = 0;
			int t = (int) (Math.random() * tables.size());
			for( int j = 0; j < niceGroups.get(i).getSize(); j++){
				if(tables.get(t).getSize() - tables.get(t).getCurrentSize() 
						- niceGroups.get(i).getSize() >= 0){
					if((tables.get(t).testAdd(niceGroups.get(i).get(j)).equals("added"))){
						count++;
					}					
				}					
			}	
			if(count == niceGroups.get(i).getSize()){
				for( int j = 0; j < niceGroups.get(i).getSize(); j++){
				tables.get(t).add(niceGroups.get(i).get(j)).equals("added");
				}
			}			
			else{
				i--;
			}
		}

		//Fill Tables with Students
		int count = 0;
		while( count < students.length ){
			int t = (int) (Math.random() * tables.size());	
			if(students[count].getInNiceGroup()){
				count++;
			}
			else if(tables.get(t).add(students[count]).equals("added")){
				count++;
			}
		}
		print(tables);
		printToTextDocument(tables);		
	}
	private static void fillNaughtyGroups(ArrayList<NaughtyGroup> naughtyGroups, 
			String[] seperateIntoGroups,Student[] students) {
		for(int i = 0; i < seperateIntoGroups.length; i++){
			String[] groups = seperateIntoGroups[i].split(",");
			NaughtyGroup n = new NaughtyGroup();
			for( int j = 0; j < groups.length; j++){
				for( int z = 0; z < students.length; z++){
					if(groups[j].trim().equals(students[z].getName())){
						n.addStudent(students[z]);
					}
				}
			}
			naughtyGroups.add(n);
		}		
	}
	private static void fillNiceGroups(ArrayList<NiceGroup> niceGroups,	
			ArrayList<Student> helpers, ArrayList<Student> helpees) {
		for(int i = 0; i < helpers.size(); i++){
			if(helpees.size() > 0){
				int b = (int) (Math.random() * helpees.size());
				NiceGroup n = new NiceGroup();
				n.addStudent(helpers.get(i));
				n.addStudent(helpees.get(b));
				helpees.remove(b);
				niceGroups.add(n);
			}
		}		
	}
	private static void fillHelp(String[] seperate,	ArrayList<Student> help, Student[] students) {
		for( int i = 0; i < seperate.length; i++){
			for( int z = 0; z < students.length; z++){
				if(seperate[i].trim().equals(students[z].getName())){
					help.add(students[z]);
				}
			}
		}		
	}
	private static void printToTextDocument(ArrayList<Table> tables) {
		String str= "";
		for( int i = 0; i < tables.size(); i++){
			str += tables.get(i).toString();
		}
		writeStringToFile("seatingArrangement.txt", str);
		
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
