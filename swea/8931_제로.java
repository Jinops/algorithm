import java.io.*;
import java.util.*;

public class Solution {
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    
    for(int t=1; t<=T; t++) {
      
      int K = Integer.parseInt(br.readLine());
      Stack<Integer> stack = new Stack<>();
      
      for(int i=0; i<K; i++) {
        int n = Integer.parseInt(br.readLine());
        if(n==0) {
          stack.pop();
        } else {
          stack.add(n);
        }
      }
      
      int result = 0;
      for(int n:stack) {
        result += n;
      }

      System.out.printf("#%d %d\n", t, result);
    }
  }
}
