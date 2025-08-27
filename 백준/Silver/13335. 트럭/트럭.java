import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Deque<Integer> q = new ArrayDeque<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 트럭 수 
		int w = Integer.parseInt(st.nextToken()); // 다리의 길이 
		int L = Integer.parseInt(st.nextToken()); // 다리의 최대 하중 
		int [] arr = new int [n]; 
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// enqueue를 두번 하면 서 2초씩 가는 루프
		// 큐 sum이 넘으면 0을 enqueue 그리고 큐 길이가 w가 되면 dequeue
		int sum = 0;
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			if (q.size() == w) {
				sum -= q.removeFirst();
			}
			if (sum+arr[i] <= L) {
				q.addLast(arr[i]);
				sum+=arr[i];
			}
			else {
				q.addLast(0);
				i--;
			}
			cnt++;
		}
		System.out.println(cnt+w);

	}
}
