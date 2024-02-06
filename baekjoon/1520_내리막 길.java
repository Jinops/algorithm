// 13% 메모리 초과
import java.util.*;
import java.awt.Point;
import java.io.*;

public class Main {
  static int M, N;
  static int[][] map;
  static boolean[][] isToEnd;
  static int result  = 0;
  
  static final int[][] deltas = {{1,0},{-1,0},{0,1},{0,-1}};
  
  static Point[] getNearPoints(int x, int y) {
    ArrayList<Point> points = new ArrayList<>();
    for(int[] delta:deltas) {
      int nx = x+delta[0];
      int ny = y+delta[1];
      if(0<=nx && nx<N && 0<=ny && ny<M && map[y][x]>map[ny][nx]) {
          points.add(new Point(nx, ny));
        }
      }
    return points.toArray(new Point[points.size()]);
  }
  
  static void setIsToEnd(Queue<Point> queue) {
    for(Point np:queue) {
      isToEnd[np.y][np.x] = true; 
    }
  }
  
  static void DFS(Deque<Point> queue) {
    Point p = queue.peekLast();
//    System.out.println(queue.toString());
    if(isToEnd[p.y][p.x] || (p.x==N-1 && p.y==M-1)) {
      result += 1;
      setIsToEnd(queue);
      return;
    }
    
    Point[] nearPoints = getNearPoints(p.x, p.y);
    for(Point np:nearPoints) {
      if(nearPoints.length == 1) {
        queue.add(np);
        DFS(queue);
      } else { // 분기 시 분기 이후의 것들을 따로 queue에 넣음
        Deque<Point> branchedQueue = new LinkedList<>();
        branchedQueue.add(np);
        DFS(branchedQueue);
      }
    }
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    M = Integer.parseInt(st.nextToken());
    N = Integer.parseInt(st.nextToken());
    map = new int[M][N];
    isToEnd = new boolean[M][N];
    
    for(int i=0; i<M; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<N; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    };
    
    Deque<Point> queue = new LinkedList<>();
    queue.add(new Point(0, 0));
    
    DFS(queue);
    
    
    System.out.println(result);
  }
}
