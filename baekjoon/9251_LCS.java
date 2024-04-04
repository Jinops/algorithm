import java.io.*;

class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    String A = br.readLine();
    String B = br.readLine();
    
    int[][] dp = new int[A.length()+1][B.length()+1];
    // A의 i번째 문자까지 보았을 때, B의 j번쨰로 갔을 떄 누적
    
    for(int a=0; a<A.length(); a++) {
      for(int b=0; b<B.length(); b++) {
        if(A.charAt(a)==B.charAt(b)) {
          dp[a+1][b+1] = dp[a][b] + 1;
        } else {
          dp[a+1][b+1] = Math.max(dp[a+1][b], dp[a][b+1]);
        }
      }
    }
    
    System.out.println(dp[A.length()][B.length()]);
  }
}
