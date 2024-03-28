// 프림+PQ 풀이
import java.util.*;
import java.io.*;

class Node{
  int idx;
  int x;
  int y;
  
  public Node(int idx, int x, int y) {
    this.idx = idx;
    this.x = x;
    this.y = y;
  }
}

class Edge implements Comparable<Edge>{
  Node to;
  double cost;
  
  public Edge(Node from, Node to, double E) {
    this.to = to;
    this.cost = E*(Math.pow(from.x-to.x, 2)+Math.pow(from.y-to.y, 2));
  }

  @Override
  public int compareTo(Edge o) {
    if(cost==o.cost) return 0;
    return Double.compare(cost, o.cost);
  }
}

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    int T = Integer.parseInt(br.readLine());
    for(int t=1; t<=T; t++) {
      int N = Integer.parseInt(br.readLine()); // 섬의 개수
      
      Node[] nodes = new Node[N];
      
      String[] xs = br.readLine().split(" ");
      String[] ys = br.readLine().split(" ");
      
      for(int i=0; i<N; i++) {
        int x = Integer.parseInt(xs[i]);
        int y = Integer.parseInt(ys[i]);
        nodes[i] = new Node(i, x, y);
      }

      double E = Double.parseDouble(br.readLine());
      
      List<Edge>[] adjEdges = new ArrayList[N];
      // idx: from node's idx 
      for(int i=0; i<adjEdges.length; i++) {
        adjEdges[i] = new ArrayList<>();
      }
      
      for(int i=0; i<N; i++) {
        for(int j=i+1; j<N; j++) {
          Node from = nodes[i];
          Node to = nodes[j];
          adjEdges[from.idx].add(new Edge(from, to, E));
          adjEdges[to.idx].add(new Edge(to, from, E));
        }
      }

      double result = 0;
      
      PriorityQueue<Edge> pq = new PriorityQueue<>();
      boolean[] visited = new boolean[N];
      
      pq.addAll(adjEdges[0]);
      visited[0] = true;
      int pick = 1;
      
      while(pick < N) {
        Edge adjEdge = pq.poll();
        if(visited[adjEdge.to.idx]) {
          continue;
        }
        result += adjEdge.cost;
        pq.addAll(adjEdges[adjEdge.to.idx]);
        visited[adjEdge.to.idx] = true;
        pick += 1;
      }
      
      System.out.printf("#%d %d\n", t, Math.round(result));
    }
  }
}
