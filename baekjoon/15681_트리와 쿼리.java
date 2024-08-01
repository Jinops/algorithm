import java.io.*;
import java.util.*;

public class Main {
  static List<Integer>[] nodes;
  static int[] counts;
  static boolean[] visited;
  
  static void DFS(int from) {
    visited[from] = true;
    int count = 1;
    
    for(int node:nodes[from]) {
      if(!visited[node]) {
        DFS(node);
        count += counts[node];
      }
    }
    
    counts[from] = count;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int N = Integer.parseInt(st.nextToken());
    int R = Integer.parseInt(st.nextToken());
    int Q = Integer.parseInt(st.nextToken());
    
    nodes = new ArrayList[N+1];
    for(int i=1; i<=N; i++) {
      nodes[i] = new ArrayList<>();
    }
    
    counts = new int[N+1];
    visited = new boolean[N+1];
    
    for(int n=0; n<N-1; n++) {
      st = new StringTokenizer(br.readLine());
      int U = Integer.parseInt(st.nextToken());
      int V = Integer.parseInt(st.nextToken());
      
      nodes[U].add(V);
      nodes[V].add(U);
    }
    
    DFS(R);
    
    for(int q=0; q<Q; q++) {
      int U = Integer.parseInt(br.readLine());
      System.out.println(counts[U]);
    }
  }
}
