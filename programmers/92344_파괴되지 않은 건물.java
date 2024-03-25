// 2차원 누적합 문제
// 입력받을 때, 누적합 배열로 받는다 (값 들어가는 지점에 +n, 끝나는 지점 뒤에 -n)
// 입력 다 받으면, 행-열 기준 누적합 계산을 실시한다. (따라서 마지막 한 번만 계산됨)
// 해당 누적합 배열에 기존 배열 값 합쳐서, 계산을 한다.

class Solution {
    final static int ATTACK = 1;
    final static int HEAL = 2;
    
    public int solution(int[][] board, int[][] skills) {
        int[][] acc = new int[board.length+1][board[0].length+1];
        int cnt=0;
        for(int[] skill:skills){
            int type = skill[0];
            int y1 = skill[1];
            int x1 = skill[2];
            int y2 = skill[3];
            int x2 = skill[4];
            int degree = skill[5] * (type==ATTACK?-1:1);
            
            acc[y1][x1] += degree;
            acc[y1][x2+1] -= degree;
            acc[y2+1][x1] -= degree;
            acc[y2+1][x2+1] += degree;
        }
        
        for(int i=0; i<board.length; i++){
            for(int j=1; j<board[i].length; j++){
                acc[i][j] += acc[i][j-1];
            }
        }
        
        for(int i=1; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                acc[i][j] += acc[i-1][j];
            }
        }
        
        int result = 0;
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length; j++){
                if(board[i][j]+acc[i][j]>0){
                    result++;
                }
            }
        }
        return result;
    }
}
