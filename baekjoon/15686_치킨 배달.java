import java.util.*;
import java.io.*;

// 치킨집의 위치들을 저장한다
// 모든 집에 대해, 모든 치킨집으로 가는 거리를 계산한다.
// M개의 치킨집을 고르며, 거리가 최소가 되는 조합을 구한다.

class Point {
  int y;
  int x;
  int dist;

  Point(int y, int x){
    this.y = y;
    this.x = x;
  }

  Point(int y, int x, int dist){
    this.y = y;
    this.x = x;
    this.dist = dist;
  }
}

class Main {
  static int N;
  static int M;
  static int[][] dists;
  static int result = Integer.MAX_VALUE;

  static List<Point> houses;
  static List<Point> stores;

  final static int[][] ds = {{0,1},{1,0},{0,-1},{-1,0}};

  static boolean inRange(Point p){
    return 0<=p.y&&p.y<N && 0<=p.x&&p.x<N;
  }

  static void pick(int i, int cnt, boolean[] isPick){
    if(cnt==M){
      int sum = 0;
      for(int k=0; k<houses.size(); k++){
        int dist = Integer.MAX_VALUE;
        for(int j=0; j<stores.size(); j++){
          if(isPick[j]){
            dist = Math.min(dist, dists[k][j]);
          }
        }
        sum += dist;
      }
      result = Math.min(result, sum);
      return;
    }

    if(i==stores.size()){
      return;
    }

    if(cnt<M){
      isPick[i] = true;
      pick(i+1, cnt+1, isPick);
      isPick[i] = false;
    }
    pick(i+1, cnt, isPick);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken()); // 크기
    M = Integer.parseInt(st.nextToken()); // 최대 M개
    int[][] mtx = new int[N][N];

    houses = new ArrayList<>();
    stores = new ArrayList<>();

    for(int i=0; i<N; i++){
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<N; j++){
        mtx[i][j] = Integer.parseInt(st.nextToken());
        if(mtx[i][j]==1){
          houses.add(new Point(i, j));
        } else if(mtx[i][j]==2){
          stores.add(new Point(i, j));
        }
      }
    }

    dists = new int[houses.size()][stores.size()];
    // 집으로부터 가게로 가는 거리 합
    for(int i=0; i<houses.size(); i++){
      Queue<Point> queue = new LinkedList<>();
      boolean[][] visited = new boolean[N][N];
      Point house = houses.get(i);

      queue.add(house);
      visited[house.y][house.x] = true;

      while(!queue.isEmpty()){
        Point p = queue.poll();

        if(mtx[p.y][p.x]==2){
          for(int j=0; j<stores.size(); j++){
            Point store = stores.get(j);
            if(p.y==store.y && p.x==store.x){
              dists[i][j] = p.dist;
              break;
            }
          }
        }

        for(int[] d:ds){
          Point np = new Point(p.y+d[0], p.x+d[1], p.dist+1);
          if(inRange(np) && !visited[np.y][np.x]){
            visited[np.y][np.x] = true;
            queue.add(np);
          }
        }
      }
    }

    pick(0, 0, new boolean[stores.size()]);
    System.out.println(result);
  }
}
