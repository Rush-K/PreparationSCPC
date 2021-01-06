/** Baekjoon Online Judge
*   Problem No. 17140
*   이차원 배열과 연산 
*   Writed by Rush.K
*   Using Priority Queue
*/

package SCPC;

import java.util.PriorityQueue;
import java.util.Scanner;

class Value implements Comparable<Value>{
	int x;
	int num;
	
	public Value(int x_, int num_) {
		x = x_; num = num_;
	}
	
	@Override
	public int compareTo(Value o) {
		if (this.num > o.num) {
			return 1;
		} else if (this.num < o.num){
			return -1;
		} else {
			if (this.x > o.x) {
				return 1;
			} else if (this.x < o.x) {
				return -1;
			} else {
				return 0;
			}
		}
	}
}

public class P17140 {
	public static PriorityQueue<Value> sorting = new PriorityQueue<Value>();
	public static PriorityQueue<Value> complete = new PriorityQueue<Value>();
	public static int[][] A = new int[100][100];
	public static int Ai = 2;
	public static int Aj = 2;
	
	public static void rowChange() { // Operation R
		boolean isFind = false;
		Aj = 0;
		
		for (int i = 0; i < 100; i++) {
			int j = 0;
			
			while (j < 100) { // sorting
				if (A[i][j] == 0) {
					j++;
					continue;
				}
				if (j == 0) {
					Value w = new Value(A[i][j], 1);
					sorting.add(w);
				} else {
					while (!sorting.isEmpty()) {
						Value temp = sorting.poll();
						if (temp.x == A[i][j]) {
							temp.num = temp.num + 1;
							complete.add(temp);
							while (!sorting.isEmpty()) {
								complete.add(sorting.poll());
							}
							isFind = true;
							break;
						} else {
							complete.add(temp);
						}
					}
					if (isFind == false) {
						Value w = new Value(A[i][j], 1);
						complete.add(w);
					}
					while (!complete.isEmpty()) sorting.add(complete.poll());
				}
				isFind = false;
				j++;
			}
			
			j = 0;
			
			while (!sorting.isEmpty()) {
				if (j == 100) {
					while (!sorting.isEmpty()) sorting.poll();
					break;
				}
				Value temp = sorting.poll();
				A[i][j++] = temp.x;
				A[i][j++] = temp.num;
			}
			Aj = Math.max(Aj, j);
			for (; j < 100; j++) A[i][j] = 0;
		}
	}
	
	public static void colChange() { // Operation C
		boolean isFind = false;
		Ai = 0;
		
		for (int j = 0; j < 100; j++) {
			int i = 0;
			
			while (i < 100) { // sorting
				if (A[i][j] == 0) {
					i++;
					continue;
				}
				if (i == 0) {
					Value w = new Value(A[i][j], 1);
					sorting.add(w);
				} else {
					while (!sorting.isEmpty()) {
						Value temp = sorting.poll();
						if (temp.x == A[i][j]) {
							temp.num = temp.num + 1;
							complete.add(temp);
							while (!sorting.isEmpty()) {
								complete.add(sorting.poll());
							}
							isFind = true;
							break;
						} else {
							complete.add(temp);
						}
					}
					if (isFind == false) {
						Value w = new Value(A[i][j], 1);
						complete.add(w);
					}
					while (!complete.isEmpty()) sorting.add(complete.poll());
				}
				isFind = false;
				i++;
			}
			
			i = 0;
			
			while (!sorting.isEmpty()) {
				if (i == 100) {
					while (!sorting.isEmpty()) sorting.poll();
					break;
				}
				Value temp = sorting.poll();
				A[i++][j] = temp.x;
				A[i++][j] = temp.num;
			}
			
			Ai = Math.max(Ai, i);
			for (; i < 100; i++) A[i][j] = 0;
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int r = scanner.nextInt();
		int c = scanner.nextInt();
		int k = scanner.nextInt();
		int time = 0;
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				A[i][j] = scanner.nextInt();
			}
		}
		
		while (!(A[r - 1][c - 1] == k)) { // Finding
			if (time > 100) break;
			
			if (Ai >= Aj) {
				rowChange();
			} else {
				colChange();
			}
			
			time++;
		}
		
		if (time == 101) time = -1;

		System.out.println(time); // output
	}
}
