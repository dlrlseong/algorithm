import java.io.*;
import java.util.*;

public class Solution {
    static int N;
    static int[] balloons;
    static int[][] dp;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            balloons = new int[N + 2];
            
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                balloons[i] = Integer.parseInt(st.nextToken());
            }
            balloons[0] = 1;      // 왼쪽 경계
            balloons[N + 1] = 1;  // 오른쪽 경계
            
            dp = new int[N + 1][N + 1];
            
            int maxScore = solveBottomUp();
            sb.append('#').append(tc).append(' ').append(maxScore).append('\n');
        }
        System.out.print(sb);
        br.close();
    }
    
    private static int solveBottomUp() {
        // 길이별로 상향식 계산
        for (int len = 1; len <= N; len++) {           // 구간 길이
            for (int start = 1; start <= N - len + 1; start++) { // 시작점
                int end = start + len - 1;             // 끝점
                
                // start부터 end까지 구간에서 k번째 풍선을 마지막에 터뜨리는 경우
                for (int k = start; k <= end; k++) {
                    int currentScore = 0;
                    
                    // 왼쪽 구간: [start, k-1]
                    if (k - 1 >= start) {
                        currentScore += dp[start][k - 1];
                    }
                    
                    // 오른쪽 구간: [k+1, end]  
                    if (k + 1 <= end) {
                        currentScore += dp[k + 1][end];
                    }
                    
                    // k번째 풍선을 터뜨릴 때 얻는 점수
                    int leftVal = balloons[start - 1];   // 구간 왼쪽 경계
                    int rightVal = balloons[end + 1];    // 구간 오른쪽 경계
                    
                    if (start == 1 && end == N) {
                        // 전체 구간이면 풍선 자체 값
                        currentScore += balloons[k];
                    } else {
                        // 부분 구간이면 경계값들의 곱
                        currentScore += leftVal * rightVal;
                    }
                    
                    dp[start][end] = Math.max(dp[start][end], currentScore);
                }
            }
        }
        return dp[1][N];
    }
}