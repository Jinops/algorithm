import java.io.*;
import java.util.*;

public class Solution {
  static int cal(int n, int m, int curM) {
    return (curM==m)? n : n*cal(n, m, curM+1);
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    for(int t=1; t<=10; t++) {
      
      int tc = Integer.parseInt(br.readLine());
      st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int result = cal(n, m, 1);
      
      System.out.printf("#%d %d\n", tc, result);
    }
  }
}
