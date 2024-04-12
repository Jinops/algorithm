// 삼성 SW 역량테스트 2023 하반 오후 1번 문제
// 총 3시간 20분 / TC 2개 추가 디버깅

// 루돌프 -> (탈락하지 않은) 산타 움직임
// 탈락한 산타: 기절or격자 밖
// 거리 = (y-y)^2+(x-x)^2

// 2) 루돌프 움직임
// 가장 가까운 산타 - (2명 이상일 경우, r좌표 큰->c좌표 큰)
// 8방 돌진 (가장 가까워지는 방향으로)

// 3) 산타
// 탈락하지 않은, 1번부터 P번까지 
// 루돌프에게 가까워지는 방향 (상,우,하,좌 우선)
// 산타칸 못감
// 움직일 수 있는 칸 없거나, 루돌프로 안가까워진다면 안움직임
// 4방
// 매 턴이 끝나면, 탈락 안한 산타는 1점 추가 
// 산타 모두 탈락하면 ""즉시"" 종료

// 4) 충돌
// 루돌프 움직임에 따른 충돌 : 산타는 점수 C 획득
// - 산타는 루돌프가 온 방향만큼 C칸 밀려남
// 산타의 움직임에 따른 충돌 : 산타는 D 점수 획득
// - 산타는 자신의 이동 반대 방향으로 D칸 밀려남
// !! 밀려나는 것은 충돌 없이, 정확한 위치에 도달
// 게임 밖으로 밀리면 탈락
// 산타 칸 갈 경우 상호작용

// 5) 상호작용 (충돌 후 밀려날 때 산타 겹칠떄만)
// 원래 위치했던 산타는, 충돌 방향만큼 1칸 이동, 연쇄작용 가능, 게임 밖가면 탈락


// 6) 기절
// 상타 루돌프 충돌 시 k+1턴까지 기절 (k+2턴부터 움직임 가능)
// 기절한 상태서 상호작용은 가능

import java.io.*;
import java.util.*;

class Point {
  int y;
  int x;
  
  public Point(int y, int x) {
    super();
    this.y = y;
    this.x = x;
  }
  
  void move(int y, int x) {
    this.y = y;
    this.x = x;
  }

  void moveD(int[] d, int size) {
    this.y += d[0]*size;
    this.x += d[1]*size;
  }
  void moveD(int[] d) {
    moveD(d, 1);
  }
}

class Ru extends Point{

  public Ru(int y, int x) {
    super(y, x);
    // TODO Auto-generated constructor stub
  }
  
}

class Santa extends Point {
  int n;
  int point;
  boolean isAlive;
  int stunK;
  
  public Santa(int y, int x, int n, int point, boolean isAlive, int stunK) {
    super(y, x);
    this.n = n;
    this.point = point;
    this.isAlive = isAlive;
    this.stunK = stunK;
  }

  boolean isStun(int k) {
    return !(k>stunK+1);
  }
}

public class Main {
  static int N;
  static int C;
  static int D;
  
  static Ru ru;
  static Santa[] santas;
  
  static int k;
  
  static boolean inRange(int y, int x) {
    return 0<=y&&y<N && 0<=x&&x<N;
  }
  
  static int dist(int y1, int x1, int y2, int x2) {
    return (y1-y2)*(y1-y2) + (x1-x2)*(x1-x2);
  }
  
  static void printMtx(Santa[][] mtx, String str) {
    System.out.println("["+str+"]" + k);
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        if(i==ru.y&&j==ru.x) System.out.print("R  ");
        else if(mtx[i][j] instanceof Santa) {
          if(mtx[i][j].isStun(k)){
            System.out.printf("%1d* ", mtx[i][j].n);
          } else {
            System.out.printf("%1d  ", mtx[i][j].n);
          }
        }
        else System.out.print(".  ");
      }
      System.out.println();
    }
    System.out.println();
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    N = Integer.parseInt(st.nextToken()); // 판 크기
    int M = Integer.parseInt(st.nextToken()); // 턴
    int P = Integer.parseInt(st.nextToken()); // 산타 수 
    C = Integer.parseInt(st.nextToken()); // 루돌프 힘
    D = Integer.parseInt(st.nextToken()); // 산타 힘
    
    st = new StringTokenizer(br.readLine());
    int Rr = Integer.parseInt(st.nextToken())-1;
    int Rc = Integer.parseInt(st.nextToken())-1;
    ru = new Ru(Rr, Rc);
    
    santas = new Santa[P+1];
    santas[0] = new Santa(-1, -1, 0, 0, false, -1); // dummy
    
    for(int i=0; i<P; i++) {
      st = new StringTokenizer(br.readLine());
      int Pn = Integer.parseInt(st.nextToken());
      int Sr = Integer.parseInt(st.nextToken())-1;
      int Sc = Integer.parseInt(st.nextToken())-1;
      
      santas[Pn] = new Santa(Sr, Sc, Pn, 0, true, -1);
    }
    //// ~ input
    
    k=1; // 턴 
    while(k<=M) {
      Santa[][] mtx = generateSantaMtx(santas);
      // TODO: check all santa valid
//      printMtx(mtx, "init");
      
      Santa target = getNearestSanta(mtx);
      // 가장 가까운 산타 찾고
      int[] ruDir = getRuDir(target);
      // 루돌프 가는 방향 찾는다
      ruMove(ruDir, mtx);
      
      mtx = generateSantaMtx(santas);
//      printMtx(mtx, "after move");
      
      
      for(Santa santa:santas) {
        if(!santa.isAlive || santa.isStun(k)) continue;
//        System.out.println("santa try move "+santa.n);
        // 탈락했거나, 스턴걸린 산타는 스스로 못움직인다.
        santaMove(santa, mtx);
        mtx = generateSantaMtx(santas);

//        printMtx(mtx, "Santa move");
        // santa move 이후 mtx를 갱신
      }
      
      // 턴 종료 
      boolean isAnyAlive = false;
      for(Santa s:santas) {
        if(s.isAlive) {
          s.point += 1;
          isAnyAlive = true;
        }
      } // 살아남은 산타 포인트 증가  
      if(!isAnyAlive) {
        break;
      }
      k++;
    }
    
    Arrays.sort(santas, new Comparator<Santa>() {
      @Override
      public int compare(Santa o1, Santa o2) {
        return o1.n - o2.n;
      }
    });
    StringBuilder sb = new StringBuilder();
    for(int i=1; i<=P; i++) {
      sb.append(santas[i].point).append(' ');
    }
    System.out.println(sb);
  }

  
  final static int[][] sds = new int[][]{{-1,0},{0,1},{1,0},{0,-1}};
  static int[] reverseD(int[] d){
    int[] nd = new int[2];
    nd[0] = d[0]*-1;
    nd[1] = d[1]*-1;
    return nd;
  }
  // (y,x) 상 우 하 좌
  private static void santaMove(Santa santa, Santa[][] mtx) {
    int nearDist = Integer.MAX_VALUE;
    int nearD[] = null;
    
    for(int[] d:sds) {
      int ny = santa.y+d[0];
      int nx = santa.x+d[1];
      
      if(!inRange(ny, nx) || mtx[ny][nx] instanceof Santa) continue;
      // 밖으로 나가거나, santa 겹치는 경우
      
      int dist = dist(ny, nx, ru.y, ru.x);
//      System.out.println(ny+","+nx);
//      System.out.println(dist);
      if(dist < nearDist) {
        nearDist = dist;
        nearD = d;
      }
    }
//   System.out.println();
    
    if(nearDist >= dist(santa.y, santa.x, ru.y, ru.x)) {
      // 거리가 가까워지는 방향이 없다면 return
      return;
    }
    
    // 산타 이동
    santa.moveD(nearD);
    
    if(nearDist!=0) return; // 충돌이 발생하지 않았다면 ealry return
    
    // 산타->루돌프 충돌이 발생한 경우
    santa.point += D;
    int d[] = reverseD(nearD);
    santa.moveD(d, D);
    santa.stunK = k;
    
    if(!inRange(santa.y, santa.x)) {
      // 장외로 벗어났다면 
      santa.isAlive = false;
      return;
    }
    
    int ny = santa.y;
    int nx = santa.x;
    
    if(!santa.equals(mtx[ny][nx])) {
      // 다시 원래 위치로 갔을 때는, chain Move x
      chainMove(mtx, ny, nx, d);
    }
  }

  private static void ruMove(int[] ruDir, Santa[][] mtx) {
    ru.moveD(ruDir);
    
    if(!(mtx[ru.y][ru.x] instanceof Santa)) return;
    // 루돌프->빈칸 갈 경우 
    

    // 루돌프->산타 충돌
    Santa santa = mtx[ru.y][ru.x];
    santa.point += C;
    santa.stunK = k;
    santa.moveD(ruDir, C);
    
    int ny = santa.y;
    int nx = santa.x;
    
//    System.out.println(ny+","+nx);
    if(!inRange(ny, nx)) {
      santa.isAlive = false;
      return;
    }
    
    chainMove(mtx, ny, nx, ruDir);
  }
  
  static void chainMove(Santa[][] mtx, int ny, int nx, int[] dir) { // 연쇄작용
    // 산타가 한 번이라도 움직여서 ny nx에 도착했을 때 실행한다.
    while(mtx[ny][nx] instanceof Santa) {
      // 연쇄작용으로 밀리기
      Santa santa = mtx[ny][nx];
      santa.moveD(dir);
      
      ny = santa.y;
      nx = santa.x;
      
      if(!inRange(ny, nx)) {
        santa.isAlive = false;
        return;
      }
    }
  }

  private static int[] getRuDir(Santa target) {
    int[] nearD = null;
    int nearDist = Integer.MAX_VALUE;
    for(int[] d:rds) {
      int ny = ru.y+d[0];
      int nx = ru.x+d[1];
      if(!inRange(ny, nx)) continue;
      int dist = dist(target.y, target.x, ny, nx);
      if(dist<nearDist) {
        nearD = d;
        nearDist = dist;
      }
    }
    
    return nearD;
  }


  static int[][] rds = {{-1,0},{0,1},{1,0},{0,-1},{-1,-1},{1,-1},{-1,1},{1,1}};
  //(y,x)
  private static Santa getNearestSanta(Santa[][] mtx) {
    Santa nearSanta = null;
    int nearDist = Integer.MAX_VALUE;
    
    Queue<Point> queue = new LinkedList<>();
    boolean[][] visited = new boolean[N][N];
    
    queue.add(ru);
    visited[ru.y][ru.x] = true;
    
    while(!queue.isEmpty()) {
      //  (2명 이상일 경우, r좌표 큰->c좌표 큰)
      Point p = queue.poll();
      if(mtx[p.y][p.x] instanceof Santa) {
        Santa santa = mtx[p.y][p.x];
        int dist = dist(ru.y, ru.x, santa.y, santa.x);
        if(dist<nearDist) {
          nearSanta = santa;
          nearDist = dist;
        } else if(dist==nearDist) {
          if(santa.y>nearSanta.y ||
              (santa.y==nearSanta.y && santa.x>nearSanta.x)){
            // r이 동일한 경우, c 좌표가 큰 산타를 향해 돌진합니다.
            nearSanta = santa;
            nearDist = dist;
          }
        }
        // 갱신 안하거나, dist>nearDist
        continue;
      }
      
      for(int[] d:rds) {
        Point np = new Point(p.y+d[0], p.x+d[1]);
        if(inRange(np.y, np.x) && !visited[np.y][np.x]) {
          visited[np.y][np.x]= true;
          queue.add(np);
        }
      }
    }
    
//    for(int i=1; i<santas.length; i++) {
//      System.out.print(santas[i].point + " ");
//    }System.out.println();
    return nearSanta;
  }

  private static Santa[][] generateSantaMtx(Santa[] santas) {
    // 이번 턴에 가용 가능한(살아 있는) 산타 - 기절 포함 
    Santa[][] mtx = new Santa[N][N];
    for(Santa santa:santas) {
      if(santa.isAlive) {
        mtx[santa.y][santa.x] = santa;
      }
    }
    
    return mtx;
  }
}
