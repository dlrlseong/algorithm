import java.io.*;
import java.util.*;

public class Solution {
	static int K, turnInfo[];
	static List<Integer> Magnet[];

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			K = Integer.parseInt(st.nextToken());
			Magnet = new ArrayList[4];
			for (int i = 0; i < 4; i++) {
				Magnet[i] = new ArrayList<>();
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 8; j++) {
					Magnet[i].add(Integer.parseInt(st.nextToken()));
				}
			}

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int n = Integer.parseInt(st.nextToken()) - 1;
				int d = Integer.parseInt(st.nextToken());
				turnInfo = new int[4];
				turnInfo[n] = d;

				// 왼쪽 자석 체크
				int tmp = d; // 회전방향정보 저장
				for (int j = n - 1; j >= 0; j--) {
					if (n == 0)
						break;
					if (Magnet[j + 1].get(6) == Magnet[j].get(2))
						break;
					tmp *= -1;
					turnInfo[j] = tmp;
				}
				// 오른쪽 자석 체크
				tmp = d; // 회전방향정보 저장
				for (int j = n + 1; j < 4; j++) {
					if (n == 3)
						break;
					if (Magnet[j - 1].get(2) == Magnet[j].get(6))
						break;
					tmp *= -1;
					turnInfo[j] = tmp;
				}
//				System.out.println(Arrays.toString(turnInfo));
				for (int j = 0; j < 4; j++) {
					turn(j, turnInfo[j]);
				}
			}
			int score = Magnet[0].get(0) * 1 + Magnet[1].get(0) * 2 + Magnet[2].get(0) * 4 + Magnet[3].get(0) * 8;
			sb.append("#").append(tc).append(" ").append(score).append("\n");
		}
		System.out.println(sb);

	}

	public static void turn(int n, int d) {
		if (d == 0) // 회전 X
			return;
		if (d == 1) { // 시계방향
			Magnet[n].add(0, Magnet[n].get(Magnet[n].size() - 1));
			Magnet[n].remove(Magnet[n].size() - 1);
		} else { // 반시계방향
			Magnet[n].add(Magnet[n].get(0));
			Magnet[n].remove(0);
		}
	}
}
