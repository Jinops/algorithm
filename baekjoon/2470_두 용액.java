import java.io.*;
import java.util.*;

// 투포인터
class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int N = Integer.parseInt(br.readLine());
    int[] nums = new int[N];
    
    st = new StringTokenizer(br.readLine());
    for(int i=0; i<N; i++) {
      nums[i] = Integer.parseInt(st.nextToken());
    }
    
    Arrays.sort(nums);
    
    int start = 0;
    int end = N-1;
    
    int minSum = Integer.MAX_VALUE;
    int[] result = new int[2];
    
    while(start!=end) {
      int sum = nums[start]+nums[end];
      
      if(Math.abs(sum)<Math.abs(minSum)) {
        minSum = sum;
        result = new int[]{nums[start], nums[end]};
        
        if(minSum==0) break;
      }
      
      if(sum<0) {
        // sum이 음수 -> 값을 올려야 함
        start += 1;
      } else if(sum>0) {
        // sum이 양수 -> 값을 줄여야 함
        end -= 1;
      }
    }
    
    System.out.println(result[0]+" "+result[1]);
  }
}
