// TODO

import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
  static int N;
  
  static Point cp;
  static int size = 2;
  
  static int[][] matrix;
  static HashMap<Integer, Integer> fishCnt = new HashMap<>();
  

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
  
  static void eat(Point p) {
    int fishSize = matrix[p.y][p.x];
    matrix[p.y][p.x] = 0;
    fishCnt.put(fishSize, fishCnt.get(fishSize)-1);
    size++;
  }
  
  static int[][] getNewDepth(Point p, int pDepth) {
    int[][] depth = new int[N][N];
    depth[p.y][p.x] = pDepth;
    return depth;
  }
  
  private static int move() { // BFS
    Queue<Point> queue = new LinkedList<>();
    queue.add(cp);
    int[][] depth = getNewDepth(cp, 1);
    while(true) {
      Point p = queue.poll();
      if(isEatable(p)) {
        eat(p);
        depth = getNewDepth(p, 1);
      }
      for(int[]a:matrix) {
        System.out.println(Arrays.toString(a));
      }
      System.out.println();
      if(!isValid()) {
        return depth[p.y][p.x];
      }
      for(int[] delta:deltas) {
        Point np = new Point(p.x+delta[0], p.y+delta[1]);
        if(inRange(np) && depth[np.y][np.x]==0 && isMovable(np)) {
          queue.add(np);
          depth[np.y][np.x] += depth[p.y][p.x]; 
        }
      }
      if(queue.isEmpty()) {
        return depth[p.y][p.x];
      }
    }
  }

  private static boolean isValid() {
    for(int fishSize=1; fishSize<Math.min(size, 6); fishSize++) {
      if(fishCnt.get(fishSize)>0) {
        return true;
      }
    }
    return false;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    N = Integer.parseInt(br.readLine());
    matrix = new int[N][N];
    
    for(int size=1; size<=6; size++) {
      fishCnt.put(size, 0);
    }
    
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<N; j++) {
        matrix[i][j] = Integer.parseInt(st.nextToken());
        if(matrix[i][j]==9) {
          cp = new Point(j, i);
          matrix[i][j] = 0;
        } else if(matrix[i][j]>0) {
          fishCnt.put(matrix[i][j], fishCnt.get(matrix[i][j])+1);
        }
      }
    }
    
    
    System.out.println(move());
    
  }

}
