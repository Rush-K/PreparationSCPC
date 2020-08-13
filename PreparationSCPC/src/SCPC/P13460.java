package SCPC;

import java.util.LinkedList;
import java.util.Scanner;

class Marble {
	int x;
	int y;
	int value;
	int num;
	Marble Blue;
	int direction;
	
	public Marble() {}
	
	public Marble(int x_, int y_, int num_, int direction_) {
		x = x_;
		y = y_;
		num = num_;
		direction = direction_;
	}
}

public class P13460 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int M = scanner.nextInt();
		String temp = scanner.nextLine();
		
		char[][] game = new char[N][M];
		for (int i = 0; i < N; i++) {
			temp = scanner.nextLine();
			game[i] = temp.toCharArray();
		}
		
		boolean[][] visit = new boolean[N][M];
		
		Marble Red = new Marble();
		Marble Blue = new Marble();
		Marble Finish = new Marble();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (game[i][j] == 'R') {
					Red = new Marble(i, j, 0, 0);
				} else if (game[i][j] == 'B') {
					Blue = new Marble(i, j, 0, 0);
				} else if (game[i][j] == 'O') {
					Finish = new Marble(i, j, 0, 0);
				}
			}
		}
		Red.Blue = Blue;
		int result = Integer.MAX_VALUE;

		LinkedList<Marble> queue = new LinkedList<>();
		queue.add(Red);
		while (!queue.isEmpty()) {
			Marble nowRed = queue.pollFirst();
			Marble tempRed;

			if (nowRed.num < 10) {
				
				if (nowRed.direction == 1) {
					if (game[nowRed.x - 1][nowRed.y] == '#' && game[nowRed.x][nowRed.y + 1] == '#'
							&& game[nowRed.x][nowRed.y - 1] == '#') {
						continue;
					}
				} else if (nowRed.direction == -1) {
					if (game[nowRed.x + 1][nowRed.y] == '#' && game[nowRed.x][nowRed.y + 1] == '#'
							&& game[nowRed.x][nowRed.y - 1] == '#') {
						continue;
					}
				} else if (nowRed.direction == 2) {
					if (game[nowRed.x + 1][nowRed.y] == '#' && game[nowRed.x - 1][nowRed.y] == '#'
							&& game[nowRed.x][nowRed.y + 1] == '#') {
						continue;
					}
				} else if (nowRed.direction == -2) {
					if (game[nowRed.x + 1][nowRed.y] == '#' && game[nowRed.x - 1][nowRed.y] == '#'
							&& game[nowRed.x][nowRed.y - 1] == '#') {
						continue;
					}
				} else if (nowRed.direction == 0) {
					if (game[nowRed.x + 1][nowRed.y] == '#' && game[nowRed.x - 1][nowRed.y] == '#'
							&& game[nowRed.x][nowRed.y - 1] == '#' && game[nowRed.x][nowRed.y + 1] == '#') {
						break;
					}
				}
				
				
				if (nowRed.direction != -1) { // Case 1 : Up, dir = 1
					int redx = nowRed.x;
					int bluex = nowRed.Blue.x;
					
					while (game[--redx][nowRed.y] != '#') {
						if (game[redx][nowRed.y] == 'O') break;
					}
					while (game[--bluex][nowRed.Blue.y] != '#') {
						if (game[bluex][nowRed.Blue.y] == 'O') break;
					}
					
					if (game[bluex][nowRed.Blue.y] != 'O') {
						if (visit[redx][nowRed.y] == false) {
							if (!(game[redx][nowRed.y] == '#' && redx == nowRed.x - 1)) {
								if (game[redx][nowRed.y] != 'O') {
									if (nowRed.y == nowRed.Blue.y) {
										if (redx == bluex) {
											if (nowRed.x < nowRed.Blue.x) {
												tempRed = new Marble(redx + 1, nowRed.y, nowRed.num + 1, 1);
												tempRed.Blue = new Marble(bluex + 2, nowRed.Blue.y, nowRed.Blue.num + 1, 1);
												visit[redx][nowRed.y] = true;
												queue.add(tempRed);
											} else {
												if (nowRed.x != redx + 2) {
													tempRed = new Marble(redx + 2, nowRed.y, nowRed.num + 1, 1);
													tempRed.Blue = new Marble(bluex + 1, nowRed.Blue.y, nowRed.Blue.num + 1, 1);
													visit[redx][nowRed.y] = true;
													queue.add(tempRed);	
												}
											}	
										} else {
											tempRed = new Marble(redx + 1, nowRed.y, nowRed.num + 1, 1);
											tempRed.Blue = new Marble(bluex + 1, nowRed.Blue.y, nowRed.Blue.num + 1, 1);
											visit[redx][nowRed.y] = true;
											queue.add(tempRed);
										}
									} else {
										tempRed = new Marble(redx + 1, nowRed.y, nowRed.num + 1, 1);
										tempRed.Blue = new Marble(bluex + 1, nowRed.Blue.y, nowRed.Blue.num + 1, 1);
										visit[redx][nowRed.y] = true;
										queue.add(tempRed);
									}
								} else {
									if (nowRed.y == nowRed.Blue.y) {
										result = Math.min(result, nowRed.num + 1);
										break;
									} else {
										result = Math.min(result, nowRed.num + 1);
										break;
									}
								}	
							}
						}	
					}	
				}
				

				if (nowRed.direction != 1) { // Case 2 : Down, dir = -1
					int redx = nowRed.x;
					int bluex = nowRed.Blue.x;
					
					while (game[++redx][nowRed.y] != '#') {
						if (game[redx][nowRed.y] == 'O') break;
					}
					while (game[++bluex][nowRed.Blue.y] != '#') {
						if (game[bluex][nowRed.Blue.y] == 'O') break;
					}
					
					if (game[bluex][nowRed.Blue.y] != 'O') {
						if (visit[redx][nowRed.y] == false) {
							if (!(game[redx][nowRed.y] == '#' && redx == nowRed.x + 1)) {
								if (game[redx][nowRed.y] != 'O') {
									if (nowRed.y == nowRed.Blue.y) {
										if (redx == bluex) {
											if (nowRed.x < nowRed.Blue.x) {
												if (nowRed.x != redx - 2) {
													tempRed = new Marble(redx - 2, nowRed.y, nowRed.num + 1, -1);
													tempRed.Blue = new Marble(bluex - 1, nowRed.Blue.y, nowRed.Blue.num + 1, -1);
													visit[redx][nowRed.y] = true;
													queue.add(tempRed);	
												}
											} else {
												tempRed = new Marble(redx - 1, nowRed.y, nowRed.num + 1, -1);
												tempRed.Blue = new Marble(bluex - 2, nowRed.Blue.y, nowRed.Blue.num + 1, -1);
												visit[redx][nowRed.y] = true;
												queue.add(tempRed);
											}	
										} else {
											tempRed = new Marble(redx - 1, nowRed.y, nowRed.num + 1, -1);
											tempRed.Blue = new Marble(bluex - 1, nowRed.Blue.y, nowRed.Blue.num + 1, -1);
											visit[redx][nowRed.y] = true;
											queue.add(tempRed);
										}
									} else {
										tempRed = new Marble(redx - 1, nowRed.y, nowRed.num + 1, -1);
										tempRed.Blue = new Marble(bluex - 1, nowRed.Blue.y, nowRed.Blue.num + 1, -1);
										visit[redx][nowRed.y] = true;
										queue.add(tempRed);
									}
								} else {
									if (nowRed.y == nowRed.Blue.y) {
										result = Math.min(result, nowRed.num + 1);
										break;
									} else {
										result = Math.min(result, nowRed.num + 1);
										break;
									}
								}
							}
						}
					}		
				}

				
				if (nowRed.direction != -2) { // Case 3 : Right, dir = 2
					int redy = nowRed.y;
					int bluey = nowRed.Blue.y;
					
					while (game[nowRed.x][++redy] != '#') {
						if (game[nowRed.x][redy] == 'O') break;
					}
					while (game[nowRed.Blue.x][++bluey] != '#') {
						if (game[nowRed.Blue.x][bluey] == 'O') break;
					}
					
					if (game[nowRed.Blue.x][bluey] != 'O') {
						if (visit[nowRed.x][redy] == false) {
							if (!(game[nowRed.x][redy] == '#' && redy == nowRed.y + 1)) {
								if (game[nowRed.x][redy] != 'O') {
									if (nowRed.x == nowRed.Blue.x) {
										if (redy == bluey) {
											if (nowRed.y < nowRed.Blue.y) {
												if (nowRed.y != redy - 2) {
													tempRed = new Marble(nowRed.x, redy - 2, nowRed.num + 1, 2);
													tempRed.Blue = new Marble(nowRed.x, bluey - 1, nowRed.Blue.num + 1, 2);
													visit[nowRed.x][redy] = true;
													queue.add(tempRed);	
												}
											} else {
												tempRed = new Marble(nowRed.x, redy - 1, nowRed.num + 1, 2);
												tempRed.Blue = new Marble(nowRed.x, bluey - 2, nowRed.Blue.num + 1, 2);
												visit[nowRed.x][redy] = true;
												queue.add(tempRed);
											}								
										} else {
											tempRed = new Marble(nowRed.x, redy - 1, nowRed.num + 1, 2);
											tempRed.Blue = new Marble(nowRed.Blue.x, bluey - 1, nowRed.Blue.num + 1, 2);
											visit[nowRed.x][redy] = true;
											queue.add(tempRed);
										}
									} else {
										tempRed = new Marble(nowRed.x, redy - 1, nowRed.num + 1, 2);
										tempRed.Blue = new Marble(nowRed.Blue.x, bluey - 1, nowRed.Blue.num + 1, 2);
										visit[nowRed.x][redy] = true;
										queue.add(tempRed);
									}
								} else {
									if (nowRed.x == nowRed.Blue.x) {
										result = Math.min(result,  nowRed.num + 1);
										break;
									} else {
										result = Math.min(result, nowRed.num + 1);
										break;
									}
								}
							}
						}	
					}
				}
				
				
				
				if (nowRed.direction != 2) { // Case 4 : left, dir = -2
					int redy = nowRed.y;
					int bluey = nowRed.Blue.y;
					
					while (game[nowRed.x][--redy] != '#') {
						if (game[nowRed.x][redy] == 'O') break;
					}
					while (game[nowRed.Blue.x][--bluey] != '#') {
						if (game[nowRed.Blue.x][bluey] == 'O') break;
					}
					
					if (game[nowRed.Blue.x][bluey] != 'O') {
						if (visit[nowRed.x][redy] == false) {
							if (!(game[nowRed.x][redy] == '#' && redy == nowRed.y - 1)) {
								if (game[nowRed.x][redy] != 'O') {
									if (nowRed.x == nowRed.Blue.x) {
										if (redy == bluey) {
											if (nowRed.y > nowRed.Blue.y) {
												if (nowRed.y != redy + 2) {
													tempRed = new Marble(nowRed.x, redy + 2, nowRed.num + 1, -2);
													tempRed.Blue = new Marble(nowRed.x, bluey + 1, nowRed.Blue.num + 1, -2);
													visit[nowRed.x][redy] = true;
													queue.add(tempRed);	
												}
											} else {
												tempRed = new Marble(nowRed.x, redy + 1, nowRed.num + 1, -2);
												tempRed.Blue = new Marble(nowRed.x, bluey + 2, nowRed.Blue.num + 1, -2);
												visit[nowRed.x][redy] = true;
												queue.add(tempRed);
											}							
										} else {
											tempRed = new Marble(nowRed.x, redy + 1, nowRed.num + 1, -2);
											tempRed.Blue = new Marble(nowRed.Blue.x, bluey + 1, nowRed.Blue.num + 1, -2);
											visit[nowRed.x][redy] = true;
											queue.add(tempRed);
										}
									} else {
										tempRed = new Marble(nowRed.x, redy + 1, nowRed.num + 1, -2);
										tempRed.Blue = new Marble(nowRed.Blue.x, bluey + 1, nowRed.Blue.num + 1, -2);
										visit[nowRed.x][redy] = true;
										queue.add(tempRed);
									}
								} else {
									if (nowRed.x == nowRed.Blue.x) {
										result = Math.min(result,  nowRed.num + 1);
										break;
									} else {
										result = Math.min(result, nowRed.num + 1);
										break;
									}
								}
							}
						}
					}
				}
			} else {
				result = -1;
				break;
			}
		}
		if (result > 10) {
			System.out.println(-1);
		} else {
			System.out.println(result);	
		}	
	}
}
