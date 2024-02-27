import java.io.*;
import java.util.*;

public class Solution {
  static int result = 0;
  static int N;
  static int K;
  static int[] nums;
  static int cnt;
  
  static void pick(int idx, int acc) {
    if(acc==K) {
      result += 1;
    }
    if(acc>=K || idx==N) {
      return;
    }
    
    pick(idx+1, acc);
    pick(idx+1, acc+nums[idx]);
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int T = Integer.parseInt(br.readLine());
    for(int t=1; t<=T; t++) {
      
      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      nums = new int[N];

      st = new StringTokenizer(br.readLine());
      for(int i=0; i<N; i++) {
        nums[i] = Integer.parseInt(st.nextToken());
      }
    
      result = 0;
      pick(0, 0);
      
      System.out.printf("#%d %d\n", t, result);
    }
  }
}
