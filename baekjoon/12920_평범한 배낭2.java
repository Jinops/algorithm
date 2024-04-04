import java.util.*;
import java.io.*;

// old)
// 같은 종류의 물건에 대해, 개수만큼 각각 1개의 물건으로 넣어줬다.
// 이 경우, 물건 종류 N(100)*개수 K(10,000) = 100만개에 대해
// 가방의 무게 M(10,000)만큼 배열을 할당하여 메모리초과가 발생했다.

// new)
// 같은 종류의 물건에 대해, 2의 거듭제곱 개로 분리해 넣어준다.
// 예를 들어 10개인 경우, 원래라면 10개를 각각으로 넣어주었다면
// 1,2,4(sum=7)과 나머지 3개를 한 묶음으로 넣어준다.
// 따라서 물건 1개에 대해 최대 개수 10,000 = 2^13+a, 즉 13+1묶음으로 나타내지므로
// 최대 N(100) * 14 * M(10,000) 만큼만 할당을 하면 된다.

// how)
// 모든 자리수가 1인 이진수에 대해 (11111 등)
// 각 자리수를 사용하지 않는 방법으로 0부터 11111까지 모든 수를 조합할 수 있다
// 따라서 이와 같은 이진수와 더불어, 나머지 값(자리수에 0이 포함된)을 하나로 사용하여
// 2의 거듭제곱(1부터 증가하는)들과 나머지 값으로 모든 경우를 조합할 수 있다.

class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    
    List<Integer> ws = new ArrayList<>();
    List<Integer> scores = new ArrayList<>();
    
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      int V = Integer.parseInt(st.nextToken());
      int C = Integer.parseInt(st.nextToken());
      int K = Integer.parseInt(st.nextToken());
      
      // 2의 거듭제곱로 나타내는 수
      for(int j=1; j<=K; j*=2) {
        ws.add(V*j);
        scores.add(C*j);
        K-=j;
      }
      // 나머지 수
      if(K>0) {
        ws.add(V*K);
        scores.add(C*K);
      }
    }
    
    int[][] dp = new int[ws.size()][M+1];
    
    for(int w=ws.get(0); w<=M; w++) {
      dp[0][w] = scores.get(0);
    }
    
    for(int i=1; i<ws.size(); i++) {
      // 물건 i 못넣는 경우
      for(int w=0; w<ws.get(i); w++) {
        dp[i][w] = dp[i-1][w];
      }
      // 물건 i 넣을 수 있는 경우
      for(int w=ws.get(i); w<=M; w++) {
        dp[i][w] = Math.max(dp[i-1][w], 
            dp[i-1][w-ws.get(i)] + scores.get(i));
      }
    }
    
    System.out.println(dp[ws.size()-1][M]);
  }
}
