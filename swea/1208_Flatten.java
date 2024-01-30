import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {
  public static void sort(int[] arr) {
    for (int i = 0; i < arr.length - 1; i++) {
      if (arr[i] > arr[i + 1]) {
        int temp = arr[i];
        arr[i] = arr[i + 1];
        arr[i + 1] = temp;
      } else {
        break;
      }
    }
    for (int i = arr.length - 1; i > 0; i--) {
      if (arr[i] < arr[i - 1]) {
        int temp = arr[i];
        arr[i] = arr[i - 1];
        arr[i - 1] = temp;
      } else {
        break;
      }
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int T = 10;
    int size = 100;

    for (int t = 1; t <= T; t++) {
      int[] arr = new int[100];
      int dumpCnt = Integer.parseInt(br.readLine());
      
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < size; i++) {
        arr[i] = Integer.parseInt(st.nextToken());
      }
      Arrays.sort(arr);

      int result;
      for (int i = 0; i < dumpCnt; i++) {
        arr[0] += 1;
        arr[size-1] -= 1;
        sort(arr);
        if(arr[size-1]-arr[0] == 0) {
          break;
        }
      }
      result = arr[size-1]-arr[0];
      
      System.out.printf("#%d %d\n", t, result);
    }
  }
}
