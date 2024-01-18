import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		// N개의 노선, A_i < i번째 노선 번호 < B_i
		// P개 버스 정류장이 몇 개 버스 노선 다니는지
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		
		for(int tc=1; tc<=T; tc++) {
			int n = Integer.parseInt(br.readLine());
			
			int[][] arr = new int[n][2];
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			
			int p = Integer.parseInt(br.readLine());
			String result = "#" + tc;
			
			for(int i=0; i<p; i++) {
				int count = 0;
				int c = Integer.parseInt(br.readLine());
				for(int j=0; j<n; j++) {
					if(c>=arr[j][0] && c<=arr[j][1]) {
						count += 1;
					}
				}
				result += " " + count;
			}
			
			System.out.println(result);
		}
	}
}
