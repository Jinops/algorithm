import java.util.*;
import java.io.*;

public class Main {
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int T = Integer.parseInt(br.readLine());
    for(int t=0; t<T; t++) {
      int K = Integer.parseInt(br.readLine());
      TreeMap<Integer, Integer> tree = new TreeMap<>();
      // key: num | value: 해당 num의 개수
      
      for(int k=0; k<K; k++) {
        st = new StringTokenizer(br.readLine());
        char cmd = st.nextToken().charAt(0);
        int num = Integer.parseInt(st.nextToken());
        
        if(cmd=='I') {
          int cnt = tree.getOrDefault(num, 0) + 1;
          tree.put(num, cnt);
          
        } else if(cmd=='D' && !tree.isEmpty()) {
          int target = -1;
          
          if(num==1) { // 최댓값 삭제
            target = tree.lastKey();
          } else if(num==-1){ // 최솟값 삭제
            target = tree.firstKey();
          }
          
          int cnt = tree.get(target);
          if(cnt > 1) {
            tree.put(target, cnt-1);
          } else {
            tree.remove(target);
          }
        }
      }
      
      if(tree.isEmpty()) {
        System.out.println("EMPTY");
      } else {
        System.out.println(tree.lastKey() + " " + tree.firstKey());
      }
    }
  }
}
