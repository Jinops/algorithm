import java.io.*;
import java.util.*;

public class Solution {
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int T = Integer.parseInt(br.readLine());
    for(int t=1; t<=T; t++) {
      int result = 0;
      boolean isZero = true;
      for(char c:br.readLine().toCharArray()) {
        if(isZero && c=='1' || !isZero && c=='0') {
          result+=1;
          isZero = !isZero;
        }
      }
      System.out.printf("#%d %d\n", t, result);
    }
  }
}
