// TODO
import java.io.*;
import java.util.*;

public class Main {
  static boolean check(int t, int[] weights) {
    System.out.println(t);
    int[] dp = new int[t+1];
    for(int w:weights) {
      if(w<=t) {
        dp[w] += 1; // 추w로 만들 수 있는 방법 추가
      }
      for(int i=t; i>=w; i--) {
        dp[i] += dp[i-w]; // 무게i만들기 = 추w빼고 무게i-w 만들기
      }
      System.out.println(Arrays.toString(dp));
      
      if(dp[t]>0) {
        return true;
      }
    }
    return false;
  }
  public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      
      int N = Integer.parseInt(br.readLine()); // 추의 개수
      int[] weights = new int[N]; // 추의 무게

       st = new StringTokenizer(br.readLine());
       for(int i=0; i<N; i++) {
         int weight = Integer.parseInt(st.nextToken());
         if(i>0 && weight==weights[i-1]) {
           weights[i] = weight + weights[i-1]; 
           // 동일한 무게의 추 -> 합쳐서 무게를 만드는 다른 경우로 만듦
         } else {
           weights[i] = weight;
         }
       }
      
       int T = Integer.parseInt(br.readLine()); // 타깃의 개수
       int[] targets = new int[T]; // 타깃의 무게
       
       st = new StringTokenizer(br.readLine());
       for(int i=0; i<N; i++) {
         targets[i] = Integer.parseInt(st.nextToken());
       }
       
       String result = "";
       for(int t:targets) {
         result += ((check(t, weights))?"Y":"N") + " ";
       }
       System.out.println(result);
  }
}
