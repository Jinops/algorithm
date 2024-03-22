import java.util.*;
import java.io.*;

class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    int[] nums = new int[N+1];
    int acc = 0;

    st = new StringTokenizer(br.readLine());
    for(int i=1; i<=N; i++){
      acc += Integer.parseInt(st.nextToken());
      nums[i] = acc;
    }

    StringBuilder sb = new StringBuilder();
    for(int k=0; k<M; k++){
      st = new StringTokenizer(br.readLine());
      int i = Integer.parseInt(st.nextToken());
      int j = Integer.parseInt(st.nextToken());
      sb.append(nums[j]-nums[i-1]).append('\n');
    }

    System.out.println(sb.toString());
  }
}
