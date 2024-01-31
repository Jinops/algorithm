import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
  static int N = 100;
  static int[][] arr;
  static boolean isLeftAble(Point p) {
    return p.x > 0 && arr[p.y][p.x-1]==1;
  }
  static boolean isRightAble(Point p) {
    return p.x < N-1 && arr[p.y][p.x+1]==1;
  }
  static int goUp(Point p) {
    if(p.y <= 0) {
      return p.x;
    }
    if(isLeftAble(p)) {
      while(isLeftAble(p)) {
        p.x -= 1;
      }
    } else if(isRightAble(p)) {
      while(isRightAble(p)) {
        p.x += 1;
      }
    }
    
    p.y -= 1;
    
    return goUp(p);
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int T = 10;

    for (int t = 1; t <= T; t++) {
      
      int tNum = Integer.parseInt(br.readLine());
      Point start = new Point();
      
      arr = new int[N][N];
      for(int i=0; i<N; i++) {
        st = new StringTokenizer(br.readLine());
        for(int j=0; j<N; j++) {
          arr[i][j] = Integer.parseInt(st.nextToken());
          if(i==N-1 && arr[i][j] == 2) {
            start.x = j;
            start.y = i;
          }
        }
      }
      
      System.out.printf("#%d %d\n", tNum, goUp(start));
    }
  }
}
