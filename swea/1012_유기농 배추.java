import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static Point[] getAdjNodes(int[][] map, Point p) {
    Point[] points = new Point[4];
    
    if(p.y > 0 && map[p.y-1][p.x] == 1) {
      points[0] = new Point(p.x, p.y-1);
    }
    if(p.y < map.length-1 && map[p.y+1][p.x] == 1) {
      points[1] = new Point(p.x, p.y+1);
    }
    if(p.x > 0 && map[p.y][p.x-1] == 1) {
      points[2] = new Point(p.x-1, p.y);
    }
    if(p.x < map[0].length-1 && map[p.y][p.x+1] == 1) {
      points[3] = new Point(p.x+1, p.y);
    }
    
    return points;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int T = Integer.parseInt(br.readLine());
    
    for(int t=0; t<T; t++) {
      st = new StringTokenizer(br.readLine());
      int X = Integer.parseInt(st.nextToken());
      int Y =Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());
      
      int[][] map = new int[Y][X];
      for(int i=0; i<k; i++) {
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        map[y][x] = 1;
      }
      
      int count = 0;
      for(int y=0; y<Y; y++) {
        for(int x=0; x<X; x++) {
          if(map[y][x]==1) {
            count += 1;
            Queue<Point> queue = new LinkedList<>();
            boolean[][] visited = new boolean[Y][X];
            queue.add(new Point(x, y));
            visited[y][x] = true;
            
            while(!queue.isEmpty()) {
              Point start = queue.poll();
              map[start.y][start.x] = 2;
              
              for(Point adj:getAdjNodes(map, start)) {
                if(adj!=null && !visited[adj.y][adj.x]) {
                  queue.add(adj);
                  visited[adj.y][adj.x] = true;
                }
              }
            }
          }
        }
      }
      System.out.println(count);
    }
  }
}
