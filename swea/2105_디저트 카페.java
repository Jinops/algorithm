import java.util.*;
import java.io.*;

// 임의의 점에서, 오른쪽 대각선 위, 아래 방향의 점을 정한다
// 해당 좌표로 이동하는 경로에 중복값이 있는지 확인한다
// 반대편에 대응하는 새로운 점으로 이동하면서도, 같은 조건을 확인한다

class Point {
  int y;
  int x;
  
  Point (int y, int x){
    this.y = y;
    this.x =x;
  }
  
  void add(int[] delta) {
    this.y += delta[0];
    this.x += delta[1];
  }
  
  boolean equals(Point p) {
    return this.y==p.y && this.x==p.x;
  }

  @Override
  public String toString() {
    return "[" + y + "," + x + "]";
  }
}

public class Solution {
  static int result;
  
  final static int[] drt = {-1,1};
  final static int[] drb = {1,1};
  
  final static int[] dlb = {1,-1};
  final static int[] dlt = {-1,-1};
  
  static int N;
  static int[][] mtx;
  
  static void printDiamond(Point left, Point top, Point right, Point bottom) {
    System.out.println();
    System.out.println("   "+top.toString());
    System.out.print(left.toString());
    System.out.println(right.toString());
    System.out.println("   "+bottom.toString());
  }
  
  static boolean inRange(Point p) {
    return 0<=p.y&&p.y<N && 0<=p.x&&p.x<N;
  }
  
  static void makeDiamond(int y, int x) {
    Point p = new Point(y, x);
    Point rt = new Point(y, x);
    
    rt.add(drt);
    
    while(inRange(rt)) {
      Point rb = new Point(y, x);
      rb.add(drb);
      
      while(inRange(rb)) {
        int sum = getDissertCnt(p, rt, rb);
//        if(sum==-1) continue;
        result = Math.max(result, sum);
        rb.add(drb);
      }
      
      rt.add(drt);
    }
  }
  
  static int getDissertCnt(Point left, Point top, Point bottom) {
    Point right = new Point(top.y-(left.y-bottom.y), top.x-(left.x-bottom.x));
    if(!inRange(right)) return -1;
//    printDiamond(left, top, right, bottom);

    boolean[] didEat = new boolean[101];
    int sum =0;
    
    // LEFT -> TOP -> RIGHT
    Point p = new Point(left.y, left.x);
    
    while(!p.equals(top)) {
      if(didEat[mtx[p.y][p.x]]) return -1;
      didEat[mtx[p.y][p.x]] = true;
      sum += 1;
      p.add(drt);
    }
    
    while(!p.equals(right)) {
      if(didEat[mtx[p.y][p.x]]) return -1;
      didEat[mtx[p.y][p.x]] = true;
      sum += 1;
      p.add(drb);
    }
    
    // RIGHT -> BOTTOM -> LEFT
    
    while(!p.equals(bottom)) {
      if(didEat[mtx[p.y][p.x]]) return -1;
      didEat[mtx[p.y][p.x]] = true;
      sum += 1;
      p.add(dlb);
    }
    
    while(!p.equals(left)) {
      if(didEat[mtx[p.y][p.x]]) return -1;
      didEat[mtx[p.y][p.x]] = true;
      sum += 1;
      p.add(dlt);
    }

    return sum;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int T = Integer.parseInt(br.readLine());
    for(int t=1; t<=T; t++) {
      result = -1;
      
      N = Integer.parseInt(br.readLine());
      mtx = new int[N][N];
      
      for(int i=0; i<N; i++) {
        st = new StringTokenizer(br.readLine());
        for(int j=0; j<N; j++) {
          mtx[i][j] = Integer.parseInt(st.nextToken());
        }
      }
      
      for(int i=0; i<N-1; i++) { // 맨위,아래 시작불가
        for(int j=0; j<N-1; j++) { // 맨오른쪽 시작불가
          makeDiamond(i, j);
        }
      }
      
      System.out.printf("#%d %d\n", t, result);
    }
  }
}
