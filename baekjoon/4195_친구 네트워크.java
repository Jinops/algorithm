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
      HashMap<String, Integer> name = new HashMap<>();
      HashMap<Integer, Integer> cnt = new HashMap<>();
      ps = new ArrayList<>();
      int idx = 0;
      
      int F = Integer.parseInt(br.readLine());
      for(int f=0; f<F; f++) {
        st = new StringTokenizer(br.readLine());
        String A = st.nextToken();
        String B = st.nextToken();
        
        if(!name.containsKey(A)) {
          name.put(A, idx);
          cnt.put(idx, 1);
          ps.add(idx);
          idx += 1;
        }
        if(!name.containsKey(B)) {
          name.put(B, idx);
          cnt.put(idx, 1);
          ps.add(idx);
          idx += 1;
        }
        
        int pA = find(name.get(A));
        int pB = find(name.get(B));
        
        if(pA!=pB) {
          // union
          ps.set(pB, pA);
          cnt.put(pA, cnt.get(pA)+cnt.get(pB));
        }
        
        sb.append(cnt.get(pA)).append('\n');
      }
    }
    
    System.out.println(sb);
  }
}
