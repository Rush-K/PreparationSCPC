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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		
		int r = scanner.nextInt();
		int c = scanner.nextInt();
		int k = scanner.nextInt();
		int time = 0;
		
		int[][] A = new int[100][100];

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				A[i][j] = scanner.nextInt();
			}
		}
		
		PriorityQueue<Value> sorting = new PriorityQueue<Value>();
		PriorityQueue<Value> complete = new PriorityQueue<Value>();
		boolean isFind = false;
		
		int Ai = 2;
		int Aj = 2;
		
		int tj = 0;
		int Tj = 0;
		
		while (!(A[r - 1][c - 1] == k)) {
			if (time == 101) break;
			if (Ai >= Aj) {
				for (int i = 0; i <= Ai; i++) {
					for (int j = 0; j <=Aj; j++) {
						int t = A[i][j];
						if (sorting.isEmpty()) {
							Value w = new Value(t, 1);
							sorting.add(w);
						} else {
							while (!sorting.isEmpty()) {
								Value temp = sorting.poll();
								if (temp.x == t) {
									temp.num = temp.num + 1;
									complete.add(temp);
									isFind = true;
								} else {
									if (sorting.isEmpty() && isFind == false) {
										Value w = new Value(t, 1);
										complete.add(temp);
										complete.add(w);
									} else {
										complete.add(temp);
									}
								}
							}
							while (!complete.isEmpty()) {
								Value temp = complete.poll();
								sorting.add(temp);
							}
							isFind = false;
						}
					}
					while (!sorting.isEmpty()) {
						if (tj < 100) {
							Value temp = sorting.poll();
							//System.out.print(temp.x + "/" + temp.num + " ");
							A[i][tj] = temp.x;
							A[i][tj + 1] = temp.num;
							tj = tj + 2;
						}
					}
					for (int a = tj; a < 100; a++) A[i][a] = 0;
					Tj = Math.max(Tj, tj - 1);
					tj = 0;
				}
				Aj = Tj;
			} else {
				for (int j = 0; j <= Aj; j++) {
					for (int i = 0; i <=Ai; i++) {
						int t = A[i][j];
						if (sorting.isEmpty()) {
							Value w = new Value(t, 1);
							sorting.add(w);
						} else {
							while (!sorting.isEmpty()) {
								Value temp = sorting.poll();
								if (temp.x == t) {
									temp.num = temp.num + 1;
									complete.add(temp);
									isFind = true;
								} else {
									if (sorting.isEmpty() && isFind == false) {
										Value w = new Value(t, 1);
										complete.add(temp);
										complete.add(w);
									} else {
										complete.add(temp);
									}
								}
							}
							while (!complete.isEmpty()) {
								Value temp = complete.poll();
								sorting.add(temp);
							}
							isFind = false;
						}
					}
					while (!sorting.isEmpty()) {
						if (tj < 100) {
							Value temp = sorting.poll();
							//System.out.print(temp.x + "/" + temp.num + " ");
							A[tj][j] = temp.x;
							A[tj + 1][j] = temp.num;
							tj = tj + 2;
						}
					}
					for (int a = tj; a < 100; a++) A[a][j] = 0;
					Tj = Math.max(Tj, tj - 1);
					tj = 0;
				}
				Ai = Tj;
			}
			time++;
		}
		
		if (time == 101) time = -1;
		
		System.out.println();
		
		System.out.println(time);
	}
}
