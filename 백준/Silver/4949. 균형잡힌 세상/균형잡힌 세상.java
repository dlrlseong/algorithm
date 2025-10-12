import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char map[] = { '[', '(', ']', ')' };
		while (true) {
			Stack<Character> stack = new Stack<>();
			boolean isBalanced = true;
			String str = br.readLine();
			if (str.equals("."))
				break;
			for (int i = 0; i < str.length(); i++) {
				char c = str.charAt(i);
				if (isBalanced) {
					for (int j = 0; j < 2; j++) {
						if (c == map[j]) {
							stack.add(c);
//							System.out.println("add " + c);
							break;
						}
					}
					for (int j = 2; j < 4; j++) {
						if (c == map[j]) {
							if (!stack.isEmpty() && stack.peek() == map[(j + 2) % 4]) {
								stack.pop();
							} else
								isBalanced = false;
							break;
						}
					}
				}
			}
//			System.out.println(isBalanced + " " + stack.size());
			if (!isBalanced || !stack.isEmpty()) {
				sb.append("no\n");
			} else {
				sb.append("yes\n");
			}
		}
		System.out.println(sb);
	}
}