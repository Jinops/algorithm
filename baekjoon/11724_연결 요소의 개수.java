import java.io.*;
import java.util.*;

public class Main {
  static HashMap<Integer, ArrayList<Integer>> E = new HashMap<>();
  static Set<Integer> visited = new HashSet<>();
  
  static void DFS(int from) {
    visited.add(from);
    if(E.get(from)==null) {
      return;
    }
    for(int nextNode:E.get(from)) {
      if(!visited.contains(nextNode))
        DFS(nextNode);
    }
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    for (int i=0; i<M; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());

      if (E.get(from) == null) {
        E.put(from, new ArrayList<>());
      }
      if (E.get(to) == null) {
        E.put(to, new ArrayList<>());
      }

      E.get(from).add(to);
      E.get(to).add(from);
    }

    int result = 0;
    for (int i=1; i<=N; i++) {
      if(visited.size()==N) {
        break;
      }
      if(!visited.contains(i)) {
        DFS(i);
        result++;
      }
    }
    
    System.out.println(result);
  }
}
