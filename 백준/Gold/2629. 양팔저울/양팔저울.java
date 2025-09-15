import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[] weights = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int totalWeight = 0;
        for (int i = 0; i < n; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
            totalWeight += weights[i];
        }
        
        boolean[] possible = new boolean[totalWeight + 1];
        possible[0] = true; 
        for (int i = 0; i < n; i++) {
            boolean[] temp = new boolean[totalWeight + 1];
            
            for (int j = 0; j <= totalWeight; j++) {
                if (possible[j]) {
                    temp[j] = true;
                    if (j + weights[i] <= totalWeight) {
                        temp[j + weights[i]] = true;
                    }
                    temp[Math.abs(j - weights[i])] = true;
                }
            }
            possible = temp;
        }
        
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int marble = Integer.parseInt(st.nextToken());
            if (marble <= totalWeight && possible[marble]) {
                sb.append("Y ");
            } else {
                sb.append("N ");
            }
        }
        
        System.out.println(sb.toString().trim());
    }
}