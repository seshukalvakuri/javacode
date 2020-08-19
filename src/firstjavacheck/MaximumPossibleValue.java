package firstjavacheck;

public class MaximumPossibleValue {
	
	    //returns reversed linked list as original linked list is in reverse order
	    static LinkedList<Integer> getReverseList(LinkedList<Integer> numList){
	        LinkedList revList = new LinkedList<Integer>();
	        Iterator<Integer> i = numList.descendingIterator();
	        while(i.hasNext()){
	            revList.add(i.next());
	        }
	        return revList;
	    }
	    
	    
	    //Returns largest possible number after inserting 5
	    static int solution(int N){
	        int sign=(N<0)?1:0; //set value if number is negative
	        N = (N<0)?-N:N;
	        
	        int digit=0, temp=N,position_five = 0, count=0, result=0,p,size;
	        LinkedList numList = new LinkedList<Integer>();
	        
	        
	        if(N==0)//test case if 0 is sent
	            return 50;
	            
	        //creating linked list from number
	        while(temp!=0){
	            digit = temp%10;
	            numList.add(digit);
	            temp=temp/10;
	        }
	        
	        LinkedList<Integer> revList = new LinkedList<Integer>();
	        revList = getReverseList(numList);//reversing the list
	        
	        
	        Iterator<Integer> i = revList.iterator();//iterating the list
	        Integer element = 0;
	        
	        //finding the position to insert 5 at
	        while(i.hasNext()){
	            element = i.next();//getting the value of int while traversing
	            if(sign == 0)//if number is positive we find highest possible value
	            {
	                if(element<5){
	                    position_five = count;
	                    break;
	                }
	            }
	            else{ //if number is negative we find lowest possible value so number is highest
	                if(element>5){
	                    position_five = count;
	                    break;
	                }
	            }
	            count++;//To keep track of where to insert 5
	        }
	        
	        if(count==revList.size()){
	            position_five = count;//if all conditions above fail, 5 comes at last position
	        }
	        
	        revList.add(position_five,5);//Adding 5 to appropriate position
	        size = revList.size();
	        i = revList.iterator();//reinstantiating iterator so it moves to beginning of list
	        
	        //calculating number from list after 5 is inserted
	        while(i.hasNext()){
	            p = (int)Math.pow(10,(size--)-1);//Multiplier
	            result = result + i.next()*p;
	        }
	        
	        return (sign==0)?result:-result;//returning result after adding appropriate sign to result
	    }
	    
		public static void main (String[] args) throws java.lang.Exception
		{
			System.out.println(solution(-27496));//Pass any value from here to find max possible value
		}
	}



