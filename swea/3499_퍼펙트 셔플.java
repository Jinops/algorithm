import java.io.*;
import java.util.*;

public class Solution {
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int T = Integer.parseInt(br.readLine());
    
    for(int t=1; t<=T; t++) {
      StringBuilder sb = new StringBuilder();
      int N = Integer.parseInt(br.readLine());
      String[] cards = new String[N];
      
      st = new StringTokenizer(br.readLine());
      for(int i=0; i<N; i++) {
        cards[i] = st.nextToken();
      }
      
      int halfIdx = (int)Math.ceil(N/2.0);
      for(int i=0; i<halfIdx; i++) {
        sb.append(cards[i] + " ");
        if(i+halfIdx<N) {
          sb.append(cards[i+halfIdx] + " ");
        }
      }
      
      System.out.printf("#%d %s\n", t, sb.toString());
    }
  }
}
