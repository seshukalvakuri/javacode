package firstjavacheck;

import java.util.Scanner;

public class GCD {

	int num1, num2;
    
	public void findGCD() {
    //Reading the input numbers
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter first number:");
    num1 = (int)scanner.nextInt();
    
    System.out.print("Enter second number:");
    num2 = (int)scanner.nextInt();
    
    //closing the scanner to avoid memory leaks
    scanner.close();
    while (num1 != num2) {
    	if(num1 > num2)
            num1 = num1 - num2;
        else
            num2 = num2 - num1;
    }

    //displaying the result
    System.out.printf("GCD of given numbers is: %d", num2);
	}
	
	}
