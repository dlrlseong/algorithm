import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        // PriorityQueue 대신 배열 사용
        int[] times = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }
        
        // 퀵 정렬 대신 계수 정렬 사용 (시간이 1~1000 범위인 경우에 효율적)
        int[] count = new int[1001]; // 문제 제약: Pi는 1~1000
        
        for (int i = 0; i < N; i++) {
            count[times[i]]++;
        }
        
        int idx = 0;
        for (int i = 1; i <= 1000; i++) {
            while (count[i] > 0) {
                times[idx++] = i;
                count[i]--;
            }
        }
        
        // 합 계산 최적화
        int sum = 0;
        int waitingTime = 0;
        
        for (int i = 0; i < N; i++) {
            waitingTime += times[i]; // 현재 사람이 기다리는 시간
            sum += waitingTime; // 총합에 추가
        }
        
        System.out.println(sum);
    }
}