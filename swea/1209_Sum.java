import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int T = 10;

    for (int t = 1; t <= T; t++) {
      
      int tNum = Integer.parseInt(br.readLine());
      int result = 0;
      int[][] arr = new int[100][100];
      
      for(int i=0; i<100; i++) {
        st = new StringTokenizer(br.readLine());
        for(int j=0; j<100; j++) {
          arr[i][j] = Integer.parseInt(st.nextToken());
        }
      }
      
      int diagonalSum1 = 0;
      int diagonalSum2 = 0;
      
      for(int i=0; i<100; i++) {
        int horizontalSum = 0;
        int verticalSum = 0;
        for(int j=0; j<100; j++) {
          horizontalSum += arr[i][j];
          verticalSum += arr[j][i];
        }
        result = Math.max(result, Math.max(horizontalSum, verticalSum));
        
        diagonalSum1 += arr[i][i];
        diagonalSum2 += arr[100-i-1][100-i-1];
      }
      result = Math.max(result,  Math.max(diagonalSum1, diagonalSum2));
      
      System.out.printf("#%d %d\n", tNum, result);
    }
  }
}
