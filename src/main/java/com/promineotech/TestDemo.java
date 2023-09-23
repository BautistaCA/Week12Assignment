package com.promineotech;

import java.util.Random;

public class TestDemo {	
	public int addPositive(int a, int b) {
	 if((a > 0) && (b > 0)) {
		 return a + b;
	 } else {
		 throw new IllegalArgumentException("Both parameters must be positive");
	 }
 }

// *This is a simple method that returns a String indicating whether or not
// *the input is cleanly divisible by 2.
	public String divisibleByTwo (int a) {
	if(a % 2 == 0) {
		return(String.valueOf(a) + " is divisible by two");
	} else {
		 throw new IllegalArgumentException(String.valueOf(a)  +" is not divisible by two");
	 }
}
	public int randomNumberSquared() {
	int squared = getRandomInt();
	return squared * squared;
		
	}
	int getRandomInt() {

	    Random random = new Random();

	    return random.nextInt(10) + 1;

	}
}
