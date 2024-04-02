// TODO: 3% 메모리 초과
import java.util.*;
import java.io.*;

class Node {
  int idx;
  int x;
  int y;
  int z;

  Node(int idx, int x, int y, int z){
    this.idx = idx;
    this.x = x;
    this.y = y;
    this.z = z;
  }
}

class Edge implements Comparable<Edge> {
  Node from;
  Node to;
  int cost;

  Edge(Node from, Node to){
    this.from = from;
    this.to = to;
    setCost();
  }

  private void setCost(){
    int costX = Math.abs(from.x-to.x);
    int costY = Math.abs(from.y-to.y);
    int costZ = Math.abs(from.z-to.z);
    this.cost = Math.min(Math.min(costX, costY), costZ);
  }

  @Override
  public int compareTo(Edge o) {
    return this.cost - o.cost;
  }
}

class Main {
  static int[] parents;
  static int findParents(int x){
    if(parents[x]!=x){
      parents[x] = findParents(parents[x]);
    }
    return parents[x];
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int N = Integer.parseInt(br.readLine());

    Node[] nodes = new Node[N];
    parents = new int[N];
    PriorityQueue<Edge> edges = new PriorityQueue<>();

    for(int i=0; i<N; i++){
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int z = Integer.parseInt(st.nextToken());
      nodes[i] = new Node(i, x, y, z);
      parents[i] = i;
    }

    for(int i=0; i<N; i++){
      for(int j=i+1; j<N; j++){
        Node from = nodes[i];
        Node to = nodes[j];
        edges.add(new Edge(from, to));
      }
    }
    
    int edgeCnt = 0;
    int cost = 0;

    while(edgeCnt < N-1){
      Edge edge = edges.poll();

      int p1 = findParents(edge.from.idx);
      int p2 = findParents(edge.to.idx);
      if(p1==p2) continue;

      parents[p2] = p1;
      edgeCnt += 1;
      cost += edge.cost;
    }

    System.out.println(cost);
  }
}
