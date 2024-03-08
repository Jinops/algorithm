import java.util.*;
import java.io.*;

class Main {
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int V = Integer.parseInt(st.nextToken());
    int[] nums = new int[N];
    
    st = new StringTokenizer(br.readLine());
    for(int i=0; i<N; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }
    
    StringBuilder sb = new StringBuilder();
    for(int i=0; i<M; i++) {
      int K = Integer.parseInt(br.readLine());
      if(K>=N) {
        int cut = V-1;
        K = (K-cut)%(N-cut) + cut;
      }
      sb.append(nums[K]).append('\n');
    }
    
    System.out.println(sb.toString());
  }
}
