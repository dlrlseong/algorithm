import java.io.*;
import java.util.*;

public class Main {
    static Map<String, String> parent;
    static Map<String, Integer> size;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int M = Integer.parseInt(br.readLine());
            parent = new HashMap<>();
            size = new HashMap<>();
            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                if (!parent.containsKey(a)) {
                    parent.put(a, a);
                    size.put(a, 1);
                }
                if (!parent.containsKey(b)) {
                    parent.put(b, b);
                    size.put(b, 1);
                }
                sb.append(union(a, b)).append("\n");
            }
        }
        System.out.print(sb);
    }

    public static String find(String x) {
        if (!parent.get(x).equals(x))
            parent.put(x, find(parent.get(x)));
        return parent.get(x);
    }

    public static int union(String x, String y) {
        String rootX = find(x);
        String rootY = find(y);
        if (!rootX.equals(rootY)) {
            parent.put(rootY, rootX);
            size.put(rootX, size.get(rootX) + size.get(rootY));
        }
        return size.get(rootX);
    }
}