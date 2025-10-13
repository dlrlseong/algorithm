import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		double sum = 0;

		Node nodeList[] = new Node[10001];
		for (int i = 0; i < nodeList.length; i++) {
			nodeList[i] = new Node(i, 0);
		}

		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int tmp = Integer.parseInt(st.nextToken());
			sum += tmp;
			list.add(tmp);
			nodeList[tmp + 5000].cnt++;
		}
		Collections.sort(list);
		Arrays.sort(nodeList, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if (o1.cnt == o2.cnt) {
					return Integer.compare(o1.num, o2.num);
				}
				return Integer.compare(o1.cnt, o2.cnt) * -1;
			}
		});
		System.out.println(Math.round(sum / (double) N));
		System.out.println(list.get(N / 2));
		int freqNum = nodeList[0].num;
		if (nodeList[0].cnt == nodeList[1].cnt) {
			freqNum = nodeList[1].num;
		}
		System.out.println(freqNum - 5000);
		System.out.println(list.get(N - 1) - list.get(0));
	}

	static class Node {
		int num, cnt;

		public Node(int num, int cnt) {
			super();
			this.num = num;
			this.cnt = cnt;
		}

	}
}