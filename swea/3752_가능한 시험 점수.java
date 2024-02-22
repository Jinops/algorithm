import java.io.*;
import java.util.*;

public class Solution {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int T = Integer.parseInt(br.readLine());
    for (int t=1; t<=T; t++) {

      int N = Integer.parseInt(br.readLine());
      int[] nums = new int[N];
      int total = 0;
      int result = 0;

      st = new StringTokenizer(br.readLine());
      for (int i=0; i<N; i++) {
        nums[i] = Integer.parseInt(st.nextToken());
        total += nums[i];
      }

      boolean[] hasResults = new boolean[total + 1];
      hasResults[0] = true;
      for (int num:nums) {
        for (int i=total; i>=num; i--) {
          hasResults[i] = hasResults[i] || hasResults[i-num];
        }
      }
      for (boolean hasResult:hasResults) {
        if (hasResult) result++;
      }

      System.out.printf("#%d %d\n", t, result);
    }
  }
}
