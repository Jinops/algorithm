// TODO: 시간초과
import java.util.*;
import java.io.*;

class Main {
  static int N;
  static int[] nums;
  
  static void reverse(int[] nums, int start, int end) {
    for(int i=0; i<(end-start+1)/2; i++) {
      int temp = nums[start-1+i];
      nums[start-1+i] = nums[end-1-i];
      nums[end-1-i] = temp;
    }
  }
  
  static boolean run(int[] nums, Stack<Integer> stack) {
    if(isDone(nums)) {
      printResult(stack);
      return true;
    }
    if(stack.size()==4) {
      return false;
    }
    
    for(int i=1; i<=N; i++) {
      if(nums[i-1]==i) {
        continue;
      }
      int start = i;
      int end = nums[i-1];
      
      if(start>end) {
        start = nums[i-1];
        end = i;
      }
      
      reverse(nums, start, end);
      stack.add(start);
      stack.add(end);
      if(run(nums, stack)) {
        return true;
      };
      reverse(nums, start, end);
      stack.pop();
      stack.pop();
    }
    return false;
  }
  
  static boolean isDone(int[] nums) {
    for(int i=1; i<=N; i++) {
      if(nums[i-1]!=i) {
        return false;
      }
    }
    return true;
  }
  
  static void printResult(Stack<Integer> stack) {
    while(stack.size()<4) {
      stack.add(1);
      stack.add(1);
    }
    
    for(int i=0; i<stack.size(); i+=2) {
      System.out.printf("%d %d\n", stack.get(i),  stack.get(i+1));
    }
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    N = Integer.parseInt(br.readLine());
    nums = new int[N];
    st = new StringTokenizer(br.readLine());
    
    for(int i=0; i<N; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }
    
    run(nums, new Stack<Integer>());
  }
}
