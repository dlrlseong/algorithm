import java.io.*;
import java.util.*;

public class Main { //하향식
    static int n, m;
    static int[][] maze;
    static int[][] memo;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        maze = new int[n+1][m+1]; // 1-indexed로 사용
        memo = new int[n+1][m+1];
        
        // 미로 입력
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                maze[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // memo 배열 초기화 (-1로 초기화하여 아직 계산하지 않음을 표시)
        for (int i = 0; i <= n; i++) {
            Arrays.fill(memo[i], -1);
        }
        
        // (n, m)에서 얻을 수 있는 최대 사탕 개수
        System.out.println(solve(n, m));
    }
    
    // 하향식 DP: (r, c)에 도달할 때까지 얻을 수 있는 최대 사탕 개수
    static int solve(int r, int c) {
        // 기저 조건: 시작점
        if (r == 1 && c == 1) {
            return maze[1][1];
        }
        
        // 범위를 벗어나면 0 (불가능한 경로)
        if (r < 1 || c < 1) {
            return 0;
        }
        
        // 이미 계산된 값이 있으면 반환 (메모이제이션)
        if (memo[r][c] != -1) {
            return memo[r][c];
        }
        
        // 세 가지 이동 방향에서 올 수 있는 경우 중 최댓값
        int result = 0;
        result = Math.max(result, solve(r-1, c));     // 위에서 오는 경우
        result = Math.max(result, solve(r, c-1));     // 왼쪽에서 오는 경우  
        result = Math.max(result, solve(r-1, c-1));   // 대각선에서 오는 경우
        
        // 현재 칸의 사탕을 더해서 저장
        return memo[r][c] = result + maze[r][c];
    }
}