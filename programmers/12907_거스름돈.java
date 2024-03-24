import java.util.*;

class Solution {
    public int solution(int N, int[] money) {
        int[] dp = new int[N+1];
        Arrays.sort(money);
        
        for(int m:money){
            dp[m] += 1;
            for(int i=m+1; i<=N; i++){
                dp[i] += dp[i-m];
                dp[i] %= 1000000007;
            }
        }
        
        return dp[N];
    }
