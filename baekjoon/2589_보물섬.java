import java.util.*;
import java.io.*;

class Node {
  public int y;
  public int x;
  public int depth;
  
  public Node() {}
  
  public Node(int y, int x) {
    this(y, x, 1);
  }
  
  public Node(int y, int x, int depth) {
    this.y = y;
    this.x = x;
    this.depth = depth;
  }
  
  public void set(Node n) {
    this.y = n.y;
    this.x = n.x;
    this.depth = n.depth;
  }

  @Override
  public String toString() {
    return "[y=" + y + ", x=" + x + ", depth=" + depth + "]";
  }
}

public class Main {
  static int H;
  static int W;
  static boolean[][] isGround;
  
  static final int[][] deltas = {{1,0},{-1,0},{0,1},{0,-1}};
  
  static boolean inRange(Node n) {
    return 0<=n.x&&n.x<W && 0<=n.y&&n.y<H;
  }
  
  static Node BFS(Node Start) {
    boolean[][] visited = new boolean[H][W];
    
    Queue<Node> queue = new LinkedList<>();
    queue.add(Start);
    visited[Start.y][Start.x] = true;
    Node lastNode = null;
    
    while(!queue.isEmpty()) {
      Node node = queue.poll();
      lastNode = node;
      
      for(int[] d:deltas) {
        Node n = new Node(node.y+d[1], node.x+d[0], node.depth+1);
        if(inRange(n) && isGround[n.y][n.x] && !visited[n.y][n.x] ) {
          queue.add(n);
          visited[n.y][n.x] = true; 
        }
      }
    }

    return lastNode;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    H = Integer.parseInt(st.nextToken());
    W = Integer.parseInt(st.nextToken());
    isGround = new boolean[H][W];
    
    for(int h=0; h<H; h++) {
      char[] cs = br.readLine().toCharArray();
      for(int w=0; w<W; w++) {
        isGround[h][w] = cs[w]=='L';
      }
    }
    
    int maxDistances = 0;
    
    for(int h=0; h<H; h++) {
      for(int w=0; w<W; w++) {
        if(isGround[h][w]) {    
          Node start = new Node(h, w);
          Node end = BFS(start);
//          System.out.println(start.toString()+"->"+end.toString()+":"+(end.depth-1));
          maxDistances = Math.max(maxDistances, end.depth-1);
        }
      }
    }
    
    System.out.println(maxDistances);
  }
}
