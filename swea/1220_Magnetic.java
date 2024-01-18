package swea;

import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution
{
	// 1:N 2:S
	static boolean checkVertical(int[][] arr, int x, int y) {
		for(int target=0; target<arr.length; target++) {
			// base:N && target:S 
			if(arr[y][x]==1 && arr[target][x]==2) {
				
			}
			// base:S && target:N 
			else if(arr[y][x]==2 && arr[target][x]==1) {
				int length = target-y;
//				if( (y+1))
			}
		}
		return true;
	}
	
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int tcCount = 10;
		
		for(int tc=1; tc<=tcCount; tc++) {
			int result = 0;
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			int arr[][] = new int[size][size];
			
			for(int i=0; i<size; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<size; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int x=0; x<size; x++) {
				boolean isStarted = false;
				int lastNum = -1;
				int yResult = 0;
				for(int y=0; y<size; y++) {
					if(arr[y][x]==0) {
						continue;
					}
					if(!isStarted && arr[y][x]==1) {
						isStarted = true;
						lastNum = arr[y][x];
					} else if(isStarted && arr[y][x]!=lastNum) {
						yResult += 1;
						isStarted = false;
					}
				}
				if(lastNum == 2) {
					yResult -= 1;
				}
				
				result += yResult;
			}
			
			System.out.printf("#%d %d\n", tc, result);
		}
	}
}
