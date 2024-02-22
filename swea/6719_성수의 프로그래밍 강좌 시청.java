// WIP
import java.io.*;
import java.util.*;

public class Solution {
  static int N;
  static int K;
  static int nums[];
  static double max;
  
  public static void pick(int idx, int cnt, double A) { // TODO
    A = (A+nums[idx])/2;
    if (++cnt==K) {
      max = Math.max(max, A);
      return;
    }
    for(int i=idx+1; i<N; i++) {
      pick(i, cnt, A);
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int T = Integer.parseInt(br.readLine());
    for (int t=1; t<=T; t++) {

      st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      nums = new int[N];
      max = Integer.MIN_VALUE;
      
      st = new StringTokenizer(br.readLine());
      for(int i=0; i<N; i++) {
        nums[i] = Integer.parseInt(st.nextToken());
      }
      
      pick(0, 0, 0);

      System.out.printf("#%d %f\n", t, max);
    }
  }
}
