import java.io.*;
import java.util.*;

public class Main {
  static int N;
  static int[] nums;
  
  static int max = Integer.MIN_VALUE;
  static int min = Integer.MAX_VALUE;
  
  static void cal(int idx, int acc, int add, int sub, int mul, int div) {
    if(idx == N-1) {
      max = Math.max(max, acc);
      min = Math.min(min, acc);
      return;
    }
    
    if(add>0) cal(idx+1, acc+nums[idx+1], add-1, sub, mul, div);
    if(sub>0) cal(idx+1, acc-nums[idx+1], add, sub-1, mul, div);
    if(mul>0) cal(idx+1, acc*nums[idx+1], add, sub, mul-1, div);
    if(div>0) cal(idx+1, acc/nums[idx+1], add, sub, mul, div-1);
  }
  
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    N = Integer.parseInt(br.readLine());
    nums = new int[N];
    int[] opCnts = new int[4]; // + - * /
    
    st = new StringTokenizer(br.readLine());
    for(int i=0; i<N; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }
    
    st = new StringTokenizer(br.readLine());
    for(int i=0; i<4; i++) {
      opCnts[i] = Integer.parseInt(st.nextToken());
    }
    
    cal(0, nums[0], opCnts[0], opCnts[1], opCnts[2], opCnts[3]);
    
    System.out.println(max);
    System.out.println(min);
  }
}
