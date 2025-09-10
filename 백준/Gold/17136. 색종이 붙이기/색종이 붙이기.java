import java.io.*;
import java.util.*;

public class Main {
    static int[] cnt = new int[6];
    static int MIN = Integer.MAX_VALUE;
    static int[][] arr = new int[10][10];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dfs(0, 0, 0);
        System.out.println(MIN == Integer.MAX_VALUE ? -1 : MIN);
    }
    
    public static void dfs(int r, int c, int usedPapers) {
        // 조기 종료 - 원본과 동일한 위치
        if (r == 10) {
            MIN = Math.min(MIN, usedPapers);
            return;
        }
        
        // 가지치기 강화 - 더 앞으로 이동
        if (usedPapers >= MIN) return;
        
        // 원본과 동일한 좌표 이동 방식 유지
        if (arr[r][c] == 0) {
            dfs(r + ((c + 1) / 10), (c + 1) % 10, usedPapers);
            return;
        }
        
        // 큰 크기부터 시도 - 원본과 동일
        for (int size = 5; size >= 1; size--) {
            if (cnt[size] < 5 && canPlace(r, c, size)) {
                place(r, c, size, true);
                dfs(r + ((c + 1) / 10), (c + 1) % 10, usedPapers + 1);
                place(r, c, size, false);
            }
        }
    }
    
    // vis 배열 없이 arr 직접 수정
    private static void place(int r, int c, int size, boolean put) {
        cnt[size] += put ? 1 : -1;
        int value = put ? 0 : 1;
        
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                arr[i][j] = value;
            }
        }
    }
    
    private static boolean canPlace(int r, int c, int size) {
        if (r + size > 10 || c + size > 10) return false;
        
        for (int i = r; i < r + size; i++) {
            for (int j = c; j < c + size; j++) {
                if (arr[i][j] != 1) return false;
            }
        }
        return true;
    }
}