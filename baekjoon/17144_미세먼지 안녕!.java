// WIP
import java.util.*;
import java.io.*;

public class Main {
  static int Y;
  static int X;
  static int[][] mtx;

  final static int[][] deltasCW = {{0,1},{1,0},{0,-1},{-1,0}};
  final static int[][] deltasCCW = {{0,1},{-1,0},{0,-1},{1,0}};

  static boolean inRange(int y, int x){
    return 0<=x&&x<X && 0<=y&&y<Y;
  }

  static void push(int[] wind, int[][] d){
    int y = wind[0];
    int x = wind[1];
    int i = wind[2];

    int ny = y + d[i][0];
    int nx = x + d[i][1];

    if(!inRange(ny, nx)){
      i += 1;
      ny = y + d[i][0];
      nx = x + d[i][1];
    }

    if(mtx[ny][nx]!=-1){
      mtx[ny][nx] += mtx[y][x]; // 밀어내기
      i = 0;
    }
    mtx[y][x] = 0;
    wind[0] = ny;
    wind[1] = nx;
    wind[2] = i;
  }

  static void spread(int y, int x, int[][] overlay){
    int power = Math.abs(mtx[y][x]/5);
    if(power<1) return;

    int cnt = 0;
    for(int[] d:deltasCW){
      int ny = y+d[0];
      int nx = x+d[1];
      if(inRange(ny, nx) && mtx[ny][nx]!=-1){
        overlay[ny][nx] -= power;
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

    int cleaner = -1; // 아래의 y값 저장

    mtx = new int[Y][X];
    for(int i=0; i<Y; i++){
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<X; j++){
        mtx[i][j] = Integer.parseInt(st.nextToken());
        if(mtx[i][j]==-1){
          cleaner = i;
        }
      }
    }

    int[] windCCW = new int[]{cleaner-1, 0, 0};
    int[] windCW = new int[]{cleaner, 0, 0};
    // y, x, 방향(delta)

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
      push(windCCW, deltasCCW);
      push(windCW, deltasCW);
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
