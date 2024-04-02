import java.util.*;
import java.io.*;

class Point {
  int y;
  int x;
  boolean hasBreaker;
  int cnt;
  
  public Point(int y, int x, boolean hasBreaker, int cnt) {
    this.y = y;
    this.x = x;
    this.hasBreaker = hasBreaker;
    this.cnt = cnt;
  }
}

public class Main {
  static int N;
  static int M;
  
  final static int[][] ds = {{1,0},{0,1},{-1,0},{0,-1}};
  static boolean inRange(Point p) {
    return 0<=p.x&&p.x<M && 0<=p.y&&p.y<N;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    boolean[][] mtx = new boolean[N][M];
    boolean[][][] visited = new boolean[N][M][2];
    
    for(int i=0; i<N; i++) {
      char[] chars = br.readLine().toCharArray();
      for(int j=0; j<M; j++) {
        mtx[i][j] = chars[j] == '1';
      }
    }
    
    Queue<Point> queue = new LinkedList<>();
    queue.add(new Point(0, 0, true, 1));
    visited[0][0][1] = true; // breaker를 갖고 있는 상태로 방문
    
    int result = Integer.MAX_VALUE;
    
    while(!queue.isEmpty()) {
      Point p = queue.poll();
      
      if(p.y==N-1 && p.x==M-1) {
        result = Math.min(result, p.cnt);
        continue;
      }
      
      for(int[] d:ds) {
        Point np = new Point(p.y+d[1], p.x+d[0], p.hasBreaker, p.cnt+1);
        if(!inRange(np)) continue;
        
        if(mtx[np.y][np.x] && np.hasBreaker && !visited[np.y][np.x][1]) {
          // 바닥 1, breaker 갖고 있는데, 방문했던 곳이 아니라면
          visited[np.y][np.x][0] = true;
          queue.add(new Point(np.y, np.x, false, np.cnt)); // 부쉬고 간다
        }
        if(!mtx[np.y][np.x] && !visited[np.y][np.x][p.hasBreaker?1:0]) {
          // 바닥 0
          visited[np.y][np.x][p.hasBreaker?1:0] = true;
          queue.add(np);
        }
      }
    }
    
    if(result==Integer.MAX_VALUE) result = -1;
    System.out.println(result);
  }
}
