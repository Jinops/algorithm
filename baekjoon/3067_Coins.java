import java.io.*;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int T = Integer.parseInt(br.readLine());
      for(int t=0; t<T; t++) {
        
        int N = Integer.parseInt(br.readLine());
        int[] coins = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
          coins[i] = Integer.parseInt(st.nextToken());
        }
        
        int M = Integer.parseInt(br.readLine());
        int[] dp = new int[M+1];
        
        for(int coin:coins) {
          if(coin>M) {
            continue;
          }
          dp[coin] += 1;
          
          for(int i=coin+1; i<=M; i++) {
            dp[i] += dp[i-coin];
          }
        }
        
        System.out.println(dp[M]);
    }
  }
}
