import java.util.*;
import java.io.*;
import java.awt.Point;

class Main {
  final static int EMPTY = 0;
  final static int APPLE = 1;
  final static int SNAKE = 2;
  
  static int N;
  static int[][] matrix;
  
  public static int[][] deltas = {{1,0},{0,1},{-1,0},{0,-1}}; // 3,9,6,12
  
  public static int getNextDeltaIdx(int idx, char dir) {
    if(dir=='L') {
      idx = (idx==0)? 3 : idx-1;
    }else if(dir=='D') {
      idx = (idx==3)? 0 : idx+1;
    }
    return idx;
  }
  
  public static Point getNextHead(Point head, int dIdx) {
    int dx = deltas[dIdx][0];
    int dy = deltas[dIdx][1];
    return new Point(head.x+dx, head.y+dy);
  }
  
  static void move(Point p, int x, int y) {
    p.x += x;
    p.y += y;
  }
  
  static boolean inRange(Point p) {
    return 0<=p.x&&p.x<N && 0<=p.y&&p.y<N;
  }
  
  static boolean isSnake(Deque<Point> snake, Point head) {
    for(Point p:snake) {
      if(p!=head && p.x==head.x && p.y==head.y) {
        return true;
      }
    }
    return false;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    N = Integer.parseInt(br.readLine());
    int K = Integer.parseInt(br.readLine());
    matrix = new int[N][N];
    
    for(int i=0; i<K; i++) {
      st = new StringTokenizer(br.readLine());
      int y = Integer.parseInt(st.nextToken())-1;
      int x = Integer.parseInt(st.nextToken())-1;
      matrix[y][x] = APPLE;
    }
    
    int L = Integer.parseInt(br.readLine());
    int times[] = new int[L];
    char dirs[] = new char[L];
    
    for(int i=0; i<L; i++) {
      st = new StringTokenizer(br.readLine());
      times[i] = Integer.parseInt(st.nextToken());
      dirs[i] = st.nextToken().charAt(0);
    }
    
    Deque<Point> snake = new LinkedList<>();
    snake.add(new Point(0, 0));
    int dIdx = 0;
    
    int idx = 0;
    int time = 0;
    
    while(true) {
      if(idx<L && time >= times[idx]) {
        dIdx = getNextDeltaIdx(dIdx, dirs[idx]);
        idx++;
      }
      Point head = snake.peekLast();
      Point nextHead = getNextHead(head, dIdx);

      time++;

      if(!(inRange(nextHead) && !isSnake(snake, nextHead))) {
        break;
      }

      if(matrix[head.y][head.x]==APPLE) {
        matrix[head.y][head.x]=EMPTY;
      } else {
        snake.pollFirst();
      }
      
      snake.add(nextHead);
    }
    
    System.out.println(time);
  }
}
