// WIP (tc25 fail)
import java.util.*;

class Point {
    int x;
    int y;
    int dir;
    int acc;
    
    Point(int x,int y, int dir){
        this(x, y, dir, Integer.MAX_VALUE);
    }
    Point(int x,int y, int dir, int acc){
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.acc = acc;
    }
}
class Solution {
    static int N;
    static int deltas[][] = {{-1,0},{1,0},{0,-1},{0,1}};
    // 좌,우,상,하
    
    static boolean inRange(Point p){
        return 0<=p.x&&p.x<N && 0<=p.y&&p.y<N;
    }
    static boolean isInverse(int from, int to){
        return (from==0&&to==1) || (from==1&&to==0) || (from==2&&to==3) || (from==3&&to==2);
    }
    static int getCost(int fDir, int tDir){
        if(fDir==-1) return 100;
        return (fDir==tDir?100:600);
    }
    
    public int solution(int[][] board) {
        N = board.length;
        int[][] costs = new int[N][N];
        for(int i=0; i<N; i++){
            for( int j=0; j<N; j++){
                costs[i][j] = Integer.MAX_VALUE;
            }
        }
        
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, -1, 0));
        
        while(queue.size()>0){
            Point p = queue.poll();
            if(p.acc > costs[p.y][p.x]){
                continue;
            }
            costs[p.y][p.x] = p.acc;
            
            for(int i=0; i<4; i++){
                if(isInverse(p.dir, i)) continue;
                int[] d = deltas[i];
                int cost = p.acc + getCost(p.dir, i);
                Point np = new Point(p.x+d[0], p.y+d[1], i, cost);
                if(inRange(np) && board[np.y][np.x]==0){
                    queue.add(np);
                }
            }
        }
        
        return costs[N-1][N-1];
    }
}
