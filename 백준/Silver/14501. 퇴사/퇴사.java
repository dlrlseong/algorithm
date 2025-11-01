import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N + 1]; // 상담 기간
        int[] P = new int[N + 1]; // 상담 수익
        int[] dp = new int[N + 2]; // dp[i] = i일부터 마지막까지 얻을 수 있는 최대 수익
        
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        
        // 뒤에서부터 계산
        for (int i = N; i >= 1; i--) {
            int next = i + T[i]; // 이 상담을 끝낸 다음 날
            
            if (next > N + 1) {
                // 상담을 끝낼 수 없는 경우 (퇴사일을 넘김)
                dp[i] = dp[i + 1];
            } else {
                // 이 상담을 하는 경우 vs 안 하는 경우 중 최댓값
                dp[i] = Math.max(P[i] + dp[next], dp[i + 1]);
            }
        }
        
        System.out.println(dp[1]);
    }
}