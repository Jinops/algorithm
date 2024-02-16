import java.io.*;
import java.util.*;

public class Main {
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    StringBuilder sb = new StringBuilder();
    
    int N = Integer.parseInt(br.readLine());
    boolean[] isStack = new boolean[N];
    Deque<Integer> queue = new LinkedList<>();
    
    st = new StringTokenizer(br.readLine());
    for(int i=0; i<N; i++) {
      isStack[i] = st.nextToken().equals("1");
    }
    
    st = new StringTokenizer(br.readLine());
    for(int i=0; i<N; i++) {
      int n = Integer.parseInt(st.nextToken());
      if(!isStack[i]) {
        queue.add(n);
      }
    }
    
    int M = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    
    for(int i=0; i<M; i++) {
      int c = Integer.parseInt(st.nextToken());
      queue.addFirst(c);
      sb.append(queue.pollLast()).append(' ');
    }
    System.out.println(sb.toString());
  }
}
