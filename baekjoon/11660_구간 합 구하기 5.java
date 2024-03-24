import java.util.*;
import java.io.*;

class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    
    int[][] acc = new int[N+1][N+1];
    
    for(int i=1; i<=N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=1; j<=N; j++) {
        acc[i][j] = Integer.parseInt(st.nextToken());
        if(j>0) {
          acc[i][j] += acc[i][j-1];
        }
        if(i>0) {
          acc[i][j] += acc[i-1][j];
          if(j>0) {
            acc[i][j] -= acc[i-1][j-1];
          }
        }
      }
    }
    
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<M; i++) {
      st = new StringTokenizer(br.readLine());
      int y1 = Integer.parseInt(st.nextToken()); 
      int x1 = Integer.parseInt(st.nextToken()); 
      int y2 = Integer.parseInt(st.nextToken()); 
      int x2 = Integer.parseInt(st.nextToken()); 
      
      int result = acc[y2][x2] - acc[y2][x1-1] - acc[y1-1][x2] + acc[y1-1][x1-1];
      
      sb.append(result).append('\n');
    }
    
    System.out.println(sb.toString());
  }
}
