import java.util.*;
import java.io.*;

class Node {
  int n;
  int depth;
  
  public Node(int n, int depth) {
    super();
    this.n = n;
    this.depth = depth;
  }
}

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int T = 10;
    for(int t=1; t<=T; t++) {
      
      st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int start = Integer.parseInt(st.nextToken());
      
      boolean[][] edges = new boolean[101][101];
      boolean[] visited = new boolean[101];
      List<Integer>[] depths = new List[N];
      for(int i=0; i<N; i++) {
        depths[i] = new ArrayList<>();
      }
      
      st = new StringTokenizer(br.readLine());
      for(int n=0; n<N/2; n++) {
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        edges[from][to] = true;
      }
      
      Queue<Node> queue = new LinkedList<>();
      queue.add(new Node(start, 0));
      visited[start] = true;
      
      int maxDepth = 0;
      
      while(!queue.isEmpty()) {
        Node node = queue.poll();
        depths[node.depth].add(node.n);
        maxDepth = node.depth;
        for(int i=1; i<=100; i++) {
          if(edges[node.n][i] && !visited[i]) {
            queue.add(new Node(i, node.depth+1));
            visited[i] = true;
          }
        }
      }
      
      depths[maxDepth].sort(Collections.reverseOrder());
      System.out.printf("#%d %d\n", t, depths[maxDepth].get(0));
    }
  }
}
