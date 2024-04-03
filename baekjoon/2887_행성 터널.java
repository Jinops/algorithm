// 1)
// 행성을 연결하는 모든 간선을 고려할 경우,
// N*(N-1)/2 개 간선이 생성.
// N은 최대 100,000이므로 N^2=10^10(100억)개 생성 => 불가.

// 2)
// 비용이 x, y, z 값의 차이인 것에 집중한다.
// 만약 최소비용을 모두 x값 차이에서 찾을 수 있다면,
// Node에서 x값이 작은 순서대로 오름차순 정렬하여 i->(i+1) 간선을 선택한다면
// N-1개의 간선 이외에는 필요가 없다.
// 마찬가지로, y와 z 기준으로만 다 쓴다 해도, 최대 N-1개의 간선만 요구된다.
// 즉, x,y,z 기준으로 정렬하여 각각 최대인 N-1개 씩만을 사용할 때
// 최소 3*(N-1)개의 Edge를 알고 있으면, 결과를 도출할 수 있다.

// +)
// x(y,z)로 오름차순 정렬한 node들 간의 i->(i+1) edge를 추가한다는 것은
// 실제 최소 거리를 보장하는 edge를 만드는 것이 아닌, 
// 각 간선을 모두 연결하는 최소거리가 x를 기준으로 존재한다는 가정으로 이루어진다.
// y, z 또한 마찬가지로 하면, 최종적으로 점 p1->p2를 연결하는 간선이 중복된다.
// 그러나 pq에 넣으면서 최소 cost를 기준으로 재정렬하여 넣고,
// 후에 union-find 과정으로 부모가 같다면 사용하지 않기 때문에 중복이 발생하진 않는다.

// 정리)
// (1)은 가능한 모든 간선을 다 만든 뒤 시행하여 N(N-1)/2 개를 만들고,
// (2)는 어차피 cost는 x,y,z 각 차이 중 최솟값이기 때문에
// 한쪽이 모두 최소라 가정하고 (N-1)*3개의 간선을 만들어 시행한다.

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

  Edge(Node from, Node to, int cost){
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

    Arrays.sort(nodes, (o1, o2) -> o1.x-o2.x);
    for(int i=0; i<N-1; i++){
      Node from = nodes[i];
      Node to = nodes[i+1];
      int cost = Math.abs(from.x-to.x);
      edges.add(new Edge(from, to, cost));
    }

    Arrays.sort(nodes, (o1, o2) -> o1.y-o2.y);
    for(int i=0; i<N-1; i++){
      Node from = nodes[i];
      Node to = nodes[i+1];
      int cost = Math.abs(from.y-to.y);
      edges.add(new Edge(from, to, cost));
    }

    Arrays.sort(nodes, (o1, o2) -> o1.z-o2.z);
    for(int i=0; i<N-1; i++){
      Node from = nodes[i];
      Node to = nodes[i+1];
      int cost = Math.abs(from.z-to.z);
      edges.add(new Edge(from, to, cost));
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
