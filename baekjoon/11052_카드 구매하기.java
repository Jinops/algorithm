import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int[] dp = new int[N+1]; // 카드 i개 사는데 필요한 최대 비용
    int[] costs = new int[N+1];

    st = new StringTokenizer(br.readLine());
    for(int i=1; i<=N; i++){
      costs[i] = Integer.parseInt(st.nextToken());
    }

    for(int i=1; i<=N; i++){
      dp[i] = costs[i];
      for(int j=1; j<=i; j++){
        dp[i] = Math.max(dp[i], costs[i-j] + dp[j]);
        //i-j개 비용 + 기존에 j개 살 때 필요한 최대 비용
      }
    }
    System.out.println(dp[N]);
  }
}
