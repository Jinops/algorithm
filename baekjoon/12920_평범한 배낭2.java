// TODO: 35% 시간초과
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken()); // 종류
    int M = Integer.parseInt(st.nextToken()); // 최대무게

    int[] V = new int[N]; // 무게
    int[] C = new int[N]; // 만족도
    int[] K = new int[N]; // 개수
    int[][] dp = new int[N][M+1];
    int minW = 10000;

    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      V[i] = Integer.parseInt(st.nextToken());
      C[i] = Integer.parseInt(st.nextToken());
      K[i] = Integer.parseInt(st.nextToken());
      minW = Math.min(minW, V[i]);
    }

    for(int w=V[0]; w<=M; w++) {
        dp[0][w] = C[0] * Math.min(w/V[0], K[0]);
    }

    for(int n=1; n<N; n++) {
      for(int w=minW; w<V[n]; w++) {
        dp[n][w] = dp[n-1][w]; // 기본적으론 이전꺼 그대로 계승하고
      }
      for(int w=V[n]; w<=M; w++) {
        dp[n][w] = dp[n-1][w]; 
        int maxK = Math.min(w/V[n], K[n]); // 현재 무게에서 넣을 수 있는 최대 무게를 가져와
        for(int k=1; k<=maxK; k++) { // 각 무게를 넣었을 때 값을 비교한다.
          dp[n][w] = Math.max(dp[n][w], C[n]*k + dp[n-1][w-V[n]*k]);
        }
      }
    }

    System.out.println(dp[N-1][M]);
  }
}
