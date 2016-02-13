package org.usfirst.frc.team687.robot;
import java.io.*;

/*
 * test FileWrite
 * 
 *@authors: Isaac Addis, Ronan Konishi
 *
 *@param: records data
 *
 *@description: FileWrite records data for given encoder values
 */

public class FileWrite extends Master{
	static FileWriter writer;
	
	public static void main(String[] args){
		generateCsvFile("C:/Users/Ronan/workspace/TBH Algorithm/src/org/usfirst/frc/team687/robot/datainput.csv");
	}
	
	public static void generateCsvFile(String sFileName){
		//Initializes writing to files
		try {
			FileWriter writer = new FileWriter(sFileName);
			
			//Headings
			writer.append("Encode");
			writer.append(",");
			writer.append(" Output");
			writer.append(",");
			writer.append(" RPM");
			writer.append("\r");
			
			//Start for loop
			for(int i=0; i <101; i++){
				double finalValue = Master.testTBH2(i*2);
				
				//Rows
				writer.append(String.valueOf(current_encode_global));
				writer.append(",");
				writer.append(" "+ String.valueOf(finalValue));
				writer.append(",");
				writer.append(" "+ String.valueOf(RPM_global));
				writer.append("\r");
				
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
}
