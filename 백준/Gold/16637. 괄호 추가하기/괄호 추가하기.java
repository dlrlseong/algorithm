import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static String expression;
	static ArrayList<Integer> numbers = new ArrayList<>();
	static ArrayList<Character> operators = new ArrayList<>();
	static int maxResult = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		expression = br.readLine();

		// 숫자와 연산자 분리
		for (int i = 0; i < N; i++) {
			if (i % 2 == 0) {
				numbers.add(expression.charAt(i) - '0');
			} else {
				operators.add(expression.charAt(i));
			}
		}

		// 첫 번째 숫자부터 시작해서 재귀 탐색
		dfs(0, numbers.get(0));

		System.out.println(maxResult);
	}

	// idx: 현재 연산자 인덱스, currentResult: 현재까지의 계산 결과
	static void dfs(int idx, int currentResult) {
		// 모든 연산자를 처리했으면 최댓값 갱신
		if (idx >= operators.size()) {
			maxResult = Math.max(maxResult, currentResult);
			return;
		}

		// 1. 현재 연산자에 괄호를 씌우지 않는 경우
		int nextResult = calculate(currentResult, numbers.get(idx + 1), operators.get(idx));
		dfs(idx + 1, nextResult);

		// 2. 현재 연산자에 괄호를 씌우는 경우 (다음 연산자가 있을 때만)
		if (idx + 1 < operators.size()) {
			// 다음 연산을 먼저 계산 (괄호 효과)
			int bracketResult = calculate(numbers.get(idx + 1), numbers.get(idx + 2), operators.get(idx + 1));
			// 현재 결과와 괄호 결과를 연산
			int finalResult = calculate(currentResult, bracketResult, operators.get(idx));
			// 괄호를 씌웠으므로 다음 연산자는 건너뛰기
			dfs(idx + 2, finalResult);
		}
	}

	// 두 수를 연산자에 따라 계산
	static int calculate(int a, int b, char operator) {
		switch (operator) {
		case '+':
			return a + b;
		case '-':
			return a - b;
		case '*':
			return a * b;
		default:
			return 0;
		}
	}
}