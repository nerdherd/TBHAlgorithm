package org.usfirst.frc.team687.robot;

/*
 * testTBH2
 * 
 *@authors: Ronan Konishi, Isaac Addis, Tabio Pela
 *
 *@param: encoder ticks
 *
 *@description: TestTBH2 performs the TBH algorithm based on the encoder ticks it is given. 
 */

public class Master {
	//Define variables
	
	//Scammer
//	static Scanner scanner = new Scanner(System.in);
	//TBH variable
	static double m_TBH = 0.0;
	static double last_encode = 0;
	static double desired = 3600;
	static double previousError = 0;
	static double last_time = 0;
	static double output = 0;
	static double current_encode_global;
	static double RPM_global;
	
	public static double testTBH2(double encode){
//		double time = currentTime; (for testing)
		
		//Integral gain
		double gain = 1.0/400.0;
		//Assign the argument to a variable called current_encode
		double current_encode = encode;
		current_encode_global = current_encode;
		//Delta time (change in time in milliseconds)
		double delta_time = (System.currentTimeMillis() - last_time)/ 60000; //change time in minutes
    	double delta_encode = current_encode - last_encode;//how can we calculate revolutions???
    	double actualrpm = (delta_encode / delta_time);
    	RPM_global = actualrpm;
		double error = desired - actualrpm;
		double output =+ gain * error;
		
//For Testing:
//		System.out.println("LastEncode " + last_encode);
//		System.out.println("DeltaTime: " + delta_time);
//		System.out.println("RPM: " + actualrpm);
//		System.out.println("error: " + error);
//		System.out.println("o1: " + output);
		
		//RPM cannot be negative
		if(actualrpm<0){
			actualrpm = 0;
		}
		//Output cannot exceed 1 and cannot be lower than 0
		if(output > 1){
			output = 1;
		} else if(output < 0){
			output = 0;
		}
		//If error isn't equal to the previous error, perform the TBH algorithm...
		if(signbit(error) != signbit(previousError)){
			m_TBH = 0.5 * (output + m_TBH);
			output = m_TBH;
			previousError = error;
		}
		//Updating values
		last_time = System.currentTimeMillis();
		last_encode = current_encode;
		previousError = error;
		
		//Return the drive value
		return output;
	}
	/*
	 *  Used for comparing error and previous error for use in TestTBH2
	 */
	 public static double signbit(double value) {
	    	if (value > 0) {
	    		return 1;
	    	}
	    	else if (value < 0) {
	    		return -1;
	    	}
	    	else {
	    		return 0;
	    	}

	 }
}
