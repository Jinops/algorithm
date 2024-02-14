import java.io.*;
import java.util.*;

class Point {
  int x;
  int y;
  int cnt;
  Point(){}
  Point(int x, int y){
    this(x, y, 0);
  }
  Point(int x, int y, int cnt){
    this.x = x;
    this.y = y;
    this.cnt = cnt;
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int tc = Integer.parseInt(br.readLine());
    for(int i=0; i<tc; i++) {
      
      int l = Integer.parseInt(br.readLine());
      boolean[][] visited = new boolean[l][l];
      st = new StringTokenizer(br.readLine());
      Point cur = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
      st = new StringTokenizer(br.readLine());
      Point target = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

      int[][] deltas = {{-2,-1},{-1,-2},{1,-2},{2,-1},{-2,1},{-1,2},{1,2},{2,1}};
      
      Queue<Point> queue = new LinkedList<>();
      queue.add(cur);
      visited[cur.y][cur.x] = true;
      
      while(!queue.isEmpty()) {
        Point p = queue.poll();
        if(p.x==target.x && p.y==target.y) {
          System.out.println(p.cnt);
          break;
        }
        for(int[] delta:deltas) {
          int nx = p.x+delta[0];
          int ny = p.y+delta[1];
          if(0<=nx&&nx<l && 0<=ny&&ny<l && !visited[ny][nx]) {
            visited[ny][nx] = true;
            queue.add(new Point(nx, ny, p.cnt+1));
          }
        }
      }
    }
  }
}
