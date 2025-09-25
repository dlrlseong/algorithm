import java.util.*;
import java.io.*;

public class Main {
	static class Item {
		int id, revenue, dest, cost;

		public Item(int id, int revenue, int dest, int cost) {
			super();
			this.id = id;
			this.revenue = revenue;
			this.dest = dest;
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Item [id=" + id + ", revenue=" + revenue + ", dest=" + dest + ", cost=" + cost + "]";
		}

	}

	static class Edge {
		int to, weight;

		public Edge(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

	}

	static Item[] itemList = new Item[30001];
	static List<Edge> graph[];
	static final int INF = Integer.MAX_VALUE;
	static int[] shortestPath;
	static PriorityQueue<Item> primaryItem = new PriorityQueue<>(new Comparator<Item>() {
		@Override
		public int compare(Item o1, Item o2) {
			if (o1.cost == o2.cost) {
				return Integer.compare(o1.id, o2.id);
			}
			return Integer.compare(o1.cost, o2.cost) * -1;
		}
	});
	static Set<Integer> deletedID = new HashSet<>();
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int Q = Integer.parseInt(st.nextToken()); // 명령 수
		for (int i = 0; i < Q; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int cmd = Integer.parseInt(st.nextToken());

			switch (cmd) {
			case 100: // INIT
				int V = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());

//				System.out.println(cmd + " " + V + " " + E);
				graph = new ArrayList[V];
				for (int j = 0; j < V; j++) {
					graph[j] = new ArrayList<>();
				}

				for (int j = 0; j < E; j++) {
					int from = Integer.parseInt(st.nextToken());
					int to = Integer.parseInt(st.nextToken());
					int weight = Integer.parseInt(st.nextToken());

					graph[from].add(new Edge(to, weight));
					graph[to].add(new Edge(from, weight));
				}
				shortestPath = new int[V];
				initStartVertex(0);
				break;

			case 200: // INSERT
				int id = Integer.parseInt(st.nextToken());
				int revenue = Integer.parseInt(st.nextToken());
				int dest = Integer.parseInt(st.nextToken());
//				System.out.println(cmd + " " + id + " " + revenue + " " + dest);
				insert(id, revenue, dest);
				break;

			case 300: // DELETE
				int deleted_id = Integer.parseInt(st.nextToken());
//				System.out.println(cmd + " " + deleted_id);
				if (itemList[deleted_id] != null) {
					deletedID.add(deleted_id);
				}
				break;

			case 400:
				while (true) {
					if (primaryItem.isEmpty() || primaryItem.peek().cost < 0) {
//						System.out.println(cmd + " " + "-1");
						sb.append("-1").append("\n");
						break;
					}
					int peekId = primaryItem.peek().id;
					if (deletedID.contains(peekId)) {
						primaryItem.poll();
						continue;
					}
					primaryItem.poll();
					deletedID.add(peekId);
//					System.out.println(cmd + " " + peekId);
					sb.append(peekId).append("\n");
					break;
				}
				break;
			case 500:
				int newStart = Integer.parseInt(st.nextToken());
//				System.out.println(cmd + " " + newStart);
				initStartVertex(newStart);
				break;

			default:
				break;
			}
		}
		System.out.print(sb);
	}

	private static void insert(int id, int revenue, int dest) {
		itemList[id] = new Item(id, revenue, dest, revenue - shortestPath[dest]);
//		System.out.println("삽입완료: " + itemList[id]);

		primaryItem.offer(itemList[id]);
//		System.out.println("최적아이템큐 추가 완료 \n 현재 최적 아이템 : " + primaryItem.peek());
	}

	private static void initStartVertex(int start) {
		// Dijkstra
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
		Arrays.fill(shortestPath, INF);
		shortestPath[start] = 0;
		pq.offer(new int[] { start, 0 });

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int curVertex = cur[0];
			int curCost = cur[1];
			if (shortestPath[curVertex] < curCost)
				continue;

			for (Edge e : graph[curVertex]) {
				int nextVertex = e.to;
				int nextCost = curCost + e.weight;

				if (shortestPath[nextVertex] > nextCost) {
					shortestPath[nextVertex] = nextCost;
					pq.offer(new int[] { nextVertex, nextCost });
				}
			}
		}
//		System.out.println("최단경로 출력: " + Arrays.toString(shortestPath));

		// UPDATE itemList
		primaryItem.clear();
		for (int i = 0; i < itemList.length; i++) {
			if (itemList[i] == null)
				continue;
			// INSERT
			insert(i, itemList[i].revenue, itemList[i].dest);
		}
	}

}
