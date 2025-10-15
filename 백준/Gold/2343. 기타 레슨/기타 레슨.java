import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int arr[] = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int start = 1;
		int end = Integer.MAX_VALUE;
		int min = end;

		while (end - start != 1) {
			int mid = (start + end) / 2;
//			System.out.println("s: " + start + " e: " + end + " m: " + mid);
			if (isPossible(arr, mid, M)) {
				end = mid;
				min = mid;
			} else {
				start = mid;
			}
		}

		System.out.println(min);

	}

	private static boolean isPossible(int[] arr, int mid, int limit) {
		int sum = 0;
		int cnt = 0;
		for (int i = 0; i < arr.length; i++) {
			int n = arr[i];
			if (n > mid)
				return false;

			if (sum == 0)
				cnt++;
			if (sum + n > mid) {
				sum = n;
				cnt++;
			} else {
				sum += n;
			}
		}
//		System.out.println(cnt);
		if (cnt <= limit)
			return true;
		return false;
	}
}