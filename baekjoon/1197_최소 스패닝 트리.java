import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge>{
  int from;
  int to;
  int cost;
  
  public Edge(int from, int to, int cost) {
    super();
    this.from = from;
    this.to = to;
    this.cost = cost;
  }

  @Override
  public int compareTo(Edge o) {
    return this.cost - o.cost;
  }
}

class Main {
  
  static int[] parents;
  static int findParent(int x) {
    if(parents[x]!=x) {
      parents[x] = findParent(parents[x]);
    }
    return parents[x];
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int V = Integer.parseInt(st.nextToken());
    int E = Integer.parseInt(st.nextToken());
    
    parents = new int[V+1];
    PriorityQueue<Edge> edges = new PriorityQueue<>();
    
    for(int i=1; i<=V; i++) {
      parents[i] = i;
    }
    
    for(int i=0; i<E; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      edges.add(new Edge(from, to, cost));
    }
    
    int pick = 0;
    int cost = 0;
    
    while(pick < V-1) {
      Edge e = edges.poll();
      
      int fromP = findParent(e.from);
      int toP = findParent(e.to);
      if(fromP==toP) continue;
      
      parents[toP] = fromP;
      pick++;
      cost+=e.cost;
    }
    
    System.out.println(cost);
  }
}
