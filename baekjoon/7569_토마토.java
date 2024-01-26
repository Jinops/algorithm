import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
  int x;
  int y;
  int z;

  public Point(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
}

public class Main {
  static int X;
  static int Y;
  static int Z;

  static int getResult(int[][][] cost) {
    int result = 0;
    for (int z = 0; z < Z; z++) {
      for (int y = 0; y < Y; y++) {
        for (int x = 0; x < X; x++) {
          if (cost[z][y][x] == Integer.MAX_VALUE) {
            return -1;
          }
          if (cost[z][y][x] > result) {
            result = cost[z][y][x];
          }
        }
      }
    }

    return result;
  }

  static Point[] getAdjPoints(int[][][] map, Point p) {
    Point[] adjPoints = new Point[6];
    if (p.y > 0 && map[p.z][p.y - 1][p.x] == 0) { // 상
      adjPoints[0] = new Point(p.x, p.y - 1, p.z);
    }
    if (p.y < Y - 1 && map[p.z][p.y + 1][p.x] == 0) { // 하
      adjPoints[1] = new Point(p.x, p.y + 1, p.z);
    }
    if (p.x > 0 && map[p.z][p.y][p.x - 1] == 0) { // 좌
      adjPoints[2] = new Point(p.x - 1, p.y, p.z);
    }
    if (p.x < X - 1 && map[p.z][p.y][p.x + 1] == 0) { // 우
      adjPoints[3] = new Point(p.x + 1, p.y, p.z);
    }
    if (p.z > 0 && map[p.z - 1][p.y][p.x] == 0) { // 윗층
      adjPoints[4] = new Point(p.x, p.y, p.z - 1);
    }
    if (p.z < Z - 1 && map[p.z + 1][p.y][p.x] == 0) { // 아래층
      adjPoints[5] = new Point(p.x, p.y, p.z + 1);
    }
    return adjPoints;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    X = Integer.parseInt(st.nextToken());
    Y = Integer.parseInt(st.nextToken());
    Z = Integer.parseInt(st.nextToken());
    
    int[][][] map = new int[Z][Y][X];
    int[][][] cost = new int[Z][Y][X];
    Queue<Point> queue = new LinkedList<>();
    for(int z=0; z<Z; z++) {
      for(int y=0; y<Y; y++) {
        st = new StringTokenizer(br.readLine());
       for(int x=0; x<X; x++) {
         map[z][y][x] = Integer.parseInt(st.nextToken());
         if(map[z][y][x]==0) {
           cost[z][y][x] = Integer.MAX_VALUE;
         } else if(map[z][y][x]==1) {
           cost[z][y][x] = 0;
           queue.add(new Point(x, y, z));
         }
       }
      }
    }
    
    while(!queue.isEmpty()) {
      Point startP = queue.poll();
      for(Point p:getAdjPoints(map, startP)) {
        if(p==null) {
          continue;
        }
        if(cost[p.z][p.y][p.x] > cost[startP.z][startP.y][startP.x] + 1) {
          cost[p.z][p.y][p.x] = cost[startP.z][startP.y][startP.x] + 1; 
        }
        map[p.z][p.y][p.x]= 1;
        queue.add(p);
      }
    }
    
    System.out.println(getResult(cost));
  }
}
