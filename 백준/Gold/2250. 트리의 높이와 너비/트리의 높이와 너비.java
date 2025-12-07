import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] left, right, parent;
    static int[] minCol, maxCol;
    static int col = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        left = new int[N + 1];
        right = new int[N + 1];
        parent = new int[N + 1];
        Arrays.fill(left, -1);
        Arrays.fill(right, -1);

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            left[v] = l;
            right[v] = r;
            if (l != -1) parent[l] = v;
            if (r != -1) parent[r] = v;
        }

        int root = 0;
        for (int i = 1; i <= N; i++) {
            if (parent[i] == 0) {
                root = i;
                break;
            }
        }

        minCol = new int[N + 2];
        maxCol = new int[N + 2];
        Arrays.fill(minCol, Integer.MAX_VALUE);

        inorder(root, 1);

        int level = 1;
        int maxWidth = 0;
        for (int d = 1; d <= N; d++) {
            if (maxCol[d] == 0) continue;
            int width = maxCol[d] - minCol[d] + 1;
            if (width > maxWidth) {
                maxWidth = width;
                level = d;
            }
        }

        System.out.println(level + " " + maxWidth);
    }

    static void inorder(int node, int depth) {
        if (node == -1) return;
        inorder(left[node], depth + 1);
        minCol[depth] = Math.min(minCol[depth], col);
        maxCol[depth] = Math.max(maxCol[depth], col);
        col++;
        inorder(right[node], depth + 1);
    }
}
