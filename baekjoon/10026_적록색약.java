import java.util.*;
import java.io.*;

class Point {
  int y;
  int x;
  
  public Point(int y, int x) {
    this.y = y;
    this.x = x;
  }
}

public class Main {
  static int N;
  static char[][] mtx;
  
  static void BFS(Point start, boolean visited[][], Set<Character> colors) {
    Queue<Point> q = new LinkedList<>();
    
    q.add(start);
    visited[start.y][start.x] = true;
    
    while(!q.isEmpty()) {
      Point p = q.poll();
      
      for(int[] d:ds) {
        Point np = new Point(p.y+d[0], p.x+d[1]);
        if(inRange(np) && colors.contains(mtx[np.y][np.x]) && !visited[np.y][np.x]) {
          visited[np.y][np.x] = true;
          q.add(np);
        }
      }
    }
  }
  
  static int[][] ds = {{1,0},{-1,0},{0,1},{0,-1}};
  
  static boolean inRange(Point p) {
    return 0<=p.x&&p.x<N && 0<=p.y&&p.y<N;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    mtx = new char[N][N];
    
    for(int i=0; i<N; i++) {
      mtx[i] = br.readLine().toCharArray();
    }
    
    Set<Character> colors = new HashSet<>();
    boolean[][] rVisited = new boolean[N][N];
    boolean[][] gVisited = new boolean[N][N];
    boolean[][] bVisited = new boolean[N][N];
    boolean[][] rgVisited = new boolean[N][N];

    int R = 0;
    int G = 0;
    int B = 0;
    int RG = 0;
    
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        if(mtx[i][j]=='R' && !rVisited[i][j]) {
          colors.clear();
          colors.add('R');
          BFS(new Point(i, j), rVisited, colors);
          R++;
        }
        if(mtx[i][j]=='G' && !gVisited[i][j]) {
          colors.clear();
          colors.add('G');
          BFS(new Point(i, j), gVisited, colors);
          G++;
        }
        if(mtx[i][j]=='B' && !bVisited[i][j]) {
          colors.clear();
          colors.add('B');
          BFS(new Point(i, j), bVisited, colors);
          B++;
        }
        if(mtx[i][j]!='B' && !rgVisited[i][j]) {
          colors.clear();
          colors.add('R');
          colors.add('G');
          BFS(new Point(i, j), rgVisited, colors);
          RG++;
        }
      }
    }
    
    System.out.printf("%d %d", R+G+B, RG+B);
  }
}
