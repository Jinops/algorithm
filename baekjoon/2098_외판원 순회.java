// 모든 경로를 다 구하면 N! -> DP를 활용한다
// 지금까지 방문한 지점과
// 현재 위치하고 있는 정점에 대해 필요했던 총 거리를 DP에 저장한다.

import java.io.*;
import java.util.*;

public class Main {
  static int N;
  static int[][] dist;
  static int[][] dp;
  final static int INF = 1000000*16;
  static int start = 0;
  
  static int TSP(int cur, int visited) {
    if(visited==(1<<N)-1) {
      // 모든 지점 방문 완료-> 처음으로
      if(dist[cur][start]==0) return INF;
      return dist[cur][start];
    }
    if(dp[cur][visited]!=-1) {
      return dp[cur][visited];
    }
    dp[cur][visited] = INF;
    // 초기값 INF
    // 처음부터 INF로 초기화한 경우, 방문을 아직 안했는지, 혹은 해봤는데 INF인지 알 수 없다.
    
    for(int next=0; next<N; next++) {
      if((visited & 1<<next)>0) continue;
      // next번째 비트가 1 = next 지점 방문했는지 체크
      if(dist[cur][next]==0) continue;
      // 이동경로 없는 경우는 제외
      int nVisited = visited | (1<<next);
      dp[cur][visited] = Math.min(dp[cur][visited], TSP(next, nVisited) + dist[cur][next]);
      // top-down으로 다음 정보를 저장한다 :
      // (next 지점 방문한 상태에서의 TSP 계산값 + next 지점 방문까지의 거리) 또는 이미 알고 있던 최솟값
    }
    
    return dp[cur][visited];
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    N = Integer.parseInt(br.readLine());
    dist = new int[N][N];
    dp = new int[N][1<<N]; 
    
    for(int i=0; i<N; i++) {
      for(int j=0; j<(1<<N); j++) {
        dp[i][j] = -1;
      }
    }
    // 현재 위치 i, 방문한 위치들 j (비트마스킹)
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<N; j++) {
        dist[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    
    System.out.println(TSP(start, 1<<start));
  }
}
