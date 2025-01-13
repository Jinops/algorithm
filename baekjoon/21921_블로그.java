import java.io.*;
import java.util.*;

public class Main {
  public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      
      int N = Integer.parseInt(st.nextToken());
      int X = Integer.parseInt(st.nextToken());
      
      int[] acc = new int[N+1];
      st = new StringTokenizer(br.readLine());
      acc[1] = Integer.parseInt(st.nextToken());
      
      for(int i=2; i<=N; i++) {
        acc[i] = acc[i-1] + Integer.parseInt(st.nextToken());
      }
      
      int topVisit = acc[X];
      int cnt = 1;
      
      for(int i=X+1; i<=N; i++) {
        int visit = acc[i] - acc[i-X];
        if(visit == topVisit) {
          cnt += 1;
        } else if(visit > topVisit) {
          topVisit = visit;
          cnt = 1;
        }
      }
      
      if(topVisit == 0) {
        System.out.println("SAD");
      } else {
        System.out.println(topVisit);
        System.out.println(cnt);
      }
  }
}
