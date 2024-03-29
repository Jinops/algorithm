import java.util.*;
import java.io.*;

// 크루스칼로 전체 마을을 연결하는 최소 Edge를 구한다
// 싸이클이 존재하지 않으므로, 하나의 Edge를 없애면 두 마을로 분리된다
// 따라서, 크루스칼을 돌리되 Edge가 Node-2개 까지만 하면 된다.

class Edge implements Comparable<Edge> {
  int from;
  int to;
  int cost;
  
  Edge(int from, int to, int cost){
    this.from = from;
    this.to = to;
    this.cost = cost;
  }

  @Override
  public int compareTo(Edge o){
    return this.cost - o.cost;
  }
}

public class Main {
  static int parents[];
  
  static int find(int x){
    // 값을 갱신하지 않고, 매 번 찾아서 했을 땐 시간초과
    if(parents[x]!=x){
      parents[x] = find(parents[x]);
    }
    return parents[x];
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

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

    while(edgeCnt < N-2){
      Edge edge = pq.poll();
      int p1 = find(edge.from);
      int p2 = find(edge.to);
      
      if(p1==p2){
        continue;
      }
      parents[p2] = p1;
      edgeCnt += 1;
      cost += edge.cost;
    }

    System.out.println(cost);
  }
}
