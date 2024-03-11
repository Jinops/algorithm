import java.util.*;
import java.awt.Point;
import java.io.*;

public class Main {
  static ArrayList<ArrayList<Integer>> idxs;
  
  static int[][] dices = {
      {-1, 0, -1},
      { 0, 0,  0},
      {-1, 0, -1},
      {-1, 0, -1}
  };
  
  final static Point dTop = new Point(1, 1);
  final static Point dBottom = new Point(1, 3);
  static Point cur = new Point();
  
  static int N;
  static int M;
  static int[][] matrix;
  
  static final int[][] deltas = {{},{1,0},{-1,0},{0,-1},{0,1}};
  
  static boolean inRange(Point p) {
    return 0<=p.x && p.x<M && 0<=p.y && p.y<N; 
  }
  
  static void updateDice(int[] delta) {
    if(delta[0]>0) { // 오른쪽 회전
      int tmp = dices[1][2];
      dices[1][2] = dices[1][1];
      dices[1][1] = dices[1][0];
      dices[1][0] = dices[3][1];
      dices[3][1] = tmp;
    } else if (delta[0]<0) { // 왼쪽 회전
      int tmp = dices[1][0];
      dices[1][0] = dices[1][1];
      dices[1][1] = dices[1][2];
      dices[1][2] = dices[3][1];
      dices[3][1] = tmp;
    } else if(delta[1]>0) { // 아래 회전
      int tmp = dices[3][1];
      dices[3][1] = dices[2][1];
      dices[2][1] = dices[1][1];
      dices[1][1] = dices[0][1];
      dices[0][1] = tmp;
    } else if(delta[1]<0) { // 위 회전
      int tmp = dices[0][1];
      dices[0][1] = dices[1][1];
      dices[1][1] = dices[2][1];
      dices[2][1] = dices[3][1];
      dices[3][1] = tmp;
    }
  }
  
  static Point getNextPoint(int cmd) {
    int[] d = deltas[cmd];
    Point np = new Point(cur.x+d[0], cur.y+d[1]);
    if(!inRange(np)) {
      return null;
    }
    updateDice(d);
    return np;
  }
  
  static void changeNum() {
    if(matrix[cur.y][cur.x]==0) {
      matrix[cur.y][cur.x] = dices[dBottom.y][dBottom.x];
    } else {
      dices[dBottom.y][dBottom.x] = matrix[cur.y][cur.x];
      matrix[cur.y][cur.x] = 0;
    }
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    cur.y = Integer.parseInt(st.nextToken());
    cur.x = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    
    matrix = new int[N][M];
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<M; j++) {
        matrix[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    
    int[] cmds = new int[K];
    st = new StringTokenizer(br.readLine());
    for(int i=0; i<K; i++) {
      cmds[i] = Integer.parseInt(st.nextToken());
    }
    
    StringBuilder sb = new StringBuilder();
    
    for(int cmd:cmds) {
      Point np = getNextPoint(cmd);
      if(np==null) {
        continue;
      }
      cur.x = np.x;
      cur.y = np.y;
      
      changeNum();
      sb.append(dices[dTop.y][dTop.x]).append('\n');
    }
    System.out.println(sb.toString());
  }
} // 동쪽은 1, 서쪽은 2, 북쪽은 3, 남쪽은 4
