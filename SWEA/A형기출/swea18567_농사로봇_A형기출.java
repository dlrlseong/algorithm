import java.io.*;
import java.util.*;

public class swea18567_농사로봇 {

	// 방향: 0=↑, 1=←, 2=↓, 3=→ (문제 규칙상 오른쪽→앞→왼쪽→뒤 탐색에 맞게 사용)
	static final int[] dr = { -1, 0, 1, 0 };
	static final int[] dc = { 0, -1, 0, 1 };
	static boolean debug = false;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			Land[][] base = new Land[N][N];
			Land[][] map = new Land[N][N];

			for (int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int c = 0; c < N; c++) {
					int v = Integer.parseInt(st.nextToken());
					// type: -1 산, 0 빈, 1 싹, 2 수확가능
					base[r][c] = new Land(v == 1 ? -1 : 0);
				}
			}

			int ans = 0;

			// 모든 시작 위치/방향 시도
			for (int sr = 0; sr < N; sr++) {
				for (int sc = 0; sc < N; sc++) {
					if (base[sr][sc].type == -1)
						continue; // 산에서 시작 불가

					for (int sd = 0; sd < 4; sd++) {

						// 맵 깊은 복사
						for (int r = 0; r < N; r++) {
							for (int c = 0; c < N; c++) {
								map[r][c] = new Land(base[r][c]);
							}
						}

						int r = sr, c = sc, d = sd;
						int harvested = 0;

						// 성장 큐: 씨를 심은 칸(아직 수확 전)을 보관
						ArrayDeque<Land> q = new ArrayDeque<>();

						for (int day = 1; day <= M; day++) {

							// 1) 아침: 모든 심은 땅 성장
							int qs = q.size();
							for (int i = 0; i < qs; i++) {
								Land land = q.poll();
								land.growth++;
								// 이후 (K==1?4:(3+K))일 추가 → 총 5 / (4+K)
								int need = (1 + 3 + land.plantK);
								if (land.growth >= need) {
									land.type = 2; // 수확 가능
									// 큐에 재등록하지 않음(수확 대기 상태)
								} else {
									q.offer(land);
								}
							}

							// 2) 이동 후보 탐색 (오른쪽→앞→왼쪽→뒤), 이동 가능한 칸: type==0 or 2
							boolean canMove = false;
							int nr = r, nc = c, nd = d;
							for (int t = 0; t < 4; t++) {
								int dir = (d + 3 + t) % 4;
								int tr = r + dr[dir];
								int tc2 = c + dc[dir];
								if (tr < 0 || tr >= N || tc2 < 0 || tc2 >= N)
									continue;
								int tt = map[tr][tc2].type;
								if (tt == 0 || tt == 2) {
									canMove = true;
									nr = tr;
									nc = tc2;
									nd = dir;
									break;
								}
							}

							// 3) 아침 작업(현재 칸)
							if (map[r][c].type == 2) {
								// 수확
								harvested++;
								map[r][c].type = 0;
								map[r][c].growth = 0;
								map[r][c].plantK = 0;
								// cnt(그 칸에서 싹난 총 횟수)는 유지
							} else {
								// 심기는 "다음 칸으로 이동할 수 있을 때" 그리고 현재 칸이 빈 칸일 때만
								if (canMove && map[r][c].type == 0) {
									Land land = map[r][c];
									land.cnt++; // 그 칸에서의 K 증가
									land.plantK = land.cnt;
									land.growth = 0; // 오늘 심었음
									land.type = 1;
									q.offer(land);
								}
							}

							// 4) 오후 이동
							if (canMove) {
								r = nr;
								c = nc;
								d = nd;
							}

							if (debug) {
								System.out.println("day=" + day + " harvested=" + harvested);
							}
						}

						ans = Math.max(ans, harvested);
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}

		System.out.print(sb);
	}

	static class Land {
		int type; // -1 산, 0 빈, 1 싹, 2 수확가능
		int growth; // 씨 심은 뒤 경과일(당일 0부터)
		int plantK; // 이번 작물의 K(그 칸 기준)
		int cnt; // 그 칸에서 지금까지 싹이 난 횟수(누적)

		Land(int type) {
			this.type = type;
		}

		Land(Land o) {
			this.type = o.type;
			this.growth = o.growth;
			this.plantK = o.plantK;
			this.cnt = o.cnt;
		}
	}
}
