import java.io.*;
import java.util.*;

public class Main {
	static class Node {
		int num, age;
		String name;

		public Node(int num, int age, String name) {
			super();
			this.num = num;
			this.age = age;
			this.name = name;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());

		List<Node> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			list.add(new Node(i, num, name));
		}

		Collections.sort(list, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if (o1.age == o2.age) {
					return Integer.compare(o1.num, o2.num);
				}
				return Integer.compare(o1.age, o2.age);
			}
		});

		for (Node node : list) {
			System.out.println(node.age + " " + node.name);
		}
	}
}
