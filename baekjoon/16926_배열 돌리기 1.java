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

class Main {
  static int N;
  static int M;
  static int[][] mtx;
  
  // 최대-최소 y,x값에 대해
  // 값을 바꾸는 행위를 한다.
  
  // 한 바퀴를 돌리면, 최대-최소 y,x값을 갱신한다.
  // 갱신값은 1씩 줄어들며,
  // N칸의 배열에 대해 N/2번 반복한다
  // ex) 4*4 -> (0,0)-(3,3), (1,1)-(2,2)
  // ex) 4*6 -> (0,0)-(3,5), (1,1)-(2,4)
  // ex) 6*6 -> (0,0)-(5,5), (1,1)-(4,4), (2,2)-(3,3)
  
  static void rotate() {
    Point lt = new Point(0, 0);
    Point rb = new Point(N-1, M-1);
    int cnt=0;
    int maxCnt = Math.min(N, M)/2;
    while(cnt++<maxCnt) {
      Point p = new Point(lt.y, lt.x);
      int beforeNum = mtx[p.y][p.x];
      // lt->lb
      while(p.y<rb.y) {
        p.y++;
        int curNum = mtx[p.y][p.x];
        mtx[p.y][p.x] = beforeNum; 
        beforeNum = curNum;
      }
      // lb->rb
      while(p.x<rb.x) {
        p.x++;
        int curNum = mtx[p.y][p.x];
        mtx[p.y][p.x] = beforeNum; 
        beforeNum = curNum;
      }
      // rb->rt
      while(p.y>lt.y) {
        p.y--;
        int curNum = mtx[p.y][p.x];
        mtx[p.y][p.x] = beforeNum; 
        beforeNum = curNum;
      }
      // rt->lt
      while(p.x>lt.x) {
        p.x--;
        int curNum = mtx[p.y][p.x];
        mtx[p.y][p.x] = beforeNum; 
        beforeNum = curNum;
      }
      // update lt, rb
      lt.y++; lt.x++;
      rb.y--; rb.x--;
    }
  }
  
  static void print() {
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<N; i++) {
      for(int j=0; j<M; j++) {
        sb.append(mtx[i][j]).append(' ');
      }
      sb.append('\n');
    }
    System.out.println(sb);
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    int R = Integer.parseInt(st.nextToken());
    
    mtx = new int[N][M];
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<M; j++) {
        mtx[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    
    int cnt = 0;
    while(cnt++<R) {
      rotate();
    }
    
    print();
  }
}
