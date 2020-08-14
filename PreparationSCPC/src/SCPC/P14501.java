/** Baekjoon Online Judge
*   Problem No. 14501
*   퇴사 
*   Writed by Rush.K
*   Using Brute Force (BFS)
*/

package SCPC;

import java.util.LinkedList;
import java.util.Scanner;

class TP {
	int index;
	int T;
	int P;
	int result;
	
	public TP(int index_, int T_, int P_) {
		index = index_;
		T = T_;
		P = P_;
		result = 0;
	}
}
public class P14501 {
	public static int N;
	public static int[] T;
	public static int[] P;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		N = scanner.nextInt();
		TP[] tp = new TP[N + 1];
		LinkedList<TP> queue = new LinkedList<>();
		
		for (int i = 1; i <= N; i++) {
			int tempT = scanner.nextInt();
			int tempP = scanner.nextInt();
			tp[i] = new TP(i, tempT, tempP);
			queue.add(tp[i]);
		} // input
		
		int result = Integer.MIN_VALUE;
		
		
		while (!queue.isEmpty()) { // BFS
			TP temp = queue.pollFirst();
			if (temp.index + temp.T - 1 <= N) temp.result += temp.P;				
			for (int i = temp.index + temp.T; i <= N; i++) {
				TP tmp = new TP(tp[i].index, tp[i].T, tp[i].P);
				tmp.result = temp.result;
				queue.add(tmp);	
			}
			result = Math.max(result, temp.result);	
		}
		System.out.println(result); // output
	}

}
