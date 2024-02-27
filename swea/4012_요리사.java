import java.io.*;
import java.util.*;

public class Solution {
  static int N;
  static int[][] ins;
  static int result;
  
  static int cal(Stack<Integer> stack) {
    int sum = 0;
    for(int i=0; i<N/2; i++) {
      for(int j=i+1; j<N/2; j++) {
        sum += ins[stack.get(i)][stack.get(j)];
        sum += ins[stack.get(j)][stack.get(i)];
      }
    }
    return sum;
  }
  
  static void pick(Stack<Integer> a, Stack<Integer> b, int idx) {
    if(idx==N) {
      result = Math.min(result, Math.abs(cal(a)-cal(b)));
      return;
    }
    
    if(a.size()<N/2) {
      a.add(idx);
      pick(a, b, idx+1);
      a.pop();
    }
    if(b.size()<N/2) {
      b.add(idx);
      pick(a, b, idx+1);
      b.pop();
    }
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int T = Integer.parseInt(br.readLine());
    for(int t=1; t<=T; t++) {

      N = Integer.parseInt(br.readLine());
      
      ins = new int [N][N];
      for(int i=0; i<N; i++) {
        st = new StringTokenizer(br.readLine());
        for(int j=0; j<N; j++) {
          ins[i][j] = Integer.parseInt(st.nextToken());
        }
      }
      
      Stack<Integer> a = new Stack<>();
      Stack<Integer> b = new Stack<>();
      result = Integer.MAX_VALUE;
      pick(a, b, 0);
      
      System.out.printf("#%d %d\n", t, result);
    }
  }
}
