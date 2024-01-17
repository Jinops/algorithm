import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	static int dp[][][] = new int[21][21][21];
	
	static int w(int a, int b, int c) {
		if(a<=0 || b<=0 || c<=0) {
			return 1;
		}
		if (a>20 || b>20 || c>20) {
			return w(20, 20, 20);
		} 
		if(dp[a][b][c]!=0) {
			return dp[a][b][c];
		}
		if ( a<b && b<c) {
			dp[a][b][c] = w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c);
			return dp[a][b][c];
		}
		dp[a][b][c] = w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1);
		return dp[a][b][c];
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		ArrayList<int[]> inputs = new ArrayList<>();
		
		while(true) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			if(a==-1 && b==-1 && c==-1) {
				break;
			}
			inputs.add(new int[]{a, b, c});
		}
		
		for(int[] input:inputs) {
			int a = input[0];
			int b = input[1];
			int c = input[2];
			System.out.printf("w(%d, %d, %d) = %d\n", a, b, c, w(a,b,c));
		}
	}
}
