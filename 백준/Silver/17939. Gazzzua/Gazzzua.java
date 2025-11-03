import java.io.*;
import java.util.*;

/*
 *  1 <= N <= 100,000
 *  P_i <= 1,000
 *  
 *  int 자료형 사용 가능
 *  
 */

public class Main {
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int P[] = new int[N];

		st = new StringTokenizer(br.readLine(), " ");
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[1] == o2[1]) {
					return Integer.compare(o1[0], o2[0]);
				}
				return Integer.compare(o1[1], o2[1]) * -1;
			}
		});
		for (int i = 0; i < N; i++) {
			P[i] = Integer.parseInt(st.nextToken());
			pq.offer(new int[] { i, P[i] });
		}

		int profit = 0;
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			int tmp = P[i];
			while (true) {
				if (pq.isEmpty())
					break;
				if (pq.peek()[0] < i)
					pq.poll();
				else {
					break;
				}
			}
			if (pq.isEmpty())
				break;
			if (tmp == pq.peek()[1]) {
				int poll[] = pq.poll();
				while (!list.isEmpty()) {
					int sell = list.remove(0);
					profit += poll[1] - sell;
				}
			} else {
				list.add(tmp);
			}
		}
		System.out.println(profit);
	}
}
