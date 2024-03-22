import java.io.*;

class Main {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    long[][] dp = new long[N+1][10];// [N] 자리일 때, [j]로 끝나는 오르막 수
    
    for(int i=0; i<10; i++){
      dp[1][i] = 1;
    }

    for(int i=1; i<=N; i++){
      for(int j=0; j<10; j++){
        for(int k=j; k<10; k++){
          dp[i][j] += dp[i-1][k] % 10007;
          dp[i][j] %= 10007;
        }
      }
    }

    int result = 0;
    for(int i=0; i<10; i++){
      result += dp[N][i] % 10007;
      result %= 10007;
    }
    System.out.println(result);
  }
}
