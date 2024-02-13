import java.io.*;
import java.util.*;
 
public class Main {
  static HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
  static ArrayList<Integer> nodes = new ArrayList<>();
  static int D;
  static int result;
  
  static int getNextNode(int from) {
    int idx = 0;
    while(from >= nodes.get(idx)) {
      idx++;
    }
    return nodes.get(idx);
  }
  
  static void find(int from, int accDist) {
    if(accDist>=result) {
      return;
    }
    if(from==D) {
      result = Math.min(result, accDist);
      return;
    }
    if(map.containsKey(from)) { // 지름길
      for(int to:map.get(from).keySet()) {
        int dist = map.get(from).get(to);
        find(to, accDist+dist);
      }
    }
    int to = getNextNode(from); // 고속도로
    int dist = to-from;
    find(to, accDist+dist);
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int N = Integer.parseInt(st.nextToken());
    D = Integer.parseInt(st.nextToken());
    result = D;
    
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int dist = Integer.parseInt(st.nextToken());
      
      if(to > D) {
        continue;
      }
      
      if(!map.containsKey(from)) {
        map.put(from, new HashMap<>());
      }
      if(!map.get(from).containsKey(to)) { // from-to (init)
        map.get(from).put(to, to-from);
      }
      if(dist < map.get(from).get(to)) { // from-to (update)
        map.get(from).put(to, dist);
      }
    }
    
    for(int key:map.keySet()) {
      nodes.add(key);
    }
    nodes.sort(null);
    nodes.add(D);
    
    find(0, 0);
    
    System.out.println(result);
  }
}
