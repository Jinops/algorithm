import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int N = Integer.parseInt(br.readLine());
    long[] nums = new long[N];
    
    st = new StringTokenizer(br.readLine());
    for(int i=0; i<N; i++) {
      nums[i] = Long.parseLong(st.nextToken());
    }
    
    Arrays.sort(nums);
    
    long best = Long.MAX_VALUE;
    long[] results = new long[3];
    
    // left의 위치를 고정하고
    // mid, right에 대해 이분탐색
    
    for(int left=0; left<N-2; left++) {
      int mid = left+1;
      int right = N-1;
      
      while(mid!=right) {
        long cur = nums[left]+nums[mid]+nums[right];
        
        if(Math.abs(cur)<best) {
          best = Math.abs(cur);
          results = new long[]{nums[left], nums[mid], nums[right]};
        }
        
        if(cur==0) break;
        else if(cur<0) {
          mid++;
        }
        else if(cur>0) {
          right--;
        }
      }
      
      if(best==0) break;
    }
    
    System.out.printf("%d %d %d", results[0], results[1], results[2]);
  }
}
