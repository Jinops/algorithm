import java.util.*;
import java.io.*;
 
// 모든 사람이 모든 계단에 대해 도달할 수 있는 최소 수를 구한다
// (도착시점+1+길이) 동안은 계단을 차지한다
// 계단을 차지한 사람이 3명이면, 계단을 이용할 수 없다
// 2 계단에 대해 사람들을 분배하여, 어느 경우가 가장 빠른지 계산한다.
 
class Point{
  int y;
  int x;
  int depth;
   
  Point(int y, int x){
    this(y, x, 0);
  }
   
  Point(int y, int x, int depth){
    this.y = y;
    this.x = x;
    this.depth = depth;
  }
}
 
public class Solution {
  static int[][] mtx;
  static List<Point> people;
  static List<Point> stairs;
  static int[][] depths;
  static int result;
   
  final static int[][] deltas = {{1,0},{-1,0},{0,1},{0,-1}};
   
  static boolean inRange(Point p, int N) {
    return 0<=p.y&&p.y<N && 0<=p.x&&p.x<N;
  }
   
  static int calculate(int stair, List<Integer> arrivals) {
    if(arrivals.size()==0) {
      return 0;
    }
     
    Queue<Integer> queue = new LinkedList<>();
    int time = arrivals.get(0)+1; // 가장 빠른 시간으로 초기 시간 설정
    int idx = 0;
     
    while(idx < arrivals.size()) {
      while(queue.peek()!=null&& time >= queue.peek()+stair) { 
        // 큐에 나갈 수 있는 것이 있으면
        queue.poll(); // 빼준다
      }
      while(idx < arrivals.size() && queue.size()<3 && time > arrivals.get(idx)) {
        // 계단 용량 남아있고, 다음 사람이 계단 탈 수 있으면
        queue.add(time); // 현재 시간에 들어갔다는 정보를 저장한다.
        idx++;
      }
      time += 1;
    }
     
    while(!queue.isEmpty()) {
      // 큐에 남아있다면, 마지막 시간에 대해 계산한다.
      time = queue.poll() + stair;
    }
     
    return time;
  }
   
  static void pick(int idx, boolean[] isFirst) {
    if(idx==people.size()) {
      List<Integer> arrivals1 = new ArrayList<>();
      List<Integer> arrivals2 = new ArrayList<>();
       
      for(int i=0; i<isFirst.length; i++) {
        if(isFirst[i]) arrivals1.add(depths[i][0]);
        else arrivals2.add(depths[i][1]);
      }
      arrivals1.sort(null);
      arrivals2.sort(null);
       
      int stair1 = mtx[stairs.get(0).y][stairs.get(0).x];
      int stair2 = mtx[stairs.get(1).y][stairs.get(1).x];
       
      int result1 = calculate(stair1, arrivals1);
      int result2 = calculate(stair2, arrivals2);
       
      result = Math.min(result, Math.max(result1, result2));
      return;
    }
     
    isFirst[idx] = true;
    pick(idx+1, isFirst);
    isFirst[idx] = false;
    pick(idx+1, isFirst);
  }
   
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
     
    int T = Integer.parseInt(br.readLine());
    for (int t = 1; t <= T; t++) {
       
      int N = Integer.parseInt(br.readLine());
      mtx = new int[N][N];
       
      people = new ArrayList<>();
      stairs = new ArrayList<>();
       
      for(int i=0; i<N; i++) {
        st = new StringTokenizer(br.readLine());
        for(int j=0; j<N; j++) {
          mtx[i][j] = Integer.parseInt(st.nextToken());
          if(mtx[i][j] == 1) {
            people.add(new Point(i,j, 0));
          } else if(mtx[i][j] > 1) {
            stairs.add(new Point(i,j));
          }
        }
      }
       
      depths = new int[people.size()][2]; // 계단 2개 도착에 대한 detph
       
      for(int i=0; i<2; i++) {
        Point stair = stairs.get(i);
         
        for(int j=0; j<people.size(); j++) {
          Point person = people.get(j);
           
          Queue<Point> queue = new LinkedList<>();
          boolean[][] visited = new boolean[N][N];
          queue.add(person);
          visited[person.y][person.x] = true; 
           
          while(!queue.isEmpty()) {
            Point p = queue.poll();
            if(p.y==stair.y && p.x==stair.x) {
              depths[j][i] = p.depth;
              break;
            }
            for(int[] d:deltas) {
              Point np = new Point(p.y+d[0], p.x+d[1], p.depth+1);
              if(inRange(np, N) && !visited[np.y][np.x]) {
                queue.add(np);
                visited[np.y][np.x] = true; 
              }
            }
          }
        }
      }
       
      result = Integer.MAX_VALUE;
      pick(0, new boolean[people.size()]);
       
      System.out.printf("#%d %d\n", t, result);
    }
  }
}
