import java.io.*;
import java.util.*;

public class Main {
    static int N, M, x, y, K;
    static int[][] map;
    static int[] dice = new int[7]; // 1~6번 면의 값
    static final int[] dx = {0, 0, 0, -1, 1}; // 동, 서, 북, 남
    static final int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int dir = Integer.parseInt(st.nextToken());
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            
            if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
            
            x = nx;
            y = ny;
            
            rollDice(dir);
            
            if (map[x][y] == 0) {
                map[x][y] = dice[6]; // 바닥면을 지도에 복사
            } else {
                dice[6] = map[x][y]; // 지도의 값을 바닥면에 복사
                map[x][y] = 0;
            }
            
            sb.append(dice[1]).append("\n"); // 윗면 출력
        }
        System.out.print(sb);
    }
    
    static void rollDice(int dir) {
        int top = dice[1], north = dice[2], east = dice[3];
        int west = dice[4], south = dice[5], bottom = dice[6];
        
        switch(dir) {
            case 1: // 동
                dice[1] = west;
                dice[3] = top;
                dice[6] = east;
                dice[4] = bottom;
                break;
            case 2: // 서
                dice[1] = east;
                dice[4] = top;
                dice[6] = west;
                dice[3] = bottom;
                break;
            case 3: // 북
                dice[1] = south;
                dice[2] = top;
                dice[6] = north;
                dice[5] = bottom;
                break;
            case 4: // 남
                dice[1] = north;
                dice[5] = top;
                dice[6] = south;
                dice[2] = bottom;
                break;
        }
    }
}