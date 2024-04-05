import java.util.*;
import java.io.*;

class Point {
  int y;
  int x;
  int height;
  int length;
  boolean hasChance;
  Set<Point> history;
  public Point(int y, int x, int height, int length, boolean hasChance, Set<Point> history) {
    super();
    this.y = y;
    this.x = x;
    this.height = height;
    this.length = length;
    this.hasChance = hasChance;
    this.history = history;
  }
  
  
}

class Solution {
  static int N;
  static int K;
  static int[][] mtx;
  static int result;
  
  static int[][] ds = {{1,0},{-1,0},{0,1},{0,-1}};
  
  static boolean inRange(int y, int x) {
    return 0<=y&&y<N && 0<=x&&x<N;
  }
  
  static void BFS(Point start) {
    Queue<Point> queue = new LinkedList<>();
    queue.add(start);
    
    int maxLength = 0;
    
    while(!queue.isEmpty()) {
      Point p = queue.poll();
      maxLength = p.length;
      
      for(int[] d:ds) {
        int ny = p.y+d[0];
        int nx = p.x+d[1];
        if(!inRange(ny, nx)) continue;
        boolean flag = false;
        for(Point his:p.history) {
          if(his.y==ny && his.x==nx) {
            flag = true;
            break;
          }
        }
        if(flag) continue;
        
        if(mtx[ny][nx] < p.height) {
          Set<Point> history = new HashSet<>(p.history);
          history.add(p);
          queue.add(new Point(ny, nx, mtx[ny][nx], p.length+1, p.hasChance, history));
        } else if(p.hasChance) {
          int cut = mtx[ny][nx] - p.height + 1;
          if(cut<=K && mtx[ny][nx]-cut>=0) {
            Set<Point> history = new HashSet<>(p.history);
            history.add(p);
            queue.add(new Point(ny, nx, mtx[ny][nx]-cut, p.length+1, false, history));
          }
        }
      }
    }
    
    result = Math.max(result, maxLength);
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int T = Integer.parseInt(br.readLine());
    for(int t=1; t<=T; t++) {
      st = new StringTokenizer(br.readLine());
      result = 0;
      
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      
      mtx = new int[N][N];
      int max = 0;
      
      for(int i=0; i<N; i++) {
        st = new StringTokenizer(br.readLine());
        for(int j=0; j<N; j++) {
          mtx[i][j] = Integer.parseInt(st.nextToken());
          max = Math.max(max,  mtx[i][j]);
        }
      }
      
      for(int i=0; i<N; i++) {
        for(int j=0; j<N; j++) {
          if(mtx[i][j]==max) {
            BFS(new Point(i, j, mtx[i][j], 1, true, new HashSet<>()));
          }
        }
      }
      
      System.out.printf("#%d %d\n", t, result);
    }
  }
}
