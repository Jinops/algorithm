// 위상 정렬
import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    
    Map<Integer, List<Integer>> adjs = new HashMap<>();
    int[] degrees = new int[N+1]; // 진입차수
    
    for(int i=1; i<=N; i++) {
      adjs.put(i, new LinkedList<>());
    }
    
    for(int i=1; i<=M; i++) {
      st = new StringTokenizer(br.readLine());
      int A = Integer.parseInt(st.nextToken());
      int B = Integer.parseInt(st.nextToken());
      // A->B
      adjs.get(A).add(B);
      degrees[B] += 1;
    }
    
    Queue<Integer> queue = new LinkedList<>();
    
    for(int i=1; i<=N; i++) {
      if(degrees[i]==0) {
        queue.add(i);
      }
    }
    
    StringBuilder sb = new StringBuilder();
    
    while(!queue.isEmpty()) {
      int node = queue.poll();
      sb.append(node).append(' ');
      
      for(int adj:adjs.get(node)) {
        degrees[adj] -= 1;
        if(degrees[adj] == 0) {
          queue.add(adj);
        }
      }
    }
    
    System.out.println(sb);
  }
}
