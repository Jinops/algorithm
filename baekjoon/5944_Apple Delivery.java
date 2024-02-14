// TODO: 메모리 초과
// dijkstra 인접노드
import java.io.*;
import java.util.*;

public class Main {
  static int P;
  static HashMap<Integer, HashMap<Integer, Integer>> edges = new HashMap<>();
  
  static int getNearestNode(Set<Integer> set, int[] distances) {
    int nearestNode = 0;
    for(int node:edges.keySet()) {
      if(!set.contains(node) && distances[node] < distances[nearestNode]) {
        nearestNode = node;
      }
    }
    return nearestNode; // may not 0
  }
  
  static int[] getDistances(int from, Set<Integer> target) {
    int[] distances = new int[P+1];
    for(int i=0; i<P+1; i++) {
      distances[i] = Integer.MAX_VALUE;
    }
    distances[from] = 0;
    
    Set<Integer> set = new HashSet<>();
    while(set.size()<P) {
      int nearestNode = getNearestNode(set, distances);
      set.add(nearestNode);
      if(set.containsAll(target)) {
        break;
      }
      for(int node:edges.get(nearestNode).keySet()) {
        if(!set.contains(node) && 
            distances[nearestNode]+edges.get(nearestNode).get(node) < distances[node]) {
          distances[node] = distances[nearestNode] + edges.get(nearestNode).get(node);
        }
      }
    }
    
    return distances;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int C = Integer.parseInt(st.nextToken());
    P = Integer.parseInt(st.nextToken());
    int PB = Integer.parseInt(st.nextToken());
    int PA1 = Integer.parseInt(st.nextToken());
    int PA2 = Integer.parseInt(st.nextToken());
    
    for(int p=1; p<=P; p++) {
      edges.put(p, new HashMap<>());
    }
    
    for(int i=0; i<C; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int dist = Integer.parseInt(st.nextToken());
      edges.get(from).put(to, dist);
      edges.get(to).put(from, dist);
    }
    
    int[] distsPB = getDistances(PB, Set.of(PA1, PA2));
    int distPA = getDistances(PA1, Set.of(PA2))[PA2];
    
    System.out.println(Math.min(distsPB[PA1], distsPB[PA2])+distPA);
  }
}
