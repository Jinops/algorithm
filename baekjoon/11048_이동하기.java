import java.io.*;
import java.util.*;
 
public class Main {
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int[][] map = new int[N][M];
    int[][] dp = new int[N][M];
    
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        dp[i][j] = map[i][j];
      }
    }
    
    dp[0][0] = map[0][0];
    for(int i=0; i<N; i++) {
      for(int j=0; j<M; j++) {
        if(i>0) {
          dp[i][j] = Math.max(dp[i][j], dp[i-1][j]+map[i][j]);
        }
        if(j>0) {
          dp[i][j] = Math.max(dp[i][j], dp[i][j-1]+map[i][j]);
        }
        if(i>0 && j>0) {
          dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1]+map[i][j]);
        }
      }
    }
    System.out.println(dp[N-1][M-1]);
  }
}
