// 다익스트라 풀이
// 트리이므로, 다익스트라 풀이보다는 BFS가 적절할듯 (거리가 갱신되지 않기 때문)
import java.util.*;
import java.io.*;

class Node implements Comparable<Node>{
  int n;
  int dist;
  
  Node(int n, int dist){
    this.n = n;
    this.dist = dist;
  }

  @Override
  public int compareTo(Node o) {
    return this.dist - o.dist;
  }
}

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    
    List<Node>[] nodes = new List[N+1];
    for(int i=1; i<=N; i++) {
      nodes[i] = new ArrayList<>();
    }
    
    for(int i=0; i<N-1; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      
      nodes[from].add(new Node(to, cost));
      nodes[to].add(new Node(from, cost));
    }
    
    for(int i=0; i<M; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      // from으로부터 다익스트라 수행
      
      int[] dists = new int[N+1];
      Arrays.fill(dists, Integer.MAX_VALUE);
      
      PriorityQueue<Node> pq = new PriorityQueue<>();
      boolean[] confirmed = new boolean[N+1];
      
      pq.add(new Node(from, 0));
      dists[from] = 0;
      
      while(!pq.isEmpty() && !confirmed[to]) {
        Node node = pq.poll();
        
        if(confirmed[node.n]) {
          continue;
        }
        confirmed[node.n]= true;
        for(Node next:nodes[node.n]) {
          if(confirmed[next.n]) continue;
          
          int dist = dists[node.n] + next.dist;
          // (from->n) 거리 + (n->next) 거리
          if(dist < dists[next.n]) {
            // ... < (from-> next) 거리
            dists[next.n] = dist;
            pq.add(new Node(next.n, dists[next.n]));
          }
        }
      }
      System.out.println(dists[to]);
    }
  }
}
