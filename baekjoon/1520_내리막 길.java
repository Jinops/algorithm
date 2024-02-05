// 16% 메모리 초과
import java.util.*;
import java.io.*;

public class Main {
  
  static int M;
  static int N;
  static int[][] map;
  
  static int result = 0;
  static final int[][] deltas = {{1,0},{-1,0},{0,1},{0,-1}};
  
  static int xyIdx(int x, int y) {
    return N*y+x;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    M = Integer.parseInt(st.nextToken());
    N = Integer.parseInt(st.nextToken());
    map = new int[M][N];
    int[][] xy = new int[M*N][2]; // [][0]=x, [][1]=y
    
    for(int i=0; i<M; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        xy[xyIdx(j, i)][0] = j;
        xy[xyIdx(j, i)][1] = i;
      }
    };
    
    Queue<Integer> queue = new LinkedList<>();
    queue.add(0);
    
    while(!queue.isEmpty()) {
      int idx = queue.poll();
      int x = xy[idx][0];
      int y = xy[idx][1];
      
      if(x==N-1 && y==M-1) {
        result += 1;
        continue;
      }
      
      for(int[] delta:deltas) {
        int nextX = x+delta[0];
        int nextY = y+delta[1];
        if(0<=nextX && nextX < N && 0<=nextY && nextY < M
            && map[y][x]>map[nextY][nextX]) {
          queue.add(xyIdx(nextX, nextY));
        }
      }
      
    }
    
    
    System.out.println(result);
  }
}
