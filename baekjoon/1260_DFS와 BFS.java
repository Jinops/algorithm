import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int n;
  static int m;
  static int v;
  static HashMap<Integer, ArrayList<Integer>> edges = new HashMap<>();
  
  static void printRoute(LinkedList<Integer> route) {
    for(int node:route) {
      System.out.print(node+" ");
    }
    System.out.println();
  }
  
  static void dfs(int start, boolean visited[], LinkedList<Integer> route) {
    route.add(start);
    visited[start] = true;
    route.add(start);
    if(route.size() == n) {
      return;
    }
    if(edges.get(start)!=null) {
      for(int node:edges.get(start)) {
        if(!visited[node]) { // TODO
          dfs(node, visited, route);
        }
      }
    }
  }
  
  static LinkedList<Integer> bfs(HashMap<Integer,Boolean> visited) {
    Queue<Integer> queue = new LinkedList<>();
    LinkedList<Integer> route = new LinkedList<>();
    queue.add(v);
    route.add(v);
    visited.put(v, true);
    
    while(!queue.isEmpty()) {
      int start = queue.poll();
      if(edges.get(start)!=null) {
        for(int node:edges.get(start)) {
          System.out.println(node);
          if(!visited.get(node)) { // TODO: node not in visited
            visited.put(node, true);
            queue.add(node);
            route.add(node);
          }
        }
      }
    }
    
    if(route.size() < n) {
      for(int node:visited.keySet()) {
        if(!visited.get(node)) {
          route.add(node);
        }
      }
    }
    
    return route;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    v = Integer.parseInt(st.nextToken());

    HashMap<Integer, Boolean> visited = new HashMap<>();
    
    for(int i=0; i<m; i++) {
      st = new StringTokenizer(br.readLine());
      int key = Integer.parseInt(st.nextToken());
      int value = Integer.parseInt(st.nextToken());
      if(edges.get(key) == null) {
        edges.put(key, new ArrayList<Integer>());
      }
      edges.get(key).add(value);
    }
    for(int key:edges.keySet()) {
      edges.get(key).sort(null);
      visited.put(key, false);
    }
    
    dfs(v, new boolean[n+1], new LinkedList<>());
    System.err.println("---");
    printRoute(bfs(new HashMap<>(visited)));
  }
}
