// TODO: 메모리 초과
import java.io.*;
import java.util.*;
 
public class Main {
  static int N;
  static Set<Integer> nodes = new HashSet<>(); // 최소거리가 확정된 set
  
  static int[] dijkstra(int start, HashMap<Integer, HashMap<Integer, Integer>> edges) {
    Set<Integer> set = new HashSet<>();
    int[] distances = new int[N+1];
    for(int n=0; n<=N; n++) {
      distances[n] = Integer.MAX_VALUE;
    }
    distances[start] = 0;
    
    while(set.size()<N) {
      int nearNode = getNearNode(set, distances); 
      set.add(nearNode); // set에 가장 가까운 (포함안된) node를 추가한다
      
      for(int nextNode:edges.get(nearNode).keySet()) { // 그 주변(다음) node들에 대해
        if(!set.contains(nextNode) && distances[nearNode] + edges.get(nearNode).get(nextNode) < distances[nextNode]) {
          // 알고 있던 거리보다, 거쳐가는 거리가 더 짧다면 update
          distances[nextNode] = distances[nearNode] + edges.get(nearNode).get(nextNode); 
        }
      }
    }
    
    return distances; 
  }
  
  static int getNearNode(Set<Integer> set, int[] distances) {
    int nearNode = -1;
    for(int node:nodes) {
      if(!set.contains(node)) { // set에 없는 node들 중 찾기
        if(nearNode == -1 || distances[node] < distances[nearNode]) {
          nearNode = node;
        }
      }
    }
    return nearNode;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int X = Integer.parseInt(st.nextToken());

    HashMap<Integer, HashMap<Integer, Integer>> edges = new HashMap<>();
    HashMap<Integer, HashMap<Integer, Integer>> edgesR = new HashMap<>(); // 역방향
    nodes = new HashSet<>(); // 최소거리가 확정된 set
    
    for (int n=1; n<=N; n++) {
      edges.put(n, new HashMap<>());
      edgesR.put(n, new HashMap<>());
      nodes.add(n);
    }
    
    for(int m=1; m<=M; m++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int distance = Integer.parseInt(st.nextToken());
      edges.get(from).put(to, distance);
      edgesR.get(to).put(from, distance);
    }
    
    int[] distance = dijkstra(X, edges); // 도착지로부터 node까지
    int[] distanceR = dijkstra(X, edgesR); // node로부터 도착지까지 = edge가 역인, 도착지로부터 node까지
    
    int result=0;
    for(int node:nodes) {
      result = Math.max(result, distance[node]+distanceR[node]);
    }
    
    System.out.println(result);
  }
}
