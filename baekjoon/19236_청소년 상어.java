// WIP
import java.util.*;
import java.io.*;

class Shark extends Fish {
  int eatSum = 0;

  void eat(Fish fish) {
    fish.isAlive = false;
    this.move(fish.y, fish.x);
    this.dir = fish.dir;
    eatSum += fish.num;
    System.out.print("> eat " + fish.y +","+fish.x+"("+fish.num+")");
  }
}

class Fish {
  int y;
  int x;
  int dir;
  int num;
  boolean isAlive;
  
  private final static int[][] deltas = {null, {0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}}; // 12시부터 반시계

  public Fish() {}
  
  public Fish(int y, int x, int dir, int num) {
    super();
    this.y = y;
    this.x = x;
    this.dir = dir;
    this.num = num;
    isAlive = true;
  }
  public void move(int y, int x) {
    this.y = y;
    this.x = x;
  }
  public void rotate() {
    dir++;
    if(dir==9) {
      dir = 1;
    }
  }
  public int nextX() {
    return nextX(1);
  }
  public int nextX(int move) {
    return x + deltas[dir][0]*move;
  }
  public int nextY() {
    return nextY(1);
  }
  public int nextY(int move) {
    return y + deltas[dir][1]*move;
  }

  @Override
  public String toString() {
    return "[" + y + "," + x + ", (" + num + "), " + isAlive + "]";
  }
  
}

public class Main {
  
  static Fish[][] matrix = new Fish[4][4];
  static Fish[] fishes = new Fish[16];
  static Shark shark = new Shark();
  
  static boolean inRange(int x, int y) {
    return 0<=x&&x<4 && 0<=y&&y<4;
  }
  
  static void fishMove() {
    for(Fish fish:fishes) {
      if(!fish.isAlive) {
        continue;
      }

      while(!(inRange(fish.nextX(), fish.nextY()) && matrix[fish.nextY()][fish.nextX()] != shark)) {
        fish.rotate();
      }
      
      int ny = fish.nextY();
      int nx = fish.nextX();
      
      matrix[ny][nx].move(fish.y, fish.x);
      matrix[fish.y][fish.x] = matrix[ny][nx];
      matrix[ny][nx] = fish;
      fish.move(ny, nx);
    }
  }
  
  static boolean sharkMove() {
    int move = 1;
    while(inRange(shark.nextX(move), shark.nextY(move))) {
      if(matrix[shark.nextY(move)][shark.nextX(move)].isAlive) {
        System.out.print("> find " + shark.nextY(move) +","+shark.nextX(move));
        shark.eat(matrix[shark.nextY(move)][shark.nextX(move)]);
        return true;
      }
      move++;
    }
    return false;
  }
  
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    for(int i=0; i<4; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int j=0; j<4; j++) {
        int num = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());
        Fish fish = new Fish(i, j, dir, num);
        fishes[num-1] = fish;
        matrix[i][j] = fish;
      }
    }
    
    shark.eat(matrix[0][0]);
    boolean didEat = true;
    System.out.println();
    
    while(didEat) {
      System.out.print("[FishMove]");
      fishMove();
      System.out.print("[SharkMove]");
      didEat = sharkMove();
      System.out.println(" = " + shark.eatSum);
//      for(Fish f:fishes) {
//        System.out.println(f.toString());
//      }
    }
    
    System.out.println(shark.eatSum);
  }
  
}
