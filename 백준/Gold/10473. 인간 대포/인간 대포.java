import java.io.*;
import java.util.*;

public class Main {

	static Point A, B;
	static List<Point> cannonList = new ArrayList<>();
	static double distance[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		double x = Double.parseDouble(st.nextToken());
		double y = Double.parseDouble(st.nextToken());
		A = new Point(false, x, y);
		st = new StringTokenizer(br.readLine(), " ");
		x = Double.parseDouble(st.nextToken());
		y = Double.parseDouble(st.nextToken());
		B = new Point(false, x, y);

		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			x = Double.parseDouble(st.nextToken());
			y = Double.parseDouble(st.nextToken());
			cannonList.add(new Point(true, x, y));
		}
		cannonList.add(0, A);
		cannonList.add(B);

		distance = new double[cannonList.size()][cannonList.size()];
		for (int i = 0; i < cannonList.size() - 1; i++) {
			for (int j = i + 1; j < cannonList.size(); j++) {
				distance[i][j] = getWeight(cannonList.get(i), cannonList.get(j));
				distance[j][i] = distance[i][j];
			}
		}

		double minDistance[] = new double[cannonList.size()];
		final double INF = Double.MAX_VALUE;
		Arrays.fill(minDistance, INF);
		minDistance[0] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> Double.compare(a.weight, b.weight));
		pq.add(new Node(0, 0.0));
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int from = cur.from;
			double curWeight = cur.weight;

			if (minDistance[from] < curWeight)
				continue;

			for (int to = 0; to < cannonList.size(); to++) {
				if (from == to)
					continue;
				double nextWeight = curWeight + distance[from][to];
				if (minDistance[to] > nextWeight) {
					minDistance[to] = nextWeight;
					pq.offer(new Node(to, nextWeight));
				}
//				System.out.println(Arrays.toString(minDistance));
			}
//			System.out.println();
		}

		System.out.println(minDistance[N + 1]);
	}

	static class Node {
		int from;
		double weight;

		public Node(int from, double weight) {
			super();
			this.from = from;
			this.weight = weight;
		}
	}

	private static double getWeight(Point from, Point to) {
		if (!from.isCannon) {
			return getDistance(from, to) / 5.0;
		}

		double d = getDistance(from, to);
		// 걸어가는 경우
		double d1 = d / 5.0;
		// 대포 타고 가는 경우
		double d2 = 2 + Math.abs(d - 50.0) / 5.0;
		return Math.min(d1, d2);
	}

	private static double getDistance(Point from, Point to) {
		double absX = Math.abs(from.x - to.x);
		double absY = Math.abs(from.y - to.y);
		return Math.sqrt(absX * absX + absY * absY);
	}

	static class Point {
		boolean isCannon;
		double x, y;

		public Point(boolean isCannon, double x, double y) {
			super();
			this.isCannon = isCannon;
			this.x = x;
			this.y = y;
		}
	}
}
