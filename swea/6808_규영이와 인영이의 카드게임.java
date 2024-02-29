import java.io.*;
import java.util.*;

public class Solution {
  static int[] aCards = new int[9];
  static int[] bCards = new int[9];
  static boolean[] isBpick = new boolean[9];
  
  static int aWin = 0;
  static int aLose = 0;
  
  static void pick(int turn, int aScore, int bScore) {
    if(turn==10) {
      if(aScore > bScore) aWin++;
      if(aScore < bScore) aLose++;
      return;
    }
    for(int i=0; i<9; i++) {
      if(isBpick[i]==true) {
        continue;
      }
      isBpick[i] = true;
      int turnScore = aCards[turn-1]+bCards[i];
      if(aCards[turn-1]>bCards[i]) {
        pick(turn+1, aScore+turnScore, bScore);
      } else {
        pick(turn+1, aScore, bScore+turnScore);
      }
      isBpick[i] = false;
    }
  }
  
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    
    int T = Integer.parseInt(br.readLine());
    for(int t=1; t<=T; t++) {

      Set<Integer> cards = new HashSet<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18));

      st = new StringTokenizer(br.readLine());
      for(int i=0; i<9; i++) {
        aCards[i] = Integer.parseInt(st.nextToken());
        cards.remove(aCards[i]);
      }
      bCards = cards.stream().mapToInt(Integer::intValue).toArray();
      
      aWin = 0;
      aLose = 0;
      pick(1, 0, 0);
      
      System.out.printf("#%d %d %d\n", t, aWin, aLose);
    }
  }
}
