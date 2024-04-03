import java.util.*;
import java.io.*;

class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int N = Integer.parseInt(st.nextToken());
    int S = Integer.parseInt(st.nextToken());
    int[] nums = new int[N];
    
    st = new StringTokenizer(br.readLine());
    for(int i=0; i<N; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }
    
    int start = 0;
    int end = 0;
    int intervalSum = 0;
    int min = Integer.MAX_VALUE;
    
    for(start=0; start<N; start++) {
      while(intervalSum < S && end < N) {
        intervalSum += nums[end];
        end++;
      }
      if(intervalSum >= S) {
       min = Math.min(min, end-start); 
      }
      intervalSum -= nums[start];
    }
    
    if(min == Integer.MAX_VALUE) min = 0;
    System.out.println(min);
  }
}
