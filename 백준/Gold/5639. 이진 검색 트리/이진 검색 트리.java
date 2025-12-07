import java.io.*;
import java.util.*;

public class Main {

	static int[] pre;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 1. EOF까지 전부 읽어서 리스트에 저장
		String line;
		List<Integer> list = new ArrayList<>();
		while ((line = br.readLine()) != null && line.length() > 0) {
			list.add(Integer.parseInt(line));
		}

		// 2. 배열로 옮기기
		pre = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			pre[i] = list.get(i);
		}

		// 3. 전위 순회 결과 pre[0..n-1] 전체를 하나의 트리라고 보고 처리
		solve(0, pre.length - 1);

		// 4. 출력
		System.out.print(sb);
	}

	// pre[l..r] 구간이 하나의 서브트리 (전위 순회 결과)
	static void solve(int l, int r) {
		if (l > r)
			return;

		int root = pre[l];

		// root보다 큰 값이 처음 나오는 위치 = 오른쪽 서브트리 시작 인덱스
		int idx = l + 1;
		while (idx <= r && pre[idx] < root) {
			idx++;
		}

		// 왼쪽 서브트리: (l+1 ~ idx-1)
		solve(l + 1, idx - 1);
		// 오른쪽 서브트리: (idx ~ r)
		solve(idx, r);
		// 후위 순회니까 마지막에 루트 출력
		sb.append(root).append('\n');
	}
}
