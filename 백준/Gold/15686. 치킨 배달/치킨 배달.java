import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Point> houses = new ArrayList<>();
    static ArrayList<Point> chickens = new ArrayList<>();
    static boolean[] selected;
    static int minDistance = Integer.MAX_VALUE;
    
    static class Point {
        int x, y;
        
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        // 지도 정보 입력받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int value = Integer.parseInt(st.nextToken());
                if (value == 1) {
                    houses.add(new Point(i, j));
                } else if (value == 2) {
                    chickens.add(new Point(i, j));
                }
            }
        }
        
        selected = new boolean[chickens.size()];
        
        // M개의 치킨집 선택
        selectChicken(0, 0);
        
        System.out.println(minDistance);
    }
    
    // 조합으로 M개의 치킨집 선택
    static void selectChicken(int idx, int count) {
        if (count == M) {
            // 도시의 치킨 거리 계산
            int cityDistance = 0;
            
            for (Point house : houses) {
                int chickenDistance = Integer.MAX_VALUE;
                
                // 각 집에서 선택된 치킨집까지의 최소 거리
                for (int i = 0; i < chickens.size(); i++) {
                    if (selected[i]) {
                        Point chicken = chickens.get(i);
                        int distance = Math.abs(house.x - chicken.x) + 
                                      Math.abs(house.y - chicken.y);
                        chickenDistance = Math.min(chickenDistance, distance);
                    }
                }
                
                cityDistance += chickenDistance;
            }
            
            minDistance = Math.min(minDistance, cityDistance);
            return;
        }
        
        if (idx == chickens.size()) {
            return;
        }
        
        // 현재 치킨집 선택
        selected[idx] = true;
        selectChicken(idx + 1, count + 1);
        
        // 현재 치킨집 선택 안함
        selected[idx] = false;
        selectChicken(idx + 1, count);
    }
}