// TODO: 메모리초과
import java.util.*;

public class Main {
  static int N;
  static int result=0;

  static boolean[][] getM(boolean[][] m, int x, int y) {
    boolean[][] newM = new boolean[N-y][N];
    for (int i = 0; i < N-y; i++) {
      for (int j = 0; j < N; j++) {
        if (j == x || i == y // 가로세로
            || i - j == y - x // 대각선(좌상->우하)
            || i + j == y + x) { // 대각선(좌하->우상)
          newM[i][j] = true;
        } else {
          newM[i][j] = m[i-y][j];
        }
      }
    }
    System.out.println(x+","+y);
    for(boolean[] a:newM) {
      System.out.println(Arrays.toString(a));
    }
    System.out.println();
    return newM;
  }

  static void queen(boolean[][] m, int y) {
    if (y == N) {
      result += 1;
      return;
    }
    for (int x = 0; x < N; x++) {
      if (!m[0][x]) { // 퀸 공격 범위가 아니면
        queen(getM(m, x, y), y+1); // 퀸 추가
      }
    }
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    N = sc.nextInt();
    boolean[][] m = new boolean[N][N]; // 퀸의 공격범위: true

    queen(m, 0);
    System.out.print(result);
  }
}
