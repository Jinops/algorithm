import java.io.*;

class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int N = Integer.parseInt(br.readLine());
    int[] ns = new int[N+1];
    int[] dp = new int[N+1];
    
    for(int i=1; i<=N; i++) {
      ns[i] = Integer.parseInt(br.readLine());
    }
    
    
    dp[1] = ns[1];
    if(N>1) {      
      dp[2] = dp[1]+ns[2];
    }

    // 한계단 or 두계단
    // 연속 세 개 x
    for(int i=3; i<=N; i++) {
      int c1 = dp[i-2]+ns[i]; // 두 계단 뛰기
      int c2 = dp[i-3]+ns[i-1]+ns[i]; // 연속 두 계단 오르기
      dp[i] = Math.max(c1,  c2);
    }
    
    System.out.println(dp[N]);
  }
}
