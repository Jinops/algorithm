import java.io.*;
import java.util.*;

class Main{
  static int N;
  static int[][] stats;
  static int min = Integer.MAX_VALUE;
  
  static void cal(boolean[] isStartTeams, int idx, int cnt) {
    if(cnt==N/2) {
      int startTeam = 0;
      int linkTeam = 0;
      for(int i=0; i<N; i++) {
        for(int j=i+1; j<N; j++) {
          int stat = stats[i][j] + stats[j][i];
          if(isStartTeams[i]&&isStartTeams[j]) {
            startTeam += stat;
          } else if(!isStartTeams[i]&&!isStartTeams[j]){
            linkTeam += stat;
          }
        }
      }
      min = Math.min(min, Math.abs(startTeam-linkTeam));
      return;
    }
    
    for(int i=idx; i<N; i++) {
      isStartTeams[i] = true;
      cal(isStartTeams, i+1, cnt+1);
      isStartTeams[i] = false;
    }
  }
  
  public static void main(String args[]) throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    N = Integer.parseInt(br.readLine());
    stats = new int[N][N];
    
    for(int i=0; i<N; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<N; j++) {
        stats[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    
    cal(new boolean[N], 0, 0);
    System.out.println(min);
  }
}
