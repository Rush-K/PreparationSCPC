/** Baekjoon Online Judge
*   Problem No. 14890
*   경사로 
*   Writed by Rush.K
*   Simulation 
*/

package SCPC;

import java.util.Scanner;

public class P14890 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int L = scanner.nextInt();
		
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = scanner.nextInt();
			}
		} // input 
		
		int result = 0;
		
		for (int i = 0; i < N; i++) { // 가로 길 찾기 
			int[] block = new int[N];
			boolean isPossible = true;
			int j = 1;
			int StayedBlock = map[i][0];
			while (isPossible && (j <= N - 1)) {
				if (map[i][j] == StayedBlock) {
					j++;
				} else if (map[i][j] == StayedBlock + 1) {
					if (j - L >= 0) {
						for (int k = j - 1; k >= j - L; k--) {
							if (map[i][k] != StayedBlock) {
								isPossible = false;
								break;
							}
							if (block[k] == 1) {
								isPossible = false;
								break;
							}
							if (k == j - L) { // 경사로 가능 
								for (int t = j - 1; t >= j - L; t--) block[t] = 1;
								StayedBlock = map[i][j];
								j++;
								break;
							}
						}	
					} else {
						isPossible = false;
					}
				} else if (map[i][j] == StayedBlock - 1) {
					if (j + L <= N) {
						for (int k = j; k < j + L; k++) {
							if (map[i][k] != map[i][j]) {
								isPossible = false;
								break;
							}
							if (k == j + L - 1) { // 경사로 가능 
								for (int t = j; t < j + L; t++) block[t] = 1;
								StayedBlock = map[i][j];
								for (int t = 0; t < L - 1; t++) j++;
								break;
							}
						}	
					} else {
						isPossible = false;
					}
				} else {
					isPossible = false;
				}
			}
			if (j == N) result++;
		}
		
		for (int j = 0; j < N; j++) { // 세로 길 찾기 
			int[] block = new int[N];
			boolean isPossible = true;
			int i = 1;
			int StayedBlock = map[0][j];
			while (isPossible && (i <= N - 1)) {
				if (map[i][j] == StayedBlock) {
					i++;
				} else if (map[i][j] == StayedBlock + 1) {
					if (i - L >= 0) {
						for (int k = i - 1; k >= i - L; k--) {
							if (map[k][j] != StayedBlock) {
								isPossible = false;
								break;
							}
							if (block[k] == 1) {
								isPossible = false;
								break;
							}
							if (k == i - L) { // 경사로 가능 
								for (int t = i - 1; t >= i - L; t--) block[t] = 1;
								StayedBlock = map[i][j];
								i++;
								break;
							}
						}	
					} else {
						isPossible = false;
					}
				} else if (map[i][j] == StayedBlock - 1) {
					if (i + L <= N) {
						for (int k = i; k < i + L; k++) {
							if (map[k][j] != map[i][j]) {
								isPossible = false;
								break;
							}
							if (k == i + L - 1) { // 경사로 가능 
								for (int t = i; t < i + L; t++) block[t] = 1;
								StayedBlock = map[i][j];
								for (int t = 0; t < L - 1; t++) i++;
								break;
							}
						}	
					} else {
						isPossible = false;
					}
				} else {
					isPossible = false;
				}
			}
			if (i == N) result++;
		}
		
		System.out.println(result);
	}
}
