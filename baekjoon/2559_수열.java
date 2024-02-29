import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    int[] arr = new int[N];

    st = new StringTokenizer(br.readLine());
    for(int i=0; i<N; i++){
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int result = Integer.MIN_VALUE;

    for(int i=0; i<N-K+1; i++){
      int acc = 0;
      for(int j=0; j<K; j++){
        acc += arr[i+j];
      }
      result = Math.max(result, acc);
    }

    System.out.println(result);
  }
}
