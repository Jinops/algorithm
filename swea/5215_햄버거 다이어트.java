import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int T = Integer.parseInt(br.readLine());

    for (int t = 1; t <= T; t++) {
      
      st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int L = Integer.parseInt(st.nextToken());

      int[] scores = new int[N];
      int[] calories = new int[N];
      
      for(int n=0; n<N; n++) {
        st = new StringTokenizer(br.readLine());
        scores[n] = Integer.parseInt(st.nextToken());
        calories[n] = Integer.parseInt(st.nextToken());
      }
      
      int[][] dp = new int[N][L+1];
      
      for(int cl=calories[0]; cl<=L; cl++) {
        dp[0][cl] = scores[0];
      }
      
      for(int n=1; n<N; n++) {
        for(int cl=0; cl<=L; cl++) {
          if(calories[n]>cl) { // 칼로리가 초과하면(먹을 수 없다면)
            dp[n][cl] = dp[n-1][cl];
          } else { // 먹을 수 있다면
            dp[n][cl] = Math.max(scores[n] + dp[n-1][cl-calories[n]], dp[n-1][cl]);
            // 먹기 + 남은 칼로리만큼 이전단계에서 얻을 수 있는 양, 또는 안먹은 것 둘 중 큰거
          }
        }
      }
      
      System.out.printf("#%d %d\n", t, dp[N-1][L]);
    }
  }
}
