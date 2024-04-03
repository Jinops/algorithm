import java.util.*;
import java.io.*;

// Blue가 빠지면 실패,
// 4방향 ^ 10회의 시도
// 단, 직전의 방향과 같거나 정반대 방향은 필요 없음 (좌->(상,하)만 가능)
// 즉, 4 * 2^9회의 시도

class Point {
  int y;
  int x;
  
  public Point(int y, int x) {
    this.y = y;
    this.x = x;
  }
  public Point(Point p) {
    this.y = p.y;
    this.x = p.x;
  }
  public boolean equals(Point p) {
    return this.y==p.y && this.x==p.x;
  }
  @Override
  public String toString() {
    return "[" + y + "," + x + "]";
  }
 
}

class Board {
  int n;
  boolean[] hasDeltas; // 가능한 deltas
  Point red;
  Point blue;
  boolean isRedFinish;
  
  public Board(int n, boolean[] hasDeltas, Point red, Point blue) {
    this.n = n;
    this.hasDeltas = hasDeltas.clone();
    this.red = new Point(red.y, red.x);
    this.blue = new Point(blue.y, blue.x);
  }
}

class Main {
  static char[][] mtx;
  final static int[][] deltas = {{1,0},{-1,0},{0,1},{0,-1}}; // 우,좌,상,하
  
  static boolean[] getNextDeltas(int i) {
    boolean[] hasDeltas = new boolean[4];
    if(i==0 || i==1) {
      hasDeltas[2] = true;
      hasDeltas[3] = true;
    } else if(i==2 || i==3) {
      hasDeltas[0] = true;
      hasDeltas[1] = true;
    }
    return hasDeltas;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int N = Integer.parseInt(st.nextToken()); // 세로
    int M = Integer.parseInt(st.nextToken()); // 가로
    
    mtx = new char[N][M];
    Point red = null;
    Point blue = null;
    
    
    for(int i=0; i<N; i++) {
      char[] chars = br.readLine().toCharArray();
      for(int j=0; j<M; j++) {
        mtx[i][j] = chars[j];
        if(mtx[i][j]=='R') {
          mtx[i][j] = '.';
          red = new Point(i, j);
        } else if(mtx[i][j] == 'B') {
          mtx[i][j] = '.';
          blue = new Point(i, j);
        }
      }
    }

    Queue<Board> boards = new LinkedList<>();
    boards.add(new Board(0, new boolean[]{true, true, true, true}, red, blue));

    int result = -1;
    
    while(!boards.isEmpty()) {
      Board board = boards.poll();
      if(mtx[board.blue.y][board.blue.x]=='O') {
        // blue가 빠진 경우
        continue;
      }
      if(board.isRedFinish) {
        result = board.n;
        break;
      }
      if(board.n == 10) {
        continue;
      }
      
      for(int i=0; i<4; i++) {
        if(!board.hasDeltas[i]) continue;
        int[] d = deltas[i];
        // d 방향에 대해 구슬 움직이기
        boolean[] nextDeltas = getNextDeltas(i); 
        Board nBoard = new Board(board.n+1, nextDeltas, board.red, board.blue);

        while(true) {
          Point nRed = new Point(nBoard.red);
          Point nBlue = new Point(nBoard.blue);
          
          if(!nBoard.isRedFinish && mtx[nRed.y+d[1]][nRed.x+d[0]]!='#') {
            nRed.y += d[1];
            nRed.x += d[0];
          }
          if(mtx[nBlue.y+d[1]][nBlue.x+d[0]]!='#') {
            nBlue.y += d[1];
            nBlue.x += d[0];
          }
          // nRed, nBlue가 겹치는지 확인 후, 겹치면 이전 위치로 돌린다.
          if(nRed.equals(nBlue)) {
            nRed = new Point(nBoard.red);
            nBlue = new Point(nBoard.blue);
          }
          // nRed, nBlue 모두 움직임이 없으면 종료한다.
          if(nRed.equals(nBoard.red) && nBlue.equals(nBoard.blue)) {
            break;
          }
          // red, blue를 이동시킨다
          nBoard.red = new Point(nRed);
          nBoard.blue = new Point(nBlue);
          
          // nBlue가 구멍에 빠지면 종료한다.
          if(mtx[nBoard.blue.y][nBoard.blue.x] == 'O') {
            break;
          }
          if(!nBoard.isRedFinish && mtx[nBoard.red.y][nBoard.red.x] == 'O') {
            nBoard.isRedFinish = true;
            nBoard.red = new Point(-1, -1);
          }
          // nRed가 빠지면 성공 및 종료이긴 하나,
          // nRed가 빠진 뒤 줄지어서 nBlue가 빠지면 실패이다.
          // 따라서 우선 board에서 red의 성공 유무를 따로 저장하고,
          // Blue는 이동 로직을 그대로 한다.
        }
        
        boards.add(nBoard);
      }
    }
    
    System.out.println(result);
  }
}
