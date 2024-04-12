// KMP 알고리즘 

import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    String T = br.readLine();
    String P = br.readLine();
    
    int[] pi = new int[P.length()];
    // i번째까지 문자열을 봤을 때, head와 foot이 정확히 일치하는 길이

    //// set pi
    int j=0;
    
    for(int i=1; i<P.length(); i++) {
      while(j>0 && P.charAt(i)!=P.charAt(j)) {
        j = pi[j-1];
      }
      if(P.charAt(i) == P.charAt(j)) {
        j++;
        // 다음 패턴으로 이동 
        pi[i] = j;
        // pi[i]에는 j값을 넣어준다 
        // = i번쨰 문자열까지 봤을 때, 앞 뒤 j개가 일치한다 
      }
    }
    ////
    
    //// search
    List<Integer> results = new ArrayList<>();
    j = 0;
    
    for(int i=0; i<T.length(); i++) {
      while(j>0 && T.charAt(i)!=P.charAt(j)) {
        j = pi[j-1];
        // 일치하지 않을 경우, 앞과 동일한 패턴을 가지고 있는 위치로 이동
      }
      if(T.charAt(i) == P.charAt(j)) {
        if(j==P.length()-1) { 
          // find pattern
          results.add(i+1-j);
          j = pi[j];
          // 전체패턴을 찾은 후에도, 중복되는 부분으로부터 같은 패턴이 시작될 수 있다 
          continue;
        }
        j++;
        // 다음 패턴으로 이동
      }
    }
    ////
    
    System.out.println(results.size());
    for(int result:results) {
      System.out.print(result + " ");
    }
  }
}
