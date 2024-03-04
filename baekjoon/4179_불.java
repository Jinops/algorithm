import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
  static int[][] deltas = {{1,0},{-1,0},{0,1},{0,-1}};
  
  static int R;
  static int C;
  
  static boolean inRange(Point p) {
    return 0<=p.y && p.y<R && 0<=p.x && p.x<C;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    char[][] matrix = new char[R][C];
    int[][] distances = new int[R][C];
    int[][] fDistances = new int[R][C];

    Queue<Point> queue = new LinkedList<>();
    Queue<Point> fQueue = new LinkedList<>();
    
    for(int i=0; i<R; i++) {
      String str = br.readLine();
      for(int j=0; j<C; j++) {
        matrix[i][j] = str.charAt(j);
        distances[i][j] = -1;
        fDistances[i][j] = -1;
        
        if(matrix[i][j]=='J') {
          queue.add(new Point(j, i));
          distances[i][j] = 0;
        } else if(matrix[i][j]=='F') {
          fDistances[i][j] = 0;
          fQueue.add(new Point(j, i));
        }
      }
    }
    
    int result = -1;
    
    while(!fQueue.isEmpty()) {
      Point p = fQueue.poll();
      for(int[] delta:deltas) {
        Point np = new Point(p.x+delta[0], p.y+delta[1]);
        if(inRange(np) && matrix[np.y][np.x]!='#' && fDistances[np.y][np.x]==-1) {
          fQueue.add(np);
          fDistances[np.y][np.x] = fDistances[p.y][p.x]+ 1;  
        }
      }
    }

    
    while(!queue.isEmpty() && result==-1) {
      Point p = queue.poll();
      for(int[] delta:deltas) {
        Point np = new Point(p.x+delta[0], p.y+delta[1]);
        int nDistance = distances[p.y][p.x] + 1;
        if(!inRange(np)) {
          result = nDistance;
          break;
        }
        if(matrix[np.y][np.x]=='.' && distances[np.y][np.x]==-1 
            && (fDistances[np.y][np.x] == -1 || nDistance < fDistances[np.y][np.x])) {
          queue.add(np);
          distances[np.y][np.x] = nDistance; 
        }
      }
    }

    System.out.println(result!=-1?result:"IMPOSSIBLE");
  }
}
