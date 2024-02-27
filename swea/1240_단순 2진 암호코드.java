import java.io.*;
import java.util.*;

public class Solution {
  static String[] lines;
  
  static HashMap<String, Integer> pw = new HashMap<>();
  
  static int getResut(Deque<Integer> deque) {
    int sumOdd = 0;
    int sumEven = 0;
    boolean isOdd = false;
    
    while(!deque.isEmpty()) {
      isOdd = !isOdd;
      if(isOdd) sumOdd += deque.pollFirst();
      else sumEven += deque.pollFirst();
    }
    
    return (sumOdd*3+sumEven) % 10 == 0 ? sumOdd+sumEven : 0;
  }
  
  static Deque<Integer> findPassword(int M, int N) {
    Deque<Integer> deque = new LinkedList<>();
    for(int i=0; i<N; i++) {
      for(int j=M-1; j>=6; j--) {
        if(isLastOfPasswords(i, j)) {
          int cnt = 0;
          while(cnt<8) {
            int end = j-7*cnt;
            deque.addFirst(pw.get(lines[i].substring(end-6, end+1)));
            cnt++;
          }
          return deque;
        }
      }
    }
    return null;
  }
  
  static boolean isLastOfPasswords(int i, int j) {
    if(lines[i].charAt(j)!='1') {
      return false;
    }
    String str = lines[i].substring(j-6,j+1);
    return pw.containsKey(str);
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    pw.put("0001101", 0);
    pw.put("0011001", 1);
    pw.put("0010011", 2);
    pw.put("0111101", 3);
    pw.put("0100011", 4);
    pw.put("0110001", 5);
    pw.put("0101111", 6);
    pw.put("0111011", 7);
    pw.put("0110111", 8);
    pw.put("0001011", 9);
    
    
    int T = Integer.parseInt(br.readLine());
    for(int t=1; t<=T; t++) {
      
      st = new StringTokenizer(br.readLine());
      int N = Integer.parseInt(st.nextToken());
      int M = Integer.parseInt(st.nextToken());
      
      lines = new String[N];
      for(int i=0; i<N; i++) {
        lines[i] = br.readLine();
      }
      
      Deque<Integer> deque = findPassword(M, N);
      
      System.out.printf("#%d %d\n", t, getResut(deque));
    }
  }
}