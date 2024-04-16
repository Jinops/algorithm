import java.util.*;
import java.io.*;

public class Main {

  static int part1(int[][] mtx, int N, int i, int j) {
    int base = mtx[i][j]+mtx[i][j+1]+mtx[i][j+2]+mtx[i][j+3];
    int r90 = mtx[j][i]+mtx[j+1][i]+mtx[j+2][i]+mtx[j+3][i];
    
    return Math.max(base,  r90);
  }
  
  static int part2(int[][] mtx, int N, int i, int j) {
    int base = mtx[i][j]+mtx[i][j+1]+mtx[i+1][j+1]+mtx[i+1][j+2];
    int r90 = mtx[j][i+1]+mtx[j+1][i+1]+mtx[j+1][i]+mtx[j+2][i];
    
    return Math.max(base,  r90);
  }
  
  static int part3(int[][] mtx, int N, int i, int j) {
    int maxSum = Integer.MIN_VALUE;
    
    int base = mtx[i][j]+mtx[i][j+1]+mtx[i][j+2]+mtx[i+1][j+2];
    int r180 = mtx[i][j]+mtx[i+1][j]+mtx[i+1][j+1]+mtx[i+1][j+2];
    int r90 = mtx[j][i+1]+mtx[j+1][i+1]+mtx[j+2][i+1]+mtx[j+2][i];
    int r270 = mtx[j][i+1]+mtx[j][i]+mtx[j+1][i]+mtx[j+2][i];
    
    maxSum = Math.max(maxSum, base);
    maxSum = Math.max(maxSum, r180);
    maxSum = Math.max(maxSum, r90);
    maxSum = Math.max(maxSum, r270);
    
    return maxSum;
  }
  
  static int part4(int[][] mtx, int N, int i, int j) {
    int maxSum = Integer.MIN_VALUE;
    
    int base = mtx[i][j]+mtx[i][j+1]+mtx[i][j+2]+mtx[i+1][j+1];
    int r180 = mtx[i+1][j]+mtx[i+1][j+1]+mtx[i+1][j+2]+mtx[i][j+1];
    int r90 = mtx[j][i+1]+mtx[j+1][i+1]+mtx[j+2][i+1]+mtx[j+1][i];
    int r270 = mtx[j][i]+mtx[j+1][i]+mtx[j+2][i]+mtx[j+1][i+1];
    
    maxSum = Math.max(maxSum, base);
    maxSum = Math.max(maxSum, r180);
    maxSum = Math.max(maxSum, r90);
    maxSum = Math.max(maxSum, r270);
    
    return maxSum;
  }
  
  static int part5(int[][] mtx, int N, int i, int j) {
    
    return mtx[i][j]+mtx[i+1][j]+mtx[i][j+1]+mtx[i+1][j+1];
  }
  
  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(System.in);
    
    int N = sc.nextInt();
    int tc = 0;
    
    while(N!=0) {
      int[][] mtx = new int[N][N];
      int maxSum = Integer.MIN_VALUE;
      
      for(int i=0; i<N; i++) {
        for(int j=0; j<N; j++) {
          mtx[i][j] = sc.nextInt();
        }
      }
      
      // 조각 1
      for(int i=0; i<N; i++) {
        for(int j=0; j<N-3; j++) {
          maxSum = Math.max(maxSum, part1(mtx, N, i, j));
        }
      }
      // 조각 2, 3, 4
      for(int i=0; i<N-1; i++) {
        for(int j=0; j<N-2; j++) {
          maxSum = Math.max(maxSum, part2(mtx, N, i, j));
          maxSum = Math.max(maxSum, part3(mtx, N, i, j));
          maxSum = Math.max(maxSum, part4(mtx, N, i, j));
        }
      }
      // 조각 5
      for(int i=0; i<N-1; i++) {
        for(int j=0; j<N-1; j++) {
          maxSum = Math.max(maxSum, part5(mtx, N, i, j));
        }
      }
      
      System.out.printf("%d. %d\n", ++tc, maxSum);
      N = sc.nextInt();
    }
  }
}
