// 삼성 SW 역량테스트 2023 하반기 오전 1번 문제
// 1시간 40분 풀이, TC 5번 fail

// 0빈칸, 1함정, 2벽
// 기준값 (좌상)

/* 1.기사 이동*/
// 연쇄이동 (밀림)
// 연쇄 이동 상 못움직이는 경우: 모두 안움직임

/* 2. 대결 */
// 밀린 기사는, ㅁ리린 위치에서 w*h 내의 함정 수 만큼 피해 (함정 없다면 피해x)
// 기사들은 모두 밀린 후에 대미지르 ㄹ받음

// 체스판을 나갈 수 없다 (벽이기 때문)

import java.util.*;
import java.io.*;

class Knight extends Point {
  int n;
  int h;
  int w;
  int hp;
  
  public Knight(int y, int x, int n, int h, int w, int hp) {
    super(y, x);
    this.n = n;
    this.h = h;
    this.w = w;
    this.hp = hp;
  }
  
  public void move(int[] d) {
    y += d[0];
    x += d[1];
  }
}

class Point {
  int y;
  int x;
  
  public Point(int y, int x) {
    this.y = y;
    this.x = x;
  }
}

public class Main {
  final static int BLANK = 0;
  final static int TRAP = 1;
  final static int WALL = 2;
  
  static int L;
  static int N;
  static int Q;
  static int[][] mtx;
  
  final static int[][] ds = {{-1,0},{0,1},{1,0},{0,-1}};
  // y,s : 0위 1오 2아 3왼
  
  static Knight[] copy(Knight[] orgs) {
    Knight[] knights = new Knight[N];
    for(int i=0; i<N; i++) {
      Knight org = orgs[i];
      knights[i] = new Knight(org.y, org.x, org.n, org.h, org.w, org.hp);
    }
    return knights;
  }
  
  static boolean inRange(int y, int x) {
    return 0<=y&&y<L && 0<=x&&x<L;
  }
  
  static boolean hasWall(int y, int x) {
    if(!inRange(y, x)) return true;
    return mtx[y][x] == WALL;
  }
  
  static Knight[][] getKnMtx(Knight[] kns){
    Knight[][] knMtx = new Knight[L][L];
    
    for(Knight kn:kns) {
      if(kn.hp<=0) continue;
      for(int i=kn.y; i<kn.y+kn.h; i++) {
        for(int j=kn.x; j<kn.x+kn.w; j++) {
          knMtx[i][j] = kn;
        }
      }
    }
    
    return knMtx;
  }
  
  static int getDamage(Knight kn) {
    int dam = 0;
    for(int i=kn.y; i<kn.y+kn.h; i++) {
      for(int j=kn.x; j<kn.x+kn.w; j++) {
        if(inRange(i,j) && mtx[i][j]==TRAP) {
          dam++;
        }
      }
    }
    return dam;
  }
  
  static boolean tryMove(Knight start, int dir, Knight[] kns) {
    int[] d = ds[dir];
    
    Queue<Knight> queue = new LinkedList<>();
    boolean[] isMoved = new boolean[L];
    
    queue.add(start);
    isMoved[start.n] = true;
    
    while(!queue.isEmpty()) {
      // 해당 지점에 knight가 없을 때 까지
      Knight knight = queue.poll();
      // 0. Knight mtx 생성
      Knight[][] knMtx = getKnMtx(kns);
      Knight kn = knMtx[knight.y][knight.x];
      
      // 1. 움직이기
      kn.move(d);
      
      // 데미지 주기
      if(kn.n!=start.n) {
        kns[kn.n].hp -= getDamage(kn);
//        System.out.print((kn.n+1)+" get damage " + getDamage(kn));
//        System.out.printf(" (hp: %d)", kns[kn.n].hp);
//        System.out.println();
      }
      
      // 2. 내 영역에 벽이 있는지 체크하기
      // 3. 내 영역에 기사가 있는지 체크하기
      
      
      
      for(int i=kn.y; i<kn.y+kn.h; i++) {
        for(int j=kn.x; j<kn.x+kn.w; j++) {
          if(hasWall(i, j)) {
            return false;
          }
          if(knMtx[i][j] instanceof Knight) {
            Knight nextKn = knMtx[i][j];
            if(nextKn!=kn && nextKn.hp>0 && !isMoved[nextKn.n]) {
              isMoved[nextKn.n] = true;
              queue.add(nextKn);
            }
          }
        }
      }
    }
    
    return true;
  }
  
  static void print(Knight[] kns, String str) {
    System.out.println("["+str+"]");
    int[][] printMtx = new int[L][L];
    for(int n=0; n<N; n++) {
      Knight kn = kns[n];
      for(int i=kn.y; i<kn.y+kn.h; i++) {
        for(int j=kn.x; j<kn.x+kn.w; j++) {
          printMtx[i][j] = (n+1);
        }
      }
    }
    for(int i=0; i<L; i++) {
      for(int j=0; j<L; j++) {
        if(printMtx[i][j]>0)
          System.out.printf("%d ",printMtx[i][j]);
        else {
          if(mtx[i][j]==TRAP) System.out.print("X ");
          if(mtx[i][j]==BLANK) System.out.print(". ");
          if(mtx[i][j]==WALL) System.out.print("W ");
        }
      }
      System.out.println();
    }
    
    for(int i=0; i<N; i++) {
      System.out.printf("%d: %d | ", i+1, kns[i].hp);
    }
    System.out.println();
    System.out.println();
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    L = Integer.parseInt(st.nextToken());
    N = Integer.parseInt(st.nextToken()); // 기사 수 
    Q = Integer.parseInt(st.nextToken());
    
    mtx = new int[L][L];
    
    for(int i=0; i<L; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<L; j++) {
        mtx[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    
    Knight[] knights = new Knight[N];
    int[] originHps = new int[N];
    
    for(int n=0; n<N; n++) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken()) - 1;
      int c = Integer.parseInt(st.nextToken()) - 1;
      int h = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken()); // 체력
      knights[n] = new Knight(r, c, n, h, w, k);
      originHps[n] = k;
    }
    
    // ~ 데이터 입력
    
//    print(knights, "init");
    
    for(int q=0; q<Q; q++) {
      st = new StringTokenizer(br.readLine());
      int knNum = Integer.parseInt(st.nextToken())-1;
      int dir = Integer.parseInt(st.nextToken());
      Knight kn = knights[knNum];
      
      if(kn.hp<=0) continue;
      
      Knight[] kns = copy(knights);
      if(!tryMove(kn, dir, kns)) continue;
      
      for(int i=0; i<N; i++) {
        knights[i] = kns[i]; 
      }
//      print(knights, "moved " + q);
    }
    
    int result = 0;
    for(int i=0; i<N; i++) {
      if(knights[i].hp>0) {
        result += originHps[i]-knights[i].hp;
      }
    }
    System.out.println(result);
  }
}
