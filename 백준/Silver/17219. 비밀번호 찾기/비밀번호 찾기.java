import java.util.*;
import java.io.*;

class Main {
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        HashMap<String, String> password = new HashMap<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            String site = st.nextToken();	//Key
            String ps = st.nextToken();		//Value
            password.put(site, ps);
        }
        for(int i=0;i<M;i++){
            String site = br.readLine();
            sb.append(password.get(site) + "\n");
        }
        
        System.out.println(sb);
    }
}