import java.io.*;
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int lenA = Integer.parseInt(st.nextToken());
		int lenB = Integer.parseInt(st.nextToken());
		int A[] = new int[lenA];
		Set<Integer> B = new HashSet<>();
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < lenA; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < lenB; i++) {
			B.add(Integer.parseInt(st.nextToken()));
		}
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		int cnt = 0;
		for (int i = 0; i < lenA; i++) {
			int n = A[i];
			if (!B.contains(n)) {
				pq.offer(n);
				cnt++;
			}
		}
		System.out.println(cnt);
		while (!pq.isEmpty()) {
			sb.append(pq.poll()).append(" ");
		}
		System.out.println(sb);
	}
}