import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		String str = st.nextToken();
		if (!str.equals("Fizz") && !str.equals("Buzz") && !str.equals("FizzBuzz")) {
			System.out.println(change(Integer.parseInt(str) + 3));
			return;
		}

		st = new StringTokenizer(br.readLine(), " ");
		str = st.nextToken();
		if (!str.equals("Fizz") && !str.equals("Buzz") && !str.equals("FizzBuzz")) {
			System.out.println(change(Integer.parseInt(str) + 2));
			return;
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		str = st.nextToken();
		if (!str.equals("Fizz") && !str.equals("Buzz") && !str.equals("FizzBuzz")) {
			System.out.println(change(Integer.parseInt(str) + 1));
			return;
		}

	}

	private static String change(int i) {
		if (i % 3 == 0 && i % 5 == 0)
			return "FizzBuzz";
		if (i % 3 == 0)
			return "Fizz";
		if (i % 5 == 0)
			return "Buzz";

		return Integer.toString(i);
	}

}
