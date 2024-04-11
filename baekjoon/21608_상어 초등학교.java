import java.io.*;
import java.util.*;

// 빈 칸 중에서, 좋아하는 학생 많은 곳으로
// 우선순위 : 인접 칸이 더 많이 빈 곳 -> 행 번호 작은 곳 -> 열 번호 작은 곳 

class Point implements Comparable<Point>{
  int y;
  int x;
  
  public Point(int y, int x) {
    this.y = y;
    this.x = x;
  }

  @Override
  public int compareTo(Point o) {
    if(this.y!=o.y) return this.y-o.y;
    return this.x-o.x;
  }
}

public class Main {
  static int N;
  static int[][] mtx;
  static Map<Integer, int[]> map;
  
  static int[][] ds = {{-1,0},{0,-1},{0,1},{1,0}};
  // (y,x) 0상 1좌 2우 3하 
  
  static boolean inRange(Point p) {
    return 0<=p.y&&p.y<N && 0<=p.x&&p.x<N;
  }
  
  static int getScore(Point p) {
    int cnt = 0;
    for(int[] d:ds) {
      Point np = new Point(p.y+d[0], p.x+d[1]);
      if(inRange(np)) {
        for(int next:map.get(mtx[p.y][p.x])) {
          if(mtx[np.y][np.x]==next) {
            cnt++;
          }
        }
      }
    }
    if(cnt==0) return 0;
    return (int) Math.pow(10, cnt-1);
  }
  
  static List<Point> case1(int key) {
    List<Point>[] list = new ArrayList[5];
    for(int i=0; i<=4; i++) {
      list[i] = new ArrayList<>();
    }
    // 인접한 칸이 i개인 Point 좌표 
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        if(mtx[i][j]==0) {
          int cnt = 0;
          for(int[] d:ds) {
            Point np = new Point(i+d[0], j+d[1]);
            if(!inRange(np)) continue;
            for(int next:map.get(key)) {
              if(mtx[np.y][np.x]==next) {
                cnt+=1;
                break;
              }
            } //주변에 친한 친구가 있으면 cnt++
          }
          list[cnt].add(new Point(i, j));
        }
      }
    }
    
    for(int i=4; i>=0; i--) {
      if(list[i].size()==0) continue;
      return list[i];
    } // 인접한 칸이 가장 많은 칸의 리스트 
    return null;
  }
  
  static List<Point> case2(List<Point> points){
    List<Point>[] list = new ArrayList[5];
    for(int i=0; i<=4; i++) {
      list[i] = new ArrayList<>();
    }
    
    for(Point p:points) {
      int cnt = 0;
      for(int[] d:ds) {
        Point np = new Point(p.y+d[0], p.x+d[1]);
        if(inRange(np) && mtx[np.y][np.x]==0) {
          cnt++;
        }
      }
      list[cnt].add(p);
    }
    
    for(int i=4; i>=0; i--) {
      if(list[i].size()==0) continue;
      return list[i];
    }
    return null;
  }
  
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    N = Integer.parseInt(br.readLine());
    mtx = new int[N][N];
    map = new HashMap<>();
    
    for(int i=0; i<N*N; i++) {
      st = new StringTokenizer(br.readLine());
      int key = Integer.parseInt(st.nextToken());
      int[] value = new int[4];
      for(int j=0; j<4; j++) {
        value[j] = Integer.parseInt(st.nextToken());
      }
      map.put(key, value);
      
      List<Point> points;
      
      points = case1(key);
      
      if(points.size()==1) {
        Point p = points.get(0);
        mtx[p.y][p.x] = key;
        continue;
      }
      
      points = case2(points);
      
      if(points.size()==1) {
        Point p = points.get(0);
        mtx[p.y][p.x] = key;
        continue;
      }
      
      Collections.sort(points); // case3
      Point p = points.get(0);
      mtx[p.y][p.x] = key; 
    }
    
    int result = 0;
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        result += getScore(new Point(i, j));
      }
    }
    
    System.out.println(result);
  }
}
