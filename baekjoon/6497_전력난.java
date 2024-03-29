import java.util.*;
import java.io.*;

class Edge implements Comparable<Edge>{
  int from;
  int to;
  int cost;
  
  Edge(int from, int to, int cost){
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

  static int findParent(int x){
    if(parents[x]!=x){
      parents[x] = findParent(parents[x]);
    }
    return parents[x];
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    StringBuilder sb = new StringBuilder();

    int m = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(st.nextToken());

    while(!(m==0 && n==0)) {
      parents = new int[m];
      PriorityQueue<Edge> pq = new PriorityQueue<>();
      int totalCost = 0;

      for(int i=0; i<m; i++){
        parents[i] = i;
      }

      for(int i=0; i<n; i++){
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int z = Integer.parseInt(st.nextToken());
        totalCost += z;
        pq.add(new Edge(x, y, z));
      }

      int nodeCnt = 0;
      int cost = 0;

      while(nodeCnt < m-1){
        Edge edge = pq.poll();
        int p1 = findParent(edge.from);
        int p2 = findParent(edge.to);

        if(p1==p2) continue;

        parents[p2] = p1;
        nodeCnt += 1;
        cost += edge.cost;
      }

      sb.append(totalCost-cost).append('\n');

      st = new StringTokenizer(br.readLine());
      m = Integer.parseInt(st.nextToken());
      n = Integer.parseInt(st.nextToken());
    }

    System.out.println(sb.toString());
  }
}
