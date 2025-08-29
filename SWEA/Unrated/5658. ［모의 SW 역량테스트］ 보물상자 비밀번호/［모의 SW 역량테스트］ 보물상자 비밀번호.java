import java.io.*;
import java.util.*;

public class Solution {

	static int N, K;
	static int[] plus = new int[4], minus = new int[4];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			List<Character> list = new ArrayList<>();
			HashSet<Integer> set = new HashSet<>();

			st = new StringTokenizer(br.readLine(), " ");
			String str = st.nextToken();
			String tmpStr = "";

			for (int i = 0; i < N; i++) {
				char tmp = str.charAt(i);
				list.add(tmp);
				tmpStr += tmp;
				if ((i + 1) % (N / 4) == 0) {
					set.add(Integer.parseInt(tmpStr, 16));
					tmpStr = "";
				}
			}

			for (int i = 0; i < (N / 4) - 1; i++) {
				char tmp = list.remove(list.size() - 1);
				list.add(0, tmp);
				for (int j = 0; j < N; j++) {
					tmpStr += list.get(j);
					if ((j + 1) % (N / 4) == 0) {
						set.add(Integer.parseInt(tmpStr, 16));
						tmpStr = "";
					}
				}
			}
			List<Integer> answers = new ArrayList<>();
			for (Integer integer : set) {
				answers.add(integer);
			}

			Collections.sort(answers, Comparator.reverseOrder());
			sb.append("#").append(tc).append(" ").append(answers.get(K - 1)).append("\n");
		}
		System.out.println(sb);
	}

}
