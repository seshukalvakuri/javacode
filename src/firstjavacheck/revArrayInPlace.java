package firstjavacheck;

public class revArrayInPlace {

	public static void reverse(String[] array) {
		if (array == null || array.length < 2) {
			return; 
			} 
		for (int i = 0; i < array.length / 2; i++) { 
			String temp = array[i]; 
			array[i] = array[array.length - 1 - i]; 
			array[array.length - 1 - i] = temp; 
			}
}

}
