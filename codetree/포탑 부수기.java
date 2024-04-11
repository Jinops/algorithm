// 삼성 SW 역량테스트 2023 상반기 오전 1번 문제
// 1시간 30분 풀이, TC3번 fail -> 1시간 디버깅
import java.util.*;
import java.io.*;

// 종료조건: 남은 포탑 1개
// 공격자: 가장 약한 포탑
// - 공격력 낮은 - 가장 최근 공격 - 행+열 가장 큰 - 열 가장 큰 
// 공격력 N+M 증가

// 가장 강한 포탑을 공격
// - 공격력 높은 - 가장 오래된 - 행+열 가장 작은 - 열 가장 작은

// 공격방식 //
// 1.레이저 공격
// 상하좌우 움직임, 부서진 포탑 못감, 반대편으로 나올 수 있음
// 최단거리를 공격
// - 최단 2개 : 우,하,좌,상 우선순위로 움직임
// 공격대상: 공격자 공격력만큼 공격력 줄어듦
// 경로 대상: 공격자 공격력/2만큼 공격받음

// 2.포탄 공격
// 공격대상: 공격자 공격력만큼 줄얻름
// 주변 8개: 공격자 공격력/2만큼 줄어듦
// - 반대편도 연결됨

// 공격력 0이하 되면 부서짐

// 포탑 정비
// 공격,피해 받지 않은 나머지 포탑 공격력+1

class Point {
  int y;
  int x;
  Point parent;
  
  public Point(int y, int x, Point parent) {
    this.y = y;
    this.x = x;
    this.parent = parent;
  }
}

class Tower implements Comparable<Tower>{
  @Override
  public String toString() {
    return "[" + y + ", " + x + ", att=" + att + ", lastK=" + lastK + "]";
  }

  int y;
  int x;
  int att; // 공격력
  int lastK; // 마지막 공격
  
  public Tower(int y, int x, int att, int lastK) {
    this.y = y;
    this.x = x;
    this.att = att;
    this.lastK = lastK;
  }
  
  @Override
  public int compareTo(Tower o) {
 // - 공격력 낮은 - 가장 최근 공격 - 행+열 가장 큰 - 열 가장 큰 
    if(att!=o.att) return att - o.att; // 오름차순 
    if(lastK!=o.lastK) return o.lastK - lastK; // 내림차순
    if((y+x)!=(o.y+o.x)) return (o.y+o.x) - (y+x); // 내림차순
    return o.x - x; // 내림차순
  }
}

public class Main {
  static int N;
  static int M;
  static int K;
  static int[][] mtx;
  
  final static int[][] ds = {{0,1},{1,0},{0,-1},{-1,0}};
  // (y,x) 우,하,좌,상 우선순위
  final static int[][] bombDs = {{0,1},{1,0},{0,-1},{-1,0},{1,1},{-1,1},{-1,-1},{1,-1}};
 
  static Point getNewPoint(Point p, int[] d) {
    int y = p.y+d[0];
    int x = p.x+d[1];
    
    if(y<0) y=N-1;
    else if(y>=N) y=0;
    
    if(x<0) x=M-1;
    else if(x>=M) x=0;
    
    return new Point(y, x, p);
  }
  
  static List<Point> bombAttack(Tower from, Tower to) {
    mtx[to.y][to.x] -= from.att; 
    
    List<Point> targets = new ArrayList<>();
    Point p = new Point(to.y, to.x, null);
    
    targets.add(p);
    
    for(int[] d:bombDs) {
      Point np = getNewPoint(p, d);
      targets.add(np);
      mtx[np.y][np.x] -= from.att/2; 
    }
    
    return targets;
  }
  
  static List<Point> laserAttack(Tower from, Point to) {
    mtx[to.y][to.x] -=from.att;

    List<Point> targets = new ArrayList<>();
    Point p = to.parent;
    
    targets.add(to);
    
    while(p!=null) {
      targets.add(p);
      mtx[p.y][p.x]-= from.att/2;
      p = p.parent;
    }
    
    return targets;
  }
  
  static List<Point> tryLaserAttack(Tower from, Tower to) {
    Queue<Point> queue = new LinkedList<>();
    boolean[][] visited = new boolean[N][M];
    
    queue.add(new Point(from.y, from.x, null));
    visited[from.y][from.x] = true; 
    
    while(!queue.isEmpty()) {
      Point p = queue.poll();
      
      if(p.y==to.y && p.x==to.x) {
        return laserAttack(from, p);
      }
      
      for(int[] d:ds) {
        Point np = getNewPoint(p, d);
        if(mtx[np.y][np.x]>0 && !visited[np.y][np.x]) {
          visited[np.y][np.x] = true;
          queue.add(np);
        }
      }
    }
    
    return new ArrayList<Point>();
  }
  
  static void updateAtt(Tower from, int k) {
    from.att += (N+M);
    from.lastK = k;
  }
  
  static void updateMtx(Tower from, List<Point> targets) {
    boolean[][] updated = new boolean[N][M];
    
    updated[from.y][from.x] = true; 
    
    for(Point p:targets) {
      updated[p.y][p.x] = true; 
    }
    
    mtx[from.y][from.x] = from.att; 
    
    for(int i=0; i<N; i++) {
      for(int j=0; j<M; j++) {
        if(mtx[i][j]<0) mtx[i][j] = 0;
        if(mtx[i][j]==0 || updated[i][j]) continue;
        mtx[i][j] += 1;
      }
    }
  }
  
  static List<Tower> getTowers(List<Tower> origin) {
    List<Tower> towers = new LinkedList<>();
    for(Tower t:origin) {
      if(mtx[t.y][t.x]!=0) {
        t.att = mtx[t.y][t.x];
        towers.add(t);
      }
    }
    return towers;
  }
  
  static void print(Tower from, Tower to) {
    for(int i=0; i<N; i++) {
      for(int j=0; j<M; j++) {
        if(i==from.y&&j==from.x) {
          System.out.printf("[%4d] ", mtx[i][j]);
        }
        else if(i==to.y&&j==to.x) {
          System.out.printf("<%4d> ", mtx[i][j]);
        }
        else System.out.printf("%6d ", mtx[i][j]);
      }
      System.out.println();
    }
    System.out.println();
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());
    mtx = new int[N][M];
    
    List<Tower> towers = new LinkedList<>(); 
    // 유효한 포탑만
    
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<M; j++) {
        mtx[i][j] = Integer.parseInt(st.nextToken());
        if(mtx[i][j]>0) {
          towers.add(new Tower(i, j, mtx[i][j], 0));
        }
      }
    }
    Collections.sort(towers);
    
    int k = 0; // 턴
    while(towers.size()>1 && k++<K) {
      Tower from = towers.get(0);
      Tower to = towers.get(towers.size()-1);
      
      updateAtt(from, k);
      
      List<Point> targets = tryLaserAttack(from, to);
      // include 'to'
      if(targets.size()==0) { // 레이저 공격 실패
        targets = bombAttack(from, to);
      }
      
      updateMtx(from, targets);
      towers = getTowers(towers);
      Collections.sort(towers);
    }
    
    if(towers.size()==0) System.out.println(0);
    else System.out.println(towers.get(towers.size()-1).att);
  }
}
/*
디버깅
- mtx를 업데이트 시, 주변(경로) 공격 외 나머지 지점을 처리할 때 최종 공격지점이 포함되어 있었음
- 따라서 targets에 최종 공격지점을 포함하도록 함
- 그 외 배열 크기 지정 시 오타(N<->M) 등
*/
