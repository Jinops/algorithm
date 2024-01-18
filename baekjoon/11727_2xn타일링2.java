import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		if(n==1) {
			System.out.println(1);
			return;
		}

		long[][] dp = new long[n][3];
		dp[0][0] = 1;
		dp[1][1] = 1;
		dp[1][2] = 1;
		
		for(int i=0; i<n; i++) {
			for(int j=0; j<3; j++) {
				if(dp[i][j]>0) {
					if(i+1<n) {
						dp[i+1][0] += dp[i][j]%10007;
					}
					if(i+2<n) {
						dp[i+2][1] += dp[i][j]%10007;
						dp[i+2][2] += dp[i][j]%10007;
					}
				}
			}
		}
		System.out.println((dp[n-1][0]+dp[n-1][1]+dp[n-1][2])%10007);
	}
}
