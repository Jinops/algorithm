// 31091 시간초과
import java.util.*;
import java.io.*;

public class Main {
  static int N;
  static int[] arr;
  static boolean[] isLiars;
  
  static void check(int idx, int lower, int upper, int liarCnt) {
//    System.out.println(idx + " " + upper + "<=" + liarCnt + "<=" + lower);
    if(!(upper <= liarCnt && liarCnt <= lower)) { // n명이하 < n명이상
      return;
    }
    if(idx==N) {
      isLiars[liarCnt] = true;
      return;
    }
    for(int i=idx; i<N; i++) {
      if(arr[i]>0) {
        check(i+1, lower, Math.max(upper, arr[i]), liarCnt); // 진실
        check(i+1, Math.min(lower, arr[i]+1), upper, liarCnt+1); // 거짓
        
      } else {
        check(i+1, Math.min(lower, -arr[i]), upper, liarCnt); // 진실
        check(i+1, lower, Math.max(upper, -arr[i]+1), liarCnt+1); // 거짓
      }
      
    }
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    N = Integer.parseInt(br.readLine());
    arr = new int[N]; // 거짓말 하는 사람 수 - 양:k명 이상, 음:k명 이하
    isLiars = new boolean[N+1];
    
    st = new StringTokenizer(br.readLine());
    for (int i=0; i<N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    
    check(0, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
    
    for(int cnt=0; cnt<=N; cnt++) {
      if(isLiars[cnt]) { // TODO: 출력
        System.out.print(cnt + " ");
      }
    }
  }
}
