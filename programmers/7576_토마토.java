import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int X;
  static int Y;
  
  static int getResult(int[][] cost) {
    int result = 0;
    for(int i=0; i<Y; i++) {
      for(int j=0; j<X; j++) {
        if(cost[i][j] == Integer.MAX_VALUE) {
          return -1;
        }
        if(cost[i][j] > result) {
          result = cost[i][j];
        }
      }
    }
    return result;
  }
  
  static Point[] getAdjPoints(int[][] map, Point p) {
    Point[] adjPoints = new Point[4];
    if(p.y > 0 && map[p.y-1][p.x] == 0) { // 상
      adjPoints[0] = new Point(p.x, p.y-1);
    }
    if(p.y < Y-1 && map[p.y+1][p.x] == 0) { // 하
      adjPoints[1] = new Point(p.x, p.y+1);
    }
    if(p.x > 0 && map[p.y][p.x-1] == 0) { // 좌
      adjPoints[2] = new Point(p.x-1, p.y);
    }
    if(p.x < X-1 && map[p.y][p.x+1] == 0) { // 우
      adjPoints[3] = new Point(p.x+1, p.y);
    }
    return adjPoints;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    X = Integer.parseInt(st.nextToken());
    Y = Integer.parseInt(st.nextToken());
    
    int[][] map = new int[Y][X];
    int[][] cost = new int[Y][X];
    Queue<Point> queue = new LinkedList<>();
    for(int i=0; i<Y; i++) {
       st = new StringTokenizer(br.readLine());
      for(int j=0; j<X; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        if(map[i][j]==0) {
          cost[i][j] = Integer.MAX_VALUE;
        } else if(map[i][j]==1) {
          cost[i][j] = 0;
          queue.add(new Point(j, i));
        }
      }
    }
    
    while(!queue.isEmpty()) {
      Point startP = queue.poll();
      for(Point p:getAdjPoints(map, startP)) {
        if(p==null) {
          continue;
        }
        if(cost[p.y][p.x] > cost[startP.y][startP.x] + 1) {
          cost[p.y][p.x] = cost[startP.y][startP.x] + 1; 
        }
        map[p.y][p.x]= 1;
        queue.add(p);
      }
    }
    
    System.out.println(getResult(cost));
  }
}
