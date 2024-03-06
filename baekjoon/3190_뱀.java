// WIP
import java.util.*;
import java.awt.Point;
import java.io.*;

class Main {
  final static int EMPTY = 0;
  final static int APPLE = 1;
  final static int SNAKE = 2;
  
  static int N;
  
  public static int[][] deltas = {{1,0},{0,1},{-1,0},{0,-1}}; // 3,9,6,12
  
  public static int getNextDeltaIdx(int idx, char dir) {
    if(dir=='L') {
      idx = (idx==0)? 3 : idx-1;
    }else if(dir=='R') {
      idx = (idx==3)? 0 : idx+1;
    }
    return idx;
  }
  
  static void move(Point p, int x, int y) {
    p.x += x;
    p.y += y;
  }
  
  static boolean inRange(Point p) {
    return 0<=p.x&&p.y<N && 0<=p.y&&p.y<N;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    N = Integer.parseInt(br.readLine());
    int K = Integer.parseInt(br.readLine());
    int[][] matrix = new int[N][N];
    
    for(int i=0; i<K; i++) {
      st = new StringTokenizer(br.readLine());
      int y = Integer.parseInt(st.nextToken())-1;
      int x = Integer.parseInt(st.nextToken())-1;
      matrix[y][x] = APPLE;
    }
    
    int L = Integer.parseInt(br.readLine());
    int times[] = new int[L];
    char dirs[] = new char[L];
    
    for(int i=0; i<L; i++) {
      st = new StringTokenizer(br.readLine());
      times[i] = Integer.parseInt(st.nextToken());
      dirs[i] = st.nextToken().charAt(0);
    }
    
    Point h = new Point(0, 0); // head
    Point t = new Point(0, 0); // tail
    int dIdx = 0;
    
    int idx = 0;
    int time = 0;
    
    while(inRange(h) && matrix[h.y][h.x]!= SNAKE) {
//      if(idx<L && time >= times[idx]) {
//        dIdx = getNextDeltaIdx(idx, dirs[idx]);
//      }
      
      int dx = deltas[dIdx][0];
      int dy = deltas[dIdx][1];
      
      if(matrix[h.y][h.x]==APPLE) {
        matrix[h.y][h.x]= SNAKE;
        move(h, dx, dy);
      }
      matrix[h.y][h.x] = SNAKE;
      move(h, dx, dy);
      
      matrix[t.y][t.x] = EMPTY;
      move(t, dx, dy);
      
      time++;
      
      for(int[] a:matrix) System.out.println(Arrays.toString(a));
      System.out.println();
    }
    
    System.out.println(time);
  }
}
