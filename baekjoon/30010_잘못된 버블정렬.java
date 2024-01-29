import java.util.Scanner;

public class Main {
  static void wrongSort(int[] A, int N) {
    for (int i = N - 1; i > 0; i--) {
      for (int j = i - 1; j >= 0; j--) {
        System.out.printf("%d,%d\n",i, j);
        if (A[j] > A[j + 1]) {
          int tmp = A[j];
          A[j] = A[j + 1];
          A[j + 1] = tmp;
        }
      }
    }
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    String result = "3 2";
    for(int i=2; i<n; i++) {
      result += " 1";
    }
    System.out.println(result);
  }
}
