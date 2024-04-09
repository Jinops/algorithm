import java.util.*;
import java.io.*;

// inRange에서 벗어난 모래의 합을 구한다.
// 1. 가운데 칸부터 토네이도 한 칸 씩 이동하는 것
// - 1,1, 2,2, 3,3, 4,4, 5,5, 6,6, ...
// 2. 한 칸 이동 시 마다, 흩날리는 것
// - 흩날리는 것을 계산하기 위해, 방향성을 알아야 한다.
// - 이전에 이동한 방향으로부터, 내 기준의 방향을 구하여 주변으로 흩날리도록 한다.

class Main {  
  final static int[][] ds = {{0,-1},{1,0},{0,1},{-1,0}};
  // 0좌 1하 2우 3상
  
  static int result = 0;
  
  static int N;
  static int[][] mtx;
  
  static boolean inRange(int y, int x) {
    return 0<=y&&y<N && 0<=x&&x<N;
  }
  static int set(int y, int x, double v) {
    int value = (int) v;
    if(!inRange(y, x)) result += value;
    else mtx[y][x] += value;
    return value;
  }
  
  static void wind(int y, int x, int dir) {
    // y, x는 이미 도착한 위치
    int remain = mtx[y][x];
    int[] d = ds[dir];
    if(dir%2==0) { // 좌 우 
      remain-=set(y+1, x-d[1], mtx[y][x]*0.01);
      remain-=set(y-1, x-d[1], mtx[y][x]*0.01);
      
      remain-=set(y+1, x, mtx[y][x]*0.07);
      remain-=set(y-1, x, mtx[y][x]*0.07);
      remain-=set(y+2, x, mtx[y][x]*0.02);
      remain-=set(y-2, x, mtx[y][x]*0.02);
      
      remain-=set(y+1, x+d[1], mtx[y][x]*0.10);
      remain-=set(y-1, x+d[1], mtx[y][x]*0.10);
      remain-=set(y, x+d[1]*2, mtx[y][x]*0.05);
      
    } else { // 상 하
      remain-=set(y-d[0], x+1, mtx[y][x]*0.01);
      remain-=set(y-d[0], x-1, mtx[y][x]*0.01);
      
      remain-=set(y, x+1, mtx[y][x]*0.07);
      remain-=set(y, x-1, mtx[y][x]*0.07);
      remain-=set(y, x+2, mtx[y][x]*0.02);
      remain-=set(y, x-2, mtx[y][x]*0.02);
      
      remain-=set(y+d[0], x+1, mtx[y][x]*0.10);
      remain-=set(y+d[0], x-1, mtx[y][x]*0.10);
      remain-=set(y+d[0]*2, x, mtx[y][x]*0.05);
      
    }

    set(y+d[0], x+d[1], remain);
    mtx[y][x] = 0;
  }
  
  static int rotate(int dir) {
    return (dir+1)%4;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    N = Integer.parseInt(br.readLine());
    mtx = new int[N][N];
    
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<N; j++) {
        mtx[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    
    int y = N/2;
    int x = N/2;
    int dir = 3; // 가장 처음 상->좌로 rotate 예정
    
    int step = 0; //
    int remainMove = 0; // 1 1 2 2 3 3 4 4
    
    int[] move = new int[1000];
    for(int i=1; i<1000; i++) {
      move[i] = (i+1)/2;
    }

    while(!(y==0 && x==0)) {
      if(remainMove==0) {
        dir = rotate(dir);
        step++;
        remainMove += (step+1)/2;
      }
      
      remainMove--;
      int[] d = ds[dir];      
      y+=d[0];
      x+=d[1];
      
      wind(y, x, dir);
    }
    
    System.out.println(result);
  }
}
