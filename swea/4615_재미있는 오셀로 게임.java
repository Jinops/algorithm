// WIP
import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static int M;
	static int[][] map;
	
	static void game(int[][] map, int x, int y, int color) {
		map[y][x] = color;
		int minX = 0, maxX = N-1;
		int minY = 0, maxY = N-1;
		
		while(map[y][minX++]!=color) {}
		while(map[y][maxX--]!=color) {}
		for(int mx=minX; mx<=maxX; mx++) {
			map[y][mx] = color;
		}
		while(map[minY++][x]!=color) {}
		while(map[maxY--][x]!=color) {}
		for(int my=minY; my<=maxY; my++) {
			map[my][x] = color;
		}

		// TODO
		int minM = Math.min(x, y), maxM = (x+y)-minM;
		while(map[y-minM][x-minM]!=color) {minM++;}
		while(map[y+maxM][x+maxM]!=color) {maxM--;}
		for(int m=minM; m<=minM; m++) {
			map[m][m] = color;
		}
		
		// TODO
		minM =0; maxM = N-1;
		while(map[minM][((x+y)-minM)]!=color) {minM++;}
		while(map[maxM][maxM-(x+y)]!=color) {maxM--;}
		for(int m=minM; m<=minM; m++) {
			map[m][m] = color;
		}
	}
	
	static int[] getCounts(int[][] map) {
		int[] counts = new int[2];
		for(int[] line:map) {
			for(int color:line) {
				if(color==1) counts[0]++;
				if(color==2) counts[1]++;
			}
		}
		return counts;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			map[N/2-1][N/2-1] = 2;
			map[N/2-1][N/2] = 1;
			map[N/2][N/2-1] = 1;
			map[N/2][N/2] = 2;
			for(int[] a:map) {
				System.out.println(Arrays.toString(a));
			}
			
			for(int m=1; m<=M; m++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int color = Integer.parseInt(st.nextToken());
				game(map, x-1, y-1, color);
				System.out.println("M");
				for(int[] a:map) {
					System.out.println(Arrays.toString(a));
				}
				
			}
			
			int[] counts = getCounts(map);
			System.out.printf("#%d %d %d\n", t, counts[0], counts[1]);
		}
	}
}
