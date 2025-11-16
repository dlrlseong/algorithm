import java.io.*;
import java.util.*;
public class Main {
	public static int N;
	public static int []Snow;
	public static class SnowMan{
		int height;
		int first;
		int second;
	}
	public static SnowMan []snowMan;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Snow = new int[N];
		snowMan = new SnowMan[N*(N-1)/2];
		for(int i=0;i<N;i++){
			Snow[i] = Integer.parseInt(st.nextToken());
		}
		int idx =0;
		for(int i=0;i<N-1;i++){
			for(int j=i+1;j<N;j++){
				snowMan[idx] = new SnowMan();
				snowMan[idx].first = i;
				snowMan[idx].second = j;
				snowMan[idx++].height = Snow[i]+ Snow[j];
			}
		}
		int ans = Integer.MAX_VALUE;
		Arrays.sort(snowMan,(o1,o2)->o1.height-o2.height);
		for(int i=0;i<idx-1;i++){
			for(int j=i+1;j<idx;j++){
				if(snowMan[i].first!= snowMan[j].first && snowMan[i].first != snowMan[j].second &&
						snowMan[i].second != snowMan[j].first && snowMan[i].second != snowMan[j].second){
					ans = Math.min(ans, snowMan[j].height - snowMan[i].height);
					break;
				}
			}
		}
		System.out.print(ans);
	}

}