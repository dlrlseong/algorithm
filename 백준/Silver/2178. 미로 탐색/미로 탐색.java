import java.io.*;
import java.util.*;

public class Main {
	static int N,M;
	static int[][] MIRO;
	static boolean[][] v;
	static int MIN=Integer.MAX_VALUE;
	//static int[] dr={1,0,0,-1}; // 하 우 좌 상
	//static int[] dc={0,1,-1,0};
    static int[] dr={-1,1,0,0}; // 상 하 좌 우
	static int[] dc={0,0,-1,1};
	public static void main(String[] args) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st=new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		MIRO=new int[N][M]; v=new boolean[N][M];
		for(int i=0; i<N; i++){
			String str=br.readLine();
			for(int j=0; j<M; j++){
				MIRO[i][j]=str.charAt(j)-'0';
			}
		}
		bfs();
		System.out.println(MIN);
	}
	private static void bfs() {
		Deque<int[]>q=new ArrayDeque<>();
		v[0][0]=true;
		q.offerLast(new int[]{0,0});
		while(!q.isEmpty()){
			int[] now=q.pollFirst();
			int nr=now[0]; int nc=now[1];
			if(nr==N-1 && nc==M-1){
				MIN=Math.min(MIN, MIRO[nr][nc]);
			}
			for(int d=0; d<4; d++){
				int r=nr+dr[d];
				int c=nc+dc[d];
				if(r<0||c<0||r>=N||c>=M) continue;
				if(!v[r][c] && MIRO[r][c]==1) {
					v[r][c]=true;
					MIRO[r][c]+=MIRO[nr][nc];
					q.offerLast(new int[] {r,c});
				}
			}
		}
	}
}
