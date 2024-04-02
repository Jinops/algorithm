import java.util.*;
import java.io.*;

class Point {
  int y;
  int x;
  int k;
  int cnt;
  
  public Point(int y, int x, int k, int cnt) {
    this.y = y;
    this.x = x;
    this.k = k;
    this.cnt = cnt;
  }
}

public class Main {
  static int K;
  static int W;
  static int H;
  static boolean[][] mtx;
  static boolean[][][] visited;
  final static int[][] ds = {{1,0},{-1,0},{0,1},{0,-1}};
  final static int[][] hds = {{-2,-1},{-1,-2},{1,-2},{2,-1},{2,1},{1,2},{-1,2},{-2,1}};
  
  static boolean inRange(Point p) {
    return 0<=p.x&&p.x<W && 0<=p.y&&p.y<H;
  }
  
  static boolean isValid(Point p) {
    if(!inRange(p)) return false;
    if(mtx[p.y][p.x]) return false;
    for(int k=p.k; k<=K; k++) {
      // 능력 쓰기 이전에 방문했던 곳이라면
      if(visited[p.y][p.x][k]) return false;
    }
    return true;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    K = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    W = Integer.parseInt(st.nextToken());
    H = Integer.parseInt(st.nextToken());
    mtx = new boolean[H][W];
    visited = new boolean[H][W][K+1];
    
    for(int i=0; i<H; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<W; j++) {
        mtx[i][j] = st.nextToken().equals("1");
      }
    }
    
    int minCnt = Integer.MAX_VALUE;
    
    Queue<Point> queue = new LinkedList<>();
    queue.add(new Point(0, 0, K, 0));
    visited[0][0][K] = true;
    
    while(!queue.isEmpty()) {
      Point p = queue.poll();
      
      if(p.y==H-1 && p.x==W-1) {
        minCnt = Math.min(minCnt, p.cnt);
        continue;
      }
      
      if(p.k>0) { // 말 이동
        for(int[] hd:hds) {
          Point np = new Point(p.y+hd[1], p.x+hd[0], p.k-1, p.cnt+1);
          if(isValid(np)) {
            visited[np.y][np.x][np.k] = true;
            queue.add(np);
          }
        }
      }
      for(int[] d:ds) {
        Point np = new Point(p.y+d[1], p.x+d[0], p.k, p.cnt+1);
        if(isValid(np)) {
          visited[np.y][np.x][np.k] = true;
          queue.add(np);
        }
      }
    }
    
    if(minCnt==Integer.MAX_VALUE) minCnt = -1;
    System.out.println(minCnt);
  }
}
