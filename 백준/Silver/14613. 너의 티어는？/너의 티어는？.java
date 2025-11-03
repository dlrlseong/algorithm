import java.io.*;
import java.util.*;

public class Main {
    static double W, L, D;
    static double[][] dp;
    static double[] tier;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        W = Double.parseDouble(st.nextToken());
        L = Double.parseDouble(st.nextToken());
        D = Double.parseDouble(st.nextToken());
        
        // dp[게임 수][점수] = 해당 상태에 도달할 확률
        // 점수 범위: 1000~3000 -> 인덱스 0~2000
        dp = new double[21][2001];
        tier = new double[5];
        
        dp[0][1000] = 1.0; // 시작: 0게임, 2000점 (인덱스 1000)
        
        for (int game = 0; game < 20; game++) {
            for (int score = 0; score <= 2000; score+=50) {
                if (dp[game][score] == 0) continue;
                
                double prob = dp[game][score];
                
                // 승리: +50점
                if (score + 50 <= 2000) {
                    dp[game + 1][score + 50] += prob * W;
                }
                
                // 패배: -50점
                if (score - 50 >= 0) {
                    dp[game + 1][score - 50] += prob * L;
                }
                
                // 무승부: 점수 유지
                dp[game + 1][score] += prob * D;
            }
        }
        
        // 20게임 후 각 점수대별 확률 계산
        for (int score = 0; score <= 2000; score+=50) {
            if (dp[20][score] == 0) continue;
            
            int realScore = score + 1000; // 실제 점수
            int tierIdx = (realScore - 1000) / 500;
            tierIdx = Math.min(tierIdx, 4); // 3000점 이상도 4번 티어
            
            tier[tierIdx] += dp[20][score];
        }
        
        for (int i = 0; i < 5; i++) {
            System.out.printf("%.8f\n", tier[i]);
        }
    }
}