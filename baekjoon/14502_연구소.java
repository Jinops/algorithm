import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int n;
  static int m;
  static int maxSafeAreaCount;

  static Point[] getAdjPoints(int[][] map, Point p) {
    Point[] points = new Point[4];
    if (p.y > 0 && map[p.y-1][p.x] == 0) { // 상
      points[0] = new Point(p.x, p.y - 1);
    }
    if (p.y < n-1 && map[p.y+1][p.x] == 0) { // 하
      points[1] = new Point(p.x, p.y + 1);
    }
    if (p.x > 0 && map[p.y][p.x-1] == 0) { // 좌
      points[2] = new Point(p.x-1, p.y);
    }
    if (p.x < m-1 && map[p.y][p.x+1] == 0) { // 우
      points[3] = new Point(p.x+1, p.y);
    }
    return points;
  }

  static void bfs(int[][] originMap) {
    Queue<Point> queue = new LinkedList<>();
    boolean[][] visited = new boolean[n][m];
    int safeAreaCount = 0;
    int[][] map = new int[n][m];

    for (int i = 0; i < n; i++) {
      map[i] = originMap[i].clone();
    }

    for (int i=0; i<n; i++) {
      for (int j=0; j<m; j++) {
        if (map[i][j] == 2 && !visited[i][j]) {
          queue.add(new Point(j, i));
          while (queue.size() > 0) {
            Point start = queue.poll();
            visited[start.y][start.x] = true;

            Point[] adjPoints = getAdjPoints(map, start);
            for (Point adjPoint : adjPoints) {
              if (adjPoint != null && !visited[adjPoint.y][adjPoint.x]) {
                queue.add(adjPoint);
                visited[adjPoint.y][adjPoint.x] = true;
                map[adjPoint.y][adjPoint.x] = 2;
              }
            }
          }
        }
      }
    }

    for (int i=0; i<n; i++) {
      for (int j=0; j<m; j++) {
        if (map[i][j] == 0) {
          safeAreaCount += 1;
        }
      }
    }
    if (safeAreaCount > maxSafeAreaCount) {
      maxSafeAreaCount = safeAreaCount;
    }
  }

  static void run(int[][] map, int count) {
    if (count == 3) {
      bfs(map);
      return;
    }
    for (int i=0; i<n; i++) {
      for (int j=0; j<m; j++) {
        if (map[i][j] == 0) {
          map[i][j] = 1;
          run(map, count+1);
          map[i][j] = 0;
        }
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    int[][] map = new int[n][m];

    for (int i=0; i<n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j=0; j<m; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    run(map, 0);
    System.out.println(maxSafeAreaCount);
  }
}
