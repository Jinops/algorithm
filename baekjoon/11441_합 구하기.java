import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int N = Integer.parseInt(br.readLine());
    int[] nums = new int[N];
    int acc = 0;

    st = new StringTokenizer(br.readLine());
    for(int i=0; i<N; i++){
      acc += Integer.parseInt(st.nextToken());
      nums[i] = acc;
    }

    int M = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();

    for(int k=0; k<M; k++){
      st = new StringTokenizer(br.readLine());
      int i = Integer.parseInt(st.nextToken())-1;
      int j = Integer.parseInt(st.nextToken())-1;
      int result = i==0?nums[j] : nums[j]-nums[i-1];
      sb.append(result).append('\n');
    }

    System.out.println(sb);
  }
}
