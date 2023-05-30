package presentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Archivo {
	
	public static String[] pasarAVector() {
		
		File doc = new File("arbol.txt");
		Scanner obj;
		String data = "";
		try {
			obj = new Scanner(doc);
			 while (obj.hasNextLine())
				 data=obj.nextLine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return data.split(",");
	}


}
