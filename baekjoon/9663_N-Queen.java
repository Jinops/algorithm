import java.util.*;

public class Main {
  static int N;
  static int result=0;

  static boolean isValid(int[] xs, int tarX, int tarY) {
    for(int y=0; y<tarY; y++) {
      int x = xs[y];
      if(x==tarX || y==tarY // 가로세로
          || y-x == tarY-tarX // 대각선(좌상->우하)
          || y+x == tarY+tarX) { // 대각선(좌하->우상)
        return false;
      }
    }
    return true;
  }

  static void queen(int[] xs, int y) {
    if (y == N) {
      result += 1;
      return;
    }
    for (int x = 0; x < N; x++) {
      if (isValid(xs, x, y)) {
        xs[y] = x;
        queen(xs, y+1); // 퀸 추가
        xs[y] = 0;
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    int[] xs = new int[N]; // idx:퀸의 y좌표, elem:퀸의 x좌표
    
    queen(xs, 0);
    System.out.print(result);
  }
}
