import java.io.*;
import java.util.*;

class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int N = Integer.parseInt(br.readLine());
    LinkedList<Integer>[] trees = new LinkedList[N+1];
    
    for(int i=1; i<=N; i++) {
      trees[i] = new LinkedList<>();
    }
    
    for(int i=0; i<N-1; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      
      trees[a].add(b);
      trees[b].add(a);
    }
    
    Queue<Integer> queue = new LinkedList<>();
    boolean[] visited = new boolean[N+1];
    int[] parents = new int[N+1];
    
    queue.add(1);
    visited[1] = true;
      
    while(!queue.isEmpty()) {
      int node = queue.poll();
      
      for(int next:trees[node]) {          
        if(visited[next]) continue;
        visited[next] = true;
        queue.add(next);
        parents[next] = node;
      }
    }
    
    for(int i=2; i<=N; i++) {
      System.out.println(parents[i]);
    }
  }
}
