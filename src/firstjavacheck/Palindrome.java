package firstjavacheck;

public class Palindrome {
	
	
	static boolean isPalindrome(String str) 
    { 
  
	// and the end of the string 
    int i = 0, j = str.length() - 1; 

    // While there are characters toc compare 
    while (i < j) { 

        // If there is a mismatch 
        if (str.charAt(i) != str.charAt(j)) 
            return false; 

        // Increment first pointer and 
        // decrement the other 
        i++; 
        j--; 
    } 

    // Given string is a palindrome 
    return true; 

}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str = "ses"; 
		  
        if (isPalindrome(str)) 
            System.out.print("Yes"); 
        else
            System.out.print("No"); 
		
	}
}
