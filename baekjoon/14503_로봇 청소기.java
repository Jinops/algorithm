import java.io.*;
import java.util.*;


public class Main {
  final static int NOT_CLEANED = 0;
  final static int WALL = 1;
  final static int CLEANED = 2;

  final static int[][] deltas = {{0,-1},{1,0},{0,1},{-1,0}}; // 12, 3, 6, 9 o'clock
  
  static int x;
  static int y;
  static int dir;
  
  static int M;
  static int N;
  static int[][] map;
  
  static int result = 0;
  
  static int getReverseDir() {
    return dir>=2 ? dir-2 : dir+2;
  }
  
  static void rotate() { // C12:0 C3:1 C6:2 C9:3
    dir = dir==0 ? 3: dir-1;
  }
  
  static boolean inRange(int nx, int ny) {
    return 0<=nx&&nx<M && 0<=ny&&ny<N;
  }
  
  static boolean is4wayCleaned() {
    for(int[] delta:deltas) {
      int nx = x+delta[0];
      int ny = y+delta[1];
      if(inRange(nx, ny) && map[ny][nx] == NOT_CLEANED) {
        return false;
      }
    }
    return true;
  }
  
  static void clean() {
    if(map[y][x]==NOT_CLEANED) { // 1
      map[y][x] = CLEANED;
      result++;
    }
    if(is4wayCleaned()) { // 2
      int reverseDir = getReverseDir();
      int bx = x+deltas[reverseDir][0];
      int by = y+deltas[reverseDir][1];
      if(inRange(bx, by) && map[by][bx] != WALL) { // 2-1
        x = bx;
        y = by;
        clean();
      } // else: 2-2 (stop clean)
    } else {
      rotate(); // 3-1
      int fx = x+deltas[dir][0];
      int fy = y+deltas[dir][1];
      if(inRange(fx, fy) && map[fy][fx] == NOT_CLEANED) { // 3-2
        x = fx;
        y = fy;
      }
      clean(); // 3-3
    }
  }
   
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    map = new int[N][M];
    
    st = new StringTokenizer(br.readLine());
    y = Integer.parseInt(st.nextToken());
    x = Integer.parseInt(st.nextToken());
    dir = Integer.parseInt(st.nextToken());
    
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<M; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    
    clean();
    System.out.println(result);
  }
}
