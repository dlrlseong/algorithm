import java.io.*;
import java.util.*;

public class Main {
	static int N,M,K;
	static int[][] Map;
	static boolean[][] v;
	static StringBuilder sb=new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		M=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		Map=new int[1+M][1+N]; v=new boolean[1+M][1+N];
		for(int i=0; i<K; i++){ //입력
			st=new StringTokenizer(br.readLine());
			int j1=Integer.parseInt(st.nextToken())+1; //0 -> 1
			int i1=M-Integer.parseInt(st.nextToken()); //2 -> 5-2=3
			int j2=Integer.parseInt(st.nextToken()); //4 -> 5
			int i2=M-Integer.parseInt(st.nextToken())+1; //4 -> 5-4+1=2
			for(int j=i2;j<=i1;j++) {
				for(int k=j1;k<=j2;k++) Map[j][k]=1;
			}
		}
//		for(int[] a:Map)System.out.println(Arrays.toString(a));
		List<Integer> areaList = new ArrayList<>();
		for (int i = 1; i <= M; i++) {
		    for (int j = 1; j <= N; j++) {
		        if (Map[i][j] == 0 && !v[i][j]) {
		            int area = bfs(i, j);
		            areaList.add(area);
		        }
		    }
		}
		Collections.sort(areaList);
		sb.append(areaList.size()).append("\n");
		for (int a : areaList) sb.append(a).append(" ");
		System.out.println(sb);
	}
	private static int bfs(int sy, int sx) {
	    int[] dx = {1, -1, 0, 0};
	    int[] dy = {0, 0, 1, -1};
	    int area = 0;
	    Deque<int[]> q = new ArrayDeque<>();
	    q.offer(new int[]{sy, sx});
	    v[sy][sx] = true;
	    while (!q.isEmpty()) {
	        int[] now = q.poll();
	        area++;
	        for (int dir = 0; dir < 4; dir++) {
	            int ny = now[0] + dy[dir];
	            int nx = now[1] + dx[dir];
	            if (ny < 1 || nx < 1 || ny > M || nx > N) continue; // 범위 체크
	            if (Map[ny][nx] != 0 || v[ny][nx]) continue;
	            v[ny][nx] = true;
	            q.offer(new int[]{ny, nx});
	        }
	    }
	    return area;
	}

}
