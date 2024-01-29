import java.awt.Point;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static Point[] getAdjNodes(int[][] map, Point p, int min) {
    Point[] points = new Point[4];
    
    if(p.y > 0 && map[p.y-1][p.x] > min) {
      points[0] = new Point(p.x, p.y-1);
    }
    if(p.y < map.length-1 && map[p.y+1][p.x] > min) {
      points[1] = new Point(p.x, p.y+1);
    }
    if(p.x > 0 && map[p.y][p.x-1] > min) {
      points[2] = new Point(p.x-1, p.y);
    }
    if(p.x < map[0].length-1 && map[p.y][p.x+1] > min) {
      points[3] = new Point(p.x+1, p.y);
    }
    
    return points;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int N = Integer.parseInt(br.readLine());
    int[][] originMap = new int[N][N];
    int maxHeight = 0;
    
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<N; j++) {
        originMap[i][j] = Integer.parseInt(st.nextToken());
        maxHeight = (originMap[i][j]>maxHeight)? originMap[i][j] : maxHeight;
      }
    }
    
    int[][] map;
    Queue<Point> queue;
    int count;
    int maxCount = 1;
    
    for(int sink=1; sink<maxHeight; sink++) { // n 이하가 잠겼을 때
      map = new int[N][N];
      for(int i=0; i<N; i++) {
        map[i] = originMap[i].clone();
      }
      queue = new LinkedList<Point>();
      count = 0;
      for(int i=0; i<N; i++) {
        for(int j=0; j<N; j++) {
          if(map[i][j] > sink) {
            queue.add(new Point(j, i));
            map[i][j] = 0;
            count += 1;
            
            while(!queue.isEmpty()) {
              Point start = queue.poll();
                for(Point node:getAdjNodes(map, start, sink)) {
                  if(node!=null) {
                    queue.add(node);
                    map[node.y][node.x] = 0; 
                  }
              }
            }
          }
        }
      }
      if(count > maxCount) {
        maxCount = count;
      }
    }
    System.out.println(maxCount);
  }
}
