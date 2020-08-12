/** Baekjoon Online Judge
*   Problem No. 15686
*   치킨 배달 
*   Writed by Rush.K
*   Using Brute Force
*/

package SCPC;

import java.util.LinkedList;
import java.util.Scanner;

class Store {
	int x;
	int y;
	 
	public Store(int x_, int y_) {
		x = x_;
		y = y_;
	}
}

public class P15686 {
	
	public static LinkedList<LinkedList> CaseQueue = new LinkedList<>();
	public static boolean[] visit;
	public static void Combination(int[] arr, boolean[] visit, int start, int n, int r) {
		if (r == 0) {
			LinkedList<Integer> Cases = new LinkedList<>();
			for (int i = 0; i < visit.length; i++) {
				if (visit[i] == true) {
					Cases.add(arr[i]);
				} 
			}
			CaseQueue.add(Cases);
			return;
		}
		
		for (int i = start; i < n; i++) {
			visit[i] = true;
			Combination(arr, visit, i + 1, n, r - 1);
			visit[i] = false;
		}
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		
		int[][] map = new int[N + 1][N + 1];
		LinkedList<Store> storequeue = new LinkedList<>();
		LinkedList<Store> homequeue = new LinkedList<>();
		
		for (int i = 1; i <= N; i++) { // 치킨집, 거주집 좌표 받아오
			for (int j = 1; j <= N; j++) {
				map[i][j] = scanner.nextInt();
				if (map[i][j] == 2) {
					Store chickenStore = new Store(i, j);
					storequeue.add(chickenStore);
				} else if (map[i][j] == 1) {
					Store home = new Store(i, j);
					homequeue.add(home);
				}
			}
		}
		
		Store[] chickenstore = new Store[storequeue.size()];
		for (int i = 0; i < chickenstore.length; i++) chickenstore[i] = storequeue.pollFirst();
		
		Store[] home = new Store[homequeue.size()];
		for (int i = 0; i < home.length; i++) home[i] = homequeue.pollFirst();
		
		int[][] distance = new int[chickenstore.length][home.length]; // 각 거주집에서 치킨집 사이의 치킨 거리 계
		for (int i = 0; i < chickenstore.length; i++) {
			for (int j = 0; j < home.length; j++) {
				distance[i][j] = Math.abs(chickenstore[i].x - home[j].x) + Math.abs(chickenstore[i].y - home[j].y);
			}
		}
		
		int[] Ms = new int[chickenstore.length]; // 경우의 수 queue 생성 
		visit = new boolean[chickenstore.length];
		for (int i = 0; i < chickenstore.length; i++) Ms[i] = i;
		Combination(Ms, visit, 0, chickenstore.length, M); // 가능한 치킨집 조합 계
		
		int result = 0;
		int absolute_result = Integer.MAX_VALUE;
		int tempNum = Integer.MAX_VALUE;
		while (!CaseQueue.isEmpty()) {
			LinkedList tempC = CaseQueue.pollFirst();
			int[] tempCase = new int[M];
			int t = 0;
			while (!tempC.isEmpty()) {
				tempCase[t] = (int)tempC.pollFirst();
				t++;
			}
			for (int j = 0; j < home.length; j++) {
				for (int i = 0; i < tempCase.length; i++) {
					tempNum = Math.min(tempNum, distance[tempCase[i]][j]);
				}
				result += tempNum;
				tempNum = Integer.MAX_VALUE;
			}
			absolute_result = Math.min(absolute_result, result);
			result = 0;
		}
		
		System.out.println(absolute_result); // output
	}

}
