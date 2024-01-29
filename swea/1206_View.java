import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
  static int getDiff(int[] arr, int i) {
    int leftMax = Math.max(arr[i-2], arr[i-1]);
    int rightMax = Math.max(arr[i+1], arr[i+2]);
    int diff = Math.min(arr[i]-leftMax, arr[i]-rightMax);
    
    return Math.max(diff, 0);
  }
  
  public static void main(String[] args) throws NumberFormatException, IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int T = 10;
    
    for(int t=1; t<=T; t++) {
      
      int N = Integer.parseInt(br.readLine());
      int[] arr = new int[N];
      int result = 0;
      
      st = new StringTokenizer(br.readLine());
      for(int i=0; i<N; i++) {
        arr[i] = Integer.parseInt(st.nextToken());
        if(i>=4) {
          result += getDiff(arr, i-2);
        }
      }
      
      System.out.printf("#%d %d\n", t, result);
      
    }
  }
}
