// 2636 TODO
import java.util.*;
import java.io.*;

public class Main {
  static int H;
  static int W;
  
  static int[][] deltas = {{1,0},{-1,0},{0,1},{0,-1}};
  
  public static int xyIdx(int x, int y) {
    return H*y+x;
  }
  
  static int idxX(int idx) {
    return idx/H;
  }
  
  static int idxY(int idx) {
    return idx%H;
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    H = Integer.parseInt(st.nextToken());
    W = Integer.parseInt(st.nextToken());
    int[][] map = new int[H][W];
    
    for(int i=0; i<H; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<W; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    
    int time = 0;
    int cheeseCnt = 1;
    
    while(cheeseCnt>0) {
      cheeseCnt = 0;
      boolean[] visited = new boolean[H*W];
      for(int i=0; i<H; i++) {
        for(int j=0; j<W; j++) {
          if(map[i][j]==0) {
            Queue<Integer> queue = new LinkedList<>();
            queue.add(xyIdx(j, i));
            while(!queue.isEmpty()) {
              int xy = queue.poll();
              if(visited[xy]) {
                continue;
              }
              visited[xy] = true;
              for(int[] delta:deltas) {
                int nX = idxX(xy)+delta[0];
                int nY = idxY(xy)+delta[1];
                int nXyIdx = xyIdx(nX, nY);
                if(0<=nX && nX<W && 0<=nY && nY <H && !visited[nXyIdx]) {
                  if(map[nY][nX]==0) {
                    visited[xy] = true;
                    queue.add(nXyIdx);
                  } else if(map[nY][nX]==1){ // 치즈일 경우
                    visited[xy] = true;
                    map[nY][nX] = 2;
                    cheeseCnt += 1;
                  }
                }
              }
            }
          }
        }
      }
      for(int[] a:map) {
        System.out.println(Arrays.toString(a));
      }
      
      for (int i=0; i<H; i++) {
        for(int j=0; j<W; j++) {
          if(map[i][j]==2){
            map[i][j] = 0;
            cheeseCnt -= 1;
          }
        }
      }
      time+=1;
    }
    
    System.out.println(time);
    System.out.println(cheeseCnt);
  }
}
