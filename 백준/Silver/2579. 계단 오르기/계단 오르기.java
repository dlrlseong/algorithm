import java.io.*;
import java.util.*;

public class Main {
    static int N, stairs[];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken());
        stairs = new int[1 + N];
        
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            stairs[i] = Integer.parseInt(st.nextToken());
        }
        
        if (N == 1) {
            System.out.println(stairs[1]);
            return;
        }
        
        // dp[i][j] = i번째 계단에 도달했을 때 최대 점수
        // j = 0: 한 칸 올라온 경우, j = 1: 두 칸 올라온 경우
        int[][] dp = new int[N + 1][2];
        
        dp[1][0] = stairs[1];
        dp[1][1] = stairs[1];
        dp[2][0] = stairs[1] + stairs[2];
        dp[2][1] = stairs[2];
        
        for (int i = 3; i <= N; i++) {
            // 한 칸 올라온 경우: 이전 계단은 두 칸 올라온 것이어야 함
            dp[i][0] = dp[i - 1][1] + stairs[i];
            
            // 두 칸 올라온 경우: i-2번째 계단의 최댓값
            dp[i][1] = Math.max(dp[i - 2][0], dp[i - 2][1]) + stairs[i];
        }
        
        System.out.println(Math.max(dp[N][0], dp[N][1]));
    }
}