import java.io.*;
import java.util.*;

// BFS를 통해, 국경선을 열 수 있는 인접한 국가(Point)들을 찾는다 
// 해당 국가들의 Set의 List를 만든다 
// 같은 Set에 속하는 국가들 간 인구를 분배한다

class Point {
  int y;
  int x;
  Point(int y, int x){
    this.y = y;
    this.x = x;
  }
}

public class Main {
  static int[][] ds = {{1,0},{-1,0},{0,1},{0,-1}};
  static int N;
  static int L;
  static int R;
  static int[][] mtx;
  
  static boolean inRange(Point p) {
    return 0<=p.y&&p.y<N && 0<=p.x&&p.x<N;
  }
  
  static boolean isValidDiff(Point p, Point np) {
    int diff = Math.abs(mtx[p.y][p.x]-mtx[np.y][np.x]);
    return L<=diff&&diff<=R;
  }
  
  public static void main(String[] args) throws IOException {
	  BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	  StringTokenizer st = new StringTokenizer(br.readLine());
	  
	  N = Integer.parseInt(st.nextToken());
	  L = Integer.parseInt(st.nextToken());
	  R = Integer.parseInt(st.nextToken());
	  
	  mtx = new int[N][N];
	  for(int i=0; i<N; i++) {
	    st = new StringTokenizer(br.readLine());
	    for(int j=0; j<N; j++) {
	      mtx[i][j] = Integer.parseInt(st.nextToken());
	    }
	  }
	  
	  int result = 0;
	  while(result<=2000) {
	    List<Set<Point>> list = new ArrayList<>();
	    List<Integer> sums = new ArrayList<>();
	    boolean[][] visited = new boolean[N][N];
	    
	    for(int i=0; i<N; i++) {
	      for(int j=0; j<N; j++) {
	        if(!visited[i][j]) {
	          Set<Point> set = new HashSet<>();
	          int sum = 0;
	          
	          Queue<Point> queue = new LinkedList<>();
	          queue.add(new Point(i, j));
	          visited[i][j] = true;
	          
	          while(!queue.isEmpty()) {
	            Point p = queue.poll();
	            set.add(p);
	            sum+=mtx[p.y][p.x];
	            
	            for(int[] d:ds) {
	              Point np = new Point(p.y+d[0], p.x+d[1]);
	              if(inRange(np) && !visited[np.y][np.x] && isValidDiff(p, np)) {
	                visited[np.y][np.x] = true;
	                queue.add(np);
	              }
	            }
	          }
	          
	          if(set.size()>1) {
	            list.add(set);
	            sums.add(sum);
	          }
	        }
	      }
	    }
	    
	    if(list.size()==0) {
	      break;
	    }
	    
	    result += 1;
	    
	    for(int i=0; i<list.size(); i++) {
	      Set<Point> set = list.get(i);
	      int population = sums.get(i) / set.size();
	      for(Point p:set) {
	        mtx[p.y][p.x]= population; 
	      }
	    }
	  }
	  
	  System.out.println(result);
  }
}
