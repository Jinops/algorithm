import java.util.*;
import java.awt.Point;
import java.io.*;

public class Main {
  static int Y;
  static int X;
  
  static int[][] deltas = {{1,0},{-1,0},{0,1},{0,-1}};
  
  static Point[] getNearPoints(Point p, boolean[][] visited) {
    ArrayList<Point> points = new ArrayList<>();
    for(int[] delta:deltas) {
      int newX = p.x+delta[0];
      int newY = p.y+delta[1];
      if(0<=newX&&newX<X && 0<=newY&&newY<Y && !visited[newY][newX]) {
        points.add(new Point(newX, newY));
      }
    }
    return points.toArray(new Point[points.size()]);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    Y = Integer.parseInt(st.nextToken());
    X = Integer.parseInt(st.nextToken());
    int[][] map = new int[Y][X];
    
    for(int i=0; i<Y; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<X; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    
    int curCheeseCnt = 1; // 임시값
    int meltingCheeseCnt = 0;
    int cnt = 0;
    Point startP = new Point(0, 0);
    
    while(curCheeseCnt>0) {
      Queue<Point> queue = new LinkedList<>();
      boolean[][] visited = new boolean[Y][X];
      queue.add(startP);
      visited[0][0] = true;
      
      while(!queue.isEmpty()) {
        Point fromP = queue.poll();
              
        for(Point p:getNearPoints(fromP, visited)) { // queue에 넣기 전에 visited 체크
//          System.out.println(fromP.y+","+fromP.x+"->"+p.y+","+p.x);
          if(map[p.y][p.x]==0) {
            queue.add(p);
            visited[p.y][p.x] = true;
          } else if (map[p.y][p.x]==1) {
            map[p.y][p.x] = 2;
          }
        }
      }
      
      curCheeseCnt = 0;
      meltingCheeseCnt = 0;
      
      for(int i=0; i<Y; i++) {
        for(int j=0; j<X; j++) {
          if(map[i][j]==1) {
            curCheeseCnt += 1;
          } else if(map[i][j]==2) {
            map[i][j] = 0;
            meltingCheeseCnt += 1;
          }
        }
      }
      
      if(curCheeseCnt!=0 || meltingCheeseCnt !=0) { // 애초에 치즈 없는 경우 제외
        cnt += 1;
      }
    }
  
    System.out.println(cnt);
    System.out.print(meltingCheeseCnt);
  }
}
