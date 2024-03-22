import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[][] triangle) {
        int N = triangle.length;
        int[][] dp = new int[N][N];
        
        for(int i=0; i<N; i++){
            dp[i] = new int[i+1];
        }
        
        dp[0][0] = triangle[0][0];
        for(int i=1; i<N; i++){
            dp[i][0] = triangle[i][0] + dp[i-1][0];
            dp[i][i] = triangle[i][i] + dp[i-1][i-1];
            for(int j=1; j<i; j++){
                dp[i][j] = triangle[i][j] + Math.max(dp[i-1][j-1], dp[i-1][j]);
            }
        }
        
        int result = 0;
        for(int n:dp[N-1]){
            result = Math.max(result, n);
        }
        return result;
    }
}
