// WIP
import java.util.*;
import java.io.*;

public class Main {
  
  static void search() {
    
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    
    HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
    HashMap<Integer, Integer> counts = new HashMap<>();
    
    for(int i=0; i<M; i++) {
      st = new StringTokenizer(br.readLine());
      int value = Integer.parseInt(st.nextToken());
      int key = Integer.parseInt(st.nextToken());
      if(!map.containsKey(key)) {
        map.put(key, new ArrayList<Integer>());
        counts.put(key, 1);
      }
      map.get(key).add(value);
    }

    int max = 0;
    for(int root:map.keySet()) {
      System.out.println("root " + root);
      if(counts.get(root)>1) {
        counts.remove(root);
        continue;
      }
      Stack<Integer> stack = new Stack<>();
      stack.add(root);
      
      while(!stack.isEmpty()) {
        int head = stack.pop();
        System.out.println("head " + head);
        if(!map.containsKey(head)) {
          continue;
        }
        for(int next:map.get(head)) {
          stack.add(next);
          counts.put(next, counts.get(head)+1);
        }
      }
      
      if(counts.get(root) < max) {
        counts.remove(root);
      } else {
        
      }
    }
    System.out.println(counts.toString());
  }
}
