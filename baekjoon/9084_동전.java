import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int T = Integer.parseInt(br.readLine());
    
    for(int t=0; t<T; t++) {
      int N = Integer.parseInt(br.readLine());
      int[] coins = new int[N];
      int minCoin = 10000;
      
      st = new StringTokenizer(br.readLine());
      for(int i=0; i<N; i++) {
        coins[i] = Integer.parseInt(st.nextToken());
        minCoin = Math.min(minCoin, coins[i]);
      }
      int M = Integer.parseInt(br.readLine());
      
      int[] dp = new int[M+1];
      for(int coin:coins) {
        if(coin>M) {
          continue;
        }
        dp[coin] += 1; // 동전 금액만큼 만드는 방법이 1개 생김
        
        for(int i=coin+1; i<=M; i++) {
          dp[i] += dp[i-coin];
          // i원 만들기 = 지금 추가할 동전 뺀 금액 만드는법 + 원래 알고있던 방법
        }
      }

      System.out.println(dp[M]);
    }
  }
}
