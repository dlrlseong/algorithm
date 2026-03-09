import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		double X = Double.parseDouble(st.nextToken());

		PriorityQueue<Double> pq = new PriorityQueue<>(new Comparator<Double>() {
			@Override
			public int compare(Double o1, Double o2) {
				return Double.compare(o1, o2);
			}
		});

		pq.add(64.0);
		double sum = 64;
		while (sum != X) {
			double min = pq.poll();
			sum -= min;
			double tmp = min / 2;
			pq.add(tmp);
			sum += tmp;
			if (sum < X) {
				pq.add(tmp);
				sum += tmp;
			}
		}
		System.out.println(pq.size());

	}

}
