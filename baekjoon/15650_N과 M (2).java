import java.util.*;
import java.io.*;

class Main {
  static int N;
  static int M;
  static void pick(int idx, int cnt, boolean[] check){
    if(cnt==M){
      for(int i=1; i<=N; i++){
        if(check[i]) System.out.print(i+" ");
      }
      System.out.println();
      return;
    }
    if(idx>N){
      return;
    }
    
    check[idx] = true;
    pick(idx+1, cnt+1, check);
    check[idx] = false;
    pick(idx+1, cnt, check);
  }
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    boolean[] check = new boolean[N+1];
    pick(1, 0, check);
  }
}
