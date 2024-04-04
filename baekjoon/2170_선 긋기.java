import java.io.*;
import java.util.*;

class Line implements Comparable<Line>{
  int x;
  int y;
  
  public Line(int x, int y) {
    super();
    this.x = x;
    this.y = y;
  }

  @Override
  public int compareTo(Line o) {
      return this.x - o.x;
  }
}

class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int N = Integer.parseInt(br.readLine());
    PriorityQueue<Line> pq = new PriorityQueue<>();
    
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      pq.add(new Line(x, y));
    }
    
    int result = 0;
    
    while(!pq.isEmpty()) {
      Line l = pq.poll();
      int x = l.x;
      int y = l.y;
      
      while(!pq.isEmpty() && y >= pq.peek().x) {
        y = Math.max(y, pq.poll().y);
      }
      
      result += y-x;
    }
    
    System.out.println(result);
  }
}
