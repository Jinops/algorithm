import java.util.*;

class Solution {
    static int N;
    
    static void update(boolean[][] results, int root, int start){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);

        while(!queue.isEmpty()){
            int head = queue.poll();

            for(int i=1; i<=N; i++){
                if(results[head][i] && !results[root][i]){
                    queue.add(i);
                    results[root][i] = true;
                }
            }
        }
    }
    
    public int solution(int n, int[][] results) {
        N = n;
        boolean[][] wins = new boolean[N+1][N+1];
        boolean[][] loses = new boolean[N+1][N+1];
        
        
        for(int[] result:results){
            int winner = result[0];
            int loser = result[1];
            wins[winner][loser] = true;
            loses[loser][winner] = true;
        }
        
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(wins[i][j]){
                    update(wins, i, j);
                }
                if(loses[i][j]){
                    update(loses, i, j);
                }
            }
        }
        
        int result = 0;
        for(int i=1; i<=N; i++){
            int cnt = 0;
            for(int j=1; j<=N; j++){
                if(wins[i][j] || loses[i][j]){
                    cnt++;
                }
            }
            if(cnt == N-1){
                result++;
            }
        }
        
        return result;
    }
}
