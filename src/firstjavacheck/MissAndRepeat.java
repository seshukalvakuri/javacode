package firstjavacheck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
//S = 

public class MissAndRepeat {

	
public static List<Integer> repeat(int[] numArray) {
		int size = numArray.length;
		List<Integer> resList = new ArrayList<>();
		Arrays.sort(numArray);
		for(int i=0;i<size;i++) {
			numArray[numArray[i]%size] = numArray[numArray[i]%size]+size;
		}
		for(int i=0;i<size;i++) {
			if(numArray[i]>=size*2) {
				
				resList.add(i);
			}
		}
		return resList;
	}
//n(n+1)/2 – x + y
public static int missing(int[] numArr) {
	int size= numArr.length+1;
	int total = size*(size+1)/2;
	
	int sum = Arrays.stream(numArr).sum();
	
	return total-sum;
	
}

public static int least(List<Integer> arrList) {
	return Collections.min(arrList);
}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] num = {1,2,3,1,2,2,3};
		System.out.println(repeat(num));

	}

}
