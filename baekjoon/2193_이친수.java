import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		long[][] dp = new long[n][2];
		dp[0][1] = 1;
		
		for(int i=0; i<n-1; i++) {
			if(dp[i][0]>0) {
				dp[i+1][0] += dp[i][0]; // 00
				dp[i+1][1] += dp[i][0]; // 01
			}
			if(dp[i][1]>0) {
				dp[i+1][0] += dp[i][1]; // 10
			}
		}
		
		System.out.println(dp[n-1][0]+dp[n-1][1]);
		
	}
}
