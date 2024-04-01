import java.util.*;
import java.io.*;

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    for(int t=1; t<=10; t++) {
      st = new StringTokenizer(br.readLine());
      int V = Integer.parseInt(st.nextToken());
      int E = Integer.parseInt(st.nextToken());
      
      int[] inDegrees = new int[V+1];
      List<Integer>[] adjs = new LinkedList[V+1];
      
      for(int i=1; i<=V; i++) {
        adjs[i] = new LinkedList<>();
      }
      
      st = new StringTokenizer(br.readLine());
      for(int i=0; i<E; i++) {
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());
        
        inDegrees[to] += 1;
        adjs[from].add(to);
      }
      
      Queue<Integer> queue = new LinkedList<>();
      StringBuilder sb = new StringBuilder();
      
      for(int i=1; i<=V; i++) {
        if(inDegrees[i]==0) {
          queue.add(i);
        }
      }
      
      while(!queue.isEmpty()) {
        int node = queue.poll();
        sb.append(node).append(' ');
        
        if(adjs[node].size()==0) continue;
        for(int next:adjs[node]) {
          inDegrees[next] -= 1;
          if(inDegrees[next] == 0) {
            queue.add(next);
          }
        }
      }
      
      System.out.printf("#%d %s\n", t, sb.toString());
    }
  }
}
