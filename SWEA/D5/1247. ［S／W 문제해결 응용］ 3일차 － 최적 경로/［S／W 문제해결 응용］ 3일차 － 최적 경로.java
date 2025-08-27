import java.io.*;
import java.util.*;

public class Solution {
	static int N, MIN;
	static int[] samsung, home, numbers;
	static boolean[] vis;
	static List<int[]> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(st.nextToken());

		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			samsung = new int[2];
			home = new int[2];
			st = new StringTokenizer(br.readLine(), " ");
			samsung[0] = Integer.parseInt(st.nextToken());
			samsung[1] = Integer.parseInt(st.nextToken());
			home[0] = Integer.parseInt(st.nextToken());
			home[1] = Integer.parseInt(st.nextToken());

			for (int i = 0; i < N; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				list.add(new int[] { x, y });
			}
			
//			for(int[] a:list) {
//				System.out.println(Arrays.toString(a));
//			}

			MIN = Integer.MAX_VALUE;
			numbers = new int[N];
			vis = new boolean[N];
			perm(0);
			sb.append("#").append(tc).append(" ").append(MIN).append("\n");
			
		}
		System.out.println(sb);
	}

	private static void perm(int cnt) {
		if (cnt == N) {
//			System.out.println(Arrays.toString(numbers));
			
			int dist = 0;
			dist += distance(samsung, list.get(numbers[0]));
			
			for (int i = 1; i < N; i++) {
				dist += distance(list.get(numbers[i - 1]), list.get(numbers[i]));
			}
			
			dist += distance(list.get(numbers[N - 1]), home);

			MIN = Math.min(MIN, dist);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (vis[i])
				continue;

			vis[i] = true;
			numbers[cnt] = i;
			perm(cnt + 1);
			vis[i] = false;
		}

	}

	private static int distance(int[] from, int[] to) {
		int x1 = from[0];
		int y1 = from[1];
		int x2 = to[0];
		int y2 = to[1];
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

}
