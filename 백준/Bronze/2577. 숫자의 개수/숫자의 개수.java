import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int A = Integer.parseInt(br.readLine().trim());
		int B = Integer.parseInt(br.readLine().trim());
		int C = Integer.parseInt(br.readLine().trim());
		int cnt[] = new int[10];
		int Mul = A * B * C;
		while (Mul > 0) {
			cnt[Mul % 10]++;
			Mul /= 10;
		}
		for (int i : cnt) {
			System.out.println(i);
		}
	}
}
