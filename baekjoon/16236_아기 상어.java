import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
  static int N;
  
  static Point cp;
  static int size = 2;
  static int moveCnt;
  
  static int[][] matrix;

  static int[][] deltas = {{0,-1}, {-1,0}, {0,1}, {1,0}};
  
  static boolean inRange(Point p) {
    return 0<=p.x&&p.x<N && 0<=p.y&&p.y<N;
  }

  static boolean isMovable(Point p) {
    return matrix[p.y][p.x] <= size;
  }
  
  static boolean isEatable(Point p) {
    return 0 < matrix[p.y][p.x] && matrix[p.y][p.x] < size;
  }
  
  static boolean isVisited(ArrayList<Point> base, Point target) {
    for(Point p:base) {
      if(p.x==target.x && p.y==target.y) {
        return true;
      }
    }
    return false;
  }
  
  static int[][] getMoveMatrix(Point p){
    int[][] moves = new int[N][N];
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        moves[i][j] = -1;
      }
    }
    moves[p.y][p.x] = 0;
    
    return moves;
  }
  
  private static Point getNextPoint() { // BFS
    Queue<Point> queue = new LinkedList<>();
    queue.add(cp);
    int[][] moves = getMoveMatrix(cp);
    moveCnt = N*N-1; // 움직일 최대 횟수
    List<Point> newPs = new LinkedList<>();
    while(!queue.isEmpty()) {
      Point p = queue.poll();
      if(moves[p.y][p.x]>moveCnt) {
        continue;
      }
      if(isEatable(p)) {
        newPs.add(p);
        moveCnt = moves[p.y][p.x];
        continue;
      }
      for(int[] delta:deltas) {
        Point np = new Point(p.x+delta[0], p.y+delta[1]);
        if(inRange(np) && isMovable(np) && moves[np.y][np.x]==-1) {
          // idx 범위 && 움직일 수 있는 곳 && 방문 안한 곳
          queue.add(np);
          moves[np.y][np.x] = moves[p.y][p.x]+1; 
        }
      }
    }
    
    if(newPs.size()==0) {
      return cp;
    }
    if(newPs.size()==1) {
      return newPs.get(0);
    }
    Point np = newPs.get(0);
    for(Point newP:newPs) {
      if(newP.y<np.y) {
        np = newP;
      } else if(newP.y == np.y && newP.x < newP.x) {
        np = newP;
      }
    }
    return np;
    
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    N = Integer.parseInt(br.readLine());
    matrix = new int[N][N];
    
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<N; j++) {
        matrix[i][j] = Integer.parseInt(st.nextToken());
        if(matrix[i][j]==9) {
          cp = new Point(j, i);
          matrix[i][j] = 0;
        }
      }
    }
    
    int result = 0;
    int fishCnt = 0;
    while(true) {
      Point np = getNextPoint();
      if(cp==np) {
        break;
      } else {
        cp = np;
        matrix[cp.y][cp.x] = 0;
        fishCnt++;
        
        if(fishCnt==size) {
          fishCnt = 0;
          size++;
        }
        result += moveCnt;
      }
    }
    
    System.out.println(result);
  }
}
