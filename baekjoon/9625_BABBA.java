import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine()) + 1;
		long[][] dp = new long[k][2];
		dp[0][0] = 1;
		dp[0][1] = 0;
		
		for(int i=1; i<k; i++) {
			dp[i][0] = dp[i-1][1]; // A <- (B)A
			dp[i][1] = dp[i-1][1] + dp[i-1][0]; // B(A) <- B, B <- A
		}
		
		System.out.printf("%d %d", dp[k-1][0], dp[k-1][1]);
	}
}
