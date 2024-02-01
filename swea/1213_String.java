import java.io.*;
import java.util.*;

public class Solution {
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = 10;
    
    for(int t=0; t<T; t++) {
      
      int tNum = Integer.parseInt(br.readLine());
      String input = br.readLine();
      String text = br.readLine();
      
      int result = 0;
      int idx = input.length()-1;
      char lastC = input.charAt(input.length()-1);
      while(idx<text.length()) { // 보이어 무어
        char c = text.charAt(idx);
        if(c!=lastC) {
          if(input.indexOf(c)==-1) {
            idx+=input.length(); // 패턴에 없으면 패턴 크기만큼
          } else {
            idx+=input.length()-input.indexOf(c)-1; // 패턴에 있으면 마지막 문자 일지할 만큼
          }
        } else {
          boolean isCorrect = true;
          for(int i=0; i<input.length(); i++) {
            if(text.charAt(idx-input.length()+1+i) != input.charAt(i)) {
              isCorrect = false;
              break;
            }
          }
          if(isCorrect) {
            result+=1;
          }
          idx+=input.length();
        }
      }
      System.out.printf("#%d %d\n", tNum, result);
    }
  }
}
