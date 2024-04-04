// TODO: 5% 시간초과
import java.io.*;
import java.util.*;

class Main {
  static List<Integer> ps;
  
  static int find(int x) {
    if(ps.get(x)!=x) {
      ps.set(x, find(ps.get(x)));
    }
    return ps.get(x);
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    StringTokenizer st;
    
    int T = Integer.parseInt(br.readLine());
    for(int t=0; t<T; t++) {
      HashMap<String, Integer> map = new HashMap<>();
      ps = new ArrayList<>();
      int idx = 0;
      
      int F = Integer.parseInt(br.readLine());
      for(int f=0; f<F; f++) {
        st = new StringTokenizer(br.readLine());
        String A = st.nextToken();
        String B = st.nextToken();
        
        if(!map.containsKey(A)) {
          map.put(A, idx);
          ps.add(idx);
          idx += 1;
        }
        if(!map.containsKey(B)) {
          map.put(B, idx);
          ps.add(idx);
          idx += 1;
        }
        
        int pA = find(map.get(A));
        int pB = find(map.get(B));
        
        if(pA!=pB) {
          // union
          ps.set(pB, pA);
        }
        
        // update parents
        int result = 0;
        for(int i=0; i<idx; i++) {
          int p = find(i);
          if(p==pA) {
            result += 1;
          }
        }
        sb.append(result).append('\n');
      }
    }
    
    System.out.println(sb);
  }
}
