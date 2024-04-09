import java.io.*;
import java.util.*;

// 그룹: 단일 색상 및 무지개 포함(검정 비포함) 하는 2개 이상 연결된 블록 
// 기준블록: 행 작-열 작

// 1. 가장 큰 블록 그룹(동일 시 무지 많-기준 행 큰-열 큰) 찾기 
// 2. (1) 그룹의 모든 블록 제거 
// 3. 중력 적용 (검은 블록 제외) - 아래로 떨어짐 
// 4. 반시계 90도 회전 (좌로) 
// 5. 중력 적용 
// 6. (1)을 반복하되, 블록 그룹이 없으면 제거 

class Group implements Comparable<Group>{
  Block head;
  List<Block> blocks;
  int rainbowCnt;
  
  public Group(Block head, List<Block> blocks, int rainbowCnt) {
    this.head = head;
    this.blocks = blocks;
    this.rainbowCnt = rainbowCnt;
  }
  
  public void print() {
    System.out.println(head.toString());
    for(Block b:blocks) System.out.print(b.toString()); System.out.println();
    System.out.println(rainbowCnt);
  }

  @Override
  public int compareTo(Group o) {
    if(blocks.size()!=o.blocks.size()) {
      // block 개수의 내림차순 
      return o.blocks.size() - blocks.size();
    }
   if(rainbowCnt!=o.rainbowCnt) {
     //무지개 블록 개수의 내림차순
     return o.rainbowCnt - rainbowCnt;
   }
   if(head.y!=o.head.y) {
     //기준블록 행의 내림차순 
     return o.head.y - head.y;
   }
   else {
     // 열의 내림차순
     return o.head.x - head.x;
   }
  }
}

class Block implements Comparable<Block> {
  int y;
  int x;
  
  public Block(int y, int x) {
    this.y = y;
    this.x = x;
  }

  @Override
  public int compareTo(Block o) {
   if(y!=o.y) return y-o.y;
   return x-o.x;
  }
  
  @Override
  public String toString() {
    return "[" + y + ", " + x + "]";
  }
}

public class Main {
  static int N;
  static int M;
  static int[][] mtx;
  
  static int[][] ds = {{0,1},{1,0},{0,-1},{-1,0}};
  
  static boolean inRange(Block b) {
    return 0<=b.y&&b.y<N && 0<=b.x&&b.x<N;
  }
  
  static void gravity() {
    for(int x=0; x<N; x++) { // x
      for(int y=N-2; y>=0; y--) { // y
        if(mtx[y][x]==BLACK) continue;
        int ny = y;
        while(ny+1<N && mtx[ny+1][x] == EMPTY) {
          ny += 1;
        }
        if(ny!=y) { // 떨어질 수 있을 때 
          mtx[ny][x] = mtx[y][x];
          mtx[y][x] = EMPTY;
        }
      }
    }
  }
  
  static int BFS(Block start, List<Block> blocks) {
    int rainbowCnt = 0;
    
    Queue<Block> queue = new LinkedList<>();
    boolean[][] visited = new boolean[N][N];
    
    queue.add(start);
    visited[start.y][start.x] = true;
    
    while(!queue.isEmpty()) {
      Block b = queue.poll();
      blocks.add(b);
      
      for(int[] d:ds) {
        Block nb = new Block(b.y+d[0], b.x+d[1]);
        if(inRange(nb) && !visited[nb.y][nb.x]) {
          if(mtx[start.y][start.x]==mtx[nb.y][nb.x]) {
            visited[nb.y][nb.x] = true;
            queue.add(nb);
            
          } else if(mtx[nb.y][nb.x]==RAINBOW) {
            visited[nb.y][nb.x] = true;
            queue.add(nb);
            rainbowCnt += 1;
          }
        }
      }
    }
    
    return rainbowCnt;
  }
  
  static List<Group> getGroups(){
    List<Group> groups = new ArrayList<>();
    boolean[][] visited = new boolean[N][N];
    
   for(int i=0; i<N; i++) {
     for(int j=0; j<N; j++) {
       if(!visited[i][j] && isNormalBlock(mtx[i][j])) {
         List<Block> blocks = new ArrayList<>();
         int rainbowCnt = BFS(new Block(i, j), blocks); // update blocks
         
         if(blocks.size()>=2) {
           Collections.sort(blocks);
           Block head = null;
           for(Block b:blocks) {
             if(isNormalBlock(mtx[b.y][b.x])) {
               head = b;
               break;
             }
           } // 틀렸던 점: head를 단순히 Block(i,j)로 하였음
           groups.add(new Group(head, blocks, rainbowCnt));
         }
       }
     }
   }

   Collections.sort(groups);
   return groups;
  }
  
  final static int BLACK = -1;
  final static int RAINBOW = 0;
  static int EMPTY;
  
  static boolean isNormalBlock(int n) {
    return 0<n&&n<=M;
  }
  
  static void print() {
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        if(mtx[i][j]==EMPTY) System.out.printf(" .  ");
        else System.out.printf("%3d ", mtx[i][j]);
      }
      System.out.println();
    }
    System.out.println();
  }
  
  static void rotate() {
    int[][] newMtx = new int[N][N];
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        newMtx[i][j] = mtx[j][N-1-i];
      }
    }
    mtx = newMtx;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    mtx = new int[N][N];
    
    EMPTY = Integer.MAX_VALUE;
    
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<N; j++) {
        mtx[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    
    int result = 0;
    
    List<Group> groups = getGroups();
    
    while(groups.size()>0) {
      Group group = groups.get(0);
      for(Block b:group.blocks) {
        mtx[b.y][b.x] = EMPTY; 
      }
      result += Math.pow(group.blocks.size(), 2);
      
      gravity();
      rotate();
      gravity();
      
      groups = getGroups();
    }
    
    System.out.println(result);
  }
}
