import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int max = (int)Math.sqrt(n);
		if(max*max==n) {
			System.out.println(1);
			return;
		}
		int[][] dp = new int[max+1][2];
		int[][] dpCount = new int[max+1][2];
		dp[max][1] = max*max;
		dp[max][1] = 1;
		System.out.println(max+Arrays.toString(dp[max]));
		
		for(int i=max-1; i>0; i--) {
			dp[i][0] = Math.max(dp[i+1][0], dp[i+1][1]);
			int temp1 = 0;
			int temp2 = 0;
			
			if(dp[i+1][1]+Math.pow(i, 2)<=n) {
				temp1 = (int) (dp[i+1][1]+Math.pow(i, 2));
			} 
			if(dp[i+1][0]+Math.pow(i, 2)<=n) {
				temp2 = (int) (dp[i+1][0]+Math.pow(i, 2));
			}
			
			if(temp1 > temp2) {
				dp[i][1] = temp1;
				dpCount[i][1] += dpCount[i+1][1]+1;
			} else if (temp1 < temp2) {
				dp[i][1] = temp2;
				dpCount[i][1] += dpCount[i+1][0]+1;
			} else if (dpCount[i+1][1]>=dpCount[i+1][0]) {
				dp[i][1] = temp1;
				dpCount[i][1] += dpCount[i+1][1]+1;
			} else {
				dp[i][1] = temp2;
				dpCount[i][1] += dpCount[i+1][0]+1;
			}
			
			System.out.println(i+Arrays.toString(dp[i]));
		}
		
	}
}
