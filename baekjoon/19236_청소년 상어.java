// WIP
import java.util.*;
import java.io.*;

class Shark extends Fish {
  int eatSum;

  public Shark() {
    eatSum = 0;
  }
  
  public Shark(Shark shark) {
    super(shark);
    eatSum = shark.eatSum;
  }

  void eat(Fish fish) {
    fish.isAlive = false;
    this.move(fish.y, fish.x);
    this.dir = fish.dir;
    System.out.print("> eat("+ this.eatSum + ")" + fish.y +","+fish.x+"("+fish.num+")");
    eatSum += fish.num;
  }
  
  void undoEat(Fish fish, int originY, int originX, int originDir) {
    fish.isAlive = true;
    eatSum -= fish.num;
    this.y = originY;
    this.x = originX;
    this.dir = originDir;
  }
}

class Fish {
  int y;
  int x;
  int dir;
  int num;
  boolean isAlive;
  Fish beforeState;
  
  private final static int[][] deltas = {null, {0,-1},{-1,-1},{-1,0},{-1,1},{0,1},{1,1},{1,0},{1,-1}}; // 12시부터 반시계

  public Fish() {}
  
  public Fish(int y, int x, int dir, int num) {
    this.y = y;
    this.x = x;
    this.dir = dir;
    this.num = num;
    isAlive = true;
  }
  
  public Fish(Fish fish) {
    this.y = fish.y;
    this.x = fish.x;
    this.dir = fish.dir;
    this.num = fish.num;
    isAlive = fish.isAlive;
    
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
  static int maxEatSum = 0;
  
  static boolean inRange(int x, int y) {
    return 0<=x&&x<4 && 0<=y&&y<4;
  }
  
  static void fishMove(Fish[] fishes, Shark shark, Fish[][] matrix) {
    for(Fish fish:fishes) {
      if(!fish.isAlive) {
        continue;
      }
      
      // inRange를 만족하고, shark가 아닐 때 break -> (inRange && !shark)
      while(!(inRange(fish.nextX(), fish.nextY()) && matrix[fish.nextY()][fish.nextX()] != shark)) {
        fish.rotate();
      }
      
      int ny = fish.nextY();
      int nx = fish.nextX();
      
      Fish targetFish = matrix[ny][nx];
      matrix[fish.y][fish.x] = targetFish; 
      targetFish.move(fish.y, fish.x);
      matrix[ny][nx] = fish;
      fish.move(ny, nx);
    }
  }
  
  static List<Fish> getEatableFishes(Fish[][] matrix, Shark shark) {
    int move = 1;
    int nx = shark.nextX(move);
    int ny = shark.nextY(move);
    List<Fish> eatables = new LinkedList<>();
    
    while(inRange(nx, ny)) {
      if(matrix[ny][nx].isAlive) {
        System.out.print("(" + ny +","+nx + ")");
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
    matrix[shark.y][shark.x] = shark;
    return matrix;
  }
  
  static Fish[] copyFishes(Fish[] originFishes) {
    Fish[] fishes = new Fish[16];
    
    for(int i=0; i<16; i++) {
      fishes[i] = new Fish(originFishes[i]);
    }
    return fishes;
  }
  
  static void run(Fish[] beforeFishes, Shark beforeShark) {
    System.out.println("\n[run " + beforeShark.y +","+beforeShark.x+",("+beforeShark.eatSum+")]");
    Fish[] fishes = copyFishes(beforeFishes);
    Shark shark = new Shark(beforeShark);
    Fish[][] matrix = getMatrix(fishes, shark);
    
    for(int i=0; i<4; i++) {
      for(int j=0; j<4; j++) {
        System.out.printf("%3d,",matrix[i][j].num);
      }
      System.out.println();
    }
    
    
    fishMove(fishes, shark, matrix);
    System.out.print("[SharkMove ");
    List<Fish> eatableFishes = getEatableFishes(matrix, shark);
    System.out.print("]");
    if(eatableFishes.size()==0) {
      System.out.println(" > FINISH " + shark.eatSum+">"+maxEatSum+"\n");
      maxEatSum = Math.max(maxEatSum, shark.eatSum);
      return;
    }
    
    for(Fish fish:eatableFishes) {
      shark.eat(fish);
      run(fishes, shark);
      fishes = beforeFishes;
      shark = beforeShark;
    }
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    
    Fish[] _fishes = new Fish[16];
    Fish[][] _matrix = new Fish[4][4];
    Stack<Fish[]> stack = new Stack<>();
    
    for(int i=0; i<4; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      for(int j=0; j<4; j++) {
        int num = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());
        Fish fish = new Fish(i, j, dir, num);
        _fishes[num-1] = fish;
        _matrix[i][j] = fish;
      }
    }
    
    Shark _shark = new Shark();

    _shark.eat(_matrix[0][0]);
    stack.add(_fishes);
    System.out.println();
    
    run(_fishes, _shark);
    
    
    System.out.println(maxEatSum);
  }
  
}
