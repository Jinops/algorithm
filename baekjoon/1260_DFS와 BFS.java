import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
  static int n;
  static int m;
  static int v;
  static HashMap<Integer, ArrayList<Integer>> edges = new HashMap<>();
  
  static void dfs(int start, boolean visited[], Queue<Integer> route) {
    route.add(start);
    visited[start] = true;
    if(route.size() == n) {
      for(int node:route) {
        System.out.print(node+" ");
      }
      System.out.println();
      return;
    }
    if(edges.get(start)==null) {
      return;
    }
    for(int node:edges.get(start)) {
      if(!visited[node]) {
        dfs(node, visited, route);
      }
    }
  }
  
  static void bfs() {
    String result = "";
    Queue<Integer> queue = new LinkedList<>();
    boolean[] visited = new boolean[n+1];
    queue.add(v);
    visited[v] = true;
    
    while(!queue.isEmpty()) {
      int start = queue.poll();
      result += start + " ";
      if(edges.get(start)!=null) {
        for(int node:edges.get(start)) {
          if(!visited[node]) {
            visited[node] = true;
            queue.add(node);
          }
        }
      }
    }
    
    System.out.println(result);
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());
    v = Integer.parseInt(st.nextToken());
    
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
    }
    
    dfs(v, new boolean[n+1], new LinkedList<>());
    bfs();
  }
}
