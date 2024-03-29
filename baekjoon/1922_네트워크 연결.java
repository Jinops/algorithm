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

public class Main {
  static int[] parents;
  static int unionFind(int x){
    if(parents[x]==x){
      return x;
    } else{
      return unionFind(parents[x]);
    }
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    int M = Integer.parseInt(br.readLine());
    
    PriorityQueue<Edge> pq = new PriorityQueue<>();

    for(int i=0; i<M; i++){
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());

      pq.add(new Edge(from, to, cost));
    }

    parents = new int[N+1];
    for(int i=1; i<=N; i++){
      parents[i] = i;
    }

    int edgeCnt = 0;
    int cost = 0;

    while(edgeCnt < N-1){
      Edge edge = pq.poll();
      int fromParent = unionFind(edge.from);
      int toParent = unionFind(edge.to);

      if(fromParent==toParent){
        continue;
      }
      parents[toParent] = fromParent; // union
      edgeCnt += 1;
      cost += edge.cost;
    }

    System.out.println(cost);
  }
}
