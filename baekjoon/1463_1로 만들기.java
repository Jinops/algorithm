import java.io.*;

class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int N = Integer.parseInt(br.readLine());
    int[] dp = new int[N+1]; // N을 만드는데 필요한 연산횟수
    
    dp[N] = 0;
    
    for(int i=N-1; i>=1; i--) {
      // 1을 뺴서 만드는 경우
      int case1 = dp[i+1] + 1;
      // 2로 나눠서 만드는 경우
      int case2 = Integer.MAX_VALUE;
      if(i*2<=N) case2 = dp[i*2] + 1;
      // 3으로 나눠서 만드는 경우
      int case3 = Integer.MAX_VALUE;
      if(i*3<=N) case3 = dp[i*3] + 1;
      
      dp[i] = Math.min(Math.min(case1, case2), case3);
    }
    
    System.out.println(dp[1]);
  }
}
