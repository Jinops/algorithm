import java.util.*;
import java.io.*;

public class Main {
  static int Y;
  static int X;
  static int[][] mtx;

  final static int[][] deltasCW = {{-1,0},{0,1},{1,0},{0,-1}};
  final static int[][] deltasCCW = {{1,0},{0,1},{-1,0},{0,-1}};

  static boolean inRange(int y, int x){
    return 0<=x&&x<X && 0<=y&&y<Y;
  }

  static void clean(int cleanerY, int[][] d){
    // d: 바람 방향의 역방향을 전달. 청정기쪽으로 빨아드린다
    int i = 0;
    int y = cleanerY + d[i][0];
    int x = 0 + d[i][1];
    mtx[y][x] = 0; // 청정기 바로 위(아래) 지점은 0
    
    while(!(y==cleanerY && x==1)) { // 청정기 오른쪽 지점에서 break
      int by = y + d[i][0];
      int bx = x + d[i][1];
      
      if(!inRange(by, bx) || (y==cleanerY && x==X-1)) {
        // 배열 벗어나거나, 이전 위치가 돌아가야하는(꺾이는) 위치라면
        i = (i<3) ? i+1 : 0;
        by = y + d[i][0];
        bx = x + d[i][1];
      }
      
      mtx[y][x] = mtx[by][bx];
      y = by;
      x = bx;
    }
    
    mtx[y][x] = 0; //청정기 바로 오른쪽 지점은 0
  }

  static void spread(int y, int x, int[][] overlay){
    int power = Math.abs(mtx[y][x]/5);
    if(power<1) return;

    int cnt = 0;
    for(int[] d:deltasCW){
      int ny = y+d[0];
      int nx = x+d[1];
      if(inRange(ny, nx) && mtx[ny][nx]!=-1){
        overlay[ny][nx] += power;
        cnt += 1;
      }
    }
    overlay[y][x] -= power*cnt;
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    Y = Integer.parseInt(st.nextToken());
    X = Integer.parseInt(st.nextToken());
    int T = Integer.parseInt(st.nextToken());

    int cleanerY = -1; // 아래의 y값 저장

    mtx = new int[Y][X];
    for(int i=0; i<Y; i++){
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<X; j++){
        mtx[i][j] = Integer.parseInt(st.nextToken());
        if(mtx[i][j]==-1){
          cleanerY = i;
        }
      }
    }

    int t = 0;
    while(t++<T){
      int[][] overlay = new int[Y][X];
      // 먼지 확산 계산
      for(int i=0; i<Y; i++){
        for(int j=0; j<X; j++){
          if(mtx[i][j]>0){
            spread(i, j, overlay);
          }
        }
      }
      // 확산 결과 적용
      for(int i=0; i<Y; i++){
        for(int j=0; j<X; j++){
          mtx[i][j] += overlay[i][j];
        }
      }
      // 공기청정기
      clean(cleanerY-1, deltasCW);
      clean(cleanerY, deltasCCW);
    }

    int result = 2; // 공기청정기 제외
    for(int i=0; i<Y; i++){
      for(int j=0; j<X; j++){
        result += mtx[i][j];
      }
    }

    System.out.println(Math.abs(result));
  }
}
