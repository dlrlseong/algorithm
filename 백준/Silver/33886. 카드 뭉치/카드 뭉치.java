import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N, numbers[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        numbers = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        
        // dp[i] = i번째 위치에 도달하는데 필요한 최소 이동 횟수
        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        
        for (int i = 0; i < N; i++) {
            if (dp[i] == Integer.MAX_VALUE) continue;
            
            int K = numbers[i];
            int maxReach = Math.min(i + K, N);
            
            // i번째 위치에서 점프 가능한 모든 위치 갱신
            for (int next = i + 1; next <= maxReach; next++) {
                dp[next] = Math.min(dp[next], dp[i] + 1);
            }
        }
        
        System.out.println(dp[N]);
    }
}