// WIP
import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    
    for(int i=0; i<M; i++) {
      st = new StringTokenizer(br.readLine());
      int value = Integer.parseInt(st.nextToken());
      int key = Integer.parseInt(st.nextToken());
      if(!map.containsKey(key)) {
        map.put(key, new ArrayList<Integer>());
      }
      map.get(key).add(value);
    }

    ArrayList<Integer> results = new ArrayList<>();
    int max = 0;

    for(int root:map.keySet()) {
      Stack<Integer> stack = new Stack<>();
      boolean[] visited = new boolean[N+1];
      stack.add(root);
      visited[root] = true;
      
      int count = 0;

      while(!stack.isEmpty()) {
        int head = stack.pop();
        count++;
        if(!map.containsKey(head)) {
          continue;
        }

        for(int next:map.get(head)) {
          if(!visited[next]){
            visited[next] = true;
            stack.add(next);
          }
        }
      }
      
      if(count > max){
        results.clear();
      }
      if(count >= max){
        results.add(root);
        max = count;
      }
    }

    results.sort(null);
    StringBuilder sb = new StringBuilder();
    for(int result:results){
      sb.append(result).append(' ');
    }
    System.out.println(sb.toString());
  }
}
