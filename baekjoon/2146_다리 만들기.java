import java.util.*;
import java.io.*;

class Point {
  int y;
  int x;
  int dist;
  
  public Point(int y, int x, int dist) {
    this.y = y;
    this.x = x;
    this.dist = dist;
  }
}

public class Main {
  static int N;
  static int[][] mtx;
  static int result = Integer.MAX_VALUE;
  
  static int[][] ds = {{0,1},{0,-1},{1,0},{-1,0}};
  
  static boolean inRange(Point p) {
    return 0<=p.y&&p.y<N && 0<=p.x&&p.x<N;
  }
  
  static void SetRegionNum(Point start, int n, boolean visited[][]) {
    Queue<Point> queue = new LinkedList<>();
    
    queue.add(start);
    visited[start.y][start.x] = true;
    
    while(!queue.isEmpty()) {
      Point p = queue.poll();
      mtx[p.y][p.x] = n;
      
      for(int[] d:ds) {
        Point np = new Point(p.y+d[0], p.x+d[1], 0);
        if(inRange(np) && mtx[np.y][np.x] == 1 && !visited[np.y][np.x]) {
          visited[np.y][np.x] = true; 
          queue.add(np);
        }
      }
    }
  }
  
  static void findBridge(Point start, int n) {
    Queue<Point> queue = new LinkedList<>();
    
    int[][] dists = new int[N][N];
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        dists[i][j] = Integer.MAX_VALUE;
      }
    }
    
    queue.add(start);
    dists[start.y][start.x] = 0; 
    
    while(!queue.isEmpty()) {
      Point p = queue.poll();
      if(p.dist>result) continue;
      if(mtx[p.y][p.x] != 0 && mtx[p.y][p.x] != n) {
        result = Math.min(result, p.dist);
        continue;
      }
      
      for(int[] d:ds) {
        Point np = new Point(p.y+d[0], p.x+d[1], p.dist);
        if(!inRange(np)) continue;
        if(mtx[np.y][np.x] != n) np.dist++;
        if(np.dist>=dists[np.y][np.x]) continue;
        dists[np.y][np.x]= np.dist;
        queue.add(np);
      }
    }
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    N = Integer.parseInt(br.readLine());
    mtx = new int[N][N];
    
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<N; j++) {
        mtx[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    boolean[][] visited = new boolean[N][N];
    int n = 1;
    
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        if(mtx[i][j]==1 && !visited[i][j]) {
          SetRegionNum(new Point(i, j, 0), n++, visited);
        }
      }
    }
    
    n = 1;    
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        if(mtx[i][j]==n) {
          findBridge(new Point(i, j, 0), n++);
        }
      }
    }
    
    System.out.println(result-1);
  }
}
