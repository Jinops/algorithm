// TODO: 시간초과
// 누적합을 구하고 
// M의 수만큼 탐색을 실시

import java.util.*;
import java.io.*;

public class Main {
  static int N;
  static int M;
  static int[] nums;
  
  static int result = Integer.MAX_VALUE;
  
  static void find(int i, Stack<Integer> stack) {
    if(stack.size()==M-1) {
      int max = Math.max(nums[stack.get(0)], nums[N-1] - nums[stack.get(M-2)]);
      for(int j=1; j<M-1; j++) {
        max = Math.max(max, nums[stack.get(j)] - nums[stack.get(j-1)]);
      }
      
      result = Math.min(result, max);
      return;
    }
    
    for(int idx=i; idx<N-1; idx++) {
      stack.add(idx);
      find(idx+1, stack);
      stack.pop();
    }
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    
    nums = new int[N];
    
    st = new StringTokenizer(br.readLine());
    
    nums[0] = Integer.parseInt(st.nextToken());
    for(int i=1; i<N; i++) {
      nums[i] = Integer.parseInt(st.nextToken()) + nums[i-1];
    }
    
    find(0, new Stack<>());
    System.out.println(result);
  }
}
