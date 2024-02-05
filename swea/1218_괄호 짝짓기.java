import java.io.*;
import java.util.*;

public class Solution {
  static boolean isOpen(char c) {
    return c=='(' || c=='[' || c=='{' || c=='<';
  }
  static boolean isMatch(char openC, char closeC) {
    return openC=='(' && closeC==')'
        || openC=='[' && closeC==']'
        || openC=='{' && closeC=='}'
        || openC=='<' && closeC=='>';
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = 10;
    
    for(int t=1; t<=T; t++) {
      int result = 1;
      int N = Integer.parseInt(br.readLine());
      String str = br.readLine();
      Stack<Character> stack = new Stack<>();
      stack.add(' '); // 첫문자 !isOpen 시 pop 에러 방지
      
      for(char c:str.toCharArray()) {
        if(isOpen(c)) {
          stack.add(c);
        } else {
          char beforeC = stack.pop();
          if(!isMatch(beforeC, c)) {
            result = 0;
            break;
          }
        }
      }

      System.out.printf("#%d %d\n", t, result);
    }
  }
}
