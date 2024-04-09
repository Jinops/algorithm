// 삼성 SW 역량테스트 2023 상반기 오후 1번 문제
// 1시간 40분 풀이
// TODO: tc 2 fail
import java.util.*;
import java.io.*;

// 1. 이동
// 출구로 가까워져야 함
// 상,하 움직임 우선시
// 한 칸에 2명 가능

// 2. 미로 회전
// 가장 작은 정사각형(참가자, 출구 포함) 잡기 - 작은 r,c우선
// - 출구를 기준으로, 4방향으로 사각형을 만들되,
// - 사각형에 참가자가 포함되어 있으면 해당 사각형으로 결정한다.
// 시계방향 회전 및 내구도 깎임

class Rectangle {
  Point p; // lt
  int length;
  
  public Rectangle(Point p, int length) {
    this.p = p;
    this.length = length;
  }
}

class Point {
  int y;
  int x;
  
  public Point(int y, int x) {
    this.y = y;
    this.x = x;
  }

  @Override
  public String toString() {
    return "[" + y + "," + x + "]";
  }
}

public class Main {
  static int N;
  static int M;
  static int K;
  static int[][] mtx;
  
  static int moveSum = 0;
  
  final static int PLAYER = -1; // player 명 수 만큼
  final static int EXIT = -20;
  
  final static int[][] ds = {{-1,0},{1,0},{0,-1},{0,1}};
  // (y,x) 0상 1하 2좌 3우
  static boolean inRange(Point p) {
    return 0<=p.y&&p.y<N && 0<=p.x&&p.x<N;
  }
  
  static boolean isPlayer(int n) {
    return n!=EXIT && n < 0;
  }
  
  static boolean isWall(int n) {
    return 1<=n&&n<=9;
  }
  
  static int getDistance(Point p1, Point p2) {
    return Math.abs(p1.x-p2.x)+Math.abs(p1.y-p2.y);
  }
  
  static void moveIfPossible(Point p, Point exit, int[][] afterMove) {
    int dist = getDistance(p,  exit);
    
    for(int[] d:ds) {
      Point np = new Point(p.y+d[0], p.x+d[1]);
      if(inRange(np) && !isWall(mtx[np.y][np.x])) {
        int nDist = getDistance(np, exit);
        if(nDist<dist) {
          moveSum += mtx[p.y][p.x]*-1;
          // player 개수 당 -1씩 되어 있음
          if(mtx[np.y][np.x]!=EXIT) {
            afterMove[np.y][np.x] = mtx[p.y][p.x];
            // EXIT이면, 기존 위치 값만 사라짐
          }
          afterMove[p.y][p.x] = 0; 
          return;
        }
      }
    }
  }
  
  static Point getExit() {
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        if(mtx[i][j]==EXIT) {
          return new Point(i, j);
        }
      }
    }
    return null;
  }
  
  static int[][] copy(int[][] origin){
    int[][] copy = new int[N][N];
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        copy[i][j] = origin[i][j];
      }
    }
    return copy;
  }
  
  static boolean isAllDone() {
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        if(isPlayer(mtx[i][j])) {
          return false;
        }
      }
    }
    return true;
  }
  
  static Rectangle getMinRectangle(Point exit) {
    int length = 1;
    Point lt = new Point(exit.y, exit.x);
    Point rb = new Point(exit.y, exit.x);
    
    while(true) {
      length += 1;
      
      lt.y = Math.max(lt.y-1, 0);
      lt.x = Math.max(lt.x-1, 0);
      rb.y = Math.min(rb.y+1, N-1);
      rb.x = Math.min(rb.x+1, N-1);
            
      for(int y=lt.y; y<=rb.y; y++) {
        for(int x=lt.x; x<=rb.x; x++) {
          if(isPlayer(mtx[y][x])) {
            Point p = new Point(exit.y, exit.x);
            if(y<=exit.y && x<=exit.x) {
              // 좌상 구역에 있는 경우
              p.y -= (length-1);
              p.x -= (length-1);
            }

            if(y<=exit.y && x>exit.x) {
              // 우상 구역에 있는 경우
              p.y -= (length-1);
            }

            if(y>exit.y && x<=exit.x) {
              // 좌하 구역에 있는 경우
              p.x -= (length-1);
            }

            if(y>exit.y && x>exit.x) {
            // 우하 구역에 있는 경우
            }
            
            p.y = Math.max(p.y, 0);
            p.x = Math.max(p.x, 0);
            
            return new Rectangle(p, length);
          }
        }
      }
    }
  }
  
  static int[][] getRotatedPart(Rectangle r){
    int n = r.length;
    int[][] originPart = new int[n][n];
    int[][] rotatedPart = new int[n][n];
    
    for(int i=0; i<n; i++) {
      for(int j=0; j<n; j++) {
        originPart[i][j] = mtx[r.p.y+i][r.p.x+j];
      }
    }
    
    for(int i=0; i<n; i++) {
      for(int j=0; j<n; j++) {
        rotatedPart[i][j] = originPart[n-j-1][i];
        if(isWall(rotatedPart[i][j])) {
          rotatedPart[i][j] -= 1;
        }
      }
    }
    
    return rotatedPart;
  }
  
  static void print() {
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        System.out.printf("%4d ", mtx[i][j]);
      }
      System.out.println();
    }
    System.out.println();
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    N = Integer.parseInt(st.nextToken()); //미로 크기
    M = Integer.parseInt(st.nextToken()); //참가자 수
    K = Integer.parseInt(st.nextToken()); //게임 시간
    
    mtx = new int[N][N];
    
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<N; j++) {
        mtx[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    
    for(int i=0; i<M; i++) {
      st = new StringTokenizer(br.readLine());
      int y = Integer.parseInt(st.nextToken())-1;
      int x = Integer.parseInt(st.nextToken())-1;
      mtx[y][x] += PLAYER;
    }
    
    st = new StringTokenizer(br.readLine());
    int eY = Integer.parseInt(st.nextToken())-1;
    int eX = Integer.parseInt(st.nextToken())-1;
    mtx[eY][eX] = EXIT;

    // ~ 입력
    
    int k = 0;
    while(k++<K && !isAllDone()) {
      // move
      Point exit = getExit();
      int[][] afterMove = copy(mtx);
      for(int i=0; i<N; i++) {
        for(int j=0; j<N; j++) {
          if(isPlayer(mtx[i][j])) {
            moveIfPossible(new Point(i,j), exit, afterMove);
          }
        }
      }
      mtx = afterMove;
      if(isAllDone()) break;
      
      // find
      Rectangle rect = getMinRectangle(exit);
      
      // rotate
      int[][] rotatedPart = getRotatedPart(rect);
      for(int i=0; i<rect.length; i++) {
        for(int j=0; j<rect.length; j++) {
          mtx[rect.p.y+i][rect.p.x+j] = rotatedPart[i][j];
        }
      }
    }
    
    Point exit = getExit();
    System.out.println(moveSum);
    System.out.print((exit.y+1) +" " + (exit.x+1));
  }
}
