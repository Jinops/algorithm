import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int lastDay = Integer.parseInt(br.readLine());
		int[] days = new int[lastDay+1];
		int[] pays = new int[lastDay+1];
		int[][] dp = new int[lastDay+1][lastDay+1];
		int result = 0;
		// 최대이익 = dp[n일차][n일 여유 있을 때]
		
		String a =new String("banana");
		StringTokenizer st;
		
		for(int i=1; i<=lastDay; i++) {
			st = new StringTokenizer(br.readLine());
			days[i] = Integer.parseInt(st.nextToken());
			pays[i] = Integer.parseInt(st.nextToken());
		}

		for(int i=1; i<lastDay; i++) {
			for(int j=1; j<=lastDay; j++) {
				if(j >=days[i]) {
					if(i+days[i]<=lastDay+1) {
						dp[i][j] += pays[i];
						dp[i+1][j-days[i]] += dp[i][j];
					} else {
						dp[i+1][j] += dp[i][j];
					} 
				}
			}
		}
		// DEBUG
		for(int i=1; i<=lastDay; i++) {
			System.out.printf("[%d일]\n", i);
			for(int j=1; j<=lastDay; j++) {
				System.out.printf("%d ", dp[i][j]);
			}
			System.out.println();
		}
		//
		System.out.println(result);
	}
}
