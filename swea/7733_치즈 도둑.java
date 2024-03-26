import java.util.*;
import java.io.*;

class Point {
  int y;
  int x;

  Point(int y, int x) {
    this.y = y;
    this.x = x;
  }
}

class Solution {
  static int[][] deltas = {{1,0},{-1,0},{0,1},{0,-1}};

  static boolean inRange(Point p, int N) {
    return 0 <= p.x && p.x < N && 0 <= p.y && p.y < N;
  }

  static int BFS(int N, int[][] matrix, int day) {
    int sum = 0;
    Queue<Point> queue = new LinkedList<>();
    boolean visited[][] = new boolean[N][N];
    
    for (int i=0; i<N; i++) {
      for (int j=0; j<N; j++) {
        if(matrix[i][j] < day || visited[i][j]) {
          continue;
        }
        queue.add(new Point(i, j));
        visited[i][j] = true;

        while (!queue.isEmpty()) {
          Point p = queue.poll();
          for (int[] d:deltas) {
            Point np = new Point(p.y+d[0], p.x+d[1]);
            
            if (inRange(np, N) && matrix[np.y][np.x]>=day && !visited[np.y][np.x]) {
              queue.add(np);
              visited[np.y][np.x] = true;
            }
          }
        }
        sum++;
      }
    }
    
    return sum;
  }

  public static void main(String args[]) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int tc = Integer.parseInt(br.readLine());
    for (int t=1; t<=tc; t++) {

      int N = Integer.parseInt(br.readLine());
      int[][] matrix = new int[N][N];

      for (int i=0; i<N; i++) {
        st = new StringTokenizer(br.readLine());
        for (int j=0; j<N; j++) {
          matrix[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      int result = 0;
      int day = 1;
      while (day < 100) {
        result = Math.max(result, BFS(N, matrix, day));
        day++;
      }
      
      System.out.printf("#%d %d\n", t, result);
    }
  }
}
