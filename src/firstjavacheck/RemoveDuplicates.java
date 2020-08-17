package firstjavacheck;

import java.util.HashMap;

public class RemoveDuplicates {
	
	static void removeDups(int[] arr, int n)  
    { 
  
        // Hash map which will store the 
        // elements which has appeared previously. 
        HashMap<Integer,  
                Boolean> mp = new HashMap<>(); 
  
        for (int i = 0; i < n; ++i) 
        { 
  
            // Print the element if it is not 
            // there in the hash map 
            if (mp.get(arr[i]) == null) 
                System.out.print(arr[i] + " "); 
  
            // Insert the element in the hash map 
            mp.put(arr[i], true); 
        } 
    } 
  
    // Driver Code 
    public static void main(String[] args) 
    { 
        int[] arr = { 1, 2, 5, 1, 7, 2, 4, 2 }; 
        int n = arr.length; 
        removeDups(arr, n); 
    } 

}
