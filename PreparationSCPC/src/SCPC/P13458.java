/** Baekjoon Online Judge
*   Problem No. 13458
*   시험 감독 
*   Writed by Rush.K
*   Using Math (remainder)
*/

package SCPC;

import java.util.Scanner;

public class P13458 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int[] A = new int[N];
		for (int i = 0; i < N; i++) A[i] = scanner.nextInt();
		int B = scanner.nextInt();
		int C = scanner.nextInt();
		
		long result = 0;
		
		for (int i = 0; i < N; i++) {
			if (A[i] - B <= 0) {
				result++;
				continue;
			} else {
				result++;
				int value_ = (A[i] - B) / C;
				int mod_ = (A[i] - B) % C;
				if (value_ == 0) result++;
				else if (mod_ == 0) result = result + value_;
				else result = result + value_ + 1;		
			}
		}
		
		System.out.println(result);
	}

}
