import java.util.*;
import java.io.*;

class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int n = Integer.parseInt(br.readLine());
    int[] nums = new int[n];
    
    st = new StringTokenizer(br.readLine());
    for(int i=0; i<n; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(nums);
    
    int x = Integer.parseInt(br.readLine());
    
    int start = 0;
    int end = n-1;
    int result = 0;
    
    for(start=0; start<n; start++) {
      int diff = x-nums[start];
      end = start+1;
      
      while(end<n-1 && nums[end]<diff) {
        end++;
      }
      
      if(end<n && nums[end]==diff) {
        result++;
      }
    }
    
    System.out.println(result);
  }
}
