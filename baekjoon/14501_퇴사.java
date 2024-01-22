import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int lastDay = Integer.parseInt(br.readLine());
		int[] days = new int[lastDay+1];
		int[] pays = new int[lastDay+1];
		int[] dp = new int[lastDay+1];
		
		StringTokenizer st;
		
		for(int i=1; i<=lastDay; i++) {
			st = new StringTokenizer(br.readLine());
			days[i] = Integer.parseInt(st.nextToken());
			pays[i] = Integer.parseInt(st.nextToken());
		}
		// top-down
		// dp[i] = dp[i+1](i번째 스킵) or pays[i]+dp[i번째 한 뒤] 
		dp[lastDay] = (days[lastDay]<=1)?pays[lastDay]:0;
		for(int i=lastDay-1; i>=1; i--) {
			if(i+days[i]-1==lastDay) {
				dp[i] = Math.max(dp[i+1],pays[i]);
			} else if(i+days[i]-1<lastDay) {
				dp[i] = Math.max(dp[i+1],dp[i+days[i]]+pays[i]);
			} else {
				dp[i] = dp[i+1];
			}
//			System.out.println(Arrays.toString(dp));
		}
		
		System.out.println(dp[1]);
	}
}
