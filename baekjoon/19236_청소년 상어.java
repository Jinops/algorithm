// TODO: TC pass / 4% 실패
import java.util.*;
import java.io.*;

class Shark extends Fish {
  int eatSum;

  public Shark() {
    eatSum = 0;
  }
  
  public void replace(Shark shark) {
    super.replace(shark);
    this.eatSum = shark.eatSum;
  }

  void eat(Fish fish) {
    fish.isAlive = false;
    this.move(fish.y, fish.x);
    this.dir = fish.dir;
    eatSum += fish.num;
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
    this.y = y;
    this.x = x;
    this.dir = dir;
    this.num = num;
    this.isAlive = true;
  }
  
  public void replace(Fish fish) {
    this.y = fish.y;
    this.x = fish.x;
    this.dir = fish.dir;
    this.num = fish.num;
    this.isAlive = fish.isAlive;
    
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
}

public class Main {
  static int maxEatSum = 0;
  
  static boolean inRange(int x, int y) {
    return 0<=x&&x<4 && 0<=y&&y<4;
  }
  
  static void fishMove(Fish[] fishes, Shark shark, Fish[][] matrix) {
    for(Fish fish:fishes) {
      if(!fish.isAlive) {
        continue;
      }
      int originDir = fish.dir;
      boolean isValid = true;
      int ny = fish.nextY();
      int nx = fish.nextX();
      // inRange를 만족하고, shark가 아닐 때 break -> (inRange && !shark)
      while(isValid && !(inRange(nx, ny) && !(ny==shark.y && nx==shark.x))) {
        fish.rotate();
        if(fish.dir == originDir) {
          isValid = false;
          break;
        }
        ny = fish.nextY();
        nx = fish.nextX();
      }
      
      if(!isValid) {
        continue;
      }
      
      Fish targetFish = matrix[ny][nx];
      targetFish.move(fish.y, fish.x);
      matrix[fish.y][fish.x] = targetFish; 
      fish.move(ny, nx);
      matrix[ny][nx] = fish;
    }
  }
  
  static List<Fish> getEatableFishes(Fish[][] matrix, Shark shark) {
    int move = 1;
    int nx = shark.nextX(move);
    int ny = shark.nextY(move);
    List<Fish> eatables = new LinkedList<>();
    
    while(inRange(nx, ny)) {
      if(matrix[ny][nx].isAlive) {
        eatables.add(matrix[ny][nx]);
      }
      move++;
      nx = shark.nextX(move);
      ny = shark.nextY(move);

    }
    return eatables;
  }
  
  static Fish[][] getMatrix(Fish[] fishes, Shark shark){
    Fish[][] matrix = new Fish[4][4];
    for(Fish fish:fishes) {
      matrix[fish.y][fish.x] = fish;
    }
    
    return matrix;
  }
  
  static Fish[] copyFishes(Fish[] originFishes) {
    Fish[] fishes = new Fish[16];
    
    for(int i=0; i<16; i++) {
      fishes[i] = new Fish();
      fishes[i].replace(originFishes[i]);
    }
    return fishes;
  }

  static void run(Fish[] beforeFishes, Shark beforeShark) {
    Fish[] fishes = copyFishes(beforeFishes);
    Shark shark = new Shark();
    shark.replace(beforeShark);
    Fish[][] matrix = getMatrix(fishes, shark);
    
    List<Fish> eatableFishes = getEatableFishes(matrix, shark);
    if(eatableFishes.size()==0) {
      maxEatSum = Math.max(maxEatSum, shark.eatSum);
      return;
    }
    
    for(Fish eatablefish:eatableFishes) {
      shark.eat(eatablefish);
      
      fishMove(fishes, shark, matrix);
      run(fishes, shark);
      
      for(int i=0; i<16; i++) {
        fishes[i].replace(beforeFishes[i]);
      }
      shark = beforeShark;
      matrix = getMatrix(fishes, shark);
    }
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    Fish[] fishes = new Fish[16];
    Fish[][] matrix = new Fish[4][4];
    
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
    
    Shark shark = new Shark();
    shark.eat(matrix[0][0]);
    
    fishMove(fishes, shark, matrix);
    run(fishes, shark);
    
    System.out.println(maxEatSum);
  }
}
