import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 1. 병사 수 입력
		int N = Integer.parseInt(br.readLine());

		// 2. 병사들의 전투력 입력
		int[] soldiers = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			soldiers[i] = Integer.parseInt(st.nextToken());
		}

		// 3. DP 배열 생성 (각 위치에서 가장 긴 감소 수열의 길이)
		int[] dp = new int[N];
		Arrays.fill(dp, 1); // 모든 병사는 최소 자기 자신만으로 길이 1

		// 4. LDS(Longest Decreasing Subsequence) 찾기
		for (int i = 1; i < N; i++) {
			for (int j = 0; j < i; j++) {
				// 이전 병사보다 현재 병사의 전투력이 작으면 (감소 수열)
				if (soldiers[j] > soldiers[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}

		// 5. 가장 긴 감소 수열의 길이 찾기
		int maxLength = 0;
		for (int i = 0; i < N; i++) {
			maxLength = Math.max(maxLength, dp[i]);
		}

		// 6. 열외시켜야 할 병사 수 = 전체 - 최장 감소 수열
		System.out.println(N - maxLength);
	}
}