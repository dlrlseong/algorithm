import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			String strNum = st.nextToken();
			int intNum = Integer.parseInt(strNum);
			if (intNum == 0)
				break;
			boolean isit = true;
			for (int i = 0; i < strNum.length(); i++) {
				int j = strNum.length() - 1 - i;
				if (i >= j) break;
				if(strNum.charAt(i)!=strNum.charAt(j)) {
					isit = false;
					break;
				}
			}
			sb.append(isit?"yes":"no").append("\n");
		}
		System.out.println(sb);
	}
}
