import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static class Node implements Comparable<Node> {
		int num, score;

		public Node(int num, int score) {
			super();
			this.num = num;
			this.score = score;
		}
		@Override
		public int compareTo(Node o) {
			if (this.score == o.score) {
				return Integer.compare(this.num, o.num);
			}
			return Integer.compare(this.score, o.score) * -1;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int scores[] = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			scores[i] = Integer.parseInt(st.nextToken());
		}
		int maxScore = 0;
		Node[] list = new Node[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int who = Integer.parseInt(st.nextToken());
			list[i] = new Node(who, 0);
			for (int j = 0; j < N; j++) {
				char ox = st.nextToken().charAt(0);
				if (ox == 'O') {
					list[i].score += scores[j];
					maxScore = Math.max(maxScore, list[i].score);
				}
			}
		}
		Arrays.sort(list);
		System.out.println(list[0].num + " " + list[0].score);
	}
}
