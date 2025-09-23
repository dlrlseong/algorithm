import java.io.*;
import java.util.*;

public class Main {
    static class Pillar {
        int x, h;
        
        Pillar(int x, int h) {
            this.x = x;
            this.h = h;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        List<Pillar> pillars = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            pillars.add(new Pillar(x, h));
        }
        
        // x 좌표 기준으로 정렬
        pillars.sort((a, b) -> a.x - b.x);
        
        // 가장 높은 기둥의 인덱스 찾기 (가장 왼쪽에 있는 최고 높이)
        int maxHeightIdx = 0;
        int maxHeight = 0;
        for (int i = 0; i < n; i++) {
            if (pillars.get(i).h > maxHeight) {
                maxHeight = pillars.get(i).h;
                maxHeightIdx = i;
            }
        }
        
        int area = 0;
        
        // 왼쪽에서 가장 높은 기둥까지
        int leftMaxHeight = 0;
        for (int i = 0; i <= maxHeightIdx; i++) {
            if (pillars.get(i).h > leftMaxHeight) {
                leftMaxHeight = pillars.get(i).h;
            }
            
            if (i == maxHeightIdx) {
                // 최고 기둥은 폭 1만 추가
                area += leftMaxHeight;
            } else {
                // 현재 기둥부터 다음 기둥 직전까지
                area += leftMaxHeight * (pillars.get(i+1).x - pillars.get(i).x);
            }
        }
        
        // 오른쪽에서 가장 높은 기둥까지 (역순으로)
        int rightMaxHeight = 0;
        for (int i = n-1; i > maxHeightIdx; i--) {
            if (pillars.get(i).h > rightMaxHeight) {
                rightMaxHeight = pillars.get(i).h;
            }
            
            // 현재 기둥부터 다음(왼쪽) 기둥 직전까지
            area += rightMaxHeight * (pillars.get(i).x - pillars.get(i-1).x);
        }
        
        System.out.println(area);
    }
}