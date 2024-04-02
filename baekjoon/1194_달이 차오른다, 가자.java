import java.util.*;
import java.io.*;

class Point {
  int y;
  int x;
  int keys; // 000000~111111
  int cnt;
  
  
  Point(int y, int x) {
    this(y, x, 0, 0);
  }
  
  Point(int y, int x, int keys, int cnt) {
    this.y = y;
    this.x = x;
    this.keys = keys;
    this.cnt = cnt;
  }

  void setKey(char c) {
    // fedcba의 보유 여부를 저장
    keys = keys | (1 << (c-'a'));
  }
  
  boolean hasKey(char c) {
    // 해당 key 갖고있는지 자리수 찾아 확인
    return ((keys >> (c-'a')) & 1) == 1;
  }

  @Override
  
  public String toString() {
    return cnt+" ("+y+","+x+") "+Integer.toBinaryString(keys);
  }
  
}

public class Main {
  static int N;
  static int M;
  static char[][] mtx;
  static boolean[][][] visited;
  
  final static int[][] ds = {{1,0},{0,1},{-1,0},{0,-1}};
  
  static boolean inRange(Point p) {
    return 0<=p.y&&p.y<N && 0<=p.x&&p.x<M;
  }
 
  static boolean isKey(char c) {
    return 'a' <= c && c <= 'f';
  }
  static boolean isDoor(char c) {
    return 'A' <= c && c <= 'F';
  }


  private static boolean visited(Point np) {
    // 더 상위개념에 방문했는지 확인
    for(int keys=np.keys; keys<(1<<7); keys++) {
      if((keys&np.keys)==np.keys && visited[np.y][np.x][keys]) {
        // & 연산 일치: keys가 np.keys의 상위개념
        return true;
      }
    }
    return false;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    mtx = new char[N][M];
    visited = new boolean[N][M][1<<7]; // 000000~111111
    
    Point start = null;
    
    for(int i=0; i<N; i++) {
      char[] chars = br.readLine().toCharArray();
      for(int j=0; j<M; j++) {
        mtx[i][j] = chars[j];
        if(mtx[i][j]=='0') start = new Point(i, j);
      }
    }
    
    int result = Integer.MAX_VALUE;
    
    Queue<Point> queue = new LinkedList<>();
    queue.add(start);
    visited[start.y][start.x][start.keys] = true;
    
    
    while(!queue.isEmpty()) {
      Point p = queue.poll();
      
      if(mtx[p.y][p.x]=='1') {
        result = Math.min(result, p.cnt);
        break;
      }
      
      for(int[] d:ds) {
        Point np = new Point(p.y+d[0], p.x+d[1], p.keys, p.cnt+1);
        if(!inRange(np) || mtx[np.y][np.x] == '#' || visited(np)) continue;
        
        if(isKey(mtx[np.y][np.x])) {
          np.setKey(mtx[np.y][np.x]);
        }
        
        else if(isDoor(mtx[np.y][np.x])) {
          if(!np.hasKey(mtx[np.y][np.x])) continue;
        }
        
        visited[np.y][np.x][p.keys] = true;
        queue.add(np);
      }
    }
    
    if(result==Integer.MAX_VALUE) result = -1;
    System.out.println(result);
  }
}
/*
 * 비트연산으로 key 보유 유무 및 visited를 설정한다.
 * 
 * 1) 이미 충분한 경우
 * 001 상태에서 방문하려 할 때, 101로 이미 방문했다면, 상위 개념에서 방문한 것
 * 즉, 001 & 101 = 001로 일치할 때는 방문 x (101 유지)
 * 2) 갱신할 수 있는 경우
 * 101 상태에서 방문 할 때, 100로 이미 방문했다면
 * 101 & 100 = 100으로 일치x. 새로 방문 (101로 갱신)
 * 3) 서로의 상태에 대소관계가 없는 경우
 * 001 상태에서 방문할 때, 010로 이미 방문했다면
 * 001과 010 상태 둘다를 저장해야한다. 새로 방문 (001, 010 둘 다 필요)
 * 
 * */
