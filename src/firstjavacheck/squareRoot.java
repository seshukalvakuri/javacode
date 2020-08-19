package firstjavacheck;

public class squareRoot {
	
	public static double squareRoot(int number) {
		double temp;

		double sr = number / 2;

		do {
			temp = sr;
			sr = (temp + (number / temp)) / 2;
		} while ((temp - sr) != 0);

		return sr;
	    }
	
}
