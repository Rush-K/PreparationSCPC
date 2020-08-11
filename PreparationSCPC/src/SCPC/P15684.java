/** Baekjoon Online Judge
*   Problem No. 15684
*   사다리 조작  
*   Writed by Rush.K
*   Using Brute Force
*/

package SCPC;

import java.util.Scanner;

public class P15684 {
	public static int[][] ladder;
	public static int N;
	public static int M;
	public static int H;
	
	public static int CheckLadder() {
		for (int i = 1; i < N + 1; i++) {
			int x = 1;
			int y = i;
			while (x <= H) {
				if (ladder[x][y] == 1) {
					x++;
					y++;
				} else if (ladder[x][y] == -1) {
					x++;
					y--;
				} else {
					x++;
				}
			}
			if (y != i) return 0;
		}
		return 1;
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		M = scanner.nextInt();
		H = scanner.nextInt();
		
		ladder = new int[H + 1][N + 1];
		for (int i = 0; i < M; i++) {
			int temp_H = scanner.nextInt();
			int temp_N = scanner.nextInt();
			ladder[temp_H][temp_N] = 1;
			ladder[temp_H][temp_N + 1] = -1;
		}

		
		if (CheckLadder() == 1) {
			System.out.println(0);
			return;
		}
		
		int result = Integer.MAX_VALUE;
		int find_one = 0;
		int find_two = 0;
		int find_three = 0;
		
		for (int i = 1; i < N; i++) { // 1, 2, 3개 그릴 경우 
			for (int j = 1; j < H + 1; j++) {
				if (ladder[j][i] == 0 && ladder[j][i + 1] == 0) {
					ladder[j][i] = 1;
					ladder[j][i + 1] = -1;
					
					for (int k = i; k < N; k++) {
						for (int l = 1; l < H + 1; l++) {
							if (ladder[l][k] == 0 && ladder[l][k + 1] == 0) {
								ladder[l][k] = 1;
								ladder[l][k + 1] = -1;
								
								for (int m = k; m < N; m++) {
									for (int n = 1; n < H + 1; n++) {
										if (ladder[n][m] == 0 && ladder[n][m + 1] == 0) {
											ladder[n][m] = 1;
											ladder[n][m + 1] = -1;
											
											if (CheckLadder() == 1) {
												find_three = 1;
												result = 3;
												ladder[n][m] = 0;
												ladder[n][m + 1] = 0;
												break;
											}
											
											ladder[n][m] = 0;
											ladder[n][m + 1] = 0;
										}
									}
									if (find_three == 1) break;
								}
								
								if (CheckLadder() == 1) {
									find_two = 1;
									result = 2;
									ladder[l][k] = 0;
									ladder[l][k + 1] = 0;
									break;
								}
								
								ladder[l][k] = 0;
								ladder[l][k + 1] = 0;
							}
						}
						if (find_two == 1) break;
					}
					
					if (CheckLadder() == 1) {
						find_one = 1;
						result = 1;
						ladder[j][i] = 0;
						ladder[j][i + 1] = 0;
						break;
					}
					
					ladder[j][i] = 0;
					ladder[j][i + 1] = 0;
				}
			}
			if (find_one == 1) break;
		}
		
		if (result > 3) {
			System.out.println(-1);
		} else {
			System.out.println(result);	
		}
	}
}