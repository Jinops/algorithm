import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
  static int[][] deltas = {{1,0},{-1,0},{0,1},{0,-1}};
  static boolean inRange(int n, int limit) {
    return 0<=n && n<limit;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int[][] matrix = new int[N][M];
    int[][] counts = new int[N][M];
    
    for(int i=0; i<N; i++) {
      String str = br.readLine();
      for(int j=0; j<M; j++) {
        matrix[i][j] = Character.getNumericValue(str.charAt(j));
      }
    }
    
    Queue<Point> queue = new LinkedList<>();
    queue.add(new Point(0, 0));
    counts[0][0] = 1;
    
    while(!queue.isEmpty()) {
      Point p = queue.poll();
      if(p.x==M-1 && p.y==N-1) {
        System.out.println(counts[p.y][p.x]);
        break;
      }
     for(int[] delta:deltas) {
       Point np = new Point(p.x+delta[0], p.y+delta[1]);
       if(inRange(np.x, M) && inRange(np.y, N) && matrix[np.y][np.x] == 1 && counts[np.y][np.x] == 0) {
         queue.add(np);
         counts[np.y][np.x] = counts[p.y][p.x]+1;
       }
     }
    }
  }
}
