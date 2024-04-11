import java.util.*;
import java.io.*;

class Fire {
  boolean isAlive;
  int y;
  int x;
  int m; // 질량
  int d; // 방향
  int s; // 속력
  
  public Fire(int y, int x, int m, int d, int s) {
    isAlive = true;
    this.y = y;
    this.x = x;
    this.m = m;
    this.d = d;
    this.s = s;
  }
  
  void move(int N){
    y += ds[d][0] * s;
    x += ds[d][1] * s;
    
    y %= N;
    x %= N;
    
    if(y<0) y = N+y;
    if(x<0) x = N+x;
  }
  
  final static int[][] ds = {{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1}};
}

class Main {
  
  static List<Integer>[][] newMtx(int N){
    List<Integer>[][] mtx = new List[N][N];
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        mtx[i][j] = new ArrayList<>();
      }
    }
    return mtx;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());

    List<Fire> fires = new ArrayList<>();
    for(int i=0; i<M; i++) {
      st = new StringTokenizer(br.readLine());
      int y = Integer.parseInt(st.nextToken())-1; //r
      int x = Integer.parseInt(st.nextToken())-1; //c
      int m = Integer.parseInt(st.nextToken());
      int s = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      
      fires.add(new Fire(y, x, m, d, s));
    }
    
    int k = 0;
    while(k++<K) {
      // 1. move
      List<Integer>[][] mtx = newMtx(N);
      for(int i=fires.size()-1; i>=0; i--) {
        Fire fire = fires.get(i);
        if(!fire.isAlive) continue;
        fire.move(N);
        mtx[fire.y][fire.x].add(i); 
      }
      // 2. check
      for(int i=0; i<N; i++) {
        for(int j=0; j<N; j++) {
          if(mtx[i][j].size()<2) continue;
          int nM = 0; // 질량
          int nS = 0; // 속력
          int[] nDs = new int[4]; // 방향
          boolean isOdd = false;
          boolean isEven = false;
          
          Fire[] fs = new Fire[mtx[i][j].size()];
          int fsIdx = 0;
          for(int idx:mtx[i][j]) {
            fs[fsIdx++] = fires.get(idx);
          }
          
          for(Fire f:fs) {
            nM += f.m;
            nS += f.s;
            if(f.d%2==0) isEven = true;
            else isOdd = true;
          }
          
          nM /= 5;
          nS /= mtx[i][j].size();
          
          // remove old fire
          for(int idx:mtx[i][j]) {
            fires.get(idx).isAlive = false;
          }
          
          if(nM==0) {
            // 질량이 0이면 새로운 파이어볼 생성 x
            continue;
          }
          
          if((isOdd && !isEven) || (!isOdd && isEven)) {
            nDs = new int[]{0,2,4,6};
          } else {
            nDs = new int[]{1,3,5,7};
          }
          
          // add new fire
          for(int idx=0; idx<4; idx++) {
            fires.add(new Fire(i, j, nM, nDs[idx], nS));
          }
        }
      }
    }
    
    int result = 0;
    for(Fire f:fires) {
      if(f.isAlive) {
        result += f.m;        
      }
    }
    System.out.println(result);
  }
}
