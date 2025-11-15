import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 공 정보 저장 (색상, 크기, 원래 인덱스)
        int[][] balls = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            balls[i][0] = Integer.parseInt(st.nextToken()); // 색상
            balls[i][1] = Integer.parseInt(st.nextToken()); // 크기
            balls[i][2] = i; // 원래 인덱스
        }

        // 크기 순으로 정렬 (크기 같으면 색상 순)
        Arrays.sort(balls, (a, b) -> {
            if (a[1] != b[1]) return a[1] - b[1];
            return a[0] - b[0];
        });

        int[] answer = new int[N];
        int[] colorSum = new int[200_001]; // 각 색상별 크기 합
        int totalSum = 0; // 전체 크기 합
        
        int j = 0;
        while (j < N) {
            int currentSize = balls[j][1];
            int tempSum = totalSum;
            
            // 같은 크기의 공들을 먼저 처리
            int start = j;
            while (j < N && balls[j][1] == currentSize) {
                int color = balls[j][0];
                int idx = balls[j][2];
                
                // 같은 색상의 합을 빼기
                answer[idx] = tempSum - colorSum[color];
                j++;
            }
            
            // 같은 크기 공들을 totalSum과 colorSum에 반영
            for (int k = start; k < j; k++) {
                int color = balls[k][0];
                int size = balls[k][1];
                totalSum += size;
                colorSum[color] += size;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(answer[i]).append("\n");
        }
        System.out.println(sb);
    }
}