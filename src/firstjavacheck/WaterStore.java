package firstjavacheck;

public class WaterStore {
	
public static int trap(int[] height) {
        
        int leftMax = 0;
        int rightMax = 0;
        int left = 0;
        int right = height.length-1;
        int count = 0;
        while(left<=right){
            if(height[left]<height[right]){
                if(height[left]>=leftMax){
                    leftMax = height[left];
                }else{
                    count += leftMax-height[left];
                }
                left++;
            }else{
                if(height[right]>=rightMax){
                    rightMax = height[right];
                }else{
                    count += rightMax-height[right];
                }
                right--;
            }
        }
     
        return count;
    }   

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {7,4,0,9};
		System.out.println(trap(a));
	}

}
