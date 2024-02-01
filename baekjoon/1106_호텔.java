// TODO
import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      int C = Integer.parseInt(st.nextToken()); // 목표 점수
      int N = Integer.parseInt(st.nextToken()); // 도시 개수
      
      int[] costs = new int[N];
      int[] scores = new int[N];
      int minScore = 100;
      int[][] dp = new int[N][C+1]; // dp[n번][비용] = 점수
      
      for(int i=0; i<N; i++) {
        st = new StringTokenizer(br.readLine());
        costs[i] = Integer.parseInt(st.nextToken());
        scores[i] = Integer.parseInt(st.nextToken());
        minScore = Math.min(minScore, scores[i]);
      }
      
      for(int n=0; n<N; n++) {
        for(int c=1; c<C+1; c++) {
          dp[n][c] = Integer.MAX_VALUE;
          if(c>=costs[n]) {
            dp[n][c] = scores[n] * (int)Math.floor(c/costs[n]);
          }
        }
      }
      System.out.println(Arrays.toString(dp[0]));
      
      for(int n=1; n<N; n++) {
        for(int c=0; c<=C; c++) {
          dp[n][c] = dp[n-1][c];
        }
        for(int c=costs[n]; c<=C; c++) {
//          System.out.println(c + ":" + dp[n-1][c] + " or " + scores[n] + "+" + dp[n-1][c-costs[n]]);
          for(int k=1; k<=(int)Math.floor(c/costs[n]); k++) {
            System.out.println(n + ":" + k);
            Math.min(dp[n][c], scores[n] + dp[n-1][c-costs[n]]);
          }
          System.out.println(Arrays.toString(dp[n]));
        }
      }
    }
}
