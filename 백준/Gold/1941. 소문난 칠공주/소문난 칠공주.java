import java.io.*;
import java.util.*;

public class Main {
	static char Map[][];
	static int numbers[], Cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Map = new char[5][5];
		Cnt = 0;
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String str = st.nextToken();
			numbers = new int[7];
			for (int j = 0; j < 5; j++) {
				Map[i][j] = str.charAt(j);
			}
		}
		Comb(0, 0, 0);
		System.out.println(Cnt);
	}

	private static boolean isConnected() {
		int cnt = 0;
		Queue<Integer> q = new ArrayDeque<>();
		boolean vis[] = new boolean[7];
		vis[0] = true;
		q.offer(numbers[0]);
		cnt++;

		while (!q.isEmpty()) {
			int currentPos = q.poll();
			int cr = currentPos / 5;
			int cc = currentPos % 5;

			// 나머지 선택된 위치들 중 인접한 것들 찾기
			for (int i = 0; i < 7; i++) {
				if (vis[i])
					continue;

				int nextPos = numbers[i];
				int nr = nextPos / 5;
				int nc = nextPos % 5;

				// 상하좌우 인접성 체크
				if (Math.abs(cr - nr) + Math.abs(cc - nc) == 1) {
					vis[i] = true;
					q.offer(numbers[i]);
					cnt++;
				}
			}
		}

		return cnt == 7;
	}

	private static void Comb(int cnt, int start, int Ycnt) {
		if (Ycnt > 3) { // 백트래킹
			return;
		}
		if (cnt == 7) {
			if (isConnected())
				Cnt++;
			return;
		}

		for (int i = start; i < 25; i++) {
			numbers[cnt] = i;
			int ncnt = Map[i / 5][i % 5] == 'Y' ? Ycnt + 1 : Ycnt;
			Comb(cnt + 1, i + 1, ncnt);
		}
	}

}
