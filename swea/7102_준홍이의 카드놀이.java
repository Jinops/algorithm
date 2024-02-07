import java.io.*;
import java.util.*;

public class Solution {
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    StringBuilder sb;
    int T = Integer.parseInt(br.readLine());
    
    for(int t=1; t<=T; t++) {
      st = new StringTokenizer(br.readLine());
      sb = new StringBuilder();
      int M = Integer.parseInt(st.nextToken());
      int N = Integer.parseInt(st.nextToken());
      
      int[] adds = new int[M+N+1];
      int maxCnt = 0;
      for(int m=1; m<=M; m++) {
        for(int n=1; n<=N; n++) {
          maxCnt = Math.max(maxCnt, ++adds[m+n]);
        }
      }
      
      for(int i=2; i<=M+N; i++) {
        if(adds[i]==maxCnt) {
          sb.append(i+" ");
        }
      }
      
      System.out.printf("#%d %s\n", t, sb.toString());
    }
  }
}
