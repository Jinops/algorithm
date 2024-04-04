import java.util.*;
import java.io.*;

// (이동하는 방향 끝을 기준으로, 순차적으로 옮긴다)

class Board {
  int[][] mtx;
  int cnt;
  
  public Board(int[][] mtx, int cnt) {
    this.mtx = mtx;
    this.cnt = cnt;
  }
}

class Main {
  static int N;
  
  static int[][] copy(int[][] origin){
    int[][] mtx = new int[N][N];
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        mtx[i][j] = origin[i][j];
      }
    }
    return mtx;
  }
  
  static void moveLeft(int[][] mtx) {
    boolean[][] isChanged = new boolean[N][N];
    
    for(int y=0; y<N; y++) {
      for(int x=1; x<N; x++) {
        int nx = x;
        while((nx-1)>=0 && mtx[y][nx-1]==0) {
          nx -= 1; // 빈칸인 경우 넘어갈 수 있다.
        }
        if((nx-1)>=0 && mtx[y][nx-1]==mtx[y][x] && !isChanged[y][nx-1]) {
          // 넘어갈 위치가 합쳐질 수 있다면(값이 일치하다면) 2배
          nx -= 1;
          mtx[y][x] *= 2; // 다음 if문에서 값을 옮긴다
          isChanged[y][nx] = true;
        }
        if(nx!=x) {
          // 위치 변화가 있다면 기존 위치 값 제거
          mtx[y][nx] = mtx[y][x];
          mtx[y][x] = 0;
        }
      }
    }
  }

  static void moveRight(int[][] mtx) {
    boolean[][] isChanged = new boolean[N][N];
    
    for(int y=0; y<N; y++) {
      for(int x=N-2; x>=0; x--) {
        int nx = x;
        while((nx+1)<N && mtx[y][nx+1]==0) {
          nx += 1; // 빈칸인 경우 넘어갈 수 있다.
        }
        if((nx+1)<N && mtx[y][nx+1]==mtx[y][x] && !isChanged[y][nx+1]) {
          // 넘어갈 위치가 합쳐질 수 있다면(값이 일치하다면) 2배
          nx += 1;
          mtx[y][x] *= 2; // 다음 if문에서 값을 옮긴다
          isChanged[y][nx] = true;
        }
        if(nx!=x) {
          // 위치 변화가 있다면 기존 위치 값 제거
          mtx[y][nx] = mtx[y][x];
          mtx[y][x] = 0;
        }
      }
    }
  }
  
  static void moveUp(int[][] mtx) {
    boolean[][] isChanged = new boolean[N][N];
    
    for(int x=0; x<N; x++) {
      for(int y=1; y<N; y++) {
        int ny = y;
        while((ny-1)>=0 && mtx[ny-1][x]==0) {
          ny -= 1; // 빈칸인 경우 넘어갈 수 있다.
        }
        if((ny-1)>=0 && mtx[ny-1][x]==mtx[y][x] && !isChanged[ny-1][x]) {
          // 넘어갈 위치가 합쳐질 수 있다면(값이 일치하다면) 2배
          ny -= 1;
          mtx[y][x] *= 2; // 다음 if문에서 값을 옮긴다
          isChanged[ny][x] = true;
        }
        if(ny!=y) {
          // 위치 변화가 있다면 기존 위치 값 제거
          mtx[ny][x] = mtx[y][x];
          mtx[y][x] = 0;
        }
      }
    }
  }
  
  static void moveDown(int[][] mtx) {
    boolean[][] isChanged = new boolean[N][N];
    
    for(int x=0; x<N; x++) {
      for(int y=N-2; y>=0; y--) {
        int ny = y;
        while((ny+1)<N && mtx[ny+1][x]==0) {
          ny += 1; // 빈칸인 경우 넘어갈 수 있다.
        }
        if((ny+1)<N && mtx[ny+1][x]==mtx[y][x] && !isChanged[ny+1][x]) {
          // 넘어갈 위치가 합쳐질 수 있다면(값이 일치하다면) 2배
          ny += 1;
          mtx[y][x] *= 2; // 다음 if문에서 값을 옮긴다
          isChanged[ny][x] = true;
        }
        if(ny!=y) {
          // 위치 변화가 있다면 기존 위치 값 제거
          mtx[ny][x] = mtx[y][x];
          mtx[y][x] = 0;
        }
      }
    }
  }

  static int max(int[][] mtx) {
    int max = 0;
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        max = Math.max(max, mtx[i][j]);
      }
    }
    return max;
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    N = Integer.parseInt(br.readLine());
    int[][] mtx = new int[N][N];
    
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<N; j++) {
        mtx[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    
    Queue<Board> queue = new LinkedList<>();
    queue.add(new Board(mtx, 0));
    
    int result = 0;
    
    while(!queue.isEmpty()) {
      Board b = queue.poll();
      if(b.cnt==5) {
        result = Math.max(result, max(b.mtx));
        continue;
      }
      
      int[][] mtxL = copy(b.mtx);
      int[][] mtxR = copy(b.mtx);
      int[][] mtxU = copy(b.mtx);
      int[][] mtxD = copy(b.mtx);
      
      moveLeft(mtxL);
      moveRight(mtxR);
      moveUp(mtxU);
      moveDown(mtxD);

      queue.add(new Board(mtxL, b.cnt+1));
      queue.add(new Board(mtxR, b.cnt+1));
      queue.add(new Board(mtxU, b.cnt+1));
      queue.add(new Board(mtxD, b.cnt+1));
    }
    
    System.out.println(result);
  }
}
