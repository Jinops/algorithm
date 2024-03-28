// TODO: 시간초과
import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int N = Integer.parseInt(br.readLine());
    int[] T = new int[N+1]; // 걸리는 시간
    int[] P = new int[N+1]; // 금액
    int[] ends = new int[N+1]; // 끝나는 날
    
    int[] dp = new int[N+1]; // N일차 최대 수익
    
    for(int i=1; i<=N; i++) {
      st = new StringTokenizer(br.readLine());
      T[i] = Integer.parseInt(st.nextToken());
      P[i] = Integer.parseInt(st.nextToken());
      ends[i] = i+T[i]-1;
    }

    // i일차에 상담을 하면, T[i]일 동안은 상담을 못한다.
    // i일차 상담 -> i+T[i]일차 상담 가능
    
    // dp[i]: i일차까지 지났을 때, 최대 수익
    // i일차 수익 = i-1일차 수익을 계승하거나,
    // i일차까지(start) 시작할 수 있는 상담들 중, i일차 이전에 끝나는게(end) 있다면
    // start-1일차까지 최대 수익 + P[start] 수익도 가능하다.
    // 즉, i일차 최대수익 = Max(i-1일차 최대수익, i일차에 끝나는 상담을 수행했을 때 최대수익)
    
    for(int i=1; i<=N; i++) {
      dp[i] = dp[i-1];
      for(int start=1; start<=i; start++) {
        if(ends[i] == i) {
          // end <= i 아니여도 되는 이유: i일차 이전에 이미 end<i인 경우들 고려했음
          dp[i] = Math.max(dp[i], dp[start-1] + P[start]);
        }
      }
    }

    System.out.println(dp[N]);
  }
}
