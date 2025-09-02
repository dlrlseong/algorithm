import java.io.*;
import java.util.*;

public class Solution {
    static int N, MaxCore, MinEdge;
    static int Map[][];
    static List<int[]> coreList = new ArrayList<>();
    static final int[] dr = { -1, 1, 0, 0 };
    static final int[] dc = { 0, 0, -1, 1 };
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(st.nextToken());
        
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            Map = new int[N][N];
            MaxCore = 0;
            MinEdge = Integer.MAX_VALUE;
            coreList.clear();
            
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    Map[i][j] = Integer.parseInt(st.nextToken());
                    // 가장자리가 아닌 코어만 추가
                    if (i != 0 && j != 0 && i != N-1 && j != N-1 && Map[i][j] == 1) {
                        coreList.add(new int[] { i, j });
                    }
                }
            }
            
            dfs(0, 0, 0);
            sb.append("#").append(tc).append(" ").append(MinEdge).append("\n");
        }
        System.out.println(sb);
    }
    
    private static void dfs(int idx, int connectedCore, int totalEdge) {
        // 가지치기: 남은 코어를 모두 연결해도 현재 최대값을 넘을 수 없는 경우
        if (connectedCore + (coreList.size() - idx) < MaxCore) {
            return;
        }
        
        if (idx == coreList.size()) {
            if (connectedCore > MaxCore) {
                MaxCore = connectedCore;
                MinEdge = totalEdge;
            } else if (connectedCore == MaxCore) {
                MinEdge = Math.min(MinEdge, totalEdge);
            }
            return;
        }
        
        int[] core = coreList.get(idx);
        
        // 4방향 + 연결하지 않는 경우(5번째)
        for (int dir = 0; dir <= 4; dir++) {
            if (dir == 4) { // 연결하지 않는 경우
                dfs(idx + 1, connectedCore, totalEdge);
            } else {
                int edgeCount = canConnect(core[0], core[1], dir);
                if (edgeCount > 0) {
                    // 전선 설치
                    setWire(core[0], core[1], dir, 2);
                    dfs(idx + 1, connectedCore + 1, totalEdge + edgeCount);
                    // 전선 제거 (백트래킹)
                    setWire(core[0], core[1], dir, 0);
                }
            }
        }
    }
    
    private static int canConnect(int r, int c, int dir) {
        int count = 0;
        int nr = r, nc = c;
        
        while (true) {
            nr += dr[dir];
            nc += dc[dir];
            
            if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                return count; // 가장자리 도달
            }
            
            if (Map[nr][nc] != 0) {
                return -1; // 장애물 발견
            }
            count++;
        }
    }
    
    private static void setWire(int r, int c, int dir, int value) {
        int nr = r, nc = c;
        
        while (true) {
            nr += dr[dir];
            nc += dc[dir];
            
            if (nr < 0 || nc < 0 || nr >= N || nc >= N) {
                break;
            }
            Map[nr][nc] = value;
        }
    }
}