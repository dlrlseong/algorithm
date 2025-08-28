import java.io.*;
import java.util.*;

public class Solution {

	static int N, W, H, size[], numbers[], MIN, tmpSize[];
	static List<Integer>[] Map, tmpMap;
	static boolean vis[][];
	static final int dr[] = { -1, 1, 0, 0 };
	static final int dc[] = { 0, 0, -1, 1 };

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			Map = new ArrayList[W];
			for (int i = 0; i < W; i++) {
				Map[i] = new ArrayList<>();
			}

			size = new int[W];

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < W; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					Map[j].add(0, tmp);
					if (tmp == 0)
						continue;
					size[j]++;
				}
			}
//			System.out.println(Arrays.toString(size)); //@ 입력확인
//			for (List a : Map) {
//				System.out.println(a);
//			}
			MIN = Integer.MAX_VALUE;
			numbers = new int[N];
			perm(0);
			sb.append("#").append(tc).append(" ").append(MIN).append("\n");
		}
		System.out.println(sb);

	}

	private static void perm(int cnt) {
		if (cnt == N) {
//			System.out.println(Arrays.toString(numbers));
			tmpMap = new ArrayList[W];
			for (int i = 0; i < W; i++) {
				tmpMap[i] = new ArrayList<>(Map[i]); // ArrayList로 복사
			}
			tmpSize = Arrays.copyOf(size, size.length);
			for (int i = 0; i < N; i++) {
				int n = numbers[i];
				if (tmpSize[n] == 0)
					continue;
				boom(n);
				gravity();
			}
			int score = 0;
			for (int i = 0; i < tmpSize.length; i++) {
				score += tmpSize[i];
			}
			MIN = Math.min(MIN, score);
			return;
		}

		for (int i = 0; i < W; i++) {
			numbers[cnt] = i;
			perm(cnt + 1);
		}

	}

	private static void boom(int n) {
		Deque<int[]> q = new ArrayDeque<>();
		vis = new boolean[W][H];
		q.offer(new int[] { n, tmpSize[n] - 1, tmpMap[n].get(tmpSize[n] - 1) });
		vis[n][tmpSize[n] - 1] = true;

		while (!q.isEmpty()) {
			int cur[] = q.pollFirst();
			int c = cur[0];
			int r = cur[1];
			int v = cur[2];
			tmpMap[c].set(r, 0);
			tmpSize[c]--;
			for (int d = 0; d < 4; d++) {
				int nr = r;
				int nc = c;
				for (int i = 1; i < v; i++) {
					nr += dr[d];
					nc += dc[d];
					if (nr < 0 || nc < 0 || nr >= H || nc >= W)
						continue;
					if (tmpMap[nc].get(nr) == 0)
						continue;
					if (vis[nc][nr])
						continue;

					vis[nc][nr] = true;
					q.offerLast(new int[] { nc, nr, tmpMap[nc].get(nr) });
				}
			}

		}

	}

	private static void gravity() {
		for (int i = 0; i < W; i++) {
			int cnt = 0;
			for (int j = 0; j < H; j++) {
				if (cnt == tmpSize[i])
					break;
				int tmp = tmpMap[i].get(j);
				if (tmp == 0) {
					tmpMap[i].remove(j);
					tmpMap[i].add(0);
					j--;
					continue;
				}
				cnt++;
			}
		}
	}
}