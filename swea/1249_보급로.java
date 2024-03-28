import java.util.*;
import java.io.*;

class Point {
  int y;
  int x;
  int cost;
  
  public Point(int y, int x) {
    this.y = y;
    this.x = x;
  }
}

public class Solution {
  final static int[][] deltas = {{1,0},{-1,0},{0,1},{0,-1}};
  static boolean inRange(Point p, int N) {
    return 0<=p.x&&p.x<N && 0<=p.y&&p.y<N;
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int T = Integer.parseInt(br.readLine());
    for(int t=1; t<=T; t++) {
      int N = Integer.parseInt(br.readLine());
      int[][] mtx = new int[N][N];
      int[][] acc = new int[N][N];
      
      for(int i=0; i<N; i++) {
        char[] chars = br.readLine().toCharArray();
        for(int j=0; j<N; j++) {
          mtx[i][j] = Character.getNumericValue(chars[j]);
          acc[i][j] = Integer.MAX_VALUE;
        }
      }
      
      Queue<Point> queue = new LinkedList<>();
      queue.add(new Point(0, 0));
      
      while(!queue.isEmpty()) {
        Point p = queue.poll();
        for(int[] d:deltas) {
          Point np = new Point(p.y+d[0], p.x+d[1]);
          if(inRange(np, N)) {
            np.cost = p.cost + mtx[np.y][np.x];
            if(np.cost<acc[np.y][np.x]) {
              acc[np.y][np.x]= np.cost;
              queue.add(np);
            }
          }
        }
      }
      
      System.out.printf("#%d %d\n", t, acc[N-1][N-1]);
    }
  }
}
