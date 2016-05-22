import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;


public class Main{
	
	public static void main (String[] args){
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Enter the Table Size Number: ");
		int tableSize = reader.nextInt();
		
		//Complete Putting Students in ArrayList
		String listOfStudents = getFileAsString("class.txt");
		String [] s = listOfStudents.split("\n");
				Student [] students = new Student[s.length];
		for( int i = 0; i < s.length; i++){
			Student a = new Student(s[i].trim());
			students[i] = a;
		}
		//Pair in Nice group kids that need help and kids that want help
		ArrayList<Student> helpers = new ArrayList<Student>();
		String canHelp = getFileAsString("helpers.txt");
		String[] seperateHelpers = canHelp.split("\n");
		for( int i = 0; i < seperateHelpers.length; i++){
			for( int z = 0; z < students.length; z++){
				if(seperateHelpers[i].trim().equals(students[z].getName())){
					helpers.add(students[z]);
				}
			}
		}
		ArrayList<Student> helpees = new ArrayList<Student>();
		String needHelp = getFileAsString("helpees.txt");
		String[] seperateHelpees = needHelp.split("\n");
		for( int i = 0; i < seperateHelpees.length; i++){
			for( int z = 0; z < students.length; z++){
				if(seperateHelpees[i].trim().equals(students[z].getName())){
					helpees.add(students[z]);
				}
			}
		}
		
		//Completing Grouping of Nice Pairing
		ArrayList<NiceGroup> niceGroups = new ArrayList<NiceGroup>();
		int minSize = getMinValue(helpers, helpees);
		for(int i = 0; i < minSize; i++){
			int a = (int) (Math.random() * helpers.size());
			int b = (int) (Math.random() * helpees.size());
			NiceGroup n = new NiceGroup();
			n.addStudent(helpers.get(a));
//			helpers.get(a).addCompatible(helpees.get(b));
//			helpees.get(b).addCompatible(helpers.get(a));
			n.addStudent(helpees.get(b));
			helpers.remove(a);
			helpees.remove(b);
			niceGroups.add(n);
		}
		//Add niceGroups into Compatible
		for( int i = 0; i < students.length; i++){
			for( int j = 0 ; j < niceGroups.size(); j++){
				for(int k = 0; k < niceGroups.get(j).getSize(); k++){
					if(students[i].getName().equals(niceGroups.get(j).get(k).getName())){
						for(int l = 0; l < niceGroups.get(j).getSize(); l++){
							if(!(students[i].getName().equals(niceGroups.get(j).get(k).getName()))){
								students[i].addCompatible(niceGroups.get(j).get(l));
								students[i].setInNiceGroup(true);
							}
						}
					}					
				}
			}
		}
		
		//Adding the leftover to random groups
//		if(helpers.size() > 0){
//			for( int i = 0; i < helpers.size(); i++){
//				int a = (int)(Math.random() * niceGroups.size());
//				niceGroups.get(a).addStudent(helpers.get(i));
//			}
//		}
//		else if(helpees.size() > 0){
//			for( int i = 0; i < helpees.size(); i++){
//				int a = (int)(Math.random() * niceGroups.size());
//				niceGroups.get(a).addStudent(helpers.get(i));
//			}
//		}

		
		//Completing Grouping of Naughty Students
		ArrayList<NaughtyGroup> naughtyGroups = new ArrayList<NaughtyGroup>();
		String listOfNaughtyStudents = getFileAsString("naughtylist.txt");
		String [] seperateIntoGroups = listOfNaughtyStudents.split("\n");
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
		//Add Naughty Groups into incompatible
		for( int i = 0; i < students.length; i++){
			for( int j = 0 ; j < naughtyGroups.size(); j++){
				for(int k = 0; k < naughtyGroups.get(j).getSize(); k++){
					if(students[i].getName().equals(naughtyGroups.get(j).get(k).getName())){
						for(int l = 0; l < naughtyGroups.get(j).getSize(); l++){
							if(!(students[i].getName().equals(naughtyGroups.get(j).get(k).getName()))){
								students[i].addCompatible(naughtyGroups.get(j).get(l));
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
		
		//Fill Tables with Students
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
	private static int getMinValue(ArrayList<Student> helpers,	ArrayList<Student> helpees) {
		if(helpers.size() < helpees.size()){
			return helpers.size();
		}
		return helpees.size();
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
