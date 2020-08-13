/** Baekjoon Online Judge
*   Problem No. 16235
*   나무 재테크 
*   Writed by Rush.K
*   Using Priority Queue & Deque
*/

package SCPC;

import java.util.LinkedList;
import java.util.Scanner;

class Tree implements Comparable<Tree> {
	int x;
	int y;
	int age;
	int dead;
	
	public Tree(int x_, int y_, int age_) {
		x = x_;
		y = y_;
		age = age_;
		dead = 0;
	}

	@Override
	public int compareTo(Tree o) { // 나이로 우선순위 지정 
		if (this.age > o.age) {
			return 1;
		} else if (this.age < o.age) {
			return -1;
		}
		return 0;
	}
}

public class P16235 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		int K = scanner.nextInt();
		
		int[][] A = new int[N + 1][N + 1];
		int[][] Ground = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				A[i][j] = scanner.nextInt();
				Ground[i][j] = 5;
			}
		}
		
		LinkedList<Tree> Spring = new LinkedList<>();
		LinkedList<Tree> Summer = new LinkedList<>();
		LinkedList<Tree> Autumn = new LinkedList<>();

		for (int i = 0; i < M; i++) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			int age = scanner.nextInt();
			Tree temp = new Tree(x, y, age);
			Spring.offer(temp);
		} // input

		for (int num = 0; num < K; num++) { // K년 동안 
			while (!Spring.isEmpty()) { // 봄 
				Tree Temptree = Spring.pollFirst();
				if (Ground[Temptree.x][Temptree.y] >= Temptree.age) {
					Ground[Temptree.x][Temptree.y] -= Temptree.age;
					Temptree.age++;
					Summer.add(Temptree);
				} else {
					Temptree.dead = 1;
					Summer.add(Temptree);
				}
			}
			
			while (!Summer.isEmpty()) { // 여름 
				Tree Temptree = Summer.pollFirst();
				if (Temptree.dead == 1) {
					Ground[Temptree.x][Temptree.y] += Temptree.age / 2;
				} else {
					Autumn.add(Temptree);
				}
			}
			
			while (!Autumn.isEmpty()) { // 가을 
				Tree Temptree = Autumn.pollFirst();
				if (Temptree.age % 5 == 0) { // 번식 조건 
					for (int i = Temptree.x - 1; i <= Temptree.x + 1; i++) {
						for (int j = Temptree.y - 1; j <= Temptree.y + 1; j++) {
							if ((i >= 1 && i <= N) && (j >= 1 && j <= N)) {
								if (!(i == Temptree.x && j == Temptree.y)) {
									Tree Newtree = new Tree(i, j, 1);
									Spring.addFirst(Newtree);
								} else  {
									Spring.add(Temptree);
								}	
							}
						}
					}
				} else {
					Spring.add(Temptree);
				}
			}
			
			for (int i = 1; i <= N; i++) { // 겨울 
				for (int j = 1; j <= N; j++) {
					Ground[i][j] += A[i][j];
				}
			}	
		}
		
		System.out.println(Spring.size()); // output 
	}
}
