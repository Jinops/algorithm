import java.io.*;

public class Solution {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int[] counts = new int[10000001];
    for(String str:br.readLine().split(" ")) {
      counts[Integer.parseInt(str)]++;
    }
    
    for(int i=1; i<1000001; i++) {
      counts[i] += counts[i-1];
      if(counts[i] == 500001) { // 마지막 i가 50000번째라면
        System.out.println(i);
        break;
      }
    }
  }
}
