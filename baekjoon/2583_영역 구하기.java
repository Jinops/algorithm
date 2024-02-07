import java.util.*;
import java.io.*;

public class Main {
  static int M, N;
  static boolean[][] visited;
  static int[][] deltas = {{1,0},{-1,0},{0,1},{0,-1}};
  
  static int dfs(int x, int y, int cnt) {
    visited[y][x] = true;
    for(int[] delta:deltas) {
      int nx = x + delta[0];
      int ny = y + delta[1];
      if(0<=nx&&nx<N && 0<=ny&&ny<M && !visited[ny][nx]) {
        cnt = dfs(nx, ny, cnt+1);
      }
    }
    return cnt;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    M = Integer.parseInt(st.nextToken());
    N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    visited = new boolean[M][N];
    
    for(int i=0; i<K; i++) {
      st = new StringTokenizer(br.readLine());
      int x1 = Integer.parseInt(st.nextToken());
      int y1 = Integer.parseInt(st.nextToken());
      int x2 = Integer.parseInt(st.nextToken());
      int y2 = Integer.parseInt(st.nextToken());
      
      for(int y=y1; y<=y2-1; y++) {
        for(int x=x1; x<=x2-1; x++) {
          visited[y][x] = true;
        }
      }
    }
    
    ArrayList<Integer> list = new ArrayList<>(); 
    StringBuilder sb = new StringBuilder();
    
    for(int i=0; i<M; i++) {
      for(int j=0; j<N; j++) {
        if(!visited[i][j]) {
          list.add(dfs(j, i, 1));
        }
      }
    }
    list.sort(null);
    sb.append(list.size()+"\n");
    for(int n:list) {
      sb.append(n+" ");
    }
    
    System.out.println(sb.toString());
  }
}
