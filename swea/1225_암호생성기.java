import java.io.*;
import java.util.*;

public class Solution {
  static int updateHead(int head) {
    return (head+1) % 8;
  }
  static String getPasswrod(int[] nums) {
    StringBuilder sb = new StringBuilder();
    int head = 0;
    int decrease = 1;
    boolean isDone = false;
    
    while(!isDone) {
      nums[head] -= decrease++;
      if(nums[head] <= 0) {
        nums[head] = 0;
        isDone = true;
      }
      if(decrease>5) {
        decrease = 1;
      }
      head = updateHead(head);
    }
    
    for(int i=head; i<head+8; i++) {
      sb.append(nums[i%8] + " ");
    }
 
    return sb.toString();
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    for(int t=1; t<=10; t++) {
      int tc = Integer.parseInt(br.readLine());
      int[] nums = new int[8];
      st = new StringTokenizer(br.readLine());
      for(int i=0; i<8; i++) {
        nums[i] = Integer.parseInt(st.nextToken());
      }
      System.out.printf("#%d %s\n", tc, getPasswrod(nums));
    }
  }
}
