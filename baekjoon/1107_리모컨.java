// 목표값과 가장 가까운 (누를 수 있는) 숫자 찾기
// 목표값 최대 500,000 (6자리)
// 누를 수 있는 개수: 10^6
// 100만개이므로 브루트포스 가능

// 현재 채널: 100번

import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int N = Integer.parseInt(br.readLine());
    int M = Integer.parseInt(br.readLine());
    
    boolean[] isValids = new boolean[10];
    Arrays.fill(isValids, true);
    
    if(M > 0) {
      st = new StringTokenizer(br.readLine());
      for(int i=0; i<M; i++) {
        int num = Integer.parseInt(st.nextToken());
        isValids[num] = false;
      }
    }
    
    int result = Math.abs(N-100);
    
    for(int i=0; i<=999999; i++) {
      boolean isValid = true;
      for(char digit:String.valueOf(i).toCharArray()) {
        if(!isValids[Character.getNumericValue(digit)]) {
          isValid = false;
          break;
        }
      }
      if(!isValid) continue;
      int cnt = String.valueOf(i).length() + Math.abs(N-i);
      result = Math.min(result, cnt);
    }
    
    System.out.println(result);
  }
}
