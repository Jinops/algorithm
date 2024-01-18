import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][n];
		
		for(int y=0; y<n; y++) {
			st = new StringTokenizer(br.readLine());
			for(int x=0; x<n; x++) {
				map[y][x] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][][] visited = new int[n][n][3];
		// 0:가로, 1:세로, 2:대각선
		visited[0][1][0] = 1;
		int result = 0;
		
		for(int y=0; y<n; y++) {
			for(int x=0; x<n; x++) {
				boolean toHorizontal = (x+1)<n && map[y][x+1]==0;
				boolean toVertical = (y+1)<n && map[y+1][x]==0;
				boolean toDiagonal = toHorizontal && toVertical && map[y+1][x+1]==0;
				if(toHorizontal) {
					visited[y][x+1][0] += visited[y][x][0] + visited[y][x][2];
				}
				if(toVertical) {
					visited[y+1][x][1] += visited[y][x][1] + visited[y][x][2];
				}
				if(toDiagonal) {
					visited[y+1][x+1][2] += visited[y][x][0] + visited[y][x][1] + visited[y][x][2];
				}
			}
		}
//		// for debug
//		for(int k=0; k<3; k++) {
//			System.out.printf("[%d]\n", k);
//			for(int i=0; i<n; i++) {
//				for(int j=0; j<n; j++) {
//					System.out.printf("%d ", visited[i][j][k]);
//				}
//				System.out.println();
//			}
//		}
//		//
		System.out.println(visited[n-1][n-1][0]+visited[n-1][n-1][1]+visited[n-1][n-1][2]);
		
	}
}
